package org.server.world.services;

public class GateServerInfo {

	private String host;
	private int port;

	public GateServerInfo(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

}
