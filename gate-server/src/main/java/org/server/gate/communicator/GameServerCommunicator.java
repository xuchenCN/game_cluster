package org.server.gate.communicator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mmo.server.CharacterServiceGrpc;
import com.mmo.server.CharacterServiceGrpc.CharacterServiceBlockingStub;
import com.mmo.server.CommonProtocol.CommonResponse;
import com.mmo.server.ServerGameProtocol.CharacterMoveReq;
import com.mmo.server.ServerGameProtocol.UserArrivedRegionRequest;
import com.mmo.server.UserRegionServiceGrpc;
import com.mmo.server.UserRegionServiceGrpc.UserRegionServiceBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

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

	public void userArrivedRegion(UserArrivedRegionRequest request) {
		userRegionStub.userArrivedRegion(request);
	}

	public void moveTo(CharacterMoveReq request) {
		characterStub.moveTo(request);
	}

}
