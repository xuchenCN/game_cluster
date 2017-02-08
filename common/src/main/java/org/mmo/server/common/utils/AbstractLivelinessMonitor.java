package org.mmo.server.common.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mmo.server.common.service.AbstractService;

/**
 * A simple liveliness monitor with which clients can register, trust the
 * component to monitor liveliness, get a call-back on expiry and then finally
 * unregister.
 */
public abstract class AbstractLivelinessMonitor<O> extends AbstractService {

	private static final Log LOG = LogFactory.getLog(AbstractLivelinessMonitor.class);

	//thread which runs periodically to see the last time since a heartbeat is
	//received.
	private Thread checkerThread;
	private volatile boolean stopped;
	public static final int DEFAULT_EXPIRE = 5 * 60 * 1000;//5 mins
	private int expireInterval = DEFAULT_EXPIRE;
	private int monitorInterval = expireInterval / 3;

	private final Clock clock;

	private Map<O, Long> running = new HashMap<O, Long>();

	public AbstractLivelinessMonitor(String name, Clock clock) {
		super(name);
		this.clock = clock;
	}

	@Override
	protected void serviceStart() throws Exception {
		assert !stopped : "starting when already stopped";
		resetTimer();
		checkerThread = new Thread(new PingChecker());
		checkerThread.setName(this.getName() + " Ping Checker");
		checkerThread.start();
		super.serviceStart();
	}

	@Override
	protected void serviceStop() throws Exception {
		stopped = true;
		if (checkerThread != null) {
			checkerThread.interrupt();
		}
		super.serviceStop();
	}

	protected abstract void expire(O ob);

	protected void setExpireInterval(int expireInterval) {
		this.expireInterval = expireInterval;
	}

	protected void setMonitorInterval(int monitorInterval) {
		this.monitorInterval = monitorInterval;
	}

	public synchronized void receivedPing(O ob) {
		//only put for the registered objects
		if (running.containsKey(ob)) {
			running.put(ob, clock.getTime());
		}
	}

	public synchronized void register(O ob) {
		running.put(ob, clock.getTime());
	}

	public synchronized void unregister(O ob) {
		running.remove(ob);
	}

	public synchronized void resetTimer() {
		long time = clock.getTime();
		for (O ob : running.keySet()) {
			running.put(ob, time);
		}
	}

	private class PingChecker implements Runnable {

		@Override
		public void run() {
			while (!stopped && !Thread.currentThread().isInterrupted()) {
				synchronized (AbstractLivelinessMonitor.this) {
					Iterator<Map.Entry<O, Long>> iterator = running.entrySet().iterator();

					//avoid calculating current time everytime in loop
					long currentTime = clock.getTime();

					while (iterator.hasNext()) {
						Map.Entry<O, Long> entry = iterator.next();
						if (currentTime > entry.getValue() + expireInterval) {
							iterator.remove();
							expire(entry.getKey());
							LOG.info("Expired:" + entry.getKey().toString() + " Timed out after " + expireInterval / 1000
									+ " secs");
						}
					}
				}
				try {
					Thread.sleep(monitorInterval);
				} catch (InterruptedException e) {
					LOG.info(getName() + " thread interrupted");
					break;
				}
			}
		}
	}

}
