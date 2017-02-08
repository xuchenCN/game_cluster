package org.mmo.server.common.utils;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mmo.server.common.conf.GameConfiguration;

import com.google.common.base.Throwables;

public class HuskyNetUtils {

	private static final Log LOG = LogFactory.getLog(HuskyNetUtils.class);

	private static String localHost;
	private static String localIP;

	/**
	 * Gets the port for the underline socket. This function calls
	 * {@link #getSocket(org.apache.thrift.transport.TServerSocket)}, so reflection will be
	 * used to get the port.
	 *
	 * @see #getSocket(org.apache.thrift.transport.TServerSocket)

	public static int getPort(TServerSocket thriftSocket) {
	return getSocket(thriftSocket).getLocalPort();
	}
	 */
	/**
	 * Extracts the port from the thrift socket. As of thrift 0.9, the internal socket used is not
	 * exposed in the API, so this function will use reflection to get access to it.
	 *
	 * @throws java.lang.RuntimeException if reflection calls fail

	public static ServerSocket getSocket(final TServerSocket thriftSocket) {
	try {
	Field field = TServerSocket.class.getDeclaredField("serverSocket_");
	field.setAccessible(true);
	return (ServerSocket) field.get(thriftSocket);
	} catch (NoSuchFieldException e) {
	throw Throwables.propagate(e);
	} catch (IllegalAccessException e) {
	throw Throwables.propagate(e);
	}
	}
	 */
	/**
	 * Gets a local host name for the host this JVM is running on
	 *
	 * @param conf Tachyon configuration used to look up the host resolution timeout
	 * @return the local host name, which is not based on a loopback ip address.
	 */
	public static String getLocalHostName(GameConfiguration conf) {
		if (localHost != null) {
			return localHost;
		}
		int hostResolutionTimeout = conf
				.getInt(Constants.HOST_RESOLUTION_TIMEOUT_MS, Constants.HOST_RESOLUTION_TIMEOUT_MS_DEFAULT);
		return HuskyNetUtils.getLocalHostName(hostResolutionTimeout);
	}

	/**
	 * Gets a local host name for the host this JVM is running on
	 *
	 * @param timeout Timeout in milliseconds to use for checking that a possible local
	 *                host is reachable
	 * @return the local host name, which is not based on a loopback ip address.
	 */
	public static String getLocalHostName(int timeout) {
		if (localHost != null) {
			return localHost;
		}

		try {
			localHost = InetAddress.getByName(getLocalIpAddress(timeout)).getCanonicalHostName();
			return localHost;
		} catch (UnknownHostException e) {
			LOG.error(e.getMessage(), e);
			throw Throwables.propagate(e);
		}
	}

	/**
	 * Gets a local IP address for the host this JVM is running on
	 *
	 * @param timeout Timeout in milliseconds to use for checking that a possible local IP is
	 *                reachable
	 * @return the local ip address, which is not a loopback address and is reachable
	 */
	public static String getLocalIpAddress(GameConfiguration conf) {
		if (localIP != null) {
			return localIP;
		}
		int hostResolutionTimeout = conf
				.getInt(Constants.HOST_RESOLUTION_TIMEOUT_MS, Constants.HOST_RESOLUTION_TIMEOUT_MS_DEFAULT);
		return HuskyNetUtils.getLocalIpAddress(hostResolutionTimeout);
	}

	/**
	 * Gets a local IP address for the host this JVM is running on
	 *
	 * @param timeout Timeout in milliseconds to use for checking that a possible local IP is
	 *                reachable
	 * @return the local ip address, which is not a loopback address and is reachable
	 */
	public static String getLocalIpAddress(int timeout) {
		if (localIP != null) {
			return localIP;
		}

		try {
			InetAddress address = InetAddress.getLocalHost();

			// Make sure that the address is actually reachable since in some network configurations
			// it is possible for the InetAddress.getLocalHost() call to return a non-reachable
			// address e.g. a broadcast address
			if (address.isLoopbackAddress() || !address.isReachable(timeout)) {
				Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
				while (networkInterfaces.hasMoreElements()) {
					NetworkInterface ni = networkInterfaces.nextElement();
					Enumeration<InetAddress> addresses = ni.getInetAddresses();
					while (addresses.hasMoreElements()) {
						address = addresses.nextElement();

						// Address must not be link local or loopback. And it must be reachable
						if (!address.isLinkLocalAddress() && !address.isLoopbackAddress() && (address instanceof Inet4Address)
								&& address.isReachable(timeout)) {
							localIP = address.getHostAddress();
							return localIP;
						}
					}
				}

				LOG.warn("Your hostname, " + InetAddress.getLocalHost().getHostName() + " resolves to"
						+ " a loopback/non-reachable address: " + address.getHostAddress()
						+ ", but we couldn't find any external IP address!");
			}

			localIP = address.getHostAddress();
			return localIP;
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			throw Throwables.propagate(e);
		}
	}

}
