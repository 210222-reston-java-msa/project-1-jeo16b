package com.revature.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.util.RequestHelper;


public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String URI = request.getRequestURI().replace("/project-1/", "");
		
		switch(URI) {
		case "login":
			RequestHelper.processLogin(request, response);
			break;
		case "logout":
			RequestHelper.processLogout(request, response);
			break;
		case "users":
			RequestHelper.processUsers(request, response);
			break;
		case "reimbursements":
			RequestHelper.processReimbursements(request, response);
			break;
		case "error":
			RequestHelper.processError(request, response);
			break;
		case "employee":
			break;
		case "newReimbursement":
			RequestHelper.processNewReimburse(request, response);
			break;
		case "update":
			RequestHelper.processUserUpdate(request, response);
			break;
		case "reimByUser":
			RequestHelper.processReimbursementsByUsername(request, response);
			break;
		case "pendingReimbursements":
			RequestHelper.processPendingReim(request, response);
			break;
		case "resolvedReimbursements":
			RequestHelper.processResolvedReim(request, response);
			break;
		case "myPendingReimbursements":
			RequestHelper.processPendingReimByUsername(request, response);
			break;
		case "myPastReimbursements":
			RequestHelper.processMyPastReimbursement(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
