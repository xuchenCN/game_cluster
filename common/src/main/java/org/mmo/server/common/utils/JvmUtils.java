package org.mmo.server.common.utils;

import static java.lang.Thread.State.BLOCKED;
import static java.lang.Thread.State.NEW;
import static java.lang.Thread.State.RUNNABLE;
import static java.lang.Thread.State.TIMED_WAITING;
import static java.lang.Thread.State.WAITING;

import java.lang.Thread.State;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Singleton class which reports Java Virtual Machine metrics to the metrics
 * API. Any application can create an instance of this class in order to emit
 * Java VM metrics.
 */
public class JvmUtils {

	public static final float M = 1024 * 1024;

	public static final long K = 1024;

	private static long lastSystemTime = 0;

	private static long lastProcessCpuTime = 0;

	private static long cpuUsage = 0;

	private static double cpuLoadAvg = 0;

	private static com.sun.management.OperatingSystemMXBean osMBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory
			.getOperatingSystemMXBean();

	private static int availableProcessors = osMBean.getAvailableProcessors();

	private static MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
	private static MemoryUsage memNonHeap = memoryMXBean.getNonHeapMemoryUsage();
	private static MemoryUsage memHeap = memoryMXBean.getHeapMemoryUsage();
	private static Runtime runtime = Runtime.getRuntime();

	public static final Map<java.lang.Thread.State, Integer> threadCounterMap = new HashMap<State, Integer>();

	public static void doUpdateCpuUsage() {

		long systemTime = System.nanoTime();
		long processCpuTime = 0;

		processCpuTime = osMBean.getProcessCpuTime();
		cpuLoadAvg = osMBean.getSystemLoadAverage();

		cpuUsage = Math.round(((double) (processCpuTime - lastProcessCpuTime) / (systemTime - lastSystemTime)) * 100);

		lastSystemTime = systemTime;
		lastProcessCpuTime = processCpuTime;

		baselineCpuTime();//?

	}

	public static long getCpuUsage() {
		return cpuUsage;
	}

	public static double getCpuLoadAvg() {
		return cpuLoadAvg;
	}

	public static long getNonHeapUsed() {
		return memNonHeap.getUsed();
	}

	public static long getNonHeapCommitted() {
		return memNonHeap.getCommitted();
	}

	public static long getHeapCommitted() {
		return memHeap.getCommitted();
	}

	public static long getHeapUsed() {
		return memHeap.getUsed();
	}

	public static long getMaxHeap() {
		return runtime.maxMemory();
	}

	public static long getGCCount() {
		List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();//old and Eden?
		long count = 0;
		for (GarbageCollectorMXBean gcBean : gcBeans) {
			count += gcBean.getCollectionCount();
		}

		return count;
	}

	public static long getGCTime() {
		List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
		long timeMillis = 0;
		for (GarbageCollectorMXBean gcBean : gcBeans) {
			timeMillis += gcBean.getCollectionTime();
		}
		return timeMillis;
	}

	public static void doMemoryUpdates() {
		memoryMXBean = ManagementFactory.getMemoryMXBean();
		memNonHeap = memoryMXBean.getNonHeapMemoryUsage();
		memHeap = memoryMXBean.getHeapMemoryUsage();
	}

	public static void doThreadUpdates() {
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		long threadIds[] = threadMXBean.getAllThreadIds();
		ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(threadIds, 0);

		int threadsNew = 0;
		int threadsRunnable = 0;
		int threadsBlocked = 0;
		int threadsWaiting = 0;
		int threadsTimedWaiting = 0;

		for (ThreadInfo threadInfo : threadInfos) {
			// threadInfo is null if the thread is not alive or doesn't exist
			if (threadInfo == null)
				continue;
			Thread.State state = threadInfo.getThreadState();
			if (state == NEW) {
				threadsNew++;
			} else if (state == RUNNABLE) {
				threadsRunnable++;
			} else if (state == BLOCKED) {
				threadsBlocked++;
			} else if (state == WAITING) {
				threadsWaiting++;
			} else if (state == TIMED_WAITING) {
				threadsTimedWaiting++;
			}
		}

		threadCounterMap.put(NEW, threadsNew);
		threadCounterMap.put(RUNNABLE, threadsRunnable);
		threadCounterMap.put(BLOCKED, threadsBlocked);
		threadCounterMap.put(WAITING, threadsWaiting);
		threadCounterMap.put(TIMED_WAITING, threadsTimedWaiting);
		// threadCounterMap.put(TERMINATED, threadsTerminated);

	}

	private static void baselineCpuTime() {

		lastSystemTime = System.nanoTime();

		lastProcessCpuTime = osMBean.getProcessCpuTime();
	}

}
