package com.revature.dtos;

import java.util.Objects;

import com.revature.models.Game;

public class GameDto {
	
	private int id;
	private String name;
	private boolean isExclusive;
	private double msrp;
	
	public GameDto() {
		super();
	}
	
	public GameDto(Game g) {
		id = g.getId();
		name = g.getName();
		isExclusive = g.isExclusive();
		msrp = g.getMsrp();
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

	public boolean isExclusive() {
		return isExclusive;
	}

	public void setExclusive(boolean isExclusive) {
		this.isExclusive = isExclusive;
	}

	public double getMsrp() {
		return msrp;
	}

	public void setMsrp(double msrp) {
		this.msrp = msrp;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, isExclusive, msrp, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameDto other = (GameDto) obj;
		return id == other.id && isExclusive == other.isExclusive
				&& Double.doubleToLongBits(msrp) == Double.doubleToLongBits(other.msrp)
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "GameDto [id=" + id + ", name=" + name + ", isExclusive=" + isExclusive + ", msrp=" + msrp + "]";
	}
	
	
}
