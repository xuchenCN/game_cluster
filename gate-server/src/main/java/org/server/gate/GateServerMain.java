package org.server.gate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mmo.server.common.conf.GameConfiguration;
import org.mmo.server.common.exception.HuskyUncaughtExceptionHandler;
import org.mmo.server.common.service.CompositeService.CompositeServiceShutdownHook;
import org.mmo.server.common.utils.ShutdownHookManager;
import org.mmo.server.common.utils.StringUtils;

public class GateServerMain {

  private static final Log LOG = LogFactory.getLog(GateServerMain.class);

  public static final int SHUTDOWN_HOOK_PRIORITY = 30;

  public static void main(String[] args) {
    Thread.setDefaultUncaughtExceptionHandler(new HuskyUncaughtExceptionHandler());
    StringUtils.startupShutdownMessage(GateServerMain.class, args, LOG);
    try {
      GameConfiguration conf = new GameConfiguration();
      GateServer gameServer = new GateServer(new GateServerContext(conf));

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
