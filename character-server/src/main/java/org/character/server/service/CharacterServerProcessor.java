package org.character.server.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.character.server.CharacterContext;
import org.mmo.persistent.CharacterAttrInfo;
import org.mmo.server.common.conf.GameConfiguration;
import org.mmo.server.common.service.AbstractService;
import org.mmo.server.common.utils.SystemClock;

import com.mmo.server.CommonProtocol.CommonResponse;
import com.mmo.server.CommonProtocol.CommonStat;
import com.mmo.server.CommonProtocol.EnergyInfo;
import com.mmo.server.CommonProtocol.IdentifyInfo;
import com.mmo.server.CommonProtocol.Position;
import com.mmo.server.CommonProtocol.SurvivalInfo;
import com.mmo.server.ServerCharacterProtocol.CharacterUnloadRequest;
import com.mmo.server.ServerCharacterProtocol.CreateCharacterRequest;
import com.mmo.server.ServerCharacterProtocol.CreateCharacterResponse;
import com.mmo.server.ServerCharacterProtocol.GetCharacterRequest;
import com.mmo.server.ServerCharacterProtocol.GetCharacterResponse;
import com.mmo.server.ServerCharacterProtocol.UpdateCharacterRequest;
import com.mmo.server.ServerCharacterServiceGrpc.AbstractServerCharacterService;

import io.grpc.stub.StreamObserver;

public class CharacterServerProcessor extends AbstractService {

	private CharacterContext characterContext;
	private CharacterService characterService;
	private SystemClock clock = new SystemClock();

	private Map<Integer, Pair<CharacterAttrInfo, Long>> characterInfoMap = new HashMap<Integer, Pair<CharacterAttrInfo, Long>>();

	public CharacterServerProcessor(CharacterContext characterContext) {
		super("CharacterServerProcessor");
		this.characterContext = characterContext;
	}

