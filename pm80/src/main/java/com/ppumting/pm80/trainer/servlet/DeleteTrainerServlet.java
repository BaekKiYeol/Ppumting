package com.ppumting.pm80.trainer.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppumting.pm80.trainer.domain.Trainer;
import com.ppumting.pm80.trainer.service.TrainerService;


@WebServlet("/Trainer/delete/deleteTrainer")
public class DeleteTrainerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  TrainerService trainerService;
	
	public void init() {
		trainerService = new TrainerService();
	}
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String trainerId = request.getParameter("trainerId");
		String name = request.getParameter("name");
		String ssn = request.getParameter("ssn");

		List<String> errorMsgs = new ArrayList<>();
		if(name == null || name.length() == 0) {
			errorMsgs.add("이름을 입력해주세요");
		}else if(ssn == null || ssn.length() == 0) {
			errorMsgs.add("주민번호를 입력해주세요");
		}else if(trainerId == null || trainerId.length() == 0) {
			errorMsgs.add("Id를 입력해주세요");
		}
		
		RequestDispatcher dispatcher = null;

		if(errorMsgs.size() > 0 ) {
			dispatcher = request.getRequestDispatcher("deletefailure.jsp");
			request.setAttribute("errorMsgs", errorMsgs);
			dispatcher.forward(request, response);
			return;
		}
		
		Trainer trainer = new Trainer();
		trainer.setTrainerId(trainerId);
		trainer.setName(name);
		trainer.setSsn(ssn);
		trainerService.delete(trainerId, name, ssn);
		request.setAttribute("trainer", trainer);
		
		dispatcher = request.getRequestDispatcher("deletesuccess.jsp");
		dispatcher.forward(request, response);

	}
}