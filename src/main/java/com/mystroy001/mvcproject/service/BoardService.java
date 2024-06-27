package com.mystroy001.mvcproject.service;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.mystroy001.mvcproject.dao.BoardDAO;
import com.mystroy001.mvcproject.domain.BoardDTO;
import com.mystroy001.mvcproject.domain.PageDTO;

public class BoardService {
	
	BoardDAO boardDAO = null;

	public void insertBoard(HttpServletRequest request) {
		System.out.println("BoardService insertBoard()");
		try {
			request.setCharacterEncoding("utf-8");
			
			String name = request.getParameter("name");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			
			BoardDAO boardDAO = new BoardDAO();
			int num = boardDAO.getMaxNum()+1;
			int readCount = 0;
			Timestamp date = new Timestamp(System.currentTimeMillis());
			
			BoardDTO boardDTO = new BoardDTO();
			boardDTO.setName(name);
			boardDTO.setSubject(subject);
			boardDTO.setContent(content);
			boardDTO.setDate(date);
			boardDTO.setNum(num);
			boardDTO.setReadCount(readCount);
			
			boardDAO.insertBoard(boardDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<BoardDTO> getBoardList(PageDTO pageDTO) {
		System.out.println("BoardService getBoardList()");
		ArrayList<BoardDTO> boardList = null;
		try {
			int pageSize = pageDTO.getPageSize();
			int currentPage = pageDTO.getCurrentPage();
			
			//시작하는 행 번호 구하기
			//currentPage pageSize로 startRow가 나오게 계산
			//1 10 => 1+0*10 = 1+0 = 1
			//2 10 => 1+1*10 = 10+1 = 11
			//3 10 => 1+2*10 = 20+1 = 21
			int startRow = (currentPage-1) * pageSize+1;
			
			//끝나는 행 번호 구하기
			//startRow pageSize로 endRow가 나오게 계산
			//1 10 => 1 + 10-1 => 10
			//11 10 => 11 + 10-1 => 20
			//21 10 => 21 + 10-1 => 30
			int endRow = startRow-1 + pageSize;
			
			pageDTO.setStartRow(startRow);
			pageDTO.setEndRow(endRow);
			
			BoardDAO boardDAO = new BoardDAO();
			boardList = boardDAO.getBoardList(pageDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardList;
	}

	public void updateReadCount(int num) {
		System.out.println("BoardService updateReadCount()");
		try {
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.updateReadCount(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BoardDTO getBoard(int num) {
		System.out.println("BoardService getBoard()");
		BoardDTO boardDTO = null;
		try {
			boardDAO = new BoardDAO();
			
			boardDTO = boardDAO.getBoard(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardDTO;
	}

	public void updateBoard(HttpServletRequest request) {
		System.out.println("BoardService updateBoard()");
		try {
			request.setCharacterEncoding("utf-8");
			
			int num = Integer.parseInt(request.getParameter("num"));
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			
			BoardDTO boardDTO = new BoardDTO();
			boardDTO.setNum(num);
			boardDTO.setSubject(subject);
			boardDTO.setContent(content);
			boardDAO = new BoardDAO();
			boardDAO.updateBoard(boardDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void deleteBoard(int num) {
		System.out.println("BoardService deleteBoard()");
		try {
			boardDAO = new BoardDAO();
			boardDAO.deleteBoard(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getBoardCount() {
		System.out.println("BoardService getBoardCount()");
		int count = 0;
		try {
			boardDAO = new BoardDAO();
			count = boardDAO.getBoardCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

}
