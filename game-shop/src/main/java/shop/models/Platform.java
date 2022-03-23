package shop.models;

import java.util.Objects;

public class Platform {
	private int id;
	private String name;
	private float msrp;
	
	public Platform() {
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

	public float getMsrp() {
		return msrp;
	}

	public void setMsrp(float msrp) {
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
		Platform other = (Platform) obj;
		return id == other.id && Float.floatToIntBits(msrp) == Float.floatToIntBits(other.msrp)
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Platform [id=" + id + ", name=" + name + ", msrp=" + msrp + "]";
	}
	
	
	
	
}
