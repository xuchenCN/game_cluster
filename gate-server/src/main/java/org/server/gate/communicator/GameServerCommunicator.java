package org.server.gate.communicator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.server.gate.core.GateServerRouter;

import com.mmo.server.CharacterServiceGrpc;
import com.mmo.server.CharacterServiceGrpc.CharacterServiceBlockingStub;
import com.mmo.server.UserRegionServiceGrpc;
import com.mmo.server.UserRegionServiceGrpc.UserRegionServiceBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GameServerCommunicator {

	private static final Log LOG = LogFactory.getLog(GameServerCommunicator.class);

	private ManagedChannel channel;

	private UserRegionServiceBlockingStub userRegionStub;
	private CharacterServiceBlockingStub characterStub;

	public GameServerCommunicator(String host, int port) {
		ManagedChannelBuilder channelBuilder = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true);
		channel = channelBuilder.build();
		userRegionStub = UserRegionServiceGrpc.newBlockingStub(channel);
		characterStub = CharacterServiceGrpc.newBlockingStub(channel);

		LOG.info("Init Game-Server connection " + host + ":" + port);
	}

}
