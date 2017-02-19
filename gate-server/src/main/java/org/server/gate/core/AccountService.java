package org.server.gate.core;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mmo.persistent.UserInfoPersistentBean;
import org.mmo.server.common.conf.GameConfiguration;
import org.mmo.server.common.service.CompositeService;
import org.mmo.server.common.utils.BaseX;
import org.server.gate.GateServerContext;

import com.mmo.server.ServerClientProtocol.ClientCharacter;
import com.mmo.server.ServerClientProtocol.ClientIdentifyInfo;
import com.mmo.server.ServerClientProtocol.ClientPosition;
import com.mmo.server.ServerClientProtocol.LoginCode;
import com.mmo.server.ServerClientProtocol.UserLoginRequest;
import com.mmo.server.ServerClientProtocol.UserLoginResponse;
import com.mmo.server.ServerClientProtocol.UserLogoutRequest;
import com.mmo.server.ServerClientProtocol.UserLogoutResponse;

public class AccountService extends CompositeService {

	private static final Log LOG = LogFactory.getLog(AccountService.class);

	private GateServerContext globalContext;
	private BaseX baseX;

	private Map<String, Pair<Integer, Integer>> ticketToUidMapId = new HashMap<String, Pair<Integer, Integer>>();
	private Map<Integer,String> uidToTicket = new HashMap<Integer,String>();
	
	public AccountService(GateServerContext globalContext) {
		super("AccountService");
		this.globalContext = globalContext;
		baseX = new BaseX(BaseX.DICTIONARY_62);
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
		String uname = request.getUname();
		String pwd = request.getUpwd();
		LOG.info("User login request : " + uname + ":" + pwd);
		
		UserLoginResponse.Builder responseBuilder = UserLoginResponse.newBuilder();
		
		UserInfoPersistentBean conditionBean = globalContext.getUserInfoPersistentService().getBeanFactory().createUserInfoPersistentBean();
		conditionBean.setName(uname);
		UserInfoPersistentBean userInfoPersistentBean = globalContext.getUserInfoPersistentService().getUserInfo(conditionBean);
		if(userInfoPersistentBean != null && userInfoPersistentBean.getUid() > 0) {
			//TODO md5
			if(pwd.equals(userInfoPersistentBean.getPwd())) {
				if(uidToTicket.containsKey(userInfoPersistentBean.getUid())) {
					String oldTicket = uidToTicket.remove(userInfoPersistentBean.getUid());
					ticketToUidMapId.remove(oldTicket);
				}
				
				String ticket = baseX.encode(new BigInteger(System.nanoTime() + userInfoPersistentBean.getUid() + ""));
				uidToTicket.put(userInfoPersistentBean.getUid(), ticket);
				//FIXME
				ticketToUidMapId.put(ticket, Pair.of(userInfoPersistentBean.getUid(),1));
				
				ClientIdentifyInfo clientIdentifyInfo = ClientIdentifyInfo.newBuilder().setID(userInfoPersistentBean.getUid() + "").setName(userInfoPersistentBean.getUid() + "").build();
				ClientPosition position = ClientPosition.newBuilder().setPosX(14).setPosY(0).setPosZ(15).build();
				ClientCharacter character = ClientCharacter.newBuilder().setMapId(1).setIdentify(clientIdentifyInfo).setPosition(position).build();
				
				responseBuilder.setCode(LoginCode.SUC);
				responseBuilder.setTicket(ticket);
				responseBuilder.setCharacter(character);
			} else {
				responseBuilder.setCode(LoginCode.ERROR_PWD);
			}
		} else {
			responseBuilder.setCode(LoginCode.NOT_EXIST);
		}
		
		return responseBuilder.build();
		
	}
	
	public UserLogoutResponse userLogout(UserLogoutRequest request) {
		//TODO Unload character data 
		String ticket = request.getTicket();
		LOG.info("User logout : " + ticketToUidMapId.get(ticket));
		return null;
	}

	public int getUidByTicket(String ticket) {
		return ticketToUidMapId.get(ticket).getLeft();
	}

	public int getMapIdByTicket(String ticket) {
		return ticketToUidMapId.get(ticket).getRight();
	}
	
	public Pair<Integer,Integer> getUidMapIdPair(String ticket){
		return ticketToUidMapId.get(ticket);
	}
}
