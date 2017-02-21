package org.server.gate.communicator;

import com.mmo.server.CommonProtocol.CommonResponse;
import com.mmo.server.ServerWorldProtocol.GateRegisterRequest;
import com.mmo.server.ServerWorldProtocol.GateRegisterResponse;
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

	public GateRegisterResponse registerGate(String host, int port) {
		GateRegisterRequest request = GateRegisterRequest.newBuilder().setGateHost(host).setGatePort(port).build();
		return worldServerStub.registerGate(request);
	}
	
	public CommonResponse userArrivedWorld(UserArrivedWorldRequest request) {
		return userWorldStub.userArrivedWorld(request);
	}
}
