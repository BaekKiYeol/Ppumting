package com.ppumting.pm80.point.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ppumting.pm80.point.service.PointService;

@WebServlet("/point/createAccount")
public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PointService pointService;

	public void init(ServletConfig config) throws ServletException {
		pointService = new PointService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("createAccount.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		List<String> accountNum = new ArrayList<>();
		
		//실패 시 로직 //없는 아이디나 공백을 입력하고 계좌생성 한 경우
		if ( pointService.checkPoint(userId) == null || pointService.checkPoint(userId).length() == 0) {
			request.getRequestDispatcher("createAccountResult/error.jsp").forward(request, response);
			return;
		}
		//성공 시 로직
		accountNum.add(pointService.createAccountNum(userId)); //생성된 계좌를 배열에 담기
		request.setAttribute("accountNum", accountNum);
		request.setAttribute("userId", userId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("createAccountResult/success.jsp");
		dispatcher.forward(request, response);
		
		HttpSession session = request.getSession(true);
		session.setAttribute("userId", userId);
		response.sendRedirect("createAccountResult/success.jsp");
		
	}

}