package com.revature.models;

public class ReimbursementStatus {
	
	private int id;	//PK
	private String status;
	
	public ReimbursementStatus() {
		super();
	}
	
	

	public ReimbursementStatus(int id) {
		super();
		this.id = id;
		
		if(id == 1) {
			status = "Approved";
		}else if(id == 2) {
			status = "Pending";
		}else if(id == 3) {
			status = "Denied";
		}
	}



	public ReimbursementStatus(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		ReimbursementStatus other = (ReimbursementStatus) obj;
		if (id != other.id)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReimbursmentStatus [id=" + id + ", status=" + status + "]";
	}
	
	

}
