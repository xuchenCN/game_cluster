package org.protocol.communicators;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mmo.server.CommonProtocol.CommonResponse;
import com.mmo.server.CommonProtocol.CommonStat;
import com.mmo.server.ServerWorldProtocol.CharacterRegisterRequest;
import com.mmo.server.ServerWorldProtocol.CharacterServerInfo;
import com.mmo.server.ServerWorldProtocol.GateRegisterRequest;
import com.mmo.server.ServerWorldProtocol.GateRegisterResponse;
import com.mmo.server.ServerWorldProtocol.RegionRegisterRequest;
import com.mmo.server.ServerWorldProtocol.RegionRegisterResponse;
import com.mmo.server.ServerWorldProtocol.UserArrivedWorldRequest;
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
	
	public GateRegisterResponse registerGate(String host, int port) {
		GateRegisterRequest request = GateRegisterRequest.newBuilder().setGateHost(host).setGatePort(port).build();
		return worldServerStub.registerGate(request);
	}
	
	public CommonResponse userArrivedWorld(UserArrivedWorldRequest request) {
		return userWorldStub.userArrivedWorld(request);
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
