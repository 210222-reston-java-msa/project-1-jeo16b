package com.revature.dao;

import java.util.List;

import com.revature.models.Reimbursement;


public interface ReimbursmentDao {
	
	public boolean insert(Reimbursement r);	// CREATE
	
	public List<Reimbursement> findAllReim();	// READ
	public List<Reimbursement> findByUser(String username);
	public boolean updateStatus(Reimbursement r);
	
	

}
