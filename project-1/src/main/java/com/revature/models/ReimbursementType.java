package com.revature.models;

public class ReimbursementType {
	
	private int id;
	private String type;
	
	public ReimbursementType() {
		super();
	}
	
	

	public ReimbursementType(int id) {
		super();
		this.id = id;
		
		if(id == 1) {
			type = "Lodging";
			
		}else if(id == 2) {
			type = "Travel";
			
		}else if(id == 3) {
			type = "Food";
		}else if(id == 4) {
			type = "Other";
		}
	}



	public ReimbursementType(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReimbursementType other = (ReimbursementType) obj;
		if (id != other.id)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReimbursmentType [id=" + id + ", type=" + type + "]";
	}
	
	

}
