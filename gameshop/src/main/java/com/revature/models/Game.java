package com.revature.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Check;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "games")
@Check(constraints = "msrp > 0")
public class Game {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true, nullable = false, name = "game")
	private String name;
	@ManyToOne
	@JoinColumn(nullable = false, name = "platform_id")
	private Platform platform;
	@Column(nullable = false, name = "console_exclusive")
	private boolean isExclusive;
	@Column(nullable = false, columnDefinition = "NUMERIC")
	private double msrp;
	
	public Game() {
		super();
	}
	
	public Game(int id, String name, boolean isExclusive, double msrp) {
		this();
		this.id = id;
		this.name = name;
		this.isExclusive = isExclusive;
		this.msrp = msrp;
	}
	public Game(int id, String name, Platform platform, boolean isExclusive, double msrp) {
		this();
		this.id = id;
		this.name = name;
		this.platform = platform;
		this.isExclusive = isExclusive;
		this.msrp = msrp;
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
	
	@JsonBackReference
	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
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
		return Objects.hash(id, isExclusive, msrp, name, platform);
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
		return id == other.id && isExclusive == other.isExclusive
				&& Double.doubleToLongBits(msrp) == Double.doubleToLongBits(other.msrp)
				&& Objects.equals(name, other.name) && Objects.equals(platform, other.platform);
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", platform=" + platform + ", isExclusive=" + isExclusive
				+ ", msrp=" + msrp + "]";
	}
	
	
	
	
	
	
}
