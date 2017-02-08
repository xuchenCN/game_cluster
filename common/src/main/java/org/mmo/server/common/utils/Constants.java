package org.mmo.server.common.utils;

public class Constants {
	public static final String HOST_RESOLUTION_TIMEOUT_MS = "tensorhusky.host.resolution.timeout.ms";
	public static final int HOST_RESOLUTION_TIMEOUT_MS_DEFAULT = 5000;

	public static final String ENGINE_SERVER_HOST = "tensorhusky.engine-server.host";
	public static final String ENGINE_SERVER_HOST_DEFAULT = "localhost";

	public static final String ENGINE_SERVER_SCHEDULER_CALSS = "tensorhusky.engine-server.scheduler.class";
	public static final String ENGINE_SERVER_SCHEDULER_CALSS_DEFAULT = "com.lenovo.tensorhusky.engine.scheduler.fifo.FIFOSimplifySchduler";

	public static final String ENGINE_SERVER_SCHEDULER_POLICY = "tensorhusky.engine-server.scheduler.policy";
	public static final String ENGINE_SERVER_SCHEDULER_POLICY_DEFAULT = "weight";

	public static final String ENGINE_SERVER_USER_INFO_MAPPING_CALSS = "tensorhusky.engine-server.user-info-mapping.class";
	public static final String ENGINE_SERVER_USER_INFO_MAPPING_CALSS_DEFAULT = "com.lenovo.tensorhusky.common.utils.FileUserInfoMapping";

	public static final String ENGINE_SERVER_PERSISTENT_SERVICE_CALSS = "tensorhusky.engine-server.persistent.service.class";
	public static final String AGENT_SERVER_PERSISTENT_SERVICE_CALSS = "tensorhusky.agent-server.persistent.service.class";
	public static final String ENGINE_SERVER_PERSISTENT_SERVICE_CALSS_DEFAULT = "com.lenovo.tensorhusky.persistent.redis.EnginePersistentServiceRedisImpl";
	public static final String AGENT_SERVER_PERSISTENT_SERVICE_CALSS_DEFAULT = "com.lenovo.tensorhusky.persistent.redis.AgentPersistentServiceRedisImpl";
	
	@Deprecated
	public static final String ENGINE_SERVER_SCHEDULER_ACCEPT_MAXIMUM = "tensorhusky.engine-server.scheduler.accept.queue.maximum";
	@Deprecated
	public static final int ENGINE_SERVER_SCHEDULER_ACCEPT_MAXIMUM_DEFAULT = 2 << 5;

	public static final String ENGINE_SERVER_JOB_ATTEMPT_MAXIMUM = "tensorhusky.engine-server.scheduler.job.attempt.maximum";
	public static final int ENGINE_SERVER_JOB_ATTEMPT_MAXIMUM_DEFAULT = 3;

	public static final String ENGINE_SERVER_SCHEDULER_FIFO_ASSIGN_INTERVAL = "tensorhusky.engine-server.scheduler.task.attempt.maximum.ms";
	public static final int ENGINE_SERVER_SCHEDULER_FIFO_ASSIGN_INTERVAL_DEFAULT = 3 * 1000;

	public static final String AGENT_SERVER_HOST = "tensorhusky.agent-server.host";
	public static final String AGENT_SERVER_HOST_DEFAULT = "localhost";

	public static final String ENGINE_SERVER_LISTEN_PORT = "tensorhusky.engine-server.port";
	public static final int ENGINE_SERVER_LISTEN_PORT_DEFAULT = 19999;

	public static final String AGENT_SERVER_TASK_BASE_PORT = "tensorhusky.agent-server.task.base-port";
	public static final int AGENT_SERVER_TASK_BASE_PORT_DEFAULT = 39999;

	public static final String AGENT_SERVER_TASK_PORT_STEP = "tensorhusky.agent-server.task.base-port.step";
	public static final int AGENT_SERVER_TASK_PORT_STEP_DEFAULT = 100;

	public static final String AGENT_SERVER_LISTEN_PORT = "tensorhusky.agent-server.port";
	public static final int AGENT_SERVER_LISTEN_PORT_DEFAULT = 29999;

	public static final String AGENT_SERVER_RESOURCE_CPU = "tensorhusky.agent-server.resource.cpu";
	public static final int AGENT_SERVER_RESOURCE_CPU_DEFAULT = 1;
	
	@Deprecated
	public static final String AGENT_SERVER_RESOURCE_GPU = "tensorhusky.agent-server.resource.gpu";
	@Deprecated
	public static final int AGENT_SERVER_RESOURCE_GPU_DEFAULT = 1;

