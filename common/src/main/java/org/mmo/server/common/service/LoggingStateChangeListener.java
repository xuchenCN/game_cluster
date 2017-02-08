package org.mmo.server.common.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This is a state change listener that logs events at INFO level
 */
public class LoggingStateChangeListener implements ServiceStateChangeListener {

	private static final Log LOG = LogFactory.getLog(LoggingStateChangeListener.class);

	private final Log log;

	/**
	 * Log events to the given log
	 *
	 * @param log destination for events
	 */
	public LoggingStateChangeListener(Log log) {
		//force an NPE if a null log came in
		log.isDebugEnabled();
		this.log = log;
	}

	/**
	 * Log events to the static log for this class
	 */
	public LoggingStateChangeListener() {
		this(LOG);
	}

	/**
	 * Callback for a state change event: log it
	 *
	 * @param service the service that has changed.
	 */
	@Override
	public void stateChanged(Service service) {
		log.info("Entry to state " + service.getServiceState() + " for " + service.getName());
	}
}
