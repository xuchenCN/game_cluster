package org.character.server;

import org.character.server.utils.CharacterMessageConverter;
import org.mmo.persistent.CharacterAttrInfo;
import org.mmo.persistent.UserInfoPersistentService;
import org.mmo.server.common.conf.GameConfiguration;
import org.mmo.server.common.service.AbstractService;
import org.mmo.server.common.utils.Constants;
import org.mmo.server.common.utils.ReflectionUtils;

import com.mmo.server.CommonProtocol;
import com.mmo.server.CommonProtocol.IdentifyInfo;
import com.mmo.server.CommonProtocol.Position;

public class TestCreateCharacter {

	public static void main(String[] args) {
		GameConfiguration conf = new GameConfiguration();
		UserInfoPersistentService userInfoPersistentService = createUserInfoPersistentService(conf);
		AbstractService service = (AbstractService)userInfoPersistentService;
		service.init(new GameConfiguration());
		service.start();
		
		IdentifyInfo identifyInfo = IdentifyInfo.newBuilder().setID(10002).setName("test_character1").build();
		Position position = Position.newBuilder().setPosX(255).setPosY(0).setPosZ(255).build();

		CommonProtocol.Character character = CommonProtocol.Character.newBuilder().setIdentify(identifyInfo)
				.setPosition(position).setMapId(1).build();

		CharacterAttrInfo characterAttrInfo = userInfoPersistentService.getBeanFactory().createCharacterAttrInfo();
		CharacterMessageConverter.toCharacterAttrInfo(character, characterAttrInfo);

		userInfoPersistentService.putCharacterAttrInfo(characterAttrInfo);

	}

	private static UserInfoPersistentService createUserInfoPersistentService(GameConfiguration conf) {
		String persistentClassName = conf.get(Constants.USER_INFO_PERSISTENT_SERVICE_CALSS,
				Constants.USER_INFO_PERSISTENT_SERVICE_CALSS_DEFAULT);

		try {
			Class<?> serviceClass = Class.forName(persistentClassName);
			if (UserInfoPersistentService.class.isAssignableFrom(serviceClass)) {
				return (UserInfoPersistentService) ReflectionUtils.newInstance(serviceClass);
			} else {
				throw new RuntimeException("Class: " + persistentClassName + " not instance of "
						+ UserInfoPersistentService.class.getCanonicalName());
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Could not instantiate persistentClass: " + persistentClassName, e);
		}
	}

}
