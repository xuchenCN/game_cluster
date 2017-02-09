package org.server.game.core;

import org.mmo.server.common.conf.GameConfiguration;
import org.mmo.server.common.service.AbstractService;
import org.server.game.GameServerContext;

public class CharactorLogic extends AbstractService {

	private GameServerContext globalContext;

	public CharactorLogic(GameServerContext globalContext) {
		super("CharactorLogic");
		this.globalContext = globalContext;
	}

	@Override
	protected void serviceInit(GameConfiguration conf) throws Exception {
		// TODO Auto-generated method stub
		super.serviceInit(conf);
	}

	@Override
	protected void serviceStart() throws Exception {
		
		super.serviceStart();
	}

	@Override
	protected void serviceStop() throws Exception {
		// TODO Auto-generated method stub
		super.serviceStop();
	}

}
