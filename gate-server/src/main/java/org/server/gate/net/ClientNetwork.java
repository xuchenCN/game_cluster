package org.server.gate.net;

public class ClientNetwork {

	public static void main(String[] args) throws Exception {
		GateNettyServer server = new GateNettyServer(26666);
		server.start();
		System.out.println("Server listen on " + server.getPort());
		server.await();
	}
}
