package org.server.game;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mmo.server.common.conf.GameConfiguration;
import org.mmo.server.common.service.CompositeService;
import org.mmo.server.common.utils.NetUtils;
import org.server.game.communicator.WorldServerCommunicator;
import org.server.game.core.CharactorLogic;
import org.server.game.services.GameServerProcessor;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GameServer extends CompositeService {

	private static final Log LOG = LogFactory.getLog(GameServer.class);

	private GameServerContext globalContext;
	private GameServerProcessor gameServerProcessor;
	private CharactorLogic charactorLogic;

	private Server grpcServer;

	public GameServer(GameServerContext context) {
		super("GameServer");
		this.globalContext = context;
	}

	@Override
	protected void serviceInit(GameConfiguration conf) throws Exception {

		WorldServerCommunicator worldServerCommunicator = new WorldServerCommunicator(globalContext.getWorldServerHost(),
				globalContext.getWorldServerPort());
		globalContext.setWorldServerCommunicator(worldServerCommunicator);

		charactorLogic = new CharactorLogic(globalContext);
		addIfService(charactorLogic);
		gameServerProcessor = new GameServerProcessor(globalContext);
		addIfService(gameServerProcessor);

		super.serviceInit(conf);
	}

	@Override
	protected void serviceStart() throws Exception {

		grpcServer = ServerBuilder.forPort(0).addService(gameServerProcessor.getCharacterService())
				.addService(gameServerProcessor.getUserRegionService()).build();
		grpcServer.start();
		globalContext.setListenOn(grpcServer.getPort());
		LOG.info("Game Server started , listening on " + globalContext.getListenOn());
		Thread grpcThread = new Thread() {
			public void run() {
				try {
					grpcServer.awaitTermination();
				} catch (Exception e) {
					LOG.warn("Grpc server shutdown");
				}
			}
		};
		grpcThread.join();
		grpcThread.start();

		// Register this region
		globalContext.getWorldServerCommunicator().registerRegion(NetUtils.getLocalIpAddress(getConfig()),
				globalContext.getListenOn());

		super.serviceStart();
	}

	@Override
	protected void serviceStop() throws Exception {
		// TODO Auto-generated method stub
		super.serviceStop();
	}

}
