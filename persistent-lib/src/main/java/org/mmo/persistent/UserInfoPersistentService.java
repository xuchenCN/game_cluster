package org.mmo.persistent;

public interface UserInfoPersistentService {

	public void putUserInfo(UserInfoPersistentBean userInfo);

	public UserInfoPersistentBean getUserInfo(UserInfoPersistentBean condition);

	public CharacterAttrInfo getCharacterAttrInfoByUid(Integer uid);
	
	public UserInfoPersistenceBeanFactory getBeanFactory();
	
	public void putCharacterAttrInfo(CharacterAttrInfo characterAttrInfo);
 }
