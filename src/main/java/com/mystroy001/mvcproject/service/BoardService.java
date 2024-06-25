package com.mystroy001.mvcproject.service;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import com.mystroy001.mvcproject.dao.BoardDAO;
import com.mystroy001.mvcproject.domain.BoardDTO;

public class BoardService {

	BoardDAO boardDAO = null;
	BoardDTO boardDTO = null;
	
	public void insertBoard(HttpServletRequest request) {
		System.out.println("BoardService insertBoard()");
		try {
			request.setCharacterEncoding("utf-8");
			String id = request.getParameter("id");
			String subject = request.getParameter("subject");
			String context = request.getParameter("context");
			boardDAO = new BoardDAO();
			int num = boardDAO.getMaxNum()+1;
			int readCount = 0;
			Timestamp date = new Timestamp(System.currentTimeMillis());
			
			boardDTO = new BoardDTO();
			
			boardDTO.setName(id);
			boardDTO.setSubject(subject);
			boardDTO.setContent(context);
			boardDTO.setDate(date);
			boardDTO.setNum(num);
			boardDTO.setReadCount(readCount);
			
			boardDAO.insertBoard(boardDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
