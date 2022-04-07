package com.revature.dtos;

import java.util.Objects;

import com.revature.models.Platform;

public class PlatformDto {
	
	private int id;
	private String name;
	private double msrp;
	
	public PlatformDto() {
		super();
	}
	
	public PlatformDto(Platform p) {
		id = p.getId();
		name = p.getName();
		msrp = p.getMsrp();
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

	public double getMsrp() {
		return msrp;
	}

	public void setMsrp(double msrp) {
		this.msrp = msrp;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, msrp, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlatformDto other = (PlatformDto) obj;
		return id == other.id && Double.doubleToLongBits(msrp) == Double.doubleToLongBits(other.msrp)
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "PlatformDto [id=" + id + ", name=" + name + ", msrp=" + msrp + "]";
	}
	
	
	
}
