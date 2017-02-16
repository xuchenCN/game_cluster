package org.server.gate.core;

import com.google.protobuf.MessageLite;
import com.mmo.server.MessagesLocation.MessageRegistry;

public class InstructionEvent {
	private int uid;
	private int mapId;
	private MessageRegistry messageId;
	private MessageLite body;

	public InstructionEvent(int uid, int mapId, MessageRegistry messageId, MessageLite body) {
		this.uid = uid;
		this.mapId = mapId;
		this.messageId = messageId;
		this.body = body;
	}

	public int getUid() {
		return uid;
	}

	public int getMapId() {
		return mapId;
	}

	public MessageRegistry getMessageId() {
		return messageId;
	}

	public MessageLite getBody() {
		return body;
	}

}
