package org.server.gate.net;

import com.mmo.server.MessagesLocation.MessageRegistry;

import io.netty.buffer.ByteBuf;

public class GateClientMessage {
	public MessageRegistry messageId;
	public ByteBuf body;

	public GateClientMessage(MessageRegistry messageId, ByteBuf message) {
		this.messageId = messageId;
		this.body = message;
	}

}
