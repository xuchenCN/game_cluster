package org.server.gate.core;

import org.mmo.server.common.conf.GameConfiguration;
import org.mmo.server.common.service.CompositeService;

import com.mmo.server.ServerClientProtocol.UserLoginRequest;
import com.mmo.server.ServerClientProtocol.UserLoginResponse;

public class AccountService extends CompositeService {
	
	public AccountService() {
		super("AccountService");
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

	public UserLoginResponse userLogin(UserLoginRequest request) {

		return null;
	}

	public int getUidByTicket(String ticket) {
		return -1;
	}
	
	public int getMapIdByTicket(String ticket) {
		return -1;
	}

}
