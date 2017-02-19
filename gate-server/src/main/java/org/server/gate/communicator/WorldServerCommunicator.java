package org.server.gate.communicator;

import java.util.List;

import com.mmo.server.CommonProtocol.CommonResponse;
import com.mmo.server.ServerWorldProtocol.GateRegisterRequest;
import com.mmo.server.ServerWorldProtocol.RegionServerInfo;
import com.mmo.server.ServerWorldProtocol.UserArrivedWorldRequest;
import com.mmo.server.UserWorldServiceGrpc;
import com.mmo.server.UserWorldServiceGrpc.UserWorldServiceBlockingStub;
import com.mmo.server.WorldServiceGrpc;
import com.mmo.server.WorldServiceGrpc.WorldServiceBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class WorldServerCommunicator {

	private ManagedChannel channel;

	private UserWorldServiceBlockingStub userWorldStub;
	private WorldServiceBlockingStub worldServerStub;

	public WorldServerCommunicator(String host, int port) {
		ManagedChannelBuilder channelBuilder = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true);
		channel = channelBuilder.build();
		userWorldStub = UserWorldServiceGrpc.newBlockingStub(channel);
		worldServerStub = WorldServiceGrpc.newBlockingStub(channel);
	}

	public List<RegionServerInfo> registerGate(String host, int port) {
		GateRegisterRequest request = GateRegisterRequest.newBuilder().setGateHost(host).setGatePort(port).build();
		return worldServerStub.registerGate(request).getRegionsList();
	}
	
	public CommonResponse userArrivedWorld(UserArrivedWorldRequest request) {
		return userWorldStub.userArrivedWorld(request);
	}
}
