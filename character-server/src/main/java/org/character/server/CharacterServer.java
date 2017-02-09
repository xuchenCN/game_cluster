package org.character.server;

import org.mmo.server.common.conf.GameConfiguration;
import org.mmo.server.common.service.CompositeService;

public class CharacterServer extends CompositeService {
  
  private CharacterContext globalContext;

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
