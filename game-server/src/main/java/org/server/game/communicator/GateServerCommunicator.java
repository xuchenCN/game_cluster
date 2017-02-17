package org.server.game.communicator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mmo.server.EventDispatcherGrpc;
import com.mmo.server.EventDispatcherGrpc.EventDispatcherBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GateServerCommunicator {
	
	private static final Log LOG = LogFactory.getLog(GateServerCommunicator.class);

	private ManagedChannel channel;

	private EventDispatcherBlockingStub dispatcherStub;

	public GateServerCommunicator(String host, int port) {
		ManagedChannelBuilder channelBuilder = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true);
		channel = channelBuilder.build();
		dispatcherStub = EventDispatcherGrpc.newBlockingStub(channel);
	}

}
