package org.server.world;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mmo.server.common.conf.GameConfiguration;
import org.mmo.server.common.service.CompositeService;
import org.server.world.services.WorldServerProcessor;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class WorldServer extends CompositeService {

	private static final Log LOG = LogFactory.getLog(WorldServer.class);

	private WorldServerContext globalContext;
	private WorldServerProcessor worldServerProcessor;

	private Server grpcServer;

	public WorldServer(WorldServerContext context) {
		super("WorldServer");
		this.globalContext = context;
	}

	@Override
	protected void serviceInit(GameConfiguration conf) throws Exception {
		worldServerProcessor = new WorldServerProcessor(globalContext);
		addIfService(worldServerProcessor);

		super.serviceInit(conf);
	}

	@Override
	protected void serviceStart() throws Exception {
		grpcServer = ServerBuilder.forPort(globalContext.getWorldServerPort())
				.addService(worldServerProcessor.getUserService()).addService(worldServerProcessor.getWorldService())
				.build();
		grpcServer.start();
		LOG.info("World Server started , listening on " + globalContext.getWorldServerPort());
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
		super.serviceStart();
	}

	@Override
	protected void serviceStop() throws Exception {
		// TODO Auto-generated method stub
		super.serviceStop();
	}

}
