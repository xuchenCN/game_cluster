package org.server.game.services;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mmo.server.common.conf.GameConfiguration;
import org.mmo.server.common.service.AbstractService;
import org.mmo.server.common.utils.Constants;
import org.mmo.server.common.utils.ExecutorExceptionHandler;
import org.protocol.communicators.GateServerCommunicator;
import org.server.game.GameServerContext;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.mmo.server.CharacterServiceGrpc.AbstractCharacterService;
import com.mmo.server.CommonProtocol;
import com.mmo.server.CommonProtocol.CharacterCreateEvent;
import com.mmo.server.CommonProtocol.CommonResponse;
import com.mmo.server.CommonProtocol.CommonStat;
import com.mmo.server.CommonProtocol.IdentifyInfo;
import com.mmo.server.CommonProtocol.ItemMoveEvent;
import com.mmo.server.CommonProtocol.Position;
import com.mmo.server.ServerGameProtocol.CharacterMoveReq;
import com.mmo.server.ServerGameProtocol.UserArrivedRegionRequest;
import com.mmo.server.ServerGateProtocol.CharacterCreateEventRequest;
import com.mmo.server.ServerGateProtocol.CharacterEnterMapRequest;
import com.mmo.server.ServerGateProtocol.ItemMoveEventRequest;
import com.mmo.server.ServerGateProtocol.MapEventType;
import com.mmo.server.UserRegionServiceGrpc.AbstractUserRegionService;

import io.grpc.stub.StreamObserver;

public class GameServerProcessor extends AbstractService {

	private static final Log LOG = LogFactory.getLog(GameServerProcessor.class);

	private GameServerContext globalContext;
	private UserRegionService userRegionService;
	private CharacterService characterService;

	private Map<String, GateServerCommunicator> keyToGate = new HashMap<String, GateServerCommunicator>();
	private Map<Integer, String> uidToGateKey = new HashMap<Integer, String>();

	private ArrayBlockingQueue<GameServerMessage> sendGateQueue;

	private ExecutorService sendThreadPool;

	private volatile boolean shouldRun;

	public GameServerProcessor(GameServerContext globalContext) {
		super("GameServerProcessor");
		this.globalContext = globalContext;
		this.userRegionService = new UserRegionService();
		this.characterService = new CharacterService();
		this.sendGateQueue = new ArrayBlockingQueue<GameServerMessage>(globalContext.getGameSendQueueSize());
	}

	@Override
	protected void serviceInit(GameConfiguration conf) throws Exception {
		sendThreadPool = Executors.newFixedThreadPool(
				globalContext.getConfig().getInt(Constants.GAME_SERVER_SEND_POOL_SIZE,
						Constants.GAME_SERVER_SEND_POOL_SIZE_DEFAULT),
				new ThreadFactoryBuilder().setNameFormat("RecvMessagePool #%d")
						.setUncaughtExceptionHandler(new ExecutorExceptionHandler(this)).build());

		super.serviceInit(conf);
	}

	@Override
	protected void serviceStart() throws Exception {
		shouldRun = true;
		IntStream.range(0, globalContext.getConfig().getInt(Constants.GAME_SERVER_SEND_POOL_SIZE,
				Constants.GAME_SERVER_SEND_POOL_SIZE_DEFAULT)).forEach(i -> {
					sendThreadPool.execute(new SendActor());
				});
		super.serviceStart();
	}

	@Override
	protected void serviceStop() throws Exception {
		super.serviceStop();
		shouldRun = false;
	}

	public UserRegionService getUserRegionService() {
		return userRegionService;
	}

	public CharacterService getCharacterService() {
		return characterService;
	}

	public GateServerCommunicator getGateServerCommunicator(Integer uid) {
		String gateKey = uidToGateKey.get(uid);
		if (gateKey != null) {
			GateServerCommunicator gateServerCommunicator = keyToGate.get(gateKey);
			if (gateServerCommunicator != null) {
				return gateServerCommunicator;
			}
		}
		return null;
	}

	class UserRegionService extends AbstractUserRegionService {

