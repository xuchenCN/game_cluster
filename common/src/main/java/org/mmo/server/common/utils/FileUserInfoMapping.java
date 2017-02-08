package org.mmo.server.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.util.internal.StringUtil;
import org.mmo.server.common.conf.GameConfiguration;

public class FileUserInfoMapping extends UserInfoMapping {

	private static final Log LOG = LogFactory.getLog(FileUserInfoMapping.class);

	private String filePath;

	private Map<String, UserInfo> userMapping = new HashMap<String, UserInfo>();

	public FileUserInfoMapping() {
		super();
	}

	@Override
	public void loadUserInfo(GameConfiguration conf) throws IOException {
		this.filePath = conf.get("tensorhusky.engine-server.user-info-mapping.file", "user-config.properties");
		File file = new File(filePath);
		try (BufferedReader fisr = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
			String line = null;
			userMapping.clear();
			while ((line = fisr.readLine()) != null) {
				if (line != null && !line.startsWith("#")) {
					// String[] userData = StringUtil.split(line,
					// StringUtils.EQUALS_CHAR);
					// if (userData != null && userData.length == 2) {
					String[] userValue = StringUtil.split(line, StringUtils.COLON_CHAR);
					if (userValue != null && userValue.length == 4) {
						long timeInMs = Long.valueOf(userValue[3]) * 60 * 60 * 1000;
						UserInfo userInfo = new UserInfo(userValue[0], Integer.valueOf(userValue[1]),
								Integer.valueOf(userValue[2]), timeInMs);
						userMapping.put(userInfo.getUser(), userInfo);
						LOG.info("Load User : " + userInfo);
					}
					// }
				}
			}
		} catch (Exception e) {
			LOG.error(e);
			throw new IOException(e);
		}
	}

	@Override
	public UserInfo getUserInfo(String user) {
		// UserInfo userInfo = userMapping.get(user);
		// if (userInfo == null) {
		// userInfo = new UserInfo(user, 0, 1, Long.MAX_VALUE);
		// userMapping.put(user, userInfo);
		// }
		// return userInfo;
		return userMapping.get(user);
	}

	public static void main(String[] args) throws Exception {
		FileUserInfoMapping fm = new FileUserInfoMapping();
		fm.loadUserInfo(new GameConfiguration());
	}

}
