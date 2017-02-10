package org.server.gate.core;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mmo.server.common.conf.GameConfiguration;
import org.mmo.server.common.service.AbstractService;
import org.mmo.server.common.utils.NetUtils;
import org.server.gate.GateServerContext;
import org.server.gate.communicator.WorldServerCommunicator;

import com.mmo.server.CommonProtocol.CommonResponse;
import com.mmo.server.CommonProtocol.MoveEvent;
import com.mmo.server.EventDispatcherGrpc.AbstractEventDispatcher;
import com.mmo.server.ServerGateProtocol.GateServerPing;
import com.mmo.server.ServerGateProtocol.GateServerPong;
import com.mmo.server.ServerWorldProtocol.RegionServerInfo;

import io.grpc.stub.StreamObserver;

public class GateServerRouter extends AbstractService {

	private static final Log LOG = LogFactory.getLog(GateServerRouter.class);

	private GateServerContext globalContext;
	private ForwardService forwardService;

	public GateServerRouter(GateServerContext globalContext) {
		super("GateServerRouter");
		this.globalContext = globalContext;
	}

	@Override
	protected void serviceInit(GameConfiguration conf) throws Exception {
		forwardService = new ForwardService();
		super.serviceInit(conf);
	}

	@Override
	protected void serviceStart() throws Exception {

		List<RegionServerInfo> regionServerList = globalContext.getWorldServerCommunicator()
				.registerGate(NetUtils.getLocalIpAddress(getConfig()), globalContext.getListenOn());
		LOG.info("Registered got regionServer : " + regionServerList);
		super.serviceStart();
	}

	@Override
	protected void serviceStop() throws Exception {
		// TODO Auto-generated method stub
		super.serviceStop();
	}

	public ForwardService getForwardService() {
		return forwardService;
	}

	class ForwardService extends AbstractEventDispatcher {

		@Override
		public void moveEvent(MoveEvent request, StreamObserver<CommonResponse> responseObserver) {
			// TODO Auto-generated method stub
			super.moveEvent(request, responseObserver);
		}

		@Override
		public void receivePing(GateServerPing request, StreamObserver<GateServerPong> responseObserver) {
			// TODO Auto-generated method stub
			super.receivePing(request, responseObserver);
		}

	}

}
