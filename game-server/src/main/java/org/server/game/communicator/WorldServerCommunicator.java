package org.server.game.communicator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mmo.server.CommonProtocol.CommonResponse;
import com.mmo.server.CommonProtocol.CommonStat;
import com.mmo.server.ServerWorldProtocol.RegionRegisterRequest;
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

	public void registerRegion(String host, int port,int mapId) {
		RegionRegisterRequest request = RegionRegisterRequest.newBuilder().setServerHost(host).setServerPort(port)
				.setMapId(mapId).build();
		CommonResponse response = worldServerStub.registerRegion(request);
		if (!(CommonStat.OK == response.getStat())) {
			throw new RuntimeException("Failed to register");
		}
		
		LOG.info("Register success !");
	}
}
