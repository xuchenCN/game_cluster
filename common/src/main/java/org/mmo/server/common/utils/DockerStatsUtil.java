package org.mmo.server.common.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DockerStatsUtil {

	private static final Log LOG = LogFactory.getLog(DockerStatsUtil.class);

	public static Map<String, String> getContainerStats(final String dockerPath, final String containerId) {

		Map<String, String> metrics = new HashMap<String, String>();

		String[] command = new String[] { "/bin/sh", "-c", dockerPath + " stats --no-stream " + containerId
				+ " | awk 'NR==2 {print $1,$2,$3$4,$6$7,$8,$9$10,$12$13,$14$15,$17$18,$19}'" };

		ProcessBuilder pb = new ProcessBuilder(command);

		BufferedReader is = null;

		try {
			Process p = pb.start();
			int code = p.waitFor();
			if (code == 0) {
				is = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String statsLine = is.readLine();

				if (!StringUtils.isEmpty(statsLine)) {
					String[] stats = statsLine.split(" ");
					if (stats != null && stats.length >= 9)

						metrics.put("cpu", stats[1]);
					metrics.put("mem_used", stats[2]);
					metrics.put("mem_total", stats[3]);
					metrics.put("mem_percent", stats[4]);
					metrics.put("net_in", stats[5]);
					metrics.put("net_out", stats[6]);
					metrics.put("block_in", stats[7]);
					metrics.put("block_out", stats[8]);
					metrics.put("pids", stats[9]);
				}
			}

		} catch (Exception e) {
			//			LOG.error("docker stats error ",e);
		} finally {
			IOUtils.cleanup(LOG, is);
		}

		return metrics;

	}
}
