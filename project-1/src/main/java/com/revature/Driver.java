package com.revature;

import java.sql.Timestamp;
import java.util.Date;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

public class Driver {

	public static void main(String[] args) {
	
		UserService us = new UserService();
		ReimbursementService rs = new ReimbursementService();
		User u = new User(3,"test", "testl", "user", "pass", "test@mail.com", new Role(2, "manager"));
		
		Date date = new Date();
		
		
		Reimbursement r = new Reimbursement(300, new Timestamp(date.getTime()), "For the food", u.getId(), new ReimbursementType(1, "food"),
				new ReimbursementStatus(2, "pending"));
		
		
		
		System.out.println(rs.findByUser("e123"));

	}
}
