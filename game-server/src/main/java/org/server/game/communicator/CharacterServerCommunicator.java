package org.server.game.communicator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mmo.server.CharacterServiceGrpc;
import com.mmo.server.CharacterServiceGrpc.CharacterServiceBlockingStub;
import com.mmo.server.ServerGateProtocol.CharacterCreateEventRequest;
import com.mmo.server.ServerGateProtocol.CharacterEnterMapRequest;
import com.mmo.server.ServerGateProtocol.ItemCreateEventRequest;
import com.mmo.server.ServerGateProtocol.ItemDestroyEventRequest;
import com.mmo.server.ServerGateProtocol.ItemMoveEventRequest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class CharacterServerCommunicator {
	
	
	private static final Log LOG = LogFactory.getLog(CharacterServerCommunicator.class);

	private ManagedChannel channel;

	private CharacterServiceBlockingStub characterService;
	
	public CharacterServerCommunicator(String host, int port) {
		ManagedChannelBuilder channelBuilder = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true);
		channel = channelBuilder.build();
		characterService = CharacterServiceGrpc.newBlockingStub(channel);
	}
	
	

}
