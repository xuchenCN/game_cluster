package org.mmo.server.common.conf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GameConfiguration {

	private static final Log LOG = LogFactory.getLog(GameConfiguration.class);

	/**
	 * File to set default properties
	 */
	public static final String DEFAULT_PROPERTIES = "game-default.properties";
	/**
	 * File to set customized properties
	 */
	public static final String SITE_PROPERTIES = "game-site.properties";

	private final Properties properties = new Properties();

	public GameConfiguration() {
		this(true);
	}

	public GameConfiguration(boolean loadDefault) {

		Properties defaultProps = new Properties();

		if (loadDefault) {

			InputStream defaultInputStream = GameConfiguration.class.getClassLoader()
					.getResourceAsStream(DEFAULT_PROPERTIES);
			if (defaultInputStream == null) {
				throw new RuntimeException("The default Game properties file does not exist.");
			}
			try {
				defaultProps.load(defaultInputStream);
			} catch (IOException e) {
				throw new RuntimeException("Unable to load default TensorHusky properties file.", e);
			}
		}

		// Load site specific properties file
		Properties siteProps = new Properties();
		InputStream siteInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(SITE_PROPERTIES);
		if (siteInputStream != null) {
			try {
				siteProps.load(siteInputStream);
			} catch (IOException e) {
				LOG.warn("Unable to load site TensorHusky configuration file.", e);
			}
		}

		// Load system properties
		Properties systemProps = new Properties();
		systemProps.putAll(System.getProperties());

		// Now lets combine
		properties.putAll(defaultProps);
		properties.putAll(siteProps);
		properties.putAll(systemProps);
	}

	public GameConfiguration(GameConfiguration config) {
		if (config != null) {
			properties.putAll(config.getInternalProperties());
		}
	}

	/**
	 * @return the deep copy of the internal <code>Properties</code> of this CortaraConfiguration instance.
	 */
	Properties getInternalProperties() {
		return (java.util.Properties) SerializationUtils.clone(properties);
	}

	public String get(final String key, final String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}

	public boolean getBoolean(final String key, final boolean defaultValue) {
		String v = properties.getProperty(key);
		try {
			return Boolean.valueOf(v);
		} catch (Exception e) {
			LOG.warn("Configuration cannot evaluate key " + key + " as boolean.");
		}
		return defaultValue;
	}

	public int getInt(final String key, final int defaultValue) {
		String v = properties.getProperty(key);
		try {
			return Integer.valueOf(v);
		} catch (Exception e) {
			LOG.warn("Configuration cannot evaluate key " + key + " as integer.");
		}
		return defaultValue;
	}
	
	public long getLong(final String key, final long defaultValue) {
		String v = properties.getProperty(key);
		try {
			return Long.valueOf(v);
		} catch (Exception e) {
			LOG.warn("Configuration cannot evaluate key " + key + " as long.");
		}
		return defaultValue;
	}

}
