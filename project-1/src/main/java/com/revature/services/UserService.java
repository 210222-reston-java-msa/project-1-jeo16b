package com.revature.services;

import java.util.List;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.models.User;

public class UserService {
	
	public static UserDao uDao = new UserDaoImpl();
	
	public static boolean insert(User u) {
		
		return uDao.insert(u);	
	}
	
	public static List<User> findAll(){
		
		return uDao.findAll();
	}
	
	public static User findById(int id) {
		
		return uDao.findById(id);
	}
	
	public static boolean update(User u) {
		
		return uDao.update(u);
	}
	
	public static boolean delete(User u) {
		
		return uDao.delete(u);
	}
	
	public static User findByUsername(String un) {
		
		List<User> all = uDao.findAll();
		
		for(User u: all) {
			if(u.getUsername().equals(un)) {
				return u;
			}
		}
		return null;
		
	}
	
	public static User confirmLogin(String username, String password) {
		
		User u = findByUsername(username);
		
		if(u == null) {
			return null;
		}
		
		if(u.getPassword().equals(password)) {
			return u;
		}else {
			return null;
		}
	}

}
