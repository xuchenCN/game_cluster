package org.server.gate.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mmo.server.common.conf.GameConfiguration;
import org.mmo.server.common.service.AbstractService;
import org.server.gate.GateServerContext;

public class GateServerProcessor extends AbstractService {

	private static final Log LOG = LogFactory.getLog(GateServerProcessor.class);

	private GateServerContext globalContext;

	public GateServerProcessor(GateServerContext globalContext) {
		super("GateServerProcessor");

		this.globalContext = globalContext;

	}

	@Override
	protected void serviceInit(GameConfiguration conf) throws Exception {
		// TODO Auto-generated method stub
		super.serviceInit(conf);
	}

	@Override
	protected void serviceStart() throws Exception {
		// TODO Auto-generated method stub
		super.serviceStart();
	}

	@Override
	protected void serviceStop() throws Exception {
		// TODO Auto-generated method stub
		super.serviceStop();
	}

}