	@Override
	protected void serviceInit(GameConfiguration conf) throws Exception {
		this.characterService = new CharacterService();
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

	public CharacterService getCharacterService() {
		return characterService;
	}

	private CharacterAttrInfo toCharacterAttrInfo(com.mmo.server.CommonProtocol.Character character) {
		try {
			Integer uid = Integer.valueOf(character.getIdentify().getID());
			if (character != null && uid > 0) {
				CharacterAttrInfo characterAttrInfo = characterContext.getUserInfoPersistentService().getBeanFactory()
						.createCharacterAttrInfo();
				characterAttrInfo.setUid(uid);
				characterAttrInfo.setName(character.getIdentify().getName());
				characterAttrInfo.setMapId(character.getMapId());

				characterAttrInfo.setPosX(character.getPosition().getPosX());
				characterAttrInfo.setPosY(character.getPosition().getPosY());
				characterAttrInfo.setPosZ(character.getPosition().getPosZ());

				characterAttrInfo.setHealth(character.getSurvivalInfo().getHealth());
				characterAttrInfo.setHunger(character.getSurvivalInfo().getHunger());
				characterAttrInfo.setSpirit(character.getSurvivalInfo().getSpirit());
				characterAttrInfo.setComfort(character.getSurvivalInfo().getComfort());

				characterAttrInfo.setStrength(character.getEnergyInfo().getStrength());
				characterAttrInfo.setEndurance(character.getEnergyInfo().getEndurance());
				characterAttrInfo.setAgility(character.getEnergyInfo().getAgility());
				characterAttrInfo.setDefence(character.getEnergyInfo().getDefence());

				return characterAttrInfo;
			}
		} catch (Throwable t) {
		}
		return null;
	}

	private com.mmo.server.CommonProtocol.Character toProtoCharacter(CharacterAttrInfo characterAttrInfo) {
		IdentifyInfo identifyInfo = IdentifyInfo.newBuilder().setID(characterAttrInfo.getUid().toString())
				.setName(characterAttrInfo.getName()).build();
		Position position = Position.newBuilder().setPosX(characterAttrInfo.getPosX())
				.setPosY(characterAttrInfo.getPosY()).setPosZ(characterAttrInfo.getPosZ()).build();
		SurvivalInfo survivalInfo = SurvivalInfo.newBuilder().setHealth(characterAttrInfo.getHealth())
				.setHunger(characterAttrInfo.getHunger()).setSpirit(characterAttrInfo.getSpirit())
				.setComfort(characterAttrInfo.getComfort()).build();
		EnergyInfo energyInfo = EnergyInfo.newBuilder().setStrength(characterAttrInfo.getStrength())
				.setEndurance(characterAttrInfo.getEndurance()).setAgility(characterAttrInfo.getAgility())
				.setDefence(characterAttrInfo.getDefence()).build();

		com.mmo.server.CommonProtocol.Character character = com.mmo.server.CommonProtocol.Character.newBuilder()
				.setIdentify(identifyInfo).setPosition(position).setSurvivalInfo(survivalInfo)
				.setMapId(characterAttrInfo.getMapId()).setEnergyInfo(energyInfo).build();

		return character;
	}

	class CharacterService extends AbstractServerCharacterService {

		@Override
		public void getCharacter(GetCharacterRequest request, StreamObserver<GetCharacterResponse> responseObserver) {
			Integer uid = request.getUid();
			Pair<CharacterAttrInfo, Long> characterAttrInfoPair = characterInfoMap.get(uid);
			if (characterAttrInfoPair == null) {
				CharacterAttrInfo characterAttrInfo = characterContext.getUserInfoPersistentService()
						.getCharacterAttrInfoByUid(uid);
				if (characterAttrInfo != null) {
					characterAttrInfoPair = Pair.of(characterAttrInfo, clock.getTime());
					characterInfoMap.put(uid, characterAttrInfoPair);

					GetCharacterResponse response = GetCharacterResponse.newBuilder()
							.setCharacter(toProtoCharacter(characterAttrInfo)).build();
					responseObserver.onNext(response);
					responseObserver.onCompleted();
					return;
				}
			}
			// Response a empty character
			GetCharacterResponse response = GetCharacterResponse.newBuilder()
					.setCharacter(com.mmo.server.CommonProtocol.Character.newBuilder().build()).build();
			responseObserver.onNext(response);
			responseObserver.onCompleted();
		}

		@Override
		public void updateCharacter(UpdateCharacterRequest request, StreamObserver<CommonResponse> responseObserver) {
			CharacterAttrInfo characterAttrInfo = toCharacterAttrInfo(request.getCharacter());
			Pair<CharacterAttrInfo, Long> characterAttrInfoPair = characterInfoMap.get(characterAttrInfo.getUid());
			if (characterAttrInfoPair == null) {
				characterInfoMap.put(characterAttrInfo.getUid(), Pair.of(characterAttrInfo, clock.getTime()));
			} else {
				characterInfoMap.put(characterAttrInfo.getUid(),
						Pair.of(characterAttrInfo, characterAttrInfoPair.getRight()));
			}

			responseObserver.onNext(CommonResponse.newBuilder().setStat(CommonStat.OK).build());
			responseObserver.onCompleted();
		}

		@Override
		public void createCharacter(CreateCharacterRequest request,
				StreamObserver<CreateCharacterResponse> responseObserver) {
			// TODO Auto-generated method stub
			super.createCharacter(request, responseObserver);
		}

		@Override
		public void characterUnload(CharacterUnloadRequest request, StreamObserver<CommonResponse> responseObserver) {
			Integer uid = request.getUid();
			if(uid > 0) {
				Pair<CharacterAttrInfo, Long> CharacterAttrInfoPair = characterInfoMap.remove(uid);
				//TODO persit CharacterAttrInfo;
				responseObserver.onNext(CommonResponse.newBuilder().setStat(CommonStat.OK).build());
				responseObserver.onCompleted();
				return;
			}
			
			responseObserver.onNext(CommonResponse.newBuilder().setStat(CommonStat.ERROR).build());
			responseObserver.onCompleted();
			
		}

	}

}
