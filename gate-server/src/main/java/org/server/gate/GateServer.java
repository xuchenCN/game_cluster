package org.server.gate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mmo.server.common.conf.GameConfiguration;
import org.mmo.server.common.service.CompositeService;
import org.server.gate.communicator.WorldServerCommunicator;
import org.server.gate.core.GateServerRouter;
import org.server.gate.services.GateServerProcessor;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GateServer extends CompositeService {

	private static final Log LOG = LogFactory.getLog(GateServer.class);

	private GateServerContext globalContext;

	private GateServerRouter gateServerRouter;

	private GateServerProcessor gateServerProcessor;

	private Server grpcServer;

	public GateServer(GateServerContext context) {
		super("GateServer");
		this.globalContext = context;
	}

	@Override
	protected void serviceInit(GameConfiguration conf) throws Exception {

		WorldServerCommunicator worldServerCommunicator = new WorldServerCommunicator(globalContext.getWorldServerHost(),
				globalContext.getWorldServerPort());
		globalContext.setWorldServerCommunicator(worldServerCommunicator);

		gateServerRouter = new GateServerRouter(globalContext);
		addIfService(gateServerRouter);
		gateServerProcessor = new GateServerProcessor(globalContext);
		addIfService(gateServerProcessor);

		super.serviceInit(conf);
	}

	@Override
	protected void serviceStart() throws Exception {

		grpcServer = ServerBuilder.forPort(0).addService(gateServerRouter.getForwardService()).build();
		grpcServer.start();
		globalContext.setListenOn(grpcServer.getPort());
		LOG.info("Gate Server started , listening on " + globalContext.getListenOn());
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
