package org.server.game.communicator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mmo.server.ServerWorldProtocol.CharacterServerInfo;
import com.mmo.server.ServerWorldProtocol.RegionRegisterRequest;
import com.mmo.server.ServerWorldProtocol.RegionRegisterResponse;
import com.mmo.server.UserWorldServiceGrpc;
import com.mmo.server.UserWorldServiceGrpc.UserWorldServiceBlockingStub;
import com.mmo.server.WorldServiceGrpc;
import com.mmo.server.WorldServiceGrpc.WorldServiceBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class WorldServerCommunicator {
	
	private static final Log LOG = LogFactory.getLog(WorldServerCommunicator.class);

	private ManagedChannel channel;

	private UserWorldServiceBlockingStub userWorldStub;
	private WorldServiceBlockingStub worldServerStub;
	

	public WorldServerCommunicator(String host, int port) {
		ManagedChannelBuilder channelBuilder = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true);
		channel = channelBuilder.build();
		userWorldStub = UserWorldServiceGrpc.newBlockingStub(channel);
		worldServerStub = WorldServiceGrpc.newBlockingStub(channel);
	}

	public CharacterServerCommunicator registerRegion(String host, int port,int mapId) {
		RegionRegisterRequest request = RegionRegisterRequest.newBuilder().setServerHost(host).setServerPort(port)
				.setMapId(mapId).build();
		RegionRegisterResponse response = worldServerStub.registerRegion(request);
		if(response.getCharServersCount() <= 0) {
			throw new RuntimeException("Failed to register");
		}
		
		CharacterServerInfo characterServerInfo = response.getCharServersList().get(0);
		
		LOG.info("Register success !");
		
		return new CharacterServerCommunicator(characterServerInfo.getServerHost(),characterServerInfo.getServerPort());
	}
}
