package org.server.gate.utils;

import java.lang.Thread.UncaughtExceptionHandler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ExecutorExceptionHandler implements UncaughtExceptionHandler {

	private static final Log LOG = LogFactory.getLog(ExecutorExceptionHandler.class);

	private Object handleTarget;

	public ExecutorExceptionHandler(Object handleTarget) {
		this.handleTarget = handleTarget;
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		LOG.error("ThreadPool got exception in : " + handleTarget + " cause " + t.getName(), e);
	}
}
