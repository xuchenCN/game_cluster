package org.mmo.server.common.utils;

public class Constants {

	public static final String WORLD_SERVER_HOST = "world-server.host";
	public static final String WORLD_SERVER_HOST_DEFAULT = "localhost";
	public static final String WORLD_SERVER_PORT = "world-server.port";
	public static final int WORLD_SERVER_PORT_DEFAULT = 23333;
	
	public static final String GAME_SERVER_ID= "game-server.id";
	public static final int GAME_SERVER_ID_DEFAULT = 1;
	
	//TODO Using portal server delegate 
	public static final String GATE_SERVER_HOST = "gate-server.host";
	public static final String GATE_SERVER_HOST_DEFAULT = "localhost";
	
	public static final String GATE_SERVER_PORT = "gate-netty.port";
	public static final int GATE_SERVER_PORT_DEFAULT = 24444;
	
	public static final String CHAR_SERVER_PERSIST_INTERVAL_MS = "char-server.persist.interval";
	public static final long CHAR_SERVER_PERSIST_INTERVAL_MS_DEFAULT = 5 * 60 * 1000; // 5 minute
	
	public static final String CHAR_SERVER_HOST = "char-server.host";
	public static final String CHAR_SERVER_HOST_DEFAULT = "localhost";
	
	public static final String GAME_SERVER_SEND_QUEUE_SIZE = "gate-server.send-queue.size";
	public static final int GAME_SERVER_SEND_QUEUE_SIZE_DEFAULT = 1024;
	
	public static final String GATE_SERVER_RECV_QUEUE_SIZE = "gate-server.recv-queue.size";
	public static final int GATE_SERVER_RECV_QUEUE_SIZE_DEFAULT = 1024;
	public static final String GATE_SERVER_SEND_QUEUE_SIZE = "gate-server.send-queue.size";
	public static final int GATE_SERVER_SEND_QUEUE_SIZE_DEFAULT = 1024;
	
	public static final String GATE_SERVER_RECV_POOL_SIZE = "gate-server.recv-pool.size";
	public static final int GATE_SERVER_RECV_POOL_SIZE_DEFAULT = 4;
	
	public static final String GATE_SERVER_SEND_POOL_SIZE = "gate-server.send-pool.size";
	public static final int GATE_SERVER_SEND_POOL_SIZE_DEFAULT = 4;
	
	public static final String GAME_SERVER_SEND_POOL_SIZE = "game-server.send-pool.size";
	public static final int GAME_SERVER_SEND_POOL_SIZE_DEFAULT = 4;
	
	public static final String HOST_RESOLUTION_TIMEOUT_MS = "host.resolution.timeout.ms";
	public static final int HOST_RESOLUTION_TIMEOUT_MS_DEFAULT = 5000;
	
	public static final String USER_INFO_PERSISTENT_SERVICE_CALSS = "user-info.persistent.class";
	public static final String USER_INFO_PERSISTENT_SERVICE_CALSS_DEFAULT = "org.mmo.persistent.redis.UserInfoPersistentServiceImpl";

}
