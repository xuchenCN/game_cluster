package org.server.gate.communicator;

import com.mmo.server.CharacterServiceGrpc;
import com.mmo.server.CharacterServiceGrpc.CharacterServiceBlockingStub;
import com.mmo.server.UserRegionServiceGrpc;
import com.mmo.server.UserRegionServiceGrpc.UserRegionServiceBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GameServerCommunicator {

	private ManagedChannel channel;

	private UserRegionServiceBlockingStub userRegionStub;
	private CharacterServiceBlockingStub characterStub;

	public GameServerCommunicator(String host, int port) {
		ManagedChannelBuilder channelBuilder = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true);
		channel = channelBuilder.build();
		userRegionStub = UserRegionServiceGrpc.newBlockingStub(channel);
		characterStub = CharacterServiceGrpc.newBlockingStub(channel);
	}

}
