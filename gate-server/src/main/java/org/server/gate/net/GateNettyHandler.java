package org.server.gate.net;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

import org.server.gate.GateServerContext;
import org.server.gate.core.InstructionEvent;

import com.mmo.server.CommonProtocol.Position;
import com.mmo.server.MessagesLocation.MessageRegistry;
import com.mmo.server.ServerClientProtocol;
import com.mmo.server.ServerClientProtocol.CharacterMove;
import com.mmo.server.ServerClientProtocol.LoginCode;
import com.mmo.server.ServerClientProtocol.UserLoginRequest;
import com.mmo.server.ServerClientProtocol.UserLoginResponse;
import com.mmo.server.ServerGameProtocol.CharacterMoveReq;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class GateNettyHandler extends ChannelInboundHandlerAdapter {

	private GateServerContext gateServerContext;

	public GateNettyHandler(GateServerContext gateServerContext) {
		this.gateServerContext = gateServerContext;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelActive " + ctx.channel().remoteAddress());
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelInactive" + ctx.channel().remoteAddress());
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelRegistered" + ctx.channel().remoteAddress());
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelUnregistered" + ctx.channel().remoteAddress());
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		System.out.println("userEventTriggered " + evt);
		super.userEventTriggered(ctx, evt);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		GateRecvMessage message = (GateRecvMessage) msg;

		switch (message.messageId.getNumber()) {

		case MessageRegistry.USERLOGINREQUEST_VALUE:
			UserLoginRequest userLoginRequest = (UserLoginRequest) FlexiblePBDecoder
					.decode(UserLoginRequest.getDefaultInstance(), null, message.body);

			UserLoginResponse response = gateServerContext.getAccountService().userLogin(userLoginRequest);

			GateRecvMessage responseMes = new GateRecvMessage(MessageRegistry.USERLOGINRESPONSE,
					Unpooled.wrappedBuffer(response.toByteArray()));
			ctx.writeAndFlush(responseMes);

			break;
		case MessageRegistry.USERLOGINRESPONSE_VALUE:
		case MessageRegistry.USERLOGOUTREQUEST_VALUE:
		case MessageRegistry.USERLOGOUTRESPONSE_VALUE:
		case MessageRegistry.GETCHARACTERINFOREQUEST_VALUE:
		case MessageRegistry.GETCHARACTERINFORESPONSE_VALUE:
		case MessageRegistry.CHARACTERENTERREQUEST_VALUE:
			break;

		case MessageRegistry.CHARACTERMOVE_VALUE:
			CharacterMove characterMove = (CharacterMove) FlexiblePBDecoder.decode(UserLoginRequest.getDefaultInstance(),
					null, message.body);

			int mapId = gateServerContext.getAccountService().getMapIdByTicket(characterMove.getTicket());
			int uid = gateServerContext.getAccountService().getUidByTicket(characterMove.getTicket());

			Position pos = Position.newBuilder().setPosX(characterMove.getToPos().getPosX())
					.setPosY(characterMove.getToPos().getPosY()).setPosZ(characterMove.getToPos().getPosZ()).build();
			CharacterMoveReq characterMoveReq = CharacterMoveReq.newBuilder().setUid(uid).setToPos(pos).build();

			InstructionEvent instructionEvent = new InstructionEvent(uid, mapId, MessageRegistry.CHARACTERMOVE,
					characterMoveReq);
			
			gateServerContext.getGateServerRouter().receiveMessage(instructionEvent);
			
			break;
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	private void testResp(ChannelHandlerContext ctx, Object msg) throws Exception {
		GateRecvMessage message = (GateRecvMessage) msg;

		System.out.println(message.messageId);
		System.out.println(message.body);

		switch (message.messageId.getNumber()) {
		case MessageRegistry.USERLOGINREQUEST_VALUE:
			System.out.println(FlexiblePBDecoder.decode(UserLoginRequest.getDefaultInstance(), null, message.body));
			break;
		}

		ForkJoinPool forkJoinPool = new ForkJoinPool(4);
		forkJoinPool.submit(() -> {
			IntStream.range(0, 10).parallel().forEach(n -> {
				ServerClientProtocol.UserLoginResponse resp = ServerClientProtocol.UserLoginResponse.newBuilder()
						.setCode(LoginCode.SUC).setTicket(Thread.currentThread().getName() + " " + n).build();

				GateRecvMessage responseMes = new GateRecvMessage(MessageRegistry.USERLOGINRESPONSE,
						Unpooled.wrappedBuffer(resp.toByteArray()));
				ctx.writeAndFlush(responseMes);

			});

		});
	}
	
	class UidChannelPair {
		Channel channel;
		int uid;
	}

}
