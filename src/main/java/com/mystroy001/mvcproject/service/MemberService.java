package com.mystroy001.mvcproject.service;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import com.mystroy001.mvcproject.domain.MemberDAO;
import com.mystroy001.mvcproject.domain.MemberDTO;

public class MemberService {

	public void insertMember(HttpServletRequest request) {
		System.out.println("MemberService insertMember()");
		try {
			request.setCharacterEncoding("utf-8");

			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			Timestamp date = new Timestamp(System.currentTimeMillis());
			
			//MemberDTO 객체 생성 → 메모리 할당
			MemberDTO memberDTO = new MemberDTO();
			//멤버변수에 request에 가져온 값을 set() 메서드를 호출해서 저장
			memberDTO.setId(id);
			memberDTO.setPw(pw);
			memberDTO.setName(name);
			memberDTO.setDate(date);
			
			MemberDAO memberDAO = new MemberDAO();
			memberDAO.insertMember(memberDTO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//
	
	//loginPro.me 주소가 일치하면 MemberService 객체 생성
	public boolean userCheck(HttpServletRequest request) {
		System.out.println("MemberService userCheck()");
		boolean result = false;
		try {
			request.setCharacterEncoding("utf-8");

			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			
			MemberDAO memberDAO = new MemberDAO();
			result = memberDAO.userCheck(id, pw);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public MemberDTO getMember(String id) {
		System.out.println("MemberService getMember()");
		MemberDTO memberDTO = null;
		try {
			MemberDAO memberDAO = new MemberDAO();
			memberDTO = memberDAO.getMember(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberDTO;
	}

	public void updateMember(HttpServletRequest request) {
		System.out.println("MemberService updateMember()");
		try {
			request.setCharacterEncoding("utf-8");
			
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			Timestamp date = new Timestamp(System.currentTimeMillis());
			
			MemberDTO memberDTO = new MemberDTO();
			memberDTO.setId(id);
			memberDTO.setPw(pw);
			memberDTO.setName(name);
			memberDTO.setDate(date);
			
			MemberDAO memberDAO = new MemberDAO();
			memberDAO.updateMember(memberDTO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
