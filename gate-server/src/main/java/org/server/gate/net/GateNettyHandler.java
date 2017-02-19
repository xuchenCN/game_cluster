package org.server.gate.net;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mmo.server.common.utils.Constants;
import org.mmo.server.common.utils.ExecutorExceptionHandler;
import org.server.gate.GateServerContext;
import org.server.gate.core.GateSendMessge;
import org.server.gate.core.InstructionEvent;
import org.server.gate.utils.ClientMessageConverter;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.mmo.server.CommonProtocol;
import com.mmo.server.CommonProtocol.CharacterCreateEvent;
import com.mmo.server.CommonProtocol.Item;
import com.mmo.server.CommonProtocol.Position;
import com.mmo.server.MessagesLocation.MessageRegistry;
import com.mmo.server.ServerClientProtocol;
import com.mmo.server.ServerClientProtocol.ClientCharacter;
import com.mmo.server.ServerClientProtocol.ClientCharacterCreateEvent;
import com.mmo.server.ServerClientProtocol.ClientCharacterEnterEvent;
import com.mmo.server.ServerClientProtocol.ClientCharacterEnterRequest;
import com.mmo.server.ServerClientProtocol.ClientCharacterMove;
import com.mmo.server.ServerClientProtocol.ClientCommonStat;
import com.mmo.server.ServerClientProtocol.ClientItem;
import com.mmo.server.ServerClientProtocol.ClientItemCreateEvent;
import com.mmo.server.ServerClientProtocol.ClientItemDestroyEvent;
import com.mmo.server.ServerClientProtocol.ClientItemMoveEvent;
import com.mmo.server.ServerClientProtocol.LoginCode;
import com.mmo.server.ServerClientProtocol.UserLoginRequest;
import com.mmo.server.ServerClientProtocol.UserLoginResponse;
import com.mmo.server.ServerClientProtocol.UserLogoutRequest;
import com.mmo.server.ServerGameProtocol.CharacterMoveReq;
import com.mmo.server.ServerGateProtocol.CharacterEnterMapRequest;
import com.mmo.server.ServerGateProtocol.ItemCreateEventRequest;
import com.mmo.server.ServerGateProtocol.ItemDestroyEventRequest;
import com.mmo.server.ServerGateProtocol.ItemMoveEventRequest;
import com.mmo.server.ServerGateProtocol.MapEventType;
import com.mmo.server.ServerWorldProtocol.UserArrivedWorldRequest;

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
	private Map<String, String> channelToTicket = new HashMap<String, String>();

	public GateNettyHandler(GateServerContext gateServerContext) {
		this.gateServerContext = gateServerContext;
		sendThreadPool = Executors.newFixedThreadPool(
				gateServerContext.getConfig().getInt(Constants.GATE_SERVER_SEND_POOL_SIZE,
						Constants.GATE_SERVER_SEND_POOL_SIZE_DEFAULT),
				new ThreadFactoryBuilder().setNameFormat("RecvMessagePool #%d")
						.setUncaughtExceptionHandler(new ExecutorExceptionHandler(this)).build());
		shouldRun = true;
		IntStream.range(0, gateServerContext.getConfig().getInt(Constants.GATE_SERVER_SEND_POOL_SIZE,
				Constants.GATE_SERVER_SEND_POOL_SIZE_DEFAULT)).forEach(i -> {
					sendThreadPool.execute(new SendMessageActor());
				});
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
		String ticket = channelToTicket.remove(ctx.channel().toString());
		if (!StringUtils.isEmpty(ticket)) {
			Pair<Integer, Integer> uidMapIdPair = gateServerContext.getAccountService().getUidMapIdPair(ticket);
			Map<Integer, Channel> uidChannel = mapIdToUid.get(uidMapIdPair.getLeft());
			if (uidChannel != null) {
				uidChannel.remove(uidMapIdPair.getLeft());
			}

			gateServerContext.getAccountService().userLogout(UserLogoutRequest.newBuilder().setTicket(ticket).build());

		}

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
				channelToTicket.put(ctx.channel().toString(), response.getTicket());
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
			ClientCharacterEnterRequest clientCharacterEnterRequest = (ClientCharacterEnterRequest) FlexiblePBDecoder
					.decode(ClientCharacterEnterRequest.getDefaultInstance(), null, message.body);

			Pair<Integer, Integer> uidMapidPair = gateServerContext.getAccountService()
					.getUidMapIdPair(clientCharacterEnterRequest.getTicket());
			if (uidMapidPair != null) {
				UserArrivedWorldRequest userArrivedWorldRequest = UserArrivedWorldRequest.newBuilder()
						.setGateHost(gateServerContext.getGateServerHost()).setGatePort(gateServerContext.getListenOn())
						.setMapId(uidMapidPair.getRight()).setUid(uidMapidPair.getLeft()).build();

				InstructionEvent instructionEvent = new InstructionEvent(uidMapidPair.getLeft(),
						uidMapidPair.getRight(), MessageRegistry.CHARACTERENTERREQUEST, userArrivedWorldRequest);
				gateServerContext.getGateServerRouter().receiveMessage(instructionEvent);
			}

			break;

		case MessageRegistry.CHARACTERMOVE_VALUE:
			ClientCharacterMove characterMove = (ClientCharacterMove) FlexiblePBDecoder
					.decode(ClientCharacterMove.getDefaultInstance(), null, message.body);

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
			CharacterCreateEvent characterCreateEvent = (CharacterCreateEvent) gateSendMessge
					.getMessage();
			ClientCharacterCreateEvent clientCharacterCreateEvent = ClientCharacterCreateEvent.newBuilder()
					.setCharacter(ClientMessageConverter
							.toClientCharacter(characterCreateEvent.getCharacter()))
					.build();

			return new GateClientMessage(MessageRegistry.CHARACTERCREATEEVENT,
					Unpooled.wrappedBuffer(clientCharacterCreateEvent.toByteArray()));
		case MapEventType.ITEMMOVEEVENT_VALUE:
			ItemMoveEventRequest itemMoveEventRequest = (ItemMoveEventRequest) gateSendMessge.getMessage();
			ClientItemMoveEvent clientItemMoveEvent = ClientItemMoveEvent.newBuilder()
					.setIdentify(
							ClientMessageConverter.toClientIdentifyInfo(itemMoveEventRequest.getEvent().getIdentify()))
					.setFromPos(ClientMessageConverter.toClientPosition(itemMoveEventRequest.getEvent().getFromPos()))
					.setToPos(ClientMessageConverter.toClientPosition(itemMoveEventRequest.getEvent().getToPos()))
					.setSpeed(itemMoveEventRequest.getEvent().getSpeed())
					.setPlayMotion(
							ClientMessageConverter.toClientMotionInfo(itemMoveEventRequest.getEvent().getPlayMotion()))
					.build();

			return new GateClientMessage(MessageRegistry.ITEMMOVEEVENT,
					Unpooled.wrappedBuffer(clientItemMoveEvent.toByteArray()));

		case MapEventType.ITEMCREATEEVENT_VALUE:
			ItemCreateEventRequest itemCreateEventRequest = (ItemCreateEventRequest) gateSendMessge.getMessage();
			ClientItemCreateEvent clientItemCreateEvent = ClientItemCreateEvent.newBuilder()
					.setItem(ClientMessageConverter.toClientItem(itemCreateEventRequest.getEvent().getItem())).build();

			return new GateClientMessage(MessageRegistry.ITEMCREATEEVENT,
					Unpooled.wrappedBuffer(clientItemCreateEvent.toByteArray()));
		case MapEventType.ITEMDESTROYEVENT_VALUE:
			ItemDestroyEventRequest itemDestroyEventRequest = (ItemDestroyEventRequest) gateSendMessge.getMessage();
			ClientItemDestroyEvent clientItemDestroyEvent = ClientItemDestroyEvent.newBuilder().setIdentify(
					ClientMessageConverter.toClientIdentifyInfo(itemDestroyEventRequest.getEvent().getIdentify()))
					.build();

			return new GateClientMessage(MessageRegistry.ITEMDESTROYEVENT,
					Unpooled.wrappedBuffer(clientItemDestroyEvent.toByteArray()));

		case MapEventType.CHARACHTERENTERMAP_VALUE:
			CharacterEnterMapRequest characterEnterMapRequest = (CharacterEnterMapRequest) gateSendMessge.getMessage();
			ClientCommonStat clientCommonStat = ClientCommonStat
					.valueOf(characterEnterMapRequest.getStat().getNumber());
			List<Item> items = characterEnterMapRequest.getMapItemsList();
			List<CommonProtocol.Character> characters = characterEnterMapRequest.getMapCharactersList();
			List<ClientItem> clientItems = new ArrayList<ClientItem>(characterEnterMapRequest.getMapItemsCount());
			List<ClientCharacter> clientCharacters = new ArrayList<ClientCharacter>(
					characterEnterMapRequest.getMapCharactersCount());
			for (Item item : items) {
				clientItems.add(ClientMessageConverter.toClientItem(item));
			}
			for (CommonProtocol.Character character : characters) {
				clientCharacters.add(ClientMessageConverter.toClientCharacter(character));
			}

			ClientCharacterEnterEvent clientCharacterEnterEvent = ClientCharacterEnterEvent.newBuilder()
					.setStat(clientCommonStat).setMapId(characterEnterMapRequest.getMapId())
					.addAllMapCharacters(clientCharacters).addAllMapItems(clientItems).build();
			
			return new GateClientMessage(MessageRegistry.CHARACTERENTERRESPONSE,
					Unpooled.wrappedBuffer(clientCharacterEnterEvent.toByteArray()));

		default:
			LOG.error("Unknow sendType : " + gateSendMessge.getType());
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
					boolean isEffects = effects != null && !effects.isEmpty();
					Map<Integer, Channel> uid2Channel = mapIdToUid.get(gateSendMessge.getMapId());
					if (uid2Channel != null && !uid2Channel.isEmpty()) {
						uid2Channel.entrySet().stream().filter(map -> !isEffects || effects.contains(map.getKey()))
								.forEach(entry -> {
									GateClientMessage gateClientMessage = messageConverter(gateSendMessge);
									entry.getValue().writeAndFlush(gateClientMessage);
								});
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}
