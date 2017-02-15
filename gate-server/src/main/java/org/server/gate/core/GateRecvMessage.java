package org.server.gate.core;

import com.mmo.server.MessagesLocation.MessageRegistry;

import io.netty.buffer.ByteBuf;

public class GateRecvMessage {
	public MessageRegistry messageId;
	public ByteBuf body;

	public GateRecvMessage(MessageRegistry messageId, ByteBuf message) {
		this.messageId = messageId;
		this.body = message;
	}

}
