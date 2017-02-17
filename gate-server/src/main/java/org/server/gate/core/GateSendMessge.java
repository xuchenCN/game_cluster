package org.server.gate.core;

import java.util.List;

import com.google.protobuf.MessageLite;
import com.mmo.server.ServerGateProtocol.MapEventType;

public class GateSendMessge {
	private int mapId;
	private List<Integer> effects;
	private MessageLite message;
	private MapEventType type;

	public GateSendMessge(int mapId, List<Integer> effects, MessageLite message, MapEventType type) {
		super();
		this.mapId = mapId;
		this.effects = effects;
		this.message = message;
		this.type = type;
	}

	public int getMapId() {
		return mapId;
	}

	public List<Integer> getEffects() {
		return effects;
	}

	public MessageLite getMessage() {
		return message;
	}

	public MapEventType getType() {
		return type;
	}

}
