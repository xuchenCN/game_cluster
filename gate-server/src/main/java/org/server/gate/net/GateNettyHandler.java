package org.server.gate.net;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mmo.server.common.utils.Constants;
import org.server.gate.GateServerContext;
import org.server.gate.core.GateSendMessge;
import org.server.gate.core.InstructionEvent;
import org.server.gate.utils.ExecutorExceptionHandler;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.mmo.server.CommonProtocol.ItemMoveEvent;
import com.mmo.server.CommonProtocol.Position;
import com.mmo.server.MessagesLocation.MessageRegistry;
import com.mmo.server.ServerClientProtocol;
import com.mmo.server.ServerClientProtocol.ClientCharacterMove;
import com.mmo.server.ServerClientProtocol.LoginCode;
import com.mmo.server.ServerClientProtocol.UserLoginRequest;
import com.mmo.server.ServerClientProtocol.UserLoginResponse;
import com.mmo.server.ServerGameProtocol.CharacterMoveReq;
import com.mmo.server.ServerGateProtocol.CharacterCreateEventRequest;
import com.mmo.server.ServerGateProtocol.ItemMoveEventRequest;
import com.mmo.server.ServerGateProtocol.MapEventType;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class GateNettyHandler extends ChannelInboundHandlerAdapter {

	private static final Log LOG = LogFactory.getLog(GateNettyHandler.class);

	private GateServerContext gateServerContext;

	private ExecutorService sendThreadPool;

	private volatile boolean shouldRun;

	private Map<Integer, Map<Integer, Channel>> mapIdToUid = new HashMap<Integer, Map<Integer, Channel>>();

	public GateNettyHandler(GateServerContext gateServerContext) {
		this.gateServerContext = gateServerContext;
		sendThreadPool = Executors.newFixedThreadPool(
				gateServerContext.getConfig().getInt(Constants.GATE_SERVER_RECV_POOL_SIZE,
						Constants.GATE_SERVER_RECV_POOL_SIZE_DEFAULT),
				new ThreadFactoryBuilder().setNameFormat("RecvMessagePool #%d")
						.setUncaughtExceptionHandler(new ExecutorExceptionHandler(this)).build());
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
		GateClientMessage message = (GateClientMessage) msg;

		switch (message.messageId.getNumber()) {

		case MessageRegistry.USERLOGINREQUEST_VALUE:
			UserLoginRequest userLoginRequest = (UserLoginRequest) FlexiblePBDecoder
					.decode(UserLoginRequest.getDefaultInstance(), null, message.body);

			UserLoginResponse response = gateServerContext.getAccountService().userLogin(userLoginRequest);
			if (response.getCode() == LoginCode.SUC) {
				int uid = gateServerContext.getAccountService().getUidByTicket(response.getTicket());
				int mapId = gateServerContext.getAccountService().getMapIdByTicket(response.getTicket());
				Map<Integer, Channel> uid2Channel = mapIdToUid.get(mapId);
				if (uid2Channel == null) {
					uid2Channel = new HashMap<Integer, Channel>();
				}
				uid2Channel.put(uid, ctx.channel());
				mapIdToUid.put(mapId, uid2Channel);
			}

			GateClientMessage responseMes = new GateClientMessage(MessageRegistry.USERLOGINRESPONSE,
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
			ClientCharacterMove characterMove = (ClientCharacterMove) FlexiblePBDecoder.decode(ClientCharacterMove.getDefaultInstance(),
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

	private GateClientMessage messageConverter(GateSendMessge gateSendMessge) {
		switch (gateSendMessge.getType().getNumber()) {
		case MapEventType.CHARACTERCREATEEVENT_VALUE:
			CharacterCreateEventRequest characterCreateEventRequest = (CharacterCreateEventRequest) gateSendMessge
					.getMessage();
			
//			CharacterCreateEvent characterCreateEvent = CharacterCreateEvent.newBuilder()
//					.setCharacter().build();
//			
//			return new GateClientMessage(MessageRegistry.CHARACTERCREATEEVENT,
//					Unpooled.wrappedBuffer(characterCreateEvent.toByteArray()));
		case MapEventType.ITEMMOVEEVENT_VALUE:
			ItemMoveEventRequest itemMoveEventRequest = (ItemMoveEventRequest) gateSendMessge.getMessage();
			ItemMoveEvent itemMoveEvent = ItemMoveEvent.newBuilder()
					.setIdentify(itemMoveEventRequest.getEvent().getIdentify())
					.setFromPos(itemMoveEventRequest.getEvent().getFromPos()).setToPos(itemMoveEventRequest.getEvent().getToPos())
					.setSpeed(itemMoveEventRequest.getEvent().getSpeed())
					.setPlayMotion(itemMoveEventRequest.getEvent().getPlayMotion()).build();
			
			return new GateClientMessage(MessageRegistry.ITEMMOVEEVENT, Unpooled.wrappedBuffer(itemMoveEvent.toByteArray()));

		case MapEventType.ITEMCRAATEEVENT_VALUE:
//			ItemCraateEventRequest itemCraateEventRequest = (ItemCraateEventRequest)gateSendMessge.getMessage();
//			ItemCraateEvent itemCraateEvent = ItemCraateEvent.newBuilder().setItem(itemCraateEventRequest.getEvent().getItem()).
			break;
		case MapEventType.ITEMDESTROYEVENT_VALUE:
			break;
		default:
			break;
		}
		return null;
	}

	private void testResp(ChannelHandlerContext ctx, Object msg) throws Exception {
		GateClientMessage message = (GateClientMessage) msg;

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

				GateClientMessage responseMes = new GateClientMessage(MessageRegistry.USERLOGINRESPONSE,
						Unpooled.wrappedBuffer(resp.toByteArray()));
				ctx.writeAndFlush(responseMes);

			});

		});
	}

	class SendMessageActor implements Runnable {

		@Override
		public void run() {
			while (shouldRun && !Thread.interrupted()) {
				try {
					GateSendMessge gateSendMessge = gateServerContext.getGateServerRouter().pullSendQueue();
					List<Integer> effects = gateSendMessge.getEffects();
					boolean isEffects = effects == null || effects.isEmpty();
					Map<Integer, Channel> uid2Channel = mapIdToUid.get(gateSendMessge.getMapId());
					if (uid2Channel != null && !uid2Channel.isEmpty()) {
						uid2Channel.entrySet().stream().filter(map -> isEffects && effects.contains(map.getKey()))
								.forEach(entry -> {

								});
					}

				} catch (Exception e) {

				}
			}
		}

	}
	
	

}
