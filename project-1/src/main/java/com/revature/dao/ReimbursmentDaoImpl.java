package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.util.ConnectionUtil;

public class ReimbursmentDaoImpl implements ReimbursmentDao{
	
	private static Logger log = Logger.getLogger(ReimbursmentDaoImpl.class);
	

	@Override
	public boolean insert(Reimbursement r) {
		
		Connection conn = ConnectionUtil.getConnection();
		
		String columns = "amount, submitted, description, author, status_id, type_id";
		String sql = "INSERT INTO project1.reimbursments (" + columns + ") VALUES(?, ?, ?, ?, ?, ?)";
		
		PreparedStatement stmt;
		
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setDouble(1, r.getAmount());
			stmt.setTimestamp(2, r.getSubmitted());
			stmt.setString(3, r.getDescription());
			stmt.setInt(4, r.getAuthor());
			stmt.setInt(5, 2);
			stmt.setInt(6, r.getType().getId());
			
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			log.warn("Unable to insert Reimbursment");
			e.printStackTrace();
			return false;
		}
		

		
		return true;
	}

	@Override
	public List<Reimbursement> findAllReim() {
		
		List<Reimbursement> allReimburse = new ArrayList<Reimbursement>();
		
		Connection conn = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM project1.reimbursments INNER JOIN project1.reimburse_status ON project1.reimbursments.status_id = project1.reimburse_status.id\n"
				+ " INNER JOIN project1.reimburse_type ON project1.reimbursments.type_id = project1.reimburse_type.id;";
			
		Statement stmt;
		
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				double amount = rs.getDouble("amount");
				Timestamp submitted = rs.getTimestamp("submitted");
				Timestamp resolved = rs.getTimestamp("resolved");
				String description = rs.getString("description");
				int author = rs.getInt("author");
				int resolver = rs.getInt("resolver");
				int statusId = rs.getInt("status_id");
				int typeId = rs.getInt("type_id");
				String reimburseStatus = rs.getString("r_status");
				String reimburseType = rs.getString("r_type");
				
				ReimbursementStatus status = new ReimbursementStatus(statusId, reimburseStatus);
				ReimbursementType type = new ReimbursementType(typeId, reimburseType);
				Reimbursement r = new Reimbursement(id, amount, submitted, resolved, description, author, resolver, type, status);
				
				allReimburse.add(r);	
				
			}
		} catch (SQLException e) {
			log.warn("SQL statement did not execute correctly!");
			e.printStackTrace();
			return new ArrayList<>();
		}
		
		
		
		return allReimburse;
	}

	@Override
	public List<Reimbursement> findByUser(String username) {
		
		List <Reimbursement> rList = new ArrayList<Reimbursement>();
		
		Connection conn = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM project1.reimbursments INNER JOIN project1.reimburse_status ON project1.reimbursments.status_id = project1.reimburse_status.id\n"
				+ " INNER JOIN project1.reimburse_type ON project1.reimbursments.type_id = project1.reimburse_type.id INNER JOIN project1.users ON "
				+ "project1.reimbursments.author = project1.users.id WHERE "
				+ " username = '" + username + "'";
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				double amount = rs.getDouble("amount");
				Timestamp submitted = rs.getTimestamp("submitted");
				Timestamp resolved = rs.getTimestamp("resolved");
				String description = rs.getString("description");
				int author = rs.getInt("author");
				int resolver = rs.getInt("resolver");
				int statusId = rs.getInt("status_id");
				int typeId = rs.getInt("type_id");
				String reimburseStatus = rs.getString("r_status");
				String reimburseType = rs.getString("r_type");
				
				ReimbursementStatus status = new ReimbursementStatus(statusId, reimburseStatus);
				ReimbursementType type = new ReimbursementType(typeId, reimburseType);
				Reimbursement r = new Reimbursement(id, amount, submitted, resolved, description, author, resolver, type, status);
				
				 rList.add(r);
			}
				
		} catch (SQLException e) {
			log.warn("SQL Statement did not execute");
			e.printStackTrace();
			return new ArrayList<>();
		}
		
		return rList;
	}

	@Override
	public boolean updateStatus(Reimbursement r) {
		
		int id = r.getId();
		
		Connection conn = ConnectionUtil.getConnection();
		String sql = "UPDATE project1.reimbursments SET status_id = ? WHERE id = " + id + "";
		
		try {
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, r.getStatus().getId());
		stmt.executeUpdate();
		
		
		}catch(SQLException e) {
			
			log.warn("SQL statement did not execute correctly!");
			e.printStackTrace();
			return false;
			
		}
		
		return true;
	}

}
