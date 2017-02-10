package org.server.gate.services;

import org.mmo.server.common.conf.GameConfiguration;
import org.mmo.server.common.service.AbstractService;
import org.server.gate.GateServerContext;

import com.mmo.server.ClientSideServiceGrpc.AbstractClientSideService;
import com.mmo.server.CommonProtocol.CommonResponse;
import com.mmo.server.CommonProtocol.MoveEvent;
import com.mmo.server.EventDispatcherGrpc.AbstractEventDispatcher;
import com.mmo.server.ServerClientProtocol.CharacterMove;
import com.mmo.server.ServerClientProtocol.GetCharacterInfoRequest;
import com.mmo.server.ServerClientProtocol.GetCharacterInfoResponse;
import com.mmo.server.ServerClientProtocol.UserLoginRequest;
import com.mmo.server.ServerClientProtocol.UserLoginResponse;
import com.mmo.server.ServerClientProtocol.UserLogoutRequest;
import com.mmo.server.ServerClientProtocol.UserLogoutResponse;
import com.mmo.server.ServerGateProtocol.GateServerPing;
import com.mmo.server.ServerGateProtocol.GateServerPong;
import com.mmo.server.ServerGateServiceGrpc.AbstractServerGateService;

import io.grpc.stub.StreamObserver;

public class GateServerProcessor extends AbstractService {

	private GateServerContext globalContext;

	private ClientSideService clientSideService;
	private GateService gateService;

	public GateServerProcessor(GateServerContext globalContext) {
		super("GateServerProcessor");

		this.globalContext = globalContext;

		clientSideService = new ClientSideService();
		gateService = new GateService();
	}

	public ClientSideService getClientSideService() {
		return clientSideService;
	}

	public GateService getGateService() {
		return gateService;
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

	class ClientSideService extends AbstractClientSideService {

		@Override
		public void moveTo(CharacterMove request, StreamObserver<CommonResponse> responseObserver) {
			// TODO Auto-generated method stub
			super.moveTo(request, responseObserver);
		}

	}

	class GateService extends AbstractServerGateService {

		@Override
		public void userLogin(UserLoginRequest request, StreamObserver<UserLoginResponse> responseObserver) {
			// TODO Auto-generated method stub
			super.userLogin(request, responseObserver);
		}

		@Override
		public void userLogout(UserLogoutRequest request, StreamObserver<UserLogoutResponse> responseObserver) {
			// TODO Auto-generated method stub
			super.userLogout(request, responseObserver);
		}

		@Override
		public void getCharacter(GetCharacterInfoRequest request,
				StreamObserver<GetCharacterInfoResponse> responseObserver) {
			// TODO Auto-generated method stub
			super.getCharacter(request, responseObserver);
		}

	}
}
