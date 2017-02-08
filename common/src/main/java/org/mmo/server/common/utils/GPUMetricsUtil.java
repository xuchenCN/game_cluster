package org.mmo.server.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.Path;
import org.mmo.server.common.exception.TensorHuskyRuntimeException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GPUMetricsUtil {

	private static final Log LOG = LogFactory.getLog(GPUMetricsUtil.class);

	private long updateIntervalms = 3000;
	private Map<String, String> gpuMetrics = new HashMap<String, String>();
	private Map<String, String> pidMetrics = new HashMap<String, String>();
	private DocumentBuilder docBuilder;
	private List<String> gpuIds;

	private String[] shCommand = new String[] {
			Shell.getTensorHuskyHome() + File.separator + "bin" + File.separator + "gpu.sh",
			Shell.getTensorHuskyHome() + File.separator + "gpu.xml", "init" };
	private Path gpuInfoPath = new Path(Shell.getTensorHuskyHome() + File.separator + "gpu.xml");

	public GPUMetricsUtil() throws Exception {

		Shell.execCommand(shCommand);
		shCommand[2] = "info";

		// Build XML Parser
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		docBuilderFactory.setValidating(false);
		docBuilderFactory.setNamespaceAware(true);
		docBuilderFactory.setFeature("http://xml.org/sax/features/namespaces", false);
		docBuilderFactory.setFeature("http://xml.org/sax/features/validation", false);
		docBuilderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
		docBuilderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
		docBuilderFactory.setIgnoringComments(true);
		docBuilder = docBuilderFactory.newDocumentBuilder();

		gpuIds = getGpuInfo();

		new LoadNvidiaSmiXml().start();
	}

	public Map<String, String> getGpuMetrics() {
		return gpuMetrics;
	}

	public Map<String, String> getPidMetrics() {
		return pidMetrics;
	}

	class LoadNvidiaSmiXml extends Thread {

		public LoadNvidiaSmiXml() {
			super("LoadNvidiaSmiXml");
			setDaemon(true);
		}

		@Override
		public void run() {
			try {
				while (true) {

					parseXml();

					TimeUnit.MILLISECONDS.sleep(updateIntervalms);
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private List<String> getGpuInfo() throws Exception {

		// Make sure get output of command "nvidia-smi -q -x"
		List<String> gpuIds = new ArrayList<String>();
		InputStream pios = null;
		File gpuInfoFile = new File(gpuInfoPath.toString());
		if (gpuInfoFile != null && gpuInfoFile.exists()) {
			pios = new FileInputStream(gpuInfoFile);
		} else {
			throw new TensorHuskyRuntimeException("Can't to get gpu info");
		}

		try {
			Document doc = docBuilder.parse(pios);
			Element root = doc.getDocumentElement();
			NodeList gpus = root.getElementsByTagName("gpu");
			for (int i = 0; i < gpus.getLength(); i++) {
				Element gpuNode = (Element) gpus.item(i);
				String minor_number = gpuNode.getElementsByTagName("minor_number").item(0).getTextContent();
				gpuIds.add(minor_number);
			}
			return gpuIds;
		} catch (Throwable e) {
			throw new TensorHuskyRuntimeException(e);
		} finally {
			if (gpuInfoFile != null) {
				gpuInfoFile.delete();
			}
			IOUtils.cleanup(LOG, pios);
		}
	}

	public List<String> getGpuIds() {
		return gpuIds;
	}

	private void parseXml() throws Exception {

		InputStream pios = null;

		Shell.execCommand(shCommand);

		File gpuInfoFile = new File(gpuInfoPath.toString());
		if (gpuInfoFile != null && gpuInfoFile.exists()) {
			pios = new FileInputStream(gpuInfoFile);
		} else {
			throw new TensorHuskyRuntimeException("Can't to get gpu info");
		}

		try {

			Document doc = docBuilder.parse(pios);
			Element root = doc.getDocumentElement();
			NodeList gpus = root.getElementsByTagName("gpu");
			for (int i = 0; i < gpus.getLength(); i++) {
				Element gpuNode = (Element) gpus.item(i);
				String minor_number = gpuNode.getElementsByTagName("minor_number").item(0).getTextContent();
				String gpu_util = ((Element) gpuNode.getElementsByTagName("utilization").item(0))
						.getElementsByTagName("gpu_util").item(0).getTextContent();
				String gpu_total_mem = ((Element) gpuNode.getElementsByTagName("fb_memory_usage").item(0))
						.getElementsByTagName("total").item(0).getTextContent();
				String gpu_used_mem = ((Element) gpuNode.getElementsByTagName("fb_memory_usage").item(0))
						.getElementsByTagName("used").item(0).getTextContent();

				gpuMetrics.put("gpu:" + minor_number + ":gpu_util", gpu_util);
				gpuMetrics.put("gpu:" + minor_number + ":gpu_total_mem", gpu_total_mem);
				gpuMetrics.put("gpu:" + minor_number + ":gpu_used_mem", gpu_used_mem);

				Node processes_node = gpuNode.getElementsByTagName("accounted_processes").item(0);
				if (!org.apache.commons.lang3.StringUtils.isEmpty(processes_node.getTextContent())) {
					NodeList accounted_process_info = ((Element) processes_node)
							.getElementsByTagName("accounted_process_info");
					for (int j = 0; j < accounted_process_info.getLength(); j++) {
						Element process_info = (Element) accounted_process_info.item(j);
						String pid = process_info.getElementsByTagName("pid").item(0).getTextContent();
						String p_gpu_util = process_info.getElementsByTagName("gpu_util").item(0).getTextContent();
						String p_max_memory_usage = process_info.getElementsByTagName("max_memory_usage").item(0)
								.getTextContent();

						pidMetrics.put(pid + "@gpu" + minor_number + ":gpu_util", p_gpu_util);
						pidMetrics.put(pid + "@gpu" + minor_number + ":max_memory_usage", p_max_memory_usage);
					}
				}
			}
		} catch (Throwable e) {
			throw new TensorHuskyRuntimeException(e);
		} finally {
			IOUtils.cleanup(LOG, pios);
		}
		// System.out.println(pidMetrics);
		// System.out.println(gpuMetrics);
	}

	public static void main(String[] args) throws Exception {
		GPUMetricsUtil gpum = new GPUMetricsUtil();
		// FileInputStream fis = new FileInputStream(new
		// File("src/test/resources/gpus.xml"));
		gpum.parseXml();
	}
}
