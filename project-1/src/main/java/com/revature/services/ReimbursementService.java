package com.revature.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dao.ReimbursmentDao;
import com.revature.dao.ReimbursmentDaoImpl;

import com.revature.models.Reimbursement;


public class ReimbursementService {
	
	public static ReimbursmentDao rDao = new ReimbursmentDaoImpl();
	private static Logger log = Logger.getLogger(ReimbursementService.class);
	
	public static boolean insert(Reimbursement r) {
		return rDao.insert(r);
	}
	public static List<Reimbursement> findAllReim(){
		return rDao.findAllReim();
	}
	public static List<Reimbursement> findByUser(String username){
		return rDao.findByUser(username);
	}
	
	public boolean updateStatus(Reimbursement r) {
		return rDao.updateStatus(r);
	}
	
	public boolean updatePendingStatus(Reimbursement r) {
		
		if(r.getStatus().getId() == 1 || r.getStatus().getId() == 3) {
			
			log.info("Reimbursement stautus already resolved");
			return false;
		}else {
			return updateStatus(r);
		
		}
		
		
	}
}
