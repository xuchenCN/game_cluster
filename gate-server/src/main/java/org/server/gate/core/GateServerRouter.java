package org.server.gate.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import org.mmo.server.common.utils.NetUtils;
import org.server.gate.GateServerContext;
import org.server.gate.communicator.GameServerCommunicator;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.mmo.server.CommonProtocol.CommonResponse;
import com.mmo.server.CommonProtocol.CommonStat;
import com.mmo.server.EventDispatcherGrpc.AbstractEventDispatcher;
import com.mmo.server.GateServerServiceGrpc.AbstractGateServerService;
import com.mmo.server.MessagesLocation.MessageRegistry;
import com.mmo.server.ServerGameProtocol.CharacterMoveReq;
import com.mmo.server.ServerGameProtocol.UserArrivedRegionRequest;
import com.mmo.server.ServerGateProtocol.CharacterCreateEventRequest;
import com.mmo.server.ServerGateProtocol.CharacterEnterMapRequest;
import com.mmo.server.ServerGateProtocol.GateServerPing;
import com.mmo.server.ServerGateProtocol.GateServerPong;
import com.mmo.server.ServerGateProtocol.ItemCreateEventRequest;
import com.mmo.server.ServerGateProtocol.ItemDestroyEventRequest;
import com.mmo.server.ServerGateProtocol.ItemMoveEventRequest;
import com.mmo.server.ServerGateProtocol.MapEventType;
import com.mmo.server.ServerWorldProtocol.RegionServerInfo;
import com.mmo.server.ServerWorldProtocol.UserArrivedWorldRequest;

import io.grpc.stub.StreamObserver;

public class GateServerRouter extends AbstractService {

	private static final Log LOG = LogFactory.getLog(GateServerRouter.class);

	private GateServerContext globalContext;
	private ForwardService forwardService;
	private GateServerService gateServerService;

	private Map<String, GameServerCommunicator> mapCommunicators = new HashMap<String, GameServerCommunicator>();

	private ArrayBlockingQueue<InstructionEvent> receiveMessageQueue;
	private ArrayBlockingQueue<GateSendMessge> sendEventQueue;

	private ExecutorService recvThreadPool;

	private volatile boolean shouldRun;

	public GateServerRouter(GateServerContext globalContext) {
		super("GateServerRouter");
		this.globalContext = globalContext;
		receiveMessageQueue = new ArrayBlockingQueue<InstructionEvent>(globalContext.getConfig()
				.getInt(Constants.GATE_SERVER_RECV_QUEUE_SIZE, Constants.GATE_SERVER_RECV_QUEUE_SIZE_DEFAULT));
		sendEventQueue = new ArrayBlockingQueue<GateSendMessge>(globalContext.getConfig()
				.getInt(Constants.GATE_SERVER_SEND_QUEUE_SIZE, Constants.GATE_SERVER_SEND_QUEUE_SIZE_DEFAULT));

		recvThreadPool = Executors.newFixedThreadPool(
				globalContext.getConfig().getInt(Constants.GATE_SERVER_RECV_POOL_SIZE,
						Constants.GATE_SERVER_RECV_POOL_SIZE_DEFAULT),
				new ThreadFactoryBuilder().setNameFormat("RecvMessagePool #%d")
						.setUncaughtExceptionHandler(new ExecutorExceptionHandler(this)).build());

	}

	@Override
	protected void serviceInit(GameConfiguration conf) throws Exception {
		forwardService = new ForwardService();
		gateServerService = new GateServerService();
		super.serviceInit(conf);
	}

	@Override
	protected void serviceStart() throws Exception {

		List<RegionServerInfo> regionServerList = globalContext.getWorldServerCommunicator()
				.registerGate(NetUtils.getLocalIpAddress(getConfig()), globalContext.getListenOn());
		LOG.info("Registered got regionServer : " + regionServerList);

		regionServerList.forEach(info -> {
			mapCommunicators.put(info.getMapid() + "",
					new GameServerCommunicator(info.getServerHost(), info.getServerPort()));
		});

		this.shouldRun = true;

		IntStream.range(0, globalContext.getConfig().getInt(Constants.GATE_SERVER_RECV_POOL_SIZE,
				Constants.GATE_SERVER_RECV_POOL_SIZE_DEFAULT)).forEach(i -> {
					recvThreadPool.execute(new GameServerRouter());
				});

		super.serviceStart();
	}

	@Override
	protected void serviceStop() throws Exception {
		// TODO Auto-generated method stub
		super.serviceStop();
	}

	public ForwardService getForwardService() {
		return forwardService;
	}

	public GateServerService getGateServerService() {
		return gateServerService;
	}

	public void receiveMessage(InstructionEvent message) throws InterruptedException {
		this.receiveMessageQueue.put(message);
	}

	public GateSendMessge pullSendQueue() throws InterruptedException {
		return sendEventQueue.take();
	}

