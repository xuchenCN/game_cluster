package org.server.game;

import org.mmo.server.common.conf.GameConfiguration;
import org.mmo.server.common.utils.Constants;
import org.server.game.communicator.WorldServerCommunicator;

public class GameServerContext {
	private GameConfiguration config;

	private int listenOn;

	private WorldServerCommunicator worldServerCommunicator;

	public GameServerContext(GameConfiguration config) {
		this.config = config;
	}

	public int getGameSendQueueSize() {
		return config.getInt(Constants.GAME_SERVER_SEND_QUEUE_SIZE , Constants.GAME_SERVER_SEND_QUEUE_SIZE_DEFAULT);
	}

	public String getWorldServerHost() {
		return config.get(Constants.WORLD_SERVER_HOST, Constants.WORLD_SERVER_HOST_DEFAULT);
	}

	public int getWorldServerPort() {
		return config.getInt(Constants.WORLD_SERVER_PORT, Constants.WORLD_SERVER_PORT_DEFAULT);
	}

	public int getGameServerId() {
		return config.getInt(Constants.GAME_SERVER_ID, Constants.GAME_SERVER_ID_DEFAULT);
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

	public GameConfiguration getConfig() {
		return config;
	}

}
