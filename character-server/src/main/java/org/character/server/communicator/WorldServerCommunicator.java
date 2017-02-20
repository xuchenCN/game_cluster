package org.character.server.communicator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mmo.server.CommonProtocol.CommonResponse;
import com.mmo.server.CommonProtocol.CommonStat;
import com.mmo.server.ServerWorldProtocol.CharacterRegisterRequest;
import com.mmo.server.WorldServiceGrpc;
import com.mmo.server.WorldServiceGrpc.WorldServiceBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class WorldServerCommunicator {
	
	private static final Log LOG = LogFactory.getLog(WorldServerCommunicator.class);

	private ManagedChannel channel;

	private WorldServiceBlockingStub worldServerStub;

	public WorldServerCommunicator(String host, int port) {
		ManagedChannelBuilder channelBuilder = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true);
		channel = channelBuilder.build();
		worldServerStub = WorldServiceGrpc.newBlockingStub(channel);
	}

	public void registerServer(String host, int port) {
		CharacterRegisterRequest request = CharacterRegisterRequest.newBuilder().setServerHost(host).setServerPort(port).build();
		CommonResponse response = worldServerStub.registerCharacterServer(request);
		if (!(CommonStat.OK == response.getStat())) {
			throw new RuntimeException("Failed to register");
		}
		
		LOG.info("Register success !");
	}
}
