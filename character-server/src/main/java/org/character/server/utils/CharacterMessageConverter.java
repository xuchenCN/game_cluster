package org.character.server.utils;

import org.mmo.persistent.CharacterAttrInfo;

import com.mmo.server.CommonProtocol.EnergyInfo;
import com.mmo.server.CommonProtocol.IdentifyInfo;
import com.mmo.server.CommonProtocol.Position;
import com.mmo.server.CommonProtocol.SurvivalInfo;

public class CharacterMessageConverter {
	public static void toCharacterAttrInfo(com.mmo.server.CommonProtocol.Character character,CharacterAttrInfo characterAttrInfo) {
		try {
			Integer uid = character.getIdentify().getID();
			if (character != null && uid > 0) {
//				CharacterAttrInfo characterAttrInfo = characterContext.getUserInfoPersistentService().getBeanFactory()
//						.createCharacterAttrInfo();
				characterAttrInfo.setUid(uid);
				characterAttrInfo.setName(character.getIdentify().getName());
				characterAttrInfo.setMapId(character.getMapId());

				characterAttrInfo.setPosX(character.getPosition().getPosX());
				characterAttrInfo.setPosY(character.getPosition().getPosY());
				characterAttrInfo.setPosZ(character.getPosition().getPosZ());

				characterAttrInfo.setHealth(character.getSurvivalInfo().getHealth());
				characterAttrInfo.setHunger(character.getSurvivalInfo().getHunger());
				characterAttrInfo.setSpirit(character.getSurvivalInfo().getSpirit());
				characterAttrInfo.setComfort(character.getSurvivalInfo().getComfort());

				characterAttrInfo.setStrength(character.getEnergyInfo().getStrength());
				characterAttrInfo.setEndurance(character.getEnergyInfo().getEndurance());
				characterAttrInfo.setAgility(character.getEnergyInfo().getAgility());
				characterAttrInfo.setDefence(character.getEnergyInfo().getDefence());
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public static com.mmo.server.CommonProtocol.Character toProtoCharacter(CharacterAttrInfo characterAttrInfo) {
		IdentifyInfo identifyInfo = IdentifyInfo.newBuilder().setID(characterAttrInfo.getUid())
				.setName(characterAttrInfo.getName()).build();
		Position position = Position.newBuilder().setPosX(characterAttrInfo.getPosX())
				.setPosY(characterAttrInfo.getPosY()).setPosZ(characterAttrInfo.getPosZ()).build();
		SurvivalInfo survivalInfo = SurvivalInfo.newBuilder().setHealth(characterAttrInfo.getHealth())
				.setHunger(characterAttrInfo.getHunger()).setSpirit(characterAttrInfo.getSpirit())
				.setComfort(characterAttrInfo.getComfort()).build();
		EnergyInfo energyInfo = EnergyInfo.newBuilder().setStrength(characterAttrInfo.getStrength())
				.setEndurance(characterAttrInfo.getEndurance()).setAgility(characterAttrInfo.getAgility())
				.setDefence(characterAttrInfo.getDefence()).build();

		com.mmo.server.CommonProtocol.Character character = com.mmo.server.CommonProtocol.Character.newBuilder()
				.setIdentify(identifyInfo).setPosition(position).setSurvivalInfo(survivalInfo)
				.setMapId(characterAttrInfo.getMapId()).setEnergyInfo(energyInfo).build();

		return character;
	}
}
