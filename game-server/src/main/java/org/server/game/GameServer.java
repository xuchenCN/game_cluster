package org.server.game;

import org.mmo.server.common.conf.GameConfiguration;
import org.mmo.server.common.service.CompositeService;

public class GameServer extends CompositeService {
  
  private GameServerContext globalContext;

  public GameServer(GameServerContext context) {
    super("GameServer");
    this.globalContext = context;
  }

  @Override
  protected void setConfig(GameConfiguration conf) {
    // TODO Auto-generated method stub
    super.setConfig(conf);
  }

  @Override
  protected void serviceInit(GameConfiguration conf) throws Exception {
    // TODO Auto-generated method stub
    super.serviceInit(conf);
  }

  @Override
  protected void serviceStart() throws Exception {
    // TODO Auto-generated method stub
    super.serviceStart();
  }

  @Override
  protected void serviceStop() throws Exception {
    // TODO Auto-generated method stub
    super.serviceStop();
  }

}
