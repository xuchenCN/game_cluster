package org.character.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mmo.server.common.conf.GameConfiguration;
import org.mmo.server.common.exception.HuskyUncaughtExceptionHandler;
import org.mmo.server.common.service.CompositeService.CompositeServiceShutdownHook;
import org.mmo.server.common.utils.ShutdownHookManager;
import org.mmo.server.common.utils.StringUtils;

public class CharacterServerMain {

  private static final Log LOG = LogFactory.getLog(CharacterServerMain.class);

  public static final int SHUTDOWN_HOOK_PRIORITY = 30;

  public static void main(String[] args) {
    Thread.setDefaultUncaughtExceptionHandler(new HuskyUncaughtExceptionHandler());
    StringUtils.startupShutdownMessage(CharacterServerMain.class, args, LOG);
    try {
      GameConfiguration conf = new GameConfiguration();
      CharacterServer gameServer = new CharacterServer(new CharacterContext(conf));

      ShutdownHookManager.get()
          .addShutdownHook(new CompositeServiceShutdownHook(gameServer), SHUTDOWN_HOOK_PRIORITY);

      gameServer.init(conf);
      gameServer.start();
    } catch (Throwable t) {
      LOG.error("Error starting EngineServer", t);
      System.exit(-1);
    }
  }

}
