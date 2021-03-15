package com.revature.dao;

import java.util.List;

import com.revature.models.User;

// CRUD

public interface UserDao {

	public boolean insert(User u);	// CREATE
	
	public List<User> findAll();	// READ
	public User findById(int id);
	
	public boolean update(User u);	// UPDATE
	
	public boolean delete(User u);	// DELETE
	
	
}
