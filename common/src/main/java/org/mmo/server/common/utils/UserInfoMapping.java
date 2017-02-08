package org.mmo.server.common.utils;

import java.io.IOException;

import org.mmo.server.common.conf.GameConfiguration;

public abstract class UserInfoMapping {

	public abstract void loadUserInfo(GameConfiguration conf) throws IOException;

	public abstract UserInfo getUserInfo(String user);

}
