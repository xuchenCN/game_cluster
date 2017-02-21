package org.protocol.communicators;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mmo.server.CommonProtocol;
import com.mmo.server.ServerCharacterProtocol.GetCharacterRequest;
import com.mmo.server.ServerCharacterProtocol.GetCharacterResponse;
import com.mmo.server.ServerCharacterProtocol.UpdateCharacterRequest;
import com.mmo.server.ServerCharacterServiceGrpc;
import com.mmo.server.ServerCharacterServiceGrpc.ServerCharacterServiceBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class CharacterServerCommunicator {

	private static final Log LOG = LogFactory.getLog(CharacterServerCommunicator.class);

	private ManagedChannel channel;

	private ServerCharacterServiceBlockingStub characterService;

	public CharacterServerCommunicator(String host, int port) {
		ManagedChannelBuilder channelBuilder = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true);
		channel = channelBuilder.build();
		characterService = ServerCharacterServiceGrpc.newBlockingStub(channel);
	}

	public CommonProtocol.Character getCharacter(int uid) {
		if (uid > 0) {
			GetCharacterResponse response = characterService
					.getCharacter(GetCharacterRequest.newBuilder().setUid(uid).build());
			CommonProtocol.Character character = response.getCharacter();
			if (character != null && character.getIdentify().getID() > 0) {
				return character;
			}
		}

		return null;
	}

	public void updateCharacter(CommonProtocol.Character character) {
		if (character != null && character.getIdentify().getID() > 0) {
			characterService.updateCharacter(UpdateCharacterRequest.newBuilder().setCharacter(character).build());
		}

	}

}
