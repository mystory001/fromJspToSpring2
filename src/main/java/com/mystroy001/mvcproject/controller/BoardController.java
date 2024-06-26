package com.mystroy001.mvcproject.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mystroy001.mvcproject.domain.BoardDTO;
import com.mystroy001.mvcproject.service.BoardService;

public class BoardController extends HttpServlet{
	
	RequestDispatcher dispatcher = null;
	BoardService boardService = null;
	BoardDTO boardDTO = null;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardController doGet()");
		doProcess(request, response);
	}//doGet()

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardController doPost()");
		doProcess(request, response);
	}//doPost()
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardController doProcess()");
		
		String sPath = request.getServletPath();
		System.out.println("가상주소 뽑아오기 : " + sPath);
		
		if(sPath.equals("/write.bo")) {
			System.out.println("가상주소 : " + sPath);
			dispatcher = request.getRequestDispatcher("board/write.jsp");
			dispatcher.forward(request, response);
		}
		
		if(sPath.equals("/writePro.bo")) {
			System.out.println("가상주소 : " + sPath);
			request.setAttribute("boardDTO", boardDTO);
			boardService = new BoardService();
			boardService.insertBoard(request);
			response.sendRedirect("list.bo");
		}
		
		if(sPath.equals("/list.bo")) {
			System.out.println("가상주소 /list.bo와 일치");
			request.setCharacterEncoding("utf-8");
			boardService = new BoardService();
			ArrayList<BoardDTO> boardList = boardService.getBoardList();
			request.setAttribute("boardList", boardList);
			dispatcher = request.getRequestDispatcher("board/list.jsp");
			dispatcher.forward(request, response);
		}
		
		if(sPath.equals("/content.bo")) {
			System.out.println("가상주소 /content.bo와 일치");
			//content.jsp?num=
			//request에서 num 가져오기
			int num = Integer.parseInt(request.getParameter("num"));
			
			//BoardService객체 생성 후 조회수 증가 메서드 호출
			boardService = new BoardService();
			boardService.updateReadCount(num);
			
			//num에 대한 글 가져오기
			boardDTO = boardService.getBoard(num);
			
			//request에 boardDTO 값 가져오기
			request.setAttribute("boardDTO", boardDTO);
			
			dispatcher = request.getRequestDispatcher("board/content.jsp");
			dispatcher.forward(request, response);
		}
		
		if(sPath.equals("/update.bo")) {
			System.out.println("가상주소 /update.bo와 일치");
			int num = Integer.parseInt(request.getParameter("num"));
			boardService = new BoardService();
			BoardDTO boardDTO = boardService.getBoard(num);
			request.setAttribute("boardDTO", boardDTO);
			dispatcher = request.getRequestDispatcher("board/update.jsp");
			dispatcher.forward(request, response);
		}
		
		if(sPath.equals("/updatePro.bo")) {
			System.out.println("가상주소 /updatePro.bo와 일치");
			boardService = new BoardService();
			boardService.updateBoard(request);
			response.sendRedirect("list.bo");
		}


	}


	
}