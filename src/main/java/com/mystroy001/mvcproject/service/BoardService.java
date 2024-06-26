package com.mystroy001.mvcproject.service;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.mystroy001.mvcproject.dao.BoardDAO;
import com.mystroy001.mvcproject.domain.BoardDTO;

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

	public ArrayList<BoardDTO> getBoardList() {
		System.out.println("BoardService getBoardList()");
		ArrayList<BoardDTO> boardList = null;
		try {
			BoardDAO boardDAO = new BoardDAO();
			boardList = boardDAO.getBoardList();
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
		return null;
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

}
