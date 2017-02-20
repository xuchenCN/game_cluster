package org.character.server;

import org.character.server.communicator.WorldServerCommunicator;
import org.character.server.service.CharacterServerProcessor;
import org.mmo.persistent.UserInfoPersistentService;
import org.mmo.server.common.conf.GameConfiguration;
import org.mmo.server.common.utils.Constants;

public class CharacterContext {
	private GameConfiguration config;

	private int listenOn;

	private WorldServerCommunicator worldServerCommunicator;

	private CharacterServerProcessor characterServerProcessor;

	private UserInfoPersistentService userInfoPersistentService;

	public CharacterContext(GameConfiguration config) {
		this.config = config;
	}

	public String getWorldServerHost() {
		return config.get(Constants.WORLD_SERVER_HOST, Constants.WORLD_SERVER_HOST_DEFAULT);
	}

	public int getWorldServerPort() {
		return config.getInt(Constants.WORLD_SERVER_PORT, Constants.WORLD_SERVER_PORT_DEFAULT);
	}

	public String getCharServerHost() {
		return config.get(Constants.CHAR_SERVER_HOST, Constants.CHAR_SERVER_HOST_DEFAULT);
	}

	public long getCharServerPersitInterval() {
		return config.getLong(Constants.CHAR_SERVER_PERSIST_INTERVAL_MS,
				Constants.CHAR_SERVER_PERSIST_INTERVAL_MS_DEFAULT);
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

	public CharacterServerProcessor getCharacterServerProcessor() {
		return characterServerProcessor;
	}

	public void setCharacterServerProcessor(CharacterServerProcessor characterServerProcessor) {
		this.characterServerProcessor = characterServerProcessor;
	}

	public GameConfiguration getConfig() {
		return config;
	}

}
