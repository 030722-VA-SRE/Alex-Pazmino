package com.revature.models;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Check;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="platforms")
@Check(constraints = "msrp > 0")
public class Platform {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(unique = true, nullable = false, name="platform")
	private String name;
	@Column(nullable = false)
	private double msrp;
	@OneToMany(mappedBy = "platform", orphanRemoval = true, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Game> games;
	
	public Platform() {
		super();
	}
	
	public Platform(int id) {
		this();
		this.id = id;
	}
	public Platform(int id, String name, double msrp) {
		this();
		this.id = id;
		this.name = name;
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

	public double getMsrp() {
		return msrp;
	}

	public void setMsrp(double msrp) {
		this.msrp = msrp;
	}
	
	@JsonManagedReference
	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	@Override
	public int hashCode() {
		return Objects.hash(games, id, msrp, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Platform other = (Platform) obj;
		return Objects.equals(games, other.games) && id == other.id
				&& Double.doubleToLongBits(msrp) == Double.doubleToLongBits(other.msrp)
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Platform [id=" + id + ", name=" + name + ", msrp=" + msrp + ", games=" + games + "]";
	}


	
	
	
	
	
}