	public static final String AGENT_SERVER_RESOURCE_GPU_FILTER = "tensorhusky.agent-server.resource.gpu.filter.id";

	public static final String AGENT_SERVER_RESOURCE_MEM = "tensorhusky.agent-server.resource.mem";
	public static final int AGENT_SERVER_RESOURCE_MEM_DEFAULT = 1024;

	public static final String AGENT_SERVER_HEARTBEAT_INTERVAL = "tensorhusky.agent-server.heartbeat.interval.ms";
	public static final int AGENT_SERVER_HEARTBEAT_INTERVAL_DEFAULT = 2 * 1000;

	public static final String ENGINE_SERVER_SCHEDULER_ASSIGN_INTERVAL = "tensorhusky.engine-server.scheduler.ms";
	public static final int ENGINE_SERVER_SCHEDULER_ASSIGN_INTERVAL_DEFAULT = 10 * 60 * 1000;

	public static final String AGENT_EXPIRY_INTERVAL_MS = "tensorhusky.agent.liveness-monitor.expiry-interval-ms";
	public static final int AGENT_EXPIRY_INTERVAL_MS_DEFAULT = 1 * 60 * 1000; // 1
	// min

	public static final String PROCFS_USE_SMAPS_BASED_RSS_ENABLED = "tensorhusky.agent-server.task-monitor.procfs-tree.smaps-based-rss.enabled";
	public static final boolean PROCFS_USE_SMAPS_BASED_RSS_ENABLED_DEFAULT = false;

	public static final String AGENT_SERVER_DOCKER_ENABLED = "tensorhusky.agent-server.docker.enabled";
	public static final boolean AGENT_SERVER_DOCKER_ENABLED_DEFAULT = true;

	public static final String AGENT_SERVER_DOCKER_IMAGE_NAME = "tensorhusky.agent-server.docker.image.name";
	// public static final String AGENT_SERVER_DOCKER_IMAGE_NAME_DEFAULT = true;

	@Deprecated public static final String AGENT_SERVER_DOCKER_IMAGE_WORKING_DIR = "tensorhusky.agent-server.docker.image.working-directory";

	public static final String AGENT_SERVER_DOCKER_PATH = "tensorhusky.agent-server.docker.path";

	public static final String AGENT_SERVER_DOCKER_PATH_DEFAULT = "/usr/bin/docker";

	public static enum ERROR_CODE {
		RESOURCE_NOT_ENOUGH(1, "Resource not enough"), QUEUE_IS_FULL(2, "Queue is full"), EXCEEDED_USER_MAX(3,
				"Exceeded user max"), USER_NOT_FOUND(4, "User not found");

		int code;
		String msg;

		ERROR_CODE(int code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public int getCode() {
			return code;
		}

		public String getMsg() {
			return msg;
		}

	}

	public static enum SCHEDULER_POLICY {

		FIFO(0, "fifo"), WEIGHT(1, "weight");

		String name;

		SCHEDULER_POLICY(int value, String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public static SCHEDULER_POLICY getByName(String name) throws IllegalArgumentException {
			switch (name) {
			case "fifo":
				return FIFO;
			case "weight":
				return WEIGHT;
			default:
				throw new IllegalArgumentException("Wrong value for SCHEDULER_POLICY");
			}
		}
	}

	public static final int RESOURCE_TYPE_COUNT = 3;

	public static enum RESOURCE_TYPE {

		CPU(0, "CPU"), GPU(1, "GPU"), MEM(2, "MEM");

		int value;
		String name;

		RESOURCE_TYPE(int value, String name) {
			this.value = value;
			this.name = name;
		}

		public int getValue() {
			return value;
		}

		public String getName() {
			return name;
		}

		public RESOURCE_TYPE getByValue(int value) throws IllegalArgumentException {
			switch (value) {
			case 0:
				return CPU;
			case 1:
				return GPU;
			case 2:
				return MEM;
			default:
				throw new IllegalArgumentException("Wrong value for RESOURCE_TYPE");
			}
		}
	}

	public static enum METRICS {
		CPU(0, "CPU_Usage"), GPU(1, "GPU_Usage"), VMEM(2, "Virtual_Memory"), PMEM(3, "Physical_Memory");

		int value;
		String name;

		METRICS(int value, String name) {
			this.value = value;
			this.name = name;
		}

		public int getValue() {
			return value;
		}

		public String getName() {
			return name;
		}
	}

}
