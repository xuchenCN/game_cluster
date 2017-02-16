package org.mmo.persistent;

public interface UserInfoPersistentService {

	public void putUserInfo(UserInfoPersistentBean userInfo);

	public UserInfoPersistentBean getUserInfo(UserInfoPersistentBean condition);
}
