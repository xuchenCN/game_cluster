package org.mmo.server.common.utils;

public class UserInfo {
	private String user;
	private int weight;
	private int maxResource;
	private long maxLongTime;

	public UserInfo(String user, int weight, int maxResource, long maxLongTime) {
		super();
		this.user = user;
		this.weight = weight;
		this.maxResource = maxResource;
		this.maxLongTime = maxLongTime;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getMaxResource() {
		return maxResource;
	}

	public void setMaxResource(int maxResource) {
		this.maxResource = maxResource;
	}

	public long getMaxLongTime() {
		return maxLongTime;
	}

	public void setMaxLongTime(long maxLongTime) {
		this.maxLongTime = maxLongTime;
	}

	@Override
	public String toString() {
		return "UserInfo [user=" + user + ", weight=" + weight + ", maxResource=" + maxResource + ", maxLongTime="
				+ maxLongTime + "]";
	}

}
