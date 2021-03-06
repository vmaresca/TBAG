package edu.ycp.cs320.teamproject.tbag.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.teamproject.tbag.controller.LoginController;
import edu.ycp.cs320.teamproject.tbag.model.Login;


public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Login Servlet: doGet");	
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("login Servlet: doPost");
		

		/*
		 * Initiate the controller and model
		 * Set the mode
		 * Must do this each time since they don't persist between POSTs
		 */
		LoginController controller = new LoginController();
		Login model = new Login(); 
		controller.setModel(model);

		// holds the error message text, if there is any
		String errorMessage = null;

		
		// decode POSTed form parameters and dispatch to controller
		try 
		{
			String username = getStringFromParameter(req.getParameter("username"));
			String password = getStringFromParameter(req.getParameter("password"));

			// check for errors in the form data before using is in a calculation
			if (username == null) 
			{
				errorMessage = "Please specify username";
			}
			if (password == null) 
			{
				errorMessage = "Please specify password";
			}
			// otherwise, data is good, do the calculation using controller
			else 
			{
				controller.checkID(username, password);//change to user name and password verification methods****************************
			}
		} 
		catch (NumberFormatException e) 
		{
			errorMessage = "Invalid input please try again";
		}
		
		// Add parameters as request attributes
		req.setAttribute("login", model);
		
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);

		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}

	// gets double from the request with attribute named s
	private String getStringFromParameter(String s) 
	{
		if (s == null || s.equals("")) 
		{
			return null;
		} 
		else 
		{
			return s;
		}
	}
}
