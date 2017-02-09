package org.server.world;

import org.mmo.server.common.conf.GameConfiguration;
import org.mmo.server.common.utils.Constants;

public class WorldServerContext {
	private GameConfiguration config;

	public WorldServerContext(GameConfiguration config) {
		this.config = config;
	}

	public String getWorldServerHost() {
		return config.get(Constants.WORLD_SERVER_HOST, Constants.WORLD_SERVER_HOST_DEFAULT);
	}

	public int getWorldServerPort() {
		return config.getInt(Constants.WORLD_SERVER_PORT, Constants.WORLD_SERVER_PORT_DEFAULT);
	}
}
