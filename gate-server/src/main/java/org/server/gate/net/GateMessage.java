package org.server.gate.net;

import com.mmo.server.MessagesLocation.MessageRegistry;

import io.netty.buffer.ByteBuf;

public class GateMessage {
	public MessageRegistry messageId;
	public ByteBuf body;

	public GateMessage(MessageRegistry messageId, ByteBuf message) {
		this.messageId = messageId;
		this.body = message;
	}

}
