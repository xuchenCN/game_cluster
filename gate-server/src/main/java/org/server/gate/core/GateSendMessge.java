package org.server.gate.core;

import com.google.protobuf.MessageLite;
import com.mmo.server.ServerGateProtocol.MapEventType;

public class GateSendMessge {
	int mapId;
	MessageLite message;
	MapEventType type;

	public GateSendMessge(int mapId, MessageLite message, MapEventType type) {
		this.mapId = mapId;
		this.message = message;
		this.type = type;
	}

}
