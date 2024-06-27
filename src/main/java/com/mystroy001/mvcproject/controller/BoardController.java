package com.mystroy001.mvcproject.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mystroy001.mvcproject.domain.BoardDTO;
import com.mystroy001.mvcproject.domain.PageDTO;
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
			
			//모든 게시판의 시작 1페이지
			//http://localhost:8080/MVCProject/list.bo?pageNum=?
			//한 페이지에 보여줄 글의 개수
			int pageSize = 5;
			//현 페이지 번호 가져오기
			String pageNum = request.getParameter("pageNum");
			if(pageNum==null) {
				pageNum = "1";
			}
			//페이지 번호를 정수형으로 변경해서 계산
			int currentPage = Integer.parseInt(pageNum);
			//PageDTO 객체 생성
			//pageSize, pageNum, currentPage 저장
			PageDTO pageDTO = new PageDTO();
			pageDTO.setPageSize(pageSize);
			pageDTO.setPageNum(pageNum);
			pageDTO.setCurrentPage(currentPage);
			boardService = new BoardService();
			ArrayList<BoardDTO> boardList = boardService.getBoardList(pageDTO);
			//페이징 작업
			//전체 글 개수 구하기
			int count = boardService.getBoardCount();
			//한 화면에 보여줄 페이지 개수 설정
			int pageBlock = 10;
			//1~10,11~20,21~30,...
			//시작하는 페이지 번호 구하기
			//currentPage pageBlock => startPage(정수형 나누기)
			//1~10(0~9) 10개 => (0~9)/10*10+1 => 0*10 +1 => 0 +1  => 1페이지
			//11~20(10~19) 10개 => (10~19)/10*10+1 => 1*10+1 => 10 + 1 => 2페이지
			//21~30(20~29) 10개 => (20~29)/10*10+1 => 2*10+1 => 20 + 1 => 3페이지
			int startPage = (currentPage -1)/pageBlock*pageBlock+1;
			//끝나는 페이지 구하기
			//startPage pageBlock => endPage
			int endPage = (startPage-1)+pageBlock;
			//이 작업까지하면 1~10페이지까지 나옴
			
			//전체 페이지 개수
			//전체 글 개수가 29개라면, 한 화면에 보여줄 글 개수는 10개, 29/10 = 2 => 2+(29%10=9) → 나머지가 있을 경우 1페이지 추가
			//전체 글 개수가 10개라면, 10/10=1 +(10%10=0) => 나머지가 없는 경우 0페이지 추가
			//글 개수/한 화면에 보여줄 개수 +(나머지가 없으면?0:1)
			int pageCount = count/pageSize+((count%pageSize==0)?0:1);
			//endPage > 전체 페이지 개수
			if(endPage>pageCount) {
				endPage=pageCount;
			}
			
			pageDTO.setCount(count);
			pageDTO.setPageBlock(pageBlock);
			pageDTO.setStartPage(startPage);
			pageDTO.setEndPage(endPage);
			pageDTO.setPageCount(pageCount);
			//request에 "pageDTO"저장
			request.setAttribute("pageDTO", pageDTO);
			
			
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
			BoardDTO boardDTO = boardService.getBoard(num);
			
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
		
		if(sPath.equals("/delete.bo")) {
			request.setCharacterEncoding("utf-8");
			System.out.println("가상주소 /deletePro.bo와 일치");
			//delete.bo?num=?
			int num = Integer.parseInt(request.getParameter("num"));
			boardService = new BoardService();
			boardService.deleteBoard(num);
			response.sendRedirect("list.bo");
		}


	}


	
}