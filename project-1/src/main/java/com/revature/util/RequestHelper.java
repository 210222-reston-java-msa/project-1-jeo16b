package com.revature.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.LoginTemplate;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

public class RequestHelper {
	
	private static Logger log = Logger.getLogger(RequestHelper.class);
	private static ObjectMapper om = new ObjectMapper();
	
	public static void processLogin(HttpServletRequest req, HttpServletResponse res) throws IOException{
		
		BufferedReader reader = req.getReader();	//converting request into String
		StringBuilder s = new StringBuilder();
		
		String line = reader.readLine();
		while(line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = s.toString();
		log.info(body);
		
		LoginTemplate loginAttempt = om.readValue(body, LoginTemplate.class);	// turning Json object to a java object
		
		String username = loginAttempt.getUsername();
		String password = loginAttempt.getPassword();
		
	
		
	
		
		log.info("User attempted to login with username: " + username);
		User u = UserService.confirmLogin(username, password);
		
			if(u != null) {
				
				HttpSession session = req.getSession();
				
				session.setAttribute("username", username);
				session.setAttribute("password", password);
				
				PrintWriter pw = res.getWriter();
				res.setContentType("application/json");
				
				pw.println(om.writeValueAsString(u));
			
				
				log.info(username + " has successfully logged in");
			}else {
				res.setStatus(204);
			}
			
		
	}
	
	public static void processLogout(HttpServletRequest req, HttpServletResponse res) throws IOException{
		
		HttpSession session = req.getSession(false);	// I'm capturing the session, but if there ISN'T one, I don't create a new one.
		
		log.info("Processing logout");
		
		if(session != null) {
			
			String username = (String) session.getAttribute("username");
			log.info(username + " has logged out");
			
			session.invalidate();
		}
		
		res.setStatus(200);
	
	}
		
	public static void processUsers(HttpServletRequest req, HttpServletResponse res) throws IOException{
			
			// 1. Set the content type to app/json b/c we will be send json data back to the client
			// stuck alongside the response
			
			log.info("processing Users...");
			
			res.setContentType("application/json");
			
			// 2. Get a list of all Employees in the DB
			List<User> allUsers = UserService.findAll();
			
			// 3. Turn the list of Java Obj into a JSON string
			String json = om.writeValueAsString(allUsers);
			
			// 4. Use getWriter() from the response obj to return the json string
			PrintWriter pw = res.getWriter();
			pw.println(json);
			
			
		}
	
	public static void processReimbursements(HttpServletRequest req, HttpServletResponse res) throws IOException{
		
		log.info("processing Reimbursements");
		
		res.setContentType("application/json");
		
		List<Reimbursement> allReimbuse = ReimbursementService.findAllReim();
		
		String json = om.writeValueAsString(allReimbuse);	// turns to JSON
		
		PrintWriter pw = res.getWriter();	//return json string
		pw.println(json);
	}
	
	public static void processReimbursementsByUsername(HttpServletRequest req, HttpServletResponse res) throws IOException{
		
		BufferedReader reader = req.getReader();	//converting request into String
		StringBuilder s = new StringBuilder();
		
		String line = reader.readLine();
		while(line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = s.toString();
		log.info(body);
		
		String username = om.readValue(body, String.class);
		
		res.setContentType("application/json");
		
		List<Reimbursement> rlist = ReimbursementService.findByUser(username);
		
			
		String json = om.writeValueAsString(rlist);
		PrintWriter pw = res.getWriter();
		pw.println();
		

		log.info(username + " has reimbursements");
		
	}
	
	public static void processPendingReimByUsername(HttpServletRequest req, HttpServletResponse res) throws IOException{
		
		BufferedReader reader = req.getReader();	//converting request into String
		StringBuilder s = new StringBuilder();
		
		String line = reader.readLine();
		while(line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = s.toString();
		log.info(body);
		
		User user = om.readValue(body, User.class);
		
		
		

		
		List<Reimbursement> rlist = ReimbursementService.findByUser(user.getUsername());
		List<Reimbursement> plist = new ArrayList<Reimbursement>();
		
		for(int i=0; i < rlist.size(); i++) {
			if(rlist.get(i).getStatus().getId() == 2) {
				plist.add(rlist.get(i));
				
			}
		}
		
		
		res.setContentType("application/json");
			
		String json = om.writeValueAsString(plist);
		PrintWriter pw = res.getWriter();
		pw.println(json);
		

		log.info(user.getUsername() + " has pending reimbursements");
		
		
		
		
		
	}
	
	public static void processUserUpdate(HttpServletRequest req, HttpServletResponse res) throws IOException{
		BufferedReader reader = req.getReader();	//converting request into String
		StringBuilder s = new StringBuilder();
		
		String line = reader.readLine();
		while(line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = s.toString();
		log.info(body);
		
		User u = om.readValue(body, User.class);	// turning Json object to a java object
		
		String firstName = u.getFirstName();
		String lastName = u.getLastName();
		String username = u.getUsername();
		String password = u.getPassword();
		String email = u.getEmail();
		int r = u.getRole().getId();
		
		boolean b = UserService.update(u);
		
		
		if(b == true) {

				
			HttpSession session = req.getSession();
			
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			session.setAttribute("firstName", firstName);
			session.setAttribute("lastName", lastName);
			session.setAttribute("email", email);
			session.setAttribute("role", r);
			
			
			PrintWriter pw = res.getWriter();
			res.setContentType("application/json");
			
			pw.println(om.writeValueAsString(u));
		
			
			log.info(username + " has successfully logged in");

		
		
		
		
		log.info("processing update");
		}else
			log.warn("Unable to update user");
		
		
	}
	
	public static void processNewReimburse(HttpServletRequest req, HttpServletResponse res) throws IOException{
		
		BufferedReader reader = req.getReader();	//converting request into String
		StringBuilder s = new StringBuilder();
		
		String line = reader.readLine();
		while(line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = s.toString();
		
		Reimbursement newReim = om.readValue(body, Reimbursement.class);
		
		double amount = newReim.getAmount();
		Timestamp t = newReim.getSubmitted();
		String des = newReim.getDescription();
		int author = newReim.getAuthor();
		int type = newReim.getType().getId();
		int stat = newReim.getStatus().getId();
		
		boolean update = ReimbursementService.insert(newReim);
		
		if(update == true) {
			log.info("Successful");
			

			HttpSession session = req.getSession();
			
			session.setAttribute("amount", amount);
			session.setAttribute("time", t);
			session.setAttribute("description", des);
			session.setAttribute("author", author);
			session.setAttribute("type", type);
			session.setAttribute("status", stat);
			
			PrintWriter pw = res.getWriter();
			res.setContentType("application/json");
			
			pw.println(om.writeValueAsString(newReim));
		
		}else
			log.warn("Unsucessful");
	}
	
	public static void processPendingReim(HttpServletRequest req, HttpServletResponse res) throws IOException{
		
		log.info("processing Reimbursements");
				
				res.setContentType("application/json");
				
				List<Reimbursement> allReimburse = ReimbursementService.findAllReim();
				
				List<Reimbursement> pendingReimburse = new ArrayList<Reimbursement>();
				
				for(int i=0; i < allReimburse.size(); i++) {
					if(allReimburse.get(i).getStatus().getId() == 2) {
						pendingReimburse.add(allReimburse.get(i));
						
					}
				}
				
				String json = om.writeValueAsString(pendingReimburse);	// turns to JSON
				
				PrintWriter pw = res.getWriter();	//return json string
				pw.println(json);
	}
	
	
	public static void processResolvedReim(HttpServletRequest req, HttpServletResponse res) throws IOException{
			
			log.info("processing Reimbursements");
					
					res.setContentType("application/json");
					
					List<Reimbursement> allReimburse = ReimbursementService.findAllReim();
					
					List<Reimbursement> resolvedReimburse = new ArrayList<Reimbursement>();
					
					for(int i=0; i < allReimburse.size(); i++) {
						if(allReimburse.get(i).getStatus().getId() != 2) {
							resolvedReimburse.add(allReimburse.get(i));
							
						}
					}
					
					String json = om.writeValueAsString(resolvedReimburse);	// turns to JSON
					
					PrintWriter pw = res.getWriter();	//return json string
					pw.println(json);
	}
	
	public static void processMyPastReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException{
		
		BufferedReader reader = req.getReader();	//converting request into String
		StringBuilder s = new StringBuilder();
		
		String line = reader.readLine();
		while(line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = s.toString();
		log.info(body);
		
		User u = om.readValue(body, User.class);
		
		log.info("processing Past Reimbursements");
		
		res.setContentType("application/json");
		
		List<Reimbursement> allReimburse = ReimbursementService.findByUser(u.getUsername());
		
		List<Reimbursement> pastReimburse = new ArrayList<Reimbursement>();
		
		for(int i=0; i < allReimburse.size(); i++) {
			if(allReimburse.get(i).getStatus().getId() != 2) {
				pastReimburse.add(allReimburse.get(i));
				
			}
		}
		
		String json = om.writeValueAsString(pastReimburse);	// turns to JSON
		
		PrintWriter pw = res.getWriter();	//return json string
		pw.println(json);
}

	
	public static void processError(HttpServletRequest request, HttpServletResponse response) {
			
			try {
				request.getRequestDispatcher("error.html").forward(request, response);
				// we do NOT create a new project
				//we also maintain the url...
			} catch (ServletException | IOException e) {
	
				e.printStackTrace();
			}
			
	}

}
