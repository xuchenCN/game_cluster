package org.mmo.persistent.redis;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.hadoop.io.IOUtils;
import org.mmo.persistent.CharacterAttrInfo;
import org.mmo.persistent.UserInfoPersistenceBeanFactory;
import org.mmo.persistent.UserInfoPersistentBean;
import org.mmo.persistent.UserInfoPersistentService;
import org.mmo.server.common.conf.GameConfiguration;
import org.mmo.server.common.service.AbstractService;
import org.mmo.server.common.utils.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class UserInfoPersistentServiceImpl extends AbstractService
		implements UserInfoPersistentService, UserInfoPersistenceBeanFactory {
	private static final Log LOG = LogFactory.getLog(UserInfoPersistentServiceImpl.class);

	private JedisPool pool;

	public UserInfoPersistentServiceImpl() {
		super("UserInfoPersistentServiceImpl");
	}

	@Override
	protected void serviceInit(GameConfiguration conf) throws Exception {
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMaxIdle(conf.getInt(KeyConstants.REDIS_POOL_MAXIDLE, KeyConstants.REDIS_POOL_MAXIDLE_DEFAULT));
		poolConfig.setMaxTotal(conf.getInt(KeyConstants.REDIS_POOL_MAXTOTAL, KeyConstants.REDIS_POOL_MAXTOTAL_DEFAULT));
		poolConfig.setMinIdle(conf.getInt(KeyConstants.REDIS_POOL_MINIDLE, KeyConstants.REDIS_POOL_MINIDLE_DEFAULT));

		String host = conf.get(KeyConstants.REDIS_HOST, "localhost");
		int port = conf.getInt(KeyConstants.REDIS_PORT, 7379);
		pool = new JedisPool(poolConfig, host, port);

		testConnection();

		LOG.info("Redis pool init : " + host + ":" + port);

		super.serviceInit(conf);
	}

	@Override
	protected void serviceStart() throws Exception {
		// TODO Auto-generated method stub
		super.serviceStart();
	}

	@Override
	protected void serviceStop() throws Exception {
		super.serviceStop();
		IOUtils.cleanup(LOG, pool);
	}

	@Override
	public void putUserInfo(UserInfoPersistentBean userInfo) {
		try (Jedis conn = pool.getResource()) {
			UserInfoRedisImpl userInfoBean = (UserInfoRedisImpl) userInfo;
			conn.hmset(userInfoBean.key(), userInfoBean.redisMap());
			conn.set(KeyConstants.USER_UID_NAME_PREFIX + userInfo.getUid(), userInfo.getName());
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	@Override
	public UserInfoPersistentBean getUserInfo(UserInfoPersistentBean condition) {
		try (Jedis conn = pool.getResource()) {
			UserInfoRedisImpl conditionBean = (UserInfoRedisImpl) condition;
			if (StringUtils.isEmpty(conditionBean.getName()) && conditionBean.getUid() > 0) {

				conditionBean.setName(conn.get(conditionBean.getUid() + ""));
			}
			if (!StringUtils.isEmpty(conditionBean.getName()) && !"null".equalsIgnoreCase(conditionBean.getName())) {
				Map<String, String> redisMap = conn.hgetAll(conditionBean.key());
				if (redisMap != null && !redisMap.isEmpty()) {
					UserInfoRedisImpl userInfo = new UserInfoRedisImpl();
					userInfo.redisBean(redisMap);
					return userInfo;
				}
			}
		} catch (Exception e) {
			LOG.error(e);
		}
		return null;
	}

	public void testConnection() throws Exception {
		try (Jedis conn = pool.getResource()) {

			conn.del("game_server_test_conn_str_key");

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public UserInfoPersistentBean createUserInfoPersistentBean() {
		return new UserInfoRedisImpl();
	}

	@Override
	public CharacterAttrInfo createCharacterAttrInfo() {
		return new CharacterAttrInfoImpl();
	}

	@Override
	public void putCharacterAttrInfo(CharacterAttrInfo characterAttrInfo) {
		if (characterAttrInfo != null && characterAttrInfo.getUid() > 0) {

			CharacterAttrInfoImpl characterAttrInfoImpl = (CharacterAttrInfoImpl) characterAttrInfo;
			try (Jedis conn = pool.getResource()) {
				conn.hmset(characterAttrInfoImpl.key(), characterAttrInfoImpl.redisMap());
			} catch (Exception e) {
				LOG.error(e);
			}
		}

	}

	@Override
	public CharacterAttrInfo getCharacterAttrInfoByUid(Integer uid) {
		try (Jedis conn = pool.getResource()) {
			CharacterAttrInfoImpl characterAttrInfoImpl = new CharacterAttrInfoImpl();
			characterAttrInfoImpl.setUid(uid);
			Map<String, String> redisMap = conn.hgetAll(characterAttrInfoImpl.key());
			if (redisMap != null && !redisMap.isEmpty()) {
				characterAttrInfoImpl.redisBean(redisMap);
				return characterAttrInfoImpl;
			}
		} catch (Exception e) {
			LOG.error(e);
		}
		return null;
	}

	@Override
	public UserInfoPersistenceBeanFactory getBeanFactory() {
		return this;
	}

}
