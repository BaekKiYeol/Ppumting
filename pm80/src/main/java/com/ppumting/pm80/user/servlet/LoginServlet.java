package com.ppumting.pm80.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ppumting.pm80.user.service.Userservice;

@WebServlet("/User/mypage/loginout/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Userservice userService;
	
	public void init() {
		userService = new Userservice();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String pw = request.getParameter("pw");
		
		if(userService.login(userId, pw)) {
			HttpSession session = request.getSession(true);
			session.setAttribute("userId", userId);
			response.sendRedirect("../home.jsp");
		}else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
	}
}