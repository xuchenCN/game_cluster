package org.mmo.server.common.utils;

public class Constants {

	public static final String WORLD_SERVER_HOST = "world-server.host";
	public static final String WORLD_SERVER_HOST_DEFAULT = "localhost";
	public static final String WORLD_SERVER_PORT = "world-server.port";
	public static final int WORLD_SERVER_PORT_DEFAULT = 23333;
	
	//TODO Using portal server delegate 
	public static final String GATE_SERVER_HOST = "gate-server.host";
	public static final String GATE_SERVER_HOST_DEFAULT = "localhost";
	public static final String GATE_SERVER_PORT = "gate-server.port";
	public static final int GATE_SERVER_PORT_DEFAULT = 24444;
	
	public static final String GATE_SERVER_RECV_QUEUE_SIZE = "gate-server.recv-queue.size";
	public static final int GATE_SERVER_RECV_QUEUE_SIZE_DEFAULT = 1024;
	public static final String GATE_SERVER_SEND_QUEUE_SIZE = "gate-server.send-queue.size";
	public static final int GATE_SERVER_SEND_QUEUE_SIZE_DEFAULT = 1024;
	
	public static final String HOST_RESOLUTION_TIMEOUT_MS = "host.resolution.timeout.ms";
	public static final int HOST_RESOLUTION_TIMEOUT_MS_DEFAULT = 5000;

}
