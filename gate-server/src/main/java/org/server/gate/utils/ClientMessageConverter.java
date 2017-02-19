package org.server.gate.utils;

import com.mmo.server.CommonProtocol.EnergyInfo;
import com.mmo.server.CommonProtocol.IdentifyInfo;
import com.mmo.server.CommonProtocol.Item;
import com.mmo.server.CommonProtocol.ModInfo;
import com.mmo.server.CommonProtocol.MotionInfo;
import com.mmo.server.CommonProtocol.Position;
import com.mmo.server.CommonProtocol.SurvivalInfo;
import com.mmo.server.ServerClientProtocol.ClientCharacter;
import com.mmo.server.ServerClientProtocol.ClientEnergyInfo;
import com.mmo.server.ServerClientProtocol.ClientIdentifyInfo;
import com.mmo.server.ServerClientProtocol.ClientItem;
import com.mmo.server.ServerClientProtocol.ClientItemType;
import com.mmo.server.ServerClientProtocol.ClientModInfo;
import com.mmo.server.ServerClientProtocol.ClientMotionInfo;
import com.mmo.server.ServerClientProtocol.ClientPosition;
import com.mmo.server.ServerClientProtocol.ClientSurvivalInfo;

public class ClientMessageConverter {

	public static ClientPosition toClientPosition(Position pos) {
		return ClientPosition.newBuilder().setPosX(pos.getPosX()).setPosY(pos.getPosY()).setPosZ(pos.getPosZ()).build();
	}

	public static ClientModInfo toClientModInfo(ModInfo modInfo) {
		return ClientModInfo.newBuilder().setModId(modInfo.getModId()).setMotion(modInfo.getMotion())
				.setOrientation(modInfo.getOrientation()).build();
	}

	public static ClientMotionInfo toClientMotionInfo(MotionInfo motionInfo) {
		return ClientMotionInfo.newBuilder().setMotion(motionInfo.getMotion()).setEfficacy(motionInfo.getEfficacy())
				.setSpeed(motionInfo.getSpeed()).build();
	}

	public static ClientIdentifyInfo toClientIdentifyInfo(IdentifyInfo identifyInfo) {
		return ClientIdentifyInfo.newBuilder().setID(identifyInfo.getID()).setName(identifyInfo.getName())
				.setType(ClientItemType.valueOf(identifyInfo.getTypeValue())).build();
	}

	public static ClientSurvivalInfo toClientSurvivalInfo(SurvivalInfo survivalInfo) {
		return ClientSurvivalInfo.newBuilder().setHealth(survivalInfo.getHealth()).setHunger(survivalInfo.getHunger())
				.setSpirit(survivalInfo.getSpirit()).setComfort(survivalInfo.getComfort()).build();
	}

	public static ClientItem toClientItem(Item item) {
		return ClientItem.newBuilder().setIdentify(toClientIdentifyInfo(item.getIdentify()))
				.setPosition(toClientPosition(item.getPosition())).setModInfo(toClientModInfo(item.getModInfo()))
				.setSurvivalInfo(toClientSurvivalInfo(item.getSurvivalInfo())).build();
	}

	public static ClientCharacter toClientCharacter(com.mmo.server.CommonProtocol.Character character) {
		return ClientCharacter.newBuilder().setIdentify(toClientIdentifyInfo(character.getIdentify()))
				.setPosition(toClientPosition(character.getPosition()))
				.setModInfo(toClientModInfo(character.getModInfo()))
				.setSurvivalInfo(toClientSurvivalInfo(character.getSurvivalInfo()))
				.setEnergyInfo(toClientEnergyInfo(character.getEnergyInfo())).setMapId(character.getMapId()).build();
	}

	public static ClientEnergyInfo toClientEnergyInfo(EnergyInfo energyInfo) {
		return ClientEnergyInfo.newBuilder().setStrength(energyInfo.getStrength())
				.setEndurance(energyInfo.getEndurance()).setAgility(energyInfo.getAgility())
				.setDefence(energyInfo.getDefence()).build();
	}
}
