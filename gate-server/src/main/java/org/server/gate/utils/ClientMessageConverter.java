package org.server.gate.utils;

import com.mmo.server.CommonProtocol.ModInfo;
import com.mmo.server.CommonProtocol.Position;
import com.mmo.server.ServerClientProtocol.ClientModInfo;
import com.mmo.server.ServerClientProtocol.ClientPosition;

public class ClientMessageConverter {

	public static ClientPosition toClientPosition(Position pos) {
		return ClientPosition.newBuilder().setPosX(pos.getPosX()).setPosY(pos.getPosY()).setPosZ(pos.getPosZ()).build();
	}

	public static ClientModInfo toClientModInfo(ModInfo modInfo) {
		return ClientModInfo.newBuilder().setModId(modInfo.getModId()).setMotion(modInfo.getMotion())
				.setOrientation(modInfo.getOrientation()).build();
	}
}
