package org.character.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.character.server.communicator.WorldServerCommunicator;
import org.character.server.service.CharacterServerProcessor;
import org.mmo.persistent.UserInfoPersistentService;
import org.mmo.server.common.conf.GameConfiguration;
import org.mmo.server.common.service.CompositeService;
import org.mmo.server.common.utils.Constants;
import org.mmo.server.common.utils.ReflectionUtils;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class CharacterServer extends CompositeService {

	private static final Log LOG = LogFactory.getLog(CharacterServer.class);

	private CharacterContext globalContext;

	private Server grpcServer;

	private CharacterServerProcessor characterServerProcessor;

	public CharacterServer(CharacterContext context) {
		super("CharacterServer");
		this.globalContext = context;
	}

	@Override
	protected void setConfig(GameConfiguration conf) {
		// TODO Auto-generated method stub
		super.setConfig(conf);
	}

	@Override
	protected void serviceInit(GameConfiguration conf) throws Exception {

		WorldServerCommunicator worldServerCommunicator = new WorldServerCommunicator(globalContext.getWorldServerHost(),
				globalContext.getWorldServerPort());
		globalContext.setWorldServerCommunicator(worldServerCommunicator);

		characterServerProcessor = new CharacterServerProcessor(globalContext);
		addIfService(characterServerProcessor);
		globalContext.setCharacterServerProcessor(characterServerProcessor);

		UserInfoPersistentService userInfoPersistentService = createUserInfoPersistentService();
		globalContext.setUserInfoPersistentService(userInfoPersistentService);
		addIfService(userInfoPersistentService);

		super.serviceInit(conf);
	}

	@Override
	protected void serviceStart() throws Exception {

		grpcServer = ServerBuilder.forPort(0).addService(characterServerProcessor.getCharacterService()).build();
		grpcServer.start();
		globalContext.setListenOn(grpcServer.getPort());
		LOG.info("Character Server started , listening on " + globalContext.getListenOn());
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

		globalContext.getWorldServerCommunicator().registerServer(globalContext.getCharServerHost(),
				globalContext.getListenOn());

		super.serviceStart();
	}

	@Override
	protected void serviceStop() throws Exception {
		super.serviceStop();
		if (grpcServer != null && !grpcServer.isShutdown()) {
			grpcServer.shutdownNow();
		}

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
				throw new RuntimeException("Class: " + persistentClassName + " not instance of "
						+ UserInfoPersistentService.class.getCanonicalName());
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Could not instantiate persistentClass: " + persistentClassName, e);
		}
	}

}
