package org.server.gate;

import org.mmo.persistent.UserInfoPersistentService;
import org.mmo.server.common.conf.GameConfiguration;
import org.mmo.server.common.utils.Constants;
import org.server.gate.communicator.WorldServerCommunicator;
import org.server.gate.core.AccountService;
import org.server.gate.core.GateServerRouter;

public class GateServerContext {
	private GameConfiguration config;

	private int listenOn;

	private WorldServerCommunicator worldServerCommunicator;

	private UserInfoPersistentService userInfoPersistentService;

	private GateServerRouter gateServerRouter;

	private AccountService accountService;

	public GateServerContext(GameConfiguration config) {
		this.config = config;
	}

	public String getWorldServerHost() {
		return config.get(Constants.WORLD_SERVER_HOST, Constants.WORLD_SERVER_HOST_DEFAULT);
	}

	public int getWorldServerPort() {
		return config.getInt(Constants.WORLD_SERVER_PORT, Constants.WORLD_SERVER_PORT_DEFAULT);
	}

	public String getGateServerHost() {
		return config.get(Constants.GATE_SERVER_HOST, Constants.GATE_SERVER_HOST_DEFAULT);
	}

	public int getGateNettyPort() {
		return config.getInt(Constants.GATE_SERVER_PORT, Constants.GATE_SERVER_PORT_DEFAULT);
	}

	public int getListenOn() {
		return listenOn;
	}

	public void setListenOn(int listenOn) {
		this.listenOn = listenOn;
	}

	public WorldServerCommunicator getWorldServerCommunicator() {
		return worldServerCommunicator;
	}

	public void setWorldServerCommunicator(WorldServerCommunicator worldServerCommunicator) {
		this.worldServerCommunicator = worldServerCommunicator;
	}

	public UserInfoPersistentService getUserInfoPersistentService() {
		return userInfoPersistentService;
	}

	public void setUserInfoPersistentService(UserInfoPersistentService userInfoPersistentService) {
		this.userInfoPersistentService = userInfoPersistentService;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public GateServerRouter getGateServerRouter() {
		return gateServerRouter;
	}

	public void setGateServerRouter(GateServerRouter gateServerRouter) {
		this.gateServerRouter = gateServerRouter;
	}

}
