package com.mystroy001.mvcproject.service;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.mystroy001.mvcproject.dao.MemberDAO;
import com.mystroy001.mvcproject.domain.MemberDTO;

public class MemberService {

	MemberDAO memberDAO = new MemberDAO();
	MemberDTO memberDTO = new MemberDTO();
	
	public void insertMember(HttpServletRequest request) {
		System.out.println("MemberService insertMember()");
		try {
			request.setCharacterEncoding("utf-8");

			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			Timestamp date = new Timestamp(System.currentTimeMillis());
			
			//멤버변수에 request에 가져온 값을 set() 메서드를 호출해서 저장
			memberDTO.setId(id);
			memberDTO.setPw(pw);
			memberDTO.setName(name);
			memberDTO.setDate(date);
			
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
			
			memberDTO.setId(id);
			memberDTO.setPw(pw);
			memberDTO.setName(name);
			memberDTO.setDate(date);
			
			memberDAO.updateMember(memberDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteMember(HttpServletRequest request) {
		System.out.println("MemberService deleteMember()");
		try {
			String id = request.getParameter("id");
			memberDAO.deleteMember(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<MemberDTO> getMemberList(){
		System.out.println("MemberService getMemberList()");
		ArrayList<MemberDTO> memberList = null;
		try {
			memberList = memberDAO.getMemberList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberList;
	}

}
