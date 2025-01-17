package com.revature.models;

import java.sql.Timestamp;

public class Reimbursement {
	
	private int id;	// PK
	private double amount;
	private Timestamp submitted;
	private Timestamp resolved;
	private String description;
	private int author;	//foreign key of that references users.id (PK)
	private int resolver;	//FK references users.id but for the managers
	private ReimbursementType type;	//FK 	
	private ReimbursementStatus status;	//FK
	
	public Reimbursement() {
		super();
	}
	
	

	public Reimbursement(double amount, Timestamp submitted, Timestamp resolved, String description, int author,
			int resolver, ReimbursementType type, ReimbursementStatus status) {
		super();
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.author = author;
		this.resolver = resolver;
		this.type = type;
		this.status = status;
	}
	
	



	public Reimbursement(double amount, Timestamp submitted, String description, int author, int typel, int statusl) {
		super();
		this.amount = amount;
		this.submitted = submitted;
		this.description = description;
		this.author = author;
		
		if(typel == 1) {
			type = new ReimbursementType(1, "Lodging");
		}else if(typel == 2) {
			type = new ReimbursementType(2, "Travel");
		}else if(typel == 3) {
			type = new ReimbursementType(3, "Food");
		}else if(typel == 4) {
			type = new ReimbursementType(4, "Other");
		}
		
		if(statusl == 1) {
			status = new ReimbursementStatus(1, "Approved");
		}else if(statusl == 2) {
			status = new ReimbursementStatus(2, "Pending");
		}else if(statusl == 3) {
			status = new ReimbursementStatus(3, "Denied");
		}
	}



	public Reimbursement(double amount, Timestamp submitted, String description, int author, ReimbursementType type,
			ReimbursementStatus status) {
		super();
		this.amount = amount;
		this.submitted = submitted;
		this.description = description;
		this.author = author;
		this.type = type;
		this.status = status;
	}



	public Reimbursement(int id, double amount, Timestamp submitted, Timestamp resolved, String description, int author,
			int resolver, ReimbursementType type, ReimbursementStatus status) {
		super();
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.author = author;
		this.resolver = resolver;
		this.type = type;
		this.status = status;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public double getAmount() {
		return amount;
	}



	public void setAmount(double amount) {
		this.amount = amount;
	}



	public Timestamp getSubmitted() {
		return submitted;
	}



	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}



	public Timestamp getResolved() {
		return resolved;
	}



	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public int getAuthor() {
		return author;
	}



	public void setAuthor(int author) {
		this.author = author;
	}



	public int getResolver() {
		return resolver;
	}



	public void setResolver(int resolver) {
		this.resolver = resolver;
	}



	public ReimbursementType getType() {
		return type;
	}



	public void setType(ReimbursementType type) {
		this.type = type;
	}



	public ReimbursementStatus getStatus() {
		return status;
	}



	public void setStatus(ReimbursementStatus status) {
		this.status = status;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + author;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((resolved == null) ? 0 : resolved.hashCode());
		result = prime * result + resolver;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
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
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (author != other.author)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (resolved == null) {
			if (other.resolved != null)
				return false;
		} else if (!resolved.equals(other.resolved))
			return false;
		if (resolver != other.resolver)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (submitted == null) {
			if (other.submitted != null)
				return false;
		} else if (!submitted.equals(other.submitted))
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
		return "Reimbursment [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + ", author=" + author + ", resolver=" + resolver + ", type=" + type
				+ ", status=" + status + "]";
	}
	
	
	
	

}
