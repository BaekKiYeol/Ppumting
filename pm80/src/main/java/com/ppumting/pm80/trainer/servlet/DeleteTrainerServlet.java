package com.ppumting.pm80.trainer.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ppumting.pm80.trainer.domain.Trainer;
import com.ppumting.pm80.trainer.service.TrainerService;


@WebServlet("/Trainer/delete/deleteTrainer")
public class DeleteTrainerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  TrainerService trainerService;
	
	public void init() {
		trainerService = new TrainerService();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
//		String trainerId = (String)session.getAttribute("trainerId");
		String trainerId = request.getParameter("trainerId");
		String passwd = request.getParameter("passwd");
		
		Trainer trainer = new Trainer();
		trainer.setTrainerId(trainerId);
		trainer.setSsn(passwd);
		trainerService.delete(trainerId, passwd);
		request.setAttribute("trainer", trainer);
		
		session.setAttribute("trainerId", trainerId);
		
//		RequestDispatcher dispatcher = null;
//		if(errorMsgs.size() > 0 ) {
//			dispatcher = request.getRequestDispatcher("deletefailure.jsp");
//			request.setAttribute("errorMsgs", errorMsgs);
//			dispatcher.forward(request, response);
//			return;
//		}
		
//		dispatcher = request.getRequestDispatcher("deletesuccess.jsp");
//		dispatcher.forward(request, response);

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String trainerId = request.getParameter("trainerId");
		String passwd = request.getParameter("passwd");
		
		//트레이너 삭제 실패
		if(trainerService.delete(trainerId, passwd) == false) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("delete/deletefailure.jsp");
			dispatcher.forward(request, response);
		}
		//트레이저 삭제 성공
		request.setAttribute("trainerId", trainerId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("delete/deletesuccess.jsp");
		dispatcher.forward(request, response);
		
	}
}
