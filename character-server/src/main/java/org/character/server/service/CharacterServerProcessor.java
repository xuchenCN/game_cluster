package org.character.server.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.character.server.CharacterContext;
import org.character.server.utils.CharacterMessageConverter;
import org.mmo.persistent.CharacterAttrInfo;
import org.mmo.server.common.conf.GameConfiguration;
import org.mmo.server.common.service.AbstractService;
import org.mmo.server.common.utils.Time;

import com.mmo.server.CommonProtocol.CommonResponse;
import com.mmo.server.CommonProtocol.CommonStat;
import com.mmo.server.ServerCharacterProtocol.CharacterUnloadRequest;
import com.mmo.server.ServerCharacterProtocol.CreateCharacterRequest;
import com.mmo.server.ServerCharacterProtocol.CreateCharacterResponse;
import com.mmo.server.ServerCharacterProtocol.GetCharacterRequest;
import com.mmo.server.ServerCharacterProtocol.GetCharacterResponse;
import com.mmo.server.ServerCharacterProtocol.UpdateCharacterRequest;
import com.mmo.server.ServerCharacterServiceGrpc.AbstractServerCharacterService;

import io.grpc.stub.StreamObserver;

public class CharacterServerProcessor extends AbstractService {

	private static final Log LOG = LogFactory.getLog(CharacterServerProcessor.class);

	private CharacterContext characterContext;
	private CharacterService characterService;

	private volatile boolean shouldRun;
	private Thread persistor;

	private Map<Integer, Pair<CharacterAttrInfo, Long>> characterInfoMap = new HashMap<Integer, Pair<CharacterAttrInfo, Long>>();

	public CharacterServerProcessor(CharacterContext characterContext) {
		super("CharacterServerProcessor");
		this.characterContext = characterContext;
	}

	@Override
	protected void serviceInit(GameConfiguration conf) throws Exception {
		this.characterService = new CharacterService();
		persistor = new Thread(new CharacterPersistor());
		persistor.setName("CharacterPersistor");
		persistor.setDaemon(true);
		super.serviceInit(conf);
	}

	@Override
	protected void serviceStart() throws Exception {
		shouldRun = true;
		persistor.start();
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

	class CharacterService extends AbstractServerCharacterService {

		@Override
		public void getCharacter(GetCharacterRequest request, StreamObserver<GetCharacterResponse> responseObserver) {
			Integer uid = request.getUid();
			Pair<CharacterAttrInfo, Long> characterAttrInfoPair = characterInfoMap.get(uid);
			if (characterAttrInfoPair == null) { // Check in cache
				CharacterAttrInfo characterAttrInfo = characterContext.getUserInfoPersistentService()
						.getCharacterAttrInfoByUid(uid);
				if (characterAttrInfo != null) { // Check in DB
					characterAttrInfoPair = Pair.of(characterAttrInfo, Time.monotonicNow());
					characterInfoMap.put(uid, characterAttrInfoPair);

					GetCharacterResponse response = GetCharacterResponse.newBuilder()
							.setCharacter(CharacterMessageConverter.toProtoCharacter(characterAttrInfo)).build();
					responseObserver.onNext(response);
					responseObserver.onCompleted();
					return;
				} else { // Not Exist
					// Response a empty character
					GetCharacterResponse response = GetCharacterResponse.newBuilder()
							.setCharacter(com.mmo.server.CommonProtocol.Character.newBuilder().build()).build();
					responseObserver.onNext(response);
					responseObserver.onCompleted();
				}
			} else {
				GetCharacterResponse response = GetCharacterResponse.newBuilder()
						.setCharacter(CharacterMessageConverter.toProtoCharacter(characterAttrInfoPair.getLeft())).build();
				responseObserver.onNext(response);
				responseObserver.onCompleted();
			}

		}

		@Override
		public void updateCharacter(UpdateCharacterRequest request, StreamObserver<CommonResponse> responseObserver) {

			CharacterAttrInfo characterAttrInfo = characterContext.getUserInfoPersistentService().getBeanFactory()
					.createCharacterAttrInfo();

			CharacterMessageConverter.toCharacterAttrInfo(request.getCharacter(), characterAttrInfo);
			Pair<CharacterAttrInfo, Long> characterAttrInfoPair = characterInfoMap.get(characterAttrInfo.getUid());
			if (characterAttrInfoPair == null) {
				characterInfoMap.put(characterAttrInfo.getUid(), Pair.of(characterAttrInfo, Time.monotonicNow()));
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
			super.createCharacter(request, responseObserver);
		}

		@Override
		public void characterUnload(CharacterUnloadRequest request, StreamObserver<CommonResponse> responseObserver) {
			Integer uid = request.getUid();
			if (uid > 0) {
				Pair<CharacterAttrInfo, Long> CharacterAttrInfoPair = characterInfoMap.remove(uid);
				// TODO persit CharacterAttrInfo;
				responseObserver.onNext(CommonResponse.newBuilder().setStat(CommonStat.OK).build());
				responseObserver.onCompleted();
				return;
			}

			responseObserver.onNext(CommonResponse.newBuilder().setStat(CommonStat.ERROR).build());
			responseObserver.onCompleted();

		}

	}

	class CharacterPersistor implements Runnable {

		@Override
		public void run() {
			while (shouldRun && !Thread.interrupted()) {

				try {
					LOG.info("Begin persist characters.");
					int writeCount = 0;
					long beginTime = Time.monotonicNow();

					for (Entry<Integer, Pair<CharacterAttrInfo, Long>> entry : characterInfoMap.entrySet()) {
						Pair<CharacterAttrInfo, Long> pair = entry.getValue();
						long current = Time.monotonicNow();
						if ((current - pair.getRight()) > characterContext.getCharServerPersitInterval()) {
							try {
								characterContext.getUserInfoPersistentService().putCharacterAttrInfo(pair.getLeft());
								pair.setValue(current);
								writeCount++;
							} catch (Exception e) {
								LOG.error("Write DB error " + pair.getLeft() + " cause " + e);
							}
						}
					}

					long cost = Time.monotonicNow() - beginTime;
					long chargeTime = characterContext.getCharServerPersitInterval() - cost;
					long nextInterval = Math.min(characterContext.getCharServerPersitInterval(), characterContext.getCharServerPersitInterval() + chargeTime);

					if (chargeTime < 0) {
						LOG.warn("Persit cost longer than " + characterContext.getCharServerPersitInterval() + "ms cost :"
								+ cost + "ms");
					} else {
						LOG.info("Persit cost : " + cost + "ms");
					}
					LOG.info("Total write : " + writeCount + " nextInterval : " + nextInterval);
					
					Thread.sleep(nextInterval);
				} catch (Exception e) {
					LOG.error(e);
				}

			}
		}

	}

}
