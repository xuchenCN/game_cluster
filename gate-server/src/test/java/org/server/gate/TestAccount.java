package org.server.gate;

import org.mmo.persistent.UserInfoPersistentBean;
import org.mmo.persistent.UserInfoPersistentService;
import org.mmo.server.common.conf.GameConfiguration;
import org.mmo.server.common.service.AbstractService;
import org.mmo.server.common.utils.Constants;
import org.mmo.server.common.utils.ReflectionUtils;

public class TestAccount {

	public static void main(String[] args) {
		
		UserInfoPersistentService userInfoPersistentService = createUserInfoPersistentService(new GameConfiguration());
		AbstractService service = (AbstractService)userInfoPersistentService;
		service.init(new GameConfiguration());
		service.start();
		
		UserInfoPersistentBean  userBean = userInfoPersistentService.getBeanFactory().createUserInfoPersistentBean();
		userBean.setName("tester1");
		userBean.setPwd("tester1");
		userBean.setUid(10001);
		
		userInfoPersistentService.putUserInfo(userBean);
	}
	
	protected static UserInfoPersistentService createUserInfoPersistentService(GameConfiguration conf) {
		String persistentClassName = conf.get(Constants.USER_INFO_PERSISTENT_SERVICE_CALSS,
				Constants.USER_INFO_PERSISTENT_SERVICE_CALSS_DEFAULT);

		System.out.println("Using persistentClassName: " + persistentClassName);
		try {
			Class<?> serviceClass = Class.forName(persistentClassName);
			if (UserInfoPersistentService.class.isAssignableFrom(serviceClass)) {
				return (UserInfoPersistentService) ReflectionUtils.newInstance(serviceClass);
			} else {
				throw new RuntimeException(
						"Class: " + persistentClassName + " not instance of " + UserInfoPersistentService.class.getCanonicalName());
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Could not instantiate persistentClass: " + persistentClassName, e);
		}
	}

}
