package com.jun.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.management.ServiceNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jun.service.RegisterValidateService;


public class RegisterController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9098252664450475369L;
	private HttpSession session;
	private PrintWriter pw;
	private RegisterValidateService rvs = new RegisterValidateService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = request.getSession();
		String[] url = request.getRequestURI().split("/");
		String action = url[url.length - 1];
		if(action.equals("register")){
			String email = request.getParameter("email");
			rvs.processregister(email);
			request.getRequestDispatcher("/register_success").forward(request, response);
		}
		if(action.equals("activate")){
			String email = request.getParameter("email");
			String validateCode = request.getParameter("validateCode");
			try{
				rvs.processActivate(email, validateCode);
				request.getRequestDispatcher("/activate_success").forward(request, response);
			}catch(ServiceNotFoundException | ParseException e){
				request.setAttribute("message", e.getMessage());
				request.getRequestDispatcher("/activate_failure").forward(request, response);
			}
		}
	}

	
	
}