	class GameServerRouter implements Runnable {

		@Override
		public void run() {
			while (shouldRun && !Thread.interrupted()) {
				try {
					InstructionEvent instructionEvent = receiveMessageQueue.take();
					GameServerCommunicator communicator = mapCommunicators.get(instructionEvent.getMapId() + "");
					if (communicator != null) {
						switch (instructionEvent.getMessageId().getNumber()) {
						case MessageRegistry.CHARACTERMOVE_VALUE:
							CharacterMoveReq characterMove = (CharacterMoveReq) instructionEvent.getBody();
							communicator.moveTo(characterMove);
							break;
						case MessageRegistry.CHARACTERENTERREQUEST_VALUE:
							UserArrivedWorldRequest userArrivedWorldRequest = (UserArrivedWorldRequest) instructionEvent
									.getBody();
							globalContext.getWorldServerCommunicator().userArrivedWorld(userArrivedWorldRequest);
							UserArrivedRegionRequest userArrivedRegionRequest = UserArrivedRegionRequest.newBuilder()
									.setGateHost(userArrivedWorldRequest.getGateHost())
									.setGatePort(userArrivedWorldRequest.getGatePort())
									.setUid(userArrivedWorldRequest.getUid()).build();

							communicator.userArrivedRegion(userArrivedRegionRequest);

							break;
						default:
							LOG.error("Unknow message " + instructionEvent.getMessageId().getNumber());
							break;
						}
					} else {
						LOG.error("MapId not exist " + instructionEvent.getMapId());
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * Receive from Game-server and World-server event
	 *
	 */
	class ForwardService extends AbstractEventDispatcher {

		@Override
		public void moveEvent(ItemMoveEventRequest request, StreamObserver<CommonResponse> responseObserver) {
			try {
				sendEventQueue.put(new GateSendMessge(request.getMapId(), request.getEffectsList(), request.getEvent(),
						request.getEventType()));
			} catch (InterruptedException e) {
				responseObserver.onNext(CommonResponse.newBuilder().setStat(CommonStat.ERROR).build());
			}
			responseObserver.onNext(CommonResponse.newBuilder().setStat(CommonStat.OK).build());
			responseObserver.onCompleted();
		}

		@Override
		public void createItemEvent(ItemCreateEventRequest request, StreamObserver<CommonResponse> responseObserver) {
			try {
				sendEventQueue.put(new GateSendMessge(request.getMapId(), request.getEffectsList(), request.getEvent(),
						request.getEventType()));
			} catch (InterruptedException e) {
				responseObserver.onNext(CommonResponse.newBuilder().setStat(CommonStat.ERROR).build());
			}
			responseObserver.onNext(CommonResponse.newBuilder().setStat(CommonStat.OK).build());
			responseObserver.onCompleted();
		}

		@Override
		public void destroyItemEvent(ItemDestroyEventRequest request, StreamObserver<CommonResponse> responseObserver) {
			try {
				sendEventQueue.put(new GateSendMessge(request.getMapId(), request.getEffectsList(), request.getEvent(),
						request.getEventType()));
			} catch (InterruptedException e) {
				responseObserver.onNext(CommonResponse.newBuilder().setStat(CommonStat.ERROR).build());
			}
			responseObserver.onNext(CommonResponse.newBuilder().setStat(CommonStat.OK).build());
			responseObserver.onCompleted();
		}

		@Override
		public void createCharacterEvent(CharacterCreateEventRequest request,
				StreamObserver<CommonResponse> responseObserver) {
			try {
				sendEventQueue.put(new GateSendMessge(request.getMapId(), request.getEffectsList(), request.getEvent(),
						request.getEventType()));
			} catch (InterruptedException e) {
				responseObserver.onNext(CommonResponse.newBuilder().setStat(CommonStat.ERROR).build());
			}
			responseObserver.onNext(CommonResponse.newBuilder().setStat(CommonStat.OK).build());
			responseObserver.onCompleted();
		}

	}

	class GateServerService extends AbstractGateServerService {

		@Override
		public void receivePing(GateServerPing request, StreamObserver<GateServerPong> responseObserver) {
			// TODO Auto-generated method stub
			super.receivePing(request, responseObserver);
		}

		@Override
		public void characterEnterMapRequest(CharacterEnterMapRequest request,
				StreamObserver<CommonResponse> responseObserver) {

			try {
				List<Integer> effects = new ArrayList<Integer>(1);
				effects.add(request.getUid());
				GateSendMessge message = new GateSendMessge(request.getMapId(), effects, request,
						MapEventType.CHARACHTERENTERMAP);
				sendEventQueue.put(message);
			} catch (InterruptedException e) {
				responseObserver.onNext(CommonResponse.newBuilder().setStat(CommonStat.ERROR).build());
			}
			responseObserver.onNext(CommonResponse.newBuilder().setStat(CommonStat.OK).build());
			responseObserver.onCompleted();
		}

	}

}
