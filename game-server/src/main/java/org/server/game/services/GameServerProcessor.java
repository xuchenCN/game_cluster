package org.server.game.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mmo.server.common.conf.GameConfiguration;
import org.mmo.server.common.service.AbstractService;
import org.server.game.GameServer;
import org.server.game.GameServerContext;

import com.mmo.server.CharacterServiceGrpc.AbstractCharacterService;
import com.mmo.server.CommonProtocol.CommonResponse;
import com.mmo.server.ServerGameProtocol.CharacterMoveReq;
import com.mmo.server.ServerGameProtocol.UserArrivedRegionRequest;
import com.mmo.server.UserRegionServiceGrpc.AbstractUserRegionService;

import io.grpc.stub.StreamObserver;

public class GameServerProcessor extends AbstractService {
	
	private static final Log LOG = LogFactory.getLog(GameServerProcessor.class);

	private GameServerContext globalContext;
	private UserRegionService userRegionService;
	private CharacterService characterService;

	public GameServerProcessor(GameServerContext globalContext) {
		super("GameServerProcessor");
		this.globalContext = globalContext;
		this.userRegionService = new UserRegionService();
		this.characterService = new CharacterService();
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

	public UserRegionService getUserRegionService() {
		return userRegionService;
	}

	public CharacterService getCharacterService() {
		return characterService;
	}

	class UserRegionService extends AbstractUserRegionService {

		@Override
		public void userArrivedRegion(UserArrivedRegionRequest request, StreamObserver<CommonResponse> responseObserver) {
			// TODO Auto-generated method stub
			super.userArrivedRegion(request, responseObserver);
		}

	}

	class CharacterService extends AbstractCharacterService {

		@Override
		public void moveTo(CharacterMoveReq request, StreamObserver<CommonResponse> responseObserver) {
			// TODO Auto-generated method stub
			super.moveTo(request, responseObserver);
		}

	}
}
