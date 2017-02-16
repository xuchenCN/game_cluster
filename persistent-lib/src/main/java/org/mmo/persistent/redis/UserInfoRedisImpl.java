package org.mmo.persistent.redis;

import org.mmo.persistent.RedisBean;
import org.mmo.persistent.UserInfoPersistentBean;


public class UserInfoRedisImpl extends RedisBean implements UserInfoPersistentBean {

	private String name;
	private String pwd;
	private int uid;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}
	
	@Override
	public String key() {
		return KeyConstants.USER_INFO_PREFIX + this.name;
	}
	
}
