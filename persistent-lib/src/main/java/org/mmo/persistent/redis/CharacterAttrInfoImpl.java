package org.mmo.persistent.redis;

import org.mmo.persistent.CharacterAttrInfo;
import org.mmo.persistent.RedisBean;

public class CharacterAttrInfoImpl extends RedisBean implements CharacterAttrInfo {
	private Integer uid;
	private Integer mapId;
	private Integer posX;
	private Integer posY;
	private Integer posZ;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getMapId() {
		return mapId;
	}

	public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}

	public Integer getPosX() {
		return posX;
	}

	public void setPosX(Integer posX) {
		this.posX = posX;
	}

	public Integer getPosY() {
		return posY;
	}

	public void setPosY(Integer posY) {
		this.posY = posY;
	}

	public Integer getPosZ() {
		return posZ;
	}

	public void setPosZ(Integer posZ) {
		this.posZ = posZ;
	}

	@Override
	public String key() {
		return uid.toString();
	}

}
