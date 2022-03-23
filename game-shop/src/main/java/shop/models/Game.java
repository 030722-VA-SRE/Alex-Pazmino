package shop.models;

import java.util.Objects;

public class Game {
	private int id;
	private String name;
	private String platformName;
	private boolean isConsoleExclusive;
	private double msrp;
	private int platformId;
	
	public Game() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public boolean isConsoleExclusive() {
		return isConsoleExclusive;
	}

	public void setConsoleExclusive(boolean isConsoleExclusive) {
		this.isConsoleExclusive = isConsoleExclusive;
	}

	public double getMsrp() {
		return msrp;
	}

	public void setMsrp(double msrp) {
		this.msrp = msrp;
	}

	public int getPlatformId() {
		return platformId;
	}

	public void setPlatformId(int platformId) {
		this.platformId = platformId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, isConsoleExclusive, msrp, name, platformId, platformName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		return id == other.id && isConsoleExclusive == other.isConsoleExclusive
				&& Double.doubleToLongBits(msrp) == Double.doubleToLongBits(other.msrp)
				&& Objects.equals(name, other.name) && platformId == other.platformId
				&& Objects.equals(platformName, other.platformName);
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", platformName=" + platformName + ", isConsoleExclusive="
				+ isConsoleExclusive + ", msrp=" + msrp + ", platformId=" + platformId + "]";
	}
	
	
	
	

}