		@Override
		public void userArrivedRegion(UserArrivedRegionRequest request, StreamObserver<CommonResponse> responseObserver) {

			String gateHost = request.getGateHost();
			int gatePort = request.getGatePort();
			String gateKey = gateHost + ":" + gatePort;
			Integer uid = request.getUid();
			GateServerCommunicator gateServerCommunicator = keyToGate.get(gateKey);
			if (gateServerCommunicator == null) {
				gateServerCommunicator = new GateServerCommunicator(gateHost, gatePort);
				keyToGate.put(gateKey, gateServerCommunicator);
			}
			uidToGateKey.put(uid, gateKey);

			try {
				sendGateQueue.put(new GameServerMessage(MapEventType.CHARACHTERENTERMAP, request));
			} catch (InterruptedException e) {
				e.printStackTrace();
				responseObserver.onNext(CommonResponse.newBuilder().setStat(CommonStat.ERROR).build());
				responseObserver.onCompleted();
			}
			responseObserver.onNext(CommonResponse.newBuilder().setStat(CommonStat.OK).build());
			responseObserver.onCompleted();
		}
	}

	class CharacterService extends AbstractCharacterService {

		@Override
		public void moveTo(CharacterMoveReq request, StreamObserver<CommonResponse> responseObserver) {
			try {
				sendGateQueue.put(new GameServerMessage(MapEventType.ITEMMOVEEVENT, request));
			} catch (InterruptedException e) {
				responseObserver.onNext(CommonResponse.newBuilder().setStat(CommonStat.ERROR).build());
				responseObserver.onCompleted();
			}
			responseObserver.onNext(CommonResponse.newBuilder().setStat(CommonStat.OK).build());
			responseObserver.onCompleted();
		}

	}

	private void onItemMove(CharacterMoveReq request) {
		Integer uid = request.getUid();
		Position toPos = request.getToPos();
		IdentifyInfo identifyInfo = IdentifyInfo.newBuilder().setID(uid).setName(uid + "").build();

		ItemMoveEvent itemMoveEvent = ItemMoveEvent.newBuilder().setIdentify(identifyInfo).setToPos(toPos).build();

		ItemMoveEventRequest itemMoveEventRequest = ItemMoveEventRequest.newBuilder()
				.setMapId(globalContext.getGameServerId()).setEvent(itemMoveEvent).setEventType(MapEventType.ITEMMOVEEVENT)
				.build();

		// Broadcast message to all gates
		keyToGate.forEach((k, v) -> {
			try {
				v.moveEvent(itemMoveEventRequest);
			} catch (Exception e) {
				if (v.failureTime.incrementAndGet() > 3) {
					// TODO remove
					LOG.warn("Remove gate key : " + k);
				}

			}
		});

	}

	private void onUserArrived(UserArrivedRegionRequest request) {

		Integer uid = request.getUid();
		if (uid > 0) {
			GateServerCommunicator gateServerCommunicator = getGateServerCommunicator(uid);

			CommonProtocol.Character character = globalContext.getCharacterServerCommunicator().getCharacter(uid);
			CharacterCreateEvent event = CharacterCreateEvent.newBuilder().setCharacter(character).build();

			CharacterCreateEventRequest characterCreateEventRequest = CharacterCreateEventRequest.newBuilder()
					.setMapId(globalContext.getGameServerId()).setEvent(event).setEventType(MapEventType.CHARACTERCREATEEVENT)
					.build();

			CharacterEnterMapRequest characterEnterMapRequest = CharacterEnterMapRequest.newBuilder().setStat(CommonStat.OK)
					.setMapId(globalContext.getGameServerId()).setUid(uid).build();

			// Response the gate which is hold the user channel
			gateServerCommunicator.characterEnterMapRequest(characterEnterMapRequest);

			// Broadcast message to all gates
			keyToGate.forEach((k, v) -> {
				try {
					v.createCharacterEvent(characterCreateEventRequest);
				} catch (Exception e) {
					if (v.failureTime.incrementAndGet() > 3) {
						// TODO remove
						LOG.warn("Remove gate key : " + k);
					}

				}
			});
		}

	}

	class SendActor implements Runnable {

		@Override
		public void run() {
			while (shouldRun && !Thread.interrupted()) {
				try {
					GameServerMessage gameServerMessage = sendGateQueue.take();
					switch (gameServerMessage.getType().getNumber()) {
					case MapEventType.CHARACHTERENTERMAP_VALUE:
						onUserArrived((UserArrivedRegionRequest) gameServerMessage.getMessage());
						break;

					case MapEventType.ITEMMOVEEVENT_VALUE:
						onItemMove((CharacterMoveReq) gameServerMessage.getMessage());
						break;

					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}
