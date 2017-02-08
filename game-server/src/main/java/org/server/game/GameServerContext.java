package org.server.game;

import org.mmo.server.common.conf.GameConfiguration;

public class GameServerContext {
  private GameConfiguration config;

  public GameServerContext(GameConfiguration config) {
    this.config = config;
  }

}
