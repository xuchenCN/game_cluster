package org.protocol.communicators;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mmo.server.EventDispatcherGrpc;
import com.mmo.server.EventDispatcherGrpc.EventDispatcherBlockingStub;
import com.mmo.server.GateServerServiceGrpc;
import com.mmo.server.GateServerServiceGrpc.GateServerServiceBlockingStub;
import com.mmo.server.ServerGateProtocol.CharacterCreateEventRequest;
import com.mmo.server.ServerGateProtocol.CharacterEnterMapRequest;
import com.mmo.server.ServerGateProtocol.ItemCreateEventRequest;
import com.mmo.server.ServerGateProtocol.ItemDestroyEventRequest;
import com.mmo.server.ServerGateProtocol.ItemMoveEventRequest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GateServerCommunicator {

	private static final Log LOG = LogFactory.getLog(GateServerCommunicator.class);

	private ManagedChannel channel;

	private EventDispatcherBlockingStub dispatcherStub;
	private GateServerServiceBlockingStub gateServerService;
	public AtomicInteger failureTime = new AtomicInteger(0);

	public GateServerCommunicator(String host, int port) {
		ManagedChannelBuilder channelBuilder = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true);
		channel = channelBuilder.build();
		dispatcherStub = EventDispatcherGrpc.newBlockingStub(channel);
		gateServerService = GateServerServiceGrpc.newBlockingStub(channel);
	}

	public void moveEvent(ItemMoveEventRequest request) {
		dispatcherStub.moveEvent(request);
	}

	public void createItemEvent(ItemCreateEventRequest request) {
		dispatcherStub.createItemEvent(request);
	}

	public void destroyItemEvent(ItemDestroyEventRequest request) {
		dispatcherStub.destroyItemEvent(request);
	}

	public void createCharacterEvent(CharacterCreateEventRequest request) {
		dispatcherStub.createCharacterEvent(request);
	}

	public void characterEnterMapRequest(CharacterEnterMapRequest request) {
		gateServerService.characterEnterMapRequest(request);
	}

}
