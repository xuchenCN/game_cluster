package org.server.gate.core;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mmo.server.common.conf.GameConfiguration;
import org.mmo.server.common.service.AbstractService;
import org.mmo.server.common.utils.NetUtils;
import org.server.gate.GateServerContext;
import org.server.gate.communicator.WorldServerCommunicator;

import com.mmo.server.ServerWorldProtocol.RegionServerInfo;

public class GateServerRouter extends AbstractService {

	private static final Log LOG = LogFactory.getLog(GateServerRouter.class);

	private GateServerContext globalContext;

	public GateServerRouter(GateServerContext globalContext) {
		super("GateServerRouter");
		this.globalContext = globalContext;
	}

	@Override
	protected void serviceInit(GameConfiguration conf) throws Exception {
		super.serviceInit(conf);

	}

	@Override
	protected void serviceStart() throws Exception {

		List<RegionServerInfo> regionServerList = globalContext.getWorldServerCommunicator()
				.registerGate(NetUtils.getLocalIpAddress(getConfig()), globalContext.getListenOn());

		super.serviceStart();
	}

	@Override
	protected void serviceStop() throws Exception {
		// TODO Auto-generated method stub
		super.serviceStop();
	}

}
