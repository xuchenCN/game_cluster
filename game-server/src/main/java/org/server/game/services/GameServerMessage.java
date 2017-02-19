package org.server.game.services;

import com.google.protobuf.MessageLite;
import com.mmo.server.ServerGateProtocol.MapEventType;

public class GameServerMessage {
	private MapEventType type;
	private MessageLite message;

	public GameServerMessage(MapEventType type, MessageLite message) {
		this.type = type;
		this.message = message;
	}

	public MapEventType getType() {
		return type;
	}

	public MessageLite getMessage() {
		return message;
	}

}
