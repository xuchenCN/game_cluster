package org.server.gate.core;

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
import org.mmo.server.common.utils.NetUtils;
import org.server.gate.GateServerContext;
import org.server.gate.communicator.GameServerCommunicator;
import org.server.gate.utils.ExecutorExceptionHandler;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.mmo.server.CommonProtocol.CommonResponse;
import com.mmo.server.CommonProtocol.CommonStat;
import com.mmo.server.EventDispatcherGrpc.AbstractEventDispatcher;
import com.mmo.server.GateServerServiceGrpc.AbstractGateServerService;
import com.mmo.server.MessagesLocation.MessageRegistry;
import com.mmo.server.ServerGameProtocol.CharacterMoveReq;
import com.mmo.server.ServerGateProtocol.CharacterCreateEventRequest;
import com.mmo.server.ServerGateProtocol.GateServerPing;
import com.mmo.server.ServerGateProtocol.GateServerPong;
import com.mmo.server.ServerGateProtocol.ItemCraateEventRequest;
import com.mmo.server.ServerGateProtocol.ItemDestroyEventRequest;
import com.mmo.server.ServerGateProtocol.ItemMoveEventRequest;
import com.mmo.server.ServerGateProtocol.PlayerBeginChangeMapRequest;
import com.mmo.server.ServerGateProtocol.PlayerChangeMapCompletedRequest;
import com.mmo.server.ServerWorldProtocol.RegionServerInfo;

import io.grpc.stub.StreamObserver;

public class GateServerRouter extends AbstractService {

	private static final Log LOG = LogFactory.getLog(GateServerRouter.class);

	private GateServerContext globalContext;
	private ForwardService forwardService;

	private Map<String, GameServerCommunicator> mapCommunicators = new HashMap<String, GameServerCommunicator>();

	private ArrayBlockingQueue<InstructionEvent> receiveMessageQueue;
	private ArrayBlockingQueue<GateSendMessge> sendEventQueue;

	private ExecutorService recvThreadPool;

	private volatile boolean shouldRun;

	public GateServerRouter(GateServerContext globalContext) {
		super("GateServerRouter");
		this.globalContext = globalContext;
		receiveMessageQueue = new ArrayBlockingQueue<InstructionEvent>(
				this.getConfig().getInt(Constants.GATE_SERVER_RECV_QUEUE_SIZE, Constants.GATE_SERVER_RECV_QUEUE_SIZE_DEFAULT));
		sendEventQueue = new ArrayBlockingQueue<GateSendMessge>(
				this.getConfig().getInt(Constants.GATE_SERVER_SEND_QUEUE_SIZE, Constants.GATE_SERVER_SEND_QUEUE_SIZE_DEFAULT));

		recvThreadPool = Executors.newFixedThreadPool(
				this.getConfig().getInt(Constants.GATE_SERVER_RECV_POOL_SIZE, Constants.GATE_SERVER_RECV_POOL_SIZE_DEFAULT),
				new ThreadFactoryBuilder().setNameFormat("RecvMessagePool #%d")
						.setUncaughtExceptionHandler(new ExecutorExceptionHandler(this)).build());

		IntStream
				.range(0,
						this.getConfig().getInt(Constants.GATE_SERVER_RECV_POOL_SIZE, Constants.GATE_SERVER_RECV_POOL_SIZE_DEFAULT))
				.forEach(i -> {
					recvThreadPool.execute(new GameServerRouter());
				});
	}

	@Override
	protected void serviceInit(GameConfiguration conf) throws Exception {
		forwardService = new ForwardService();
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
				sendEventQueue.put(new GateSendMessge(request.getMapId(), request.getEffectsList(),request.getEvent(), request.getEventType()));
			} catch (InterruptedException e) {
				responseObserver.onNext(CommonResponse.newBuilder().setStat(CommonStat.ERROR).build());
			}
			responseObserver.onNext(CommonResponse.newBuilder().setStat(CommonStat.OK).build());
			responseObserver.onCompleted();
		}

		@Override
		public void createItemEvent(ItemCraateEventRequest request, StreamObserver<CommonResponse> responseObserver) {
			try {
				sendEventQueue.put(new GateSendMessge(request.getMapId(), request.getEffectsList(),request.getEvent(), request.getEventType()));
			} catch (InterruptedException e) {
				responseObserver.onNext(CommonResponse.newBuilder().setStat(CommonStat.ERROR).build());
			}
			responseObserver.onNext(CommonResponse.newBuilder().setStat(CommonStat.OK).build());
			responseObserver.onCompleted();
		}

		@Override
		public void destroyItemEvent(ItemDestroyEventRequest request, StreamObserver<CommonResponse> responseObserver) {
			try {
				sendEventQueue.put(new GateSendMessge(request.getMapId(), request.getEffectsList(),request.getEvent(), request.getEventType()));
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
				sendEventQueue.put(new GateSendMessge(request.getMapId(), request.getEffectsList(),request.getEvent(), request.getEventType()));
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
		public void playBeginChangeMap(PlayerBeginChangeMapRequest request,
				StreamObserver<CommonResponse> responseObserver) {
			// TODO Auto-generated method stub
			super.playBeginChangeMap(request, responseObserver);
		}

		@Override
		public void playerChangeMapCompleted(PlayerChangeMapCompletedRequest request,
				StreamObserver<CommonResponse> responseObserver) {
			// TODO Auto-generated method stub
			super.playerChangeMapCompleted(request, responseObserver);
		}

	}

}
