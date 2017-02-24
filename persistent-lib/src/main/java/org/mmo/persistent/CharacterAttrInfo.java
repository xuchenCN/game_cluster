package org.mmo.persistent;

public interface CharacterAttrInfo {

	public Integer getUid();

	public void setUid(Integer uid);

	public Integer getMapId();

	public void setMapId(Integer mapId);

	public Float getPosX();

	public void setPosX(Float posX);

	public Float getPosY();

	public void setPosY(Float posY);

	public Float getPosZ();

	public void setPosZ(Float posZ);

	public Integer getStrength();

	public void setStrength(Integer strength);

	public Integer getEndurance();

	public void setEndurance(Integer endurance);

	public Integer getAgility();

	public void setAgility(Integer agility);

	public Integer getDefence();

	public void setDefence(Integer defence);

	public Integer getHealth();

	public void setHealth(Integer health);

	public Integer getHunger();

	public void setHunger(Integer hunger);

	public Integer getSpirit();

	public void setSpirit(Integer spirit);
	
	public Integer getComfort() ;

	public void setComfort(Integer comfort) ;
	
	public String getName() ;

	public void setName(String name);
}
