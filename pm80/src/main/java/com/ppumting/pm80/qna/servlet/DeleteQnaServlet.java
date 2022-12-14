package com.ppumting.pm80.qna	.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppumting.pm80.qna.domain.Qna;
import com.ppumting.pm80.qna.service.QnaService;

@WebServlet("/Qna/qnaInfo/deleteQna.do")
public class DeleteQnaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QnaService qnaService;
	
	public void init() {
		qnaService = new QnaService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			
			request.setCharacterEncoding("UTF-8");
			
			String qnaNo = request.getParameter("qnaNo");	// jsp에 name값이 파라미터로 들어가야함
			
			// DELETE 실패 시
			if( qnaService.deleteQna(qnaNo) == false ) { 
				RequestDispatcher dispatcher = request.getRequestDispatcher("deleteResult/error.jsp");
				dispatcher.forward(request, response);
			}
			
			// DELETE 성공 시
			request.setAttribute("qnaNo", qnaNo);
			RequestDispatcher dispatcher = request.getRequestDispatcher("deleteResult/success.jsp");
			dispatcher.forward(request, response);
	}

}
