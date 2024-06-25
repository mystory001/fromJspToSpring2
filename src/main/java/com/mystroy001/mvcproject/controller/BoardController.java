package com.mystroy001.mvcproject.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mystroy001.mvcproject.service.BoardService;

public class BoardController extends HttpServlet{
	
	RequestDispatcher dispatcher = null;
	BoardService boardService = null;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardController doGet()");
		doProcess(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardController doPost()");
		doProcess(request, response);
	}
	
	
	//주소 매핑을 위한 메서드 정의
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardController doProcess()");
		
		String sPath = request.getServletPath();
		System.out.println("가상주소 뽑아오기 : " + sPath);
		
		if(sPath.equals("/write.bo")) {
			dispatcher = request.getRequestDispatcher("board/write.jsp");
			dispatcher.forward(request, response);
		}
		
		//여기서부터
		if(sPath.equals("writePro.bo")) {
			boardService = new BoardService();
			boardService.insertBoard(request);
			
			response.sendRedirect("list.bo");
		}

	}


	
}
