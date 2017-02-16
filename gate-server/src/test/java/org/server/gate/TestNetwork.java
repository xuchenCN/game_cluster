package org.server.gate;

import org.mmo.server.common.conf.GameConfiguration;
import org.server.gate.net.GateNettyServer;

public class TestNetwork {

	public static void main(String[] args) throws Exception {
		GateNettyServer server = new GateNettyServer(new GateServerContext(new GameConfiguration()));
		server.start();
		System.out.println("Server listen on " + server.getPort());
		server.await();
	}
}
