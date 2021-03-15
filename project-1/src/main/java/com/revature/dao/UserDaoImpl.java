package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDaoImpl implements UserDao{
	
	private static Logger log = Logger.getLogger(UserDaoImpl.class);

	@Override
	public boolean insert(User u) {

		try {
		Connection conn = ConnectionUtil.getConnection();	//capture connection
		
		String sql = "INSERT INTO project1.users (first_name, last_name, username, pass, email, role_id)"
				+ " VALUES(?, ?, ?, ?, ?, ?)";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, u.getFirstName());
		stmt.setString(2, u.getLastName());
		stmt.setString(3, u.getUsername());
		stmt.setString(4, u.getPassword());
		stmt.setString(5, u.getEmail());
		stmt.setInt(6, u.getRole().getId());
		
		if(!stmt.execute()) {
			return false;
		}
		
		}catch(SQLException e) {
			log.info("SQL statement did not execute");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public List<User> findAll() {

		List<User> allUsers = new ArrayList<User>();
		
		Connection conn = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM project1.users INNER JOIN project1.roles ON project1.users.role_id = project1.roles.id";
		
		try {
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String username = rs.getString("username");
				String pass = rs.getString("pass");
				String email = rs.getString("email");
				int roleId = rs.getInt("role_id");
				String roleName = rs.getString("role_name");
				
				// now we use the data we just pulled to create a User object
				Role r = new Role(roleId, roleName);
				User u = new User(id, firstName, lastName, username, pass, email, r);
				
				// Now make sure to add it to our list of Users
				allUsers.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<User>();
		}
		
		return allUsers;
	}

	@Override
	public User findById(int id) {
		
		User u = new User();
		
		Connection conn = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM project1.users INNER JOIN project1.roles ON project1.users.role_id = project1.roles.id WHERE project1.users.id = " + id + "";
		// This is returning BOTH user info and the name of their Role
		
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				u.setId(rs.getInt("id"));
				u.setFirstName(rs.getString("first_name"));
				u.setLastName(rs.getString("last_name"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("pass"));
				u.setEmail(rs.getString("email"));
				u.setRole(new Role(rs.getInt("role_id"), rs.getString("role_name")));
				
			}
			
		
			
			
		} catch (SQLException e) {

			log.warn("SQL statement did not execute correctly");
			e.printStackTrace();
			// if something goes wrong, return an empty list
			return new User();
		}
		
	
		// If everything goes well, return the allUsers arrayList
		return u;
		
	}


	@Override
	public boolean update(User u) {
		
		int idToUpdate = u.getId();
		
		try {
			Connection conn = ConnectionUtil.getConnection();
			
			String sql = "UPDATE project1.users SET first_name = ?,last_name = ?,"
					+ "username = ?, pass = ?,email = ?, role_id = ?"
					+ "WHERE id = " + idToUpdate + "";
	
			
	
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, u.getFirstName());
			stmt.setString(2, u.getLastName());
			stmt.setString(3, u.getUsername());
			stmt.setString(4, u.getPassword());
			stmt.setString(5, u.getEmail());
			stmt.setInt(6, u.getRole().getId());
			stmt.executeUpdate();
			System.out.println("user updated");
			
		
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	
		
	}

	@Override
	public boolean delete(User u) {
		
		Connection conn = ConnectionUtil.getConnection();
		String sql = "DELETE FROM project1.users WHERE user_id = ?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, u.getId());
			stmt.executeUpdate();
			System.out.println(u.getFirstName() + " deleted!");
		} catch (SQLException e) {
			
			log.warn("SQL statement did not execute in UserDaoImpl.delete()");
			e.printStackTrace();
			return false;
		}
		
		return true;
		
		
	}

}
