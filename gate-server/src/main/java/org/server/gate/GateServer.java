package org.server.gate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mmo.persistent.UserInfoPersistentService;
import org.mmo.server.common.conf.GameConfiguration;
import org.mmo.server.common.service.CompositeService;
import org.mmo.server.common.utils.Constants;
import org.mmo.server.common.utils.ReflectionUtils;
import org.server.gate.communicator.WorldServerCommunicator;
import org.server.gate.core.AccountService;
import org.server.gate.core.GateServerRouter;
import org.server.gate.net.GateNettyServer;
import org.server.gate.services.GateServerProcessor;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GateServer extends CompositeService {

	private static final Log LOG = LogFactory.getLog(GateServer.class);

	private GateServerContext globalContext;

	private GateServerRouter gateServerRouter;

	private GateServerProcessor gateServerProcessor;
	
	private GateNettyServer gateNettyServer;

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
		
		UserInfoPersistentService userInfoPersistentService = createUserInfoPersistentService();
		globalContext.setUserInfoPersistentService(userInfoPersistentService);
		addIfService(userInfoPersistentService);
		
		AccountService accountService = new AccountService(globalContext);
		addIfService(accountService);
		globalContext.setAccountService(accountService);
		
//		CharacterServerCommunicator characterServerCommunicator = new CharacterServerCommunicator();
		
		gateServerRouter = new GateServerRouter(globalContext);
		addIfService(gateServerRouter);
		globalContext.setGateServerRouter(gateServerRouter);
		
		gateServerProcessor = new GateServerProcessor(globalContext);
		addIfService(gateServerProcessor);
		
		gateNettyServer = new GateNettyServer(globalContext);

		super.serviceInit(conf);
	}

	@Override
	protected void serviceStart() throws Exception {

		grpcServer = ServerBuilder.forPort(0).addService(gateServerRouter.getForwardService()).addService(gateServerRouter.getGateServerService()).build();
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
		
		gateNettyServer.start();
		
		super.serviceStart();
	}

	@Override
	protected void serviceStop() throws Exception {
		gateNettyServer.start();
		super.serviceStop();
	}

	protected UserInfoPersistentService createUserInfoPersistentService() {
		String persistentClassName = getConfig().get(Constants.USER_INFO_PERSISTENT_SERVICE_CALSS,
				Constants.USER_INFO_PERSISTENT_SERVICE_CALSS_DEFAULT);

		LOG.info("Using persistentClassName: " + persistentClassName);
		try {
			Class<?> serviceClass = Class.forName(persistentClassName);
			if (UserInfoPersistentService.class.isAssignableFrom(serviceClass)) {
				return (UserInfoPersistentService) ReflectionUtils.newInstance(serviceClass);
			} else {
				throw new RuntimeException(
						"Class: " + persistentClassName + " not instance of " + UserInfoPersistentService.class.getCanonicalName());
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Could not instantiate persistentClass: " + persistentClassName, e);
		}
	}

}
