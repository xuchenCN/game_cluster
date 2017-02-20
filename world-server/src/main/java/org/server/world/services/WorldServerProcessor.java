package org.server.world.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mmo.server.common.conf.GameConfiguration;
import org.mmo.server.common.service.AbstractService;
import org.server.world.WorldServerContext;

import com.mmo.server.CommonProtocol.CommonResponse;
import com.mmo.server.CommonProtocol.CommonStat;
import com.mmo.server.ServerWorldProtocol.CharacterRegisterRequest;
import com.mmo.server.ServerWorldProtocol.CharacterServerInfo;
import com.mmo.server.ServerWorldProtocol.GateRegisterRequest;
import com.mmo.server.ServerWorldProtocol.GateRegisterResponse;
import com.mmo.server.ServerWorldProtocol.RegionRegisterRequest;
import com.mmo.server.ServerWorldProtocol.RegionRegisterResponse;
import com.mmo.server.ServerWorldProtocol.RegionServerInfo;
import com.mmo.server.ServerWorldProtocol.UserArrivedWorldRequest;
import com.mmo.server.ServerWorldProtocol.UserLeaveWorldRequest;
import com.mmo.server.ServerWorldProtocol.UserTransToMapRequest;
import com.mmo.server.UserWorldServiceGrpc.AbstractUserWorldService;
import com.mmo.server.WorldServiceGrpc.AbstractWorldService;

import io.grpc.stub.StreamObserver;

public class WorldServerProcessor extends AbstractService {

	private static final Log LOG = LogFactory.getLog(WorldServerProcessor.class);

	private WorldServerContext globalContext;

	private UserWorldService userService;
	private WorldService worldService;

	private Map<Integer, RegionServerInfo> regionMapping = new HashMap<Integer, RegionServerInfo>();

	private Map<String, GateServerInfo> gateMapping = new HashMap<String, GateServerInfo>();

	private Map<String, CharacterServerInfo> charMapping = new HashMap<String, CharacterServerInfo>();

	public WorldServerProcessor(WorldServerContext globalContext) {
		super("WorldServerProcessor");
		this.globalContext = globalContext;

		userService = new UserWorldService();
		worldService = new WorldService();
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

	public UserWorldService getUserService() {
		return userService;
	}

	public WorldService getWorldService() {
		return worldService;
	}

	class UserWorldService extends AbstractUserWorldService {

		@Override
		public void userArrivedWorld(UserArrivedWorldRequest request, StreamObserver<CommonResponse> responseObserver) {
			responseObserver.onNext(CommonResponse.newBuilder().setStat(CommonStat.OK).build());
			responseObserver.onCompleted();
		}

		@Override
		public void userLeaveWorld(UserLeaveWorldRequest request, StreamObserver<CommonResponse> responseObserver) {
			// TODO Auto-generated method stub
			super.userLeaveWorld(request, responseObserver);
		}

		@Override
		public void userTransToMap(UserTransToMapRequest request, StreamObserver<CommonResponse> responseObserver) {
			// TODO Auto-generated method stub
			super.userTransToMap(request, responseObserver);
		}

	}

	class WorldService extends AbstractWorldService {

		@Override
		public void registerGate(GateRegisterRequest request, StreamObserver<GateRegisterResponse> responseObserver) {
			String host = request.getGateHost();
			int port = request.getGatePort();
			String key = host + ":" + port;
			if (gateMapping.containsKey(key)) {
				responseObserver.onNext(GateRegisterResponse.newBuilder().build());
				responseObserver.onCompleted();
				return;
			}

			GateRegisterResponse.Builder responseBuilder = GateRegisterResponse.newBuilder()
					.addAllRegions(new ArrayList<RegionServerInfo>(regionMapping.values()));
			responseObserver.onNext(responseBuilder.build());
			responseObserver.onCompleted();

			GateServerInfo gateInfo = new GateServerInfo(host, port);
			gateMapping.put(key, gateInfo);

			LOG.info("Gate registered " + key);
		}

		@Override
		public void registerRegion(RegionRegisterRequest request,
				StreamObserver<RegionRegisterResponse> responseObserver) {

			List<CharacterServerInfo> characterServers = new ArrayList<CharacterServerInfo>();

			if (regionMapping.containsKey(request.getMapId())) {
				responseObserver.onNext(RegionRegisterResponse.newBuilder().addAllCharServers(characterServers).build());
				responseObserver.onCompleted();
				return;
			}

			RegionServerInfo.Builder regionServerInfoB = RegionServerInfo.newBuilder();
			regionServerInfoB.setServerHost(request.getServerHost());
			regionServerInfoB.setServerPort(request.getServerPort());
			regionServerInfoB.setMapid(request.getMapId());

			characterServers.addAll(charMapping.values());
			responseObserver.onNext(RegionRegisterResponse.newBuilder().addAllCharServers(characterServers).build());
			responseObserver.onCompleted();

			regionMapping.put(request.getMapId(), regionServerInfoB.build());
			LOG.info("Region registered mapId : " + request.getMapId());
		}

		@Override
		public void registerCharacterServer(CharacterRegisterRequest request,
				StreamObserver<CommonResponse> responseObserver) {

			String host = request.getServerHost();
			int port = request.getServerPort();
			String key = host + ":" + port;
			CharacterServerInfo characterServerInfo = charMapping.get(key);
			if (characterServerInfo != null) {
				responseObserver.onNext(CommonResponse.newBuilder().setStat(CommonStat.ERROR).build());
				responseObserver.onCompleted();
				return;
			}

			characterServerInfo = CharacterServerInfo.newBuilder().setServerHost(host).setServerPort(port).build();
			charMapping.put(key, characterServerInfo);

			responseObserver.onNext(CommonResponse.newBuilder().setStat(CommonStat.OK).build());
			responseObserver.onCompleted();
		}

	}

}
