package org.mmo.persistent.redis;

import org.mmo.persistent.CharacterAttrInfo;
import org.mmo.persistent.RedisBean;

public class CharacterAttrInfoImpl extends RedisBean implements CharacterAttrInfo {
	private Integer uid;
	private String name;

	private Integer mapId;
	private Integer posX;
	private Integer posY;
	private Integer posZ;

	private Integer strength;
	private Integer endurance;
	private Integer agility;
	private Integer defence;

	private Integer health;
	private Integer hunger;
	private Integer spirit;
	private Integer comfort;

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

	public Integer getStrength() {
		return strength;
	}

	public void setStrength(Integer strength) {
		this.strength = strength;
	}

	public Integer getEndurance() {
		return endurance;
	}

	public void setEndurance(Integer endurance) {
		this.endurance = endurance;
	}

	public Integer getAgility() {
		return agility;
	}

	public void setAgility(Integer agility) {
		this.agility = agility;
	}

	public Integer getDefence() {
		return defence;
	}

	public void setDefence(Integer defence) {
		this.defence = defence;
	}

	public Integer getHealth() {
		return health;
	}

	public void setHealth(Integer health) {
		this.health = health;
	}

	public Integer getHunger() {
		return hunger;
	}

	public void setHunger(Integer hunger) {
		this.hunger = hunger;
	}

	public Integer getSpirit() {
		return spirit;
	}

	public void setSpirit(Integer spirit) {
		this.spirit = spirit;
	}

	public Integer getComfort() {
		return comfort;
	}

	public void setComfort(Integer comfort) {
		this.comfort = comfort;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String key() {
		return KeyConstants.CHARACTER_ATTR_PREFIX + uid;
	}

}
