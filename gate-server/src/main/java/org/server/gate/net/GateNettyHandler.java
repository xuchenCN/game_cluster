package org.server.gate.net;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

import com.google.protobuf.CodedInputStream;
import com.mmo.server.MessagesLocation.MessageRegistry;
import com.mmo.server.ServerClientProtocol;
import com.mmo.server.ServerClientProtocol.LoginCode;
import com.mmo.server.ServerClientProtocol.UserLoginRequest;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class GateNettyHandler extends ChannelInboundHandlerAdapter {
	
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

		GateMessage message = (GateMessage) msg;

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

				GateMessage responseMes = new GateMessage(MessageRegistry.USERLOGINRESPONSE,
						Unpooled.wrappedBuffer(resp.toByteArray()));
				ctx.writeAndFlush(responseMes);

			});

		});

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
