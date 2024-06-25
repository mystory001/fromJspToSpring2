package com.mystroy001.mvcproject.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;
import com.mystroy001.mvcproject.dao.MemberDAO;
import com.mystroy001.mvcproject.domain.MemberDTO;
import com.mystroy001.mvcproject.service.MemberService;

public class MemberController extends HttpServlet{

	/*
	MemberController 파일을 servlet파일로 지정
	→ public class MemberController extends HttpServlet
	→ HttpServlet 처리담당객체를 상속
	→ 웹 애플리케이션 서버(처리담당서버)가 자동으로 서블릿 메소드 호출
	→ service(), doGet(), doPost(),... 자동으로 호출
	→ doGet(), doPost() 메소드 오버라이딩해서 주소매핑 작업 처리
	 */
	
	RequestDispatcher dispatcher = null;
	HttpSession session = null;
	MemberService memberService = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberController doGet()");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberController doPost()");
		doProcess(request, response);
	}
	
	//주소 매핑을 위한 메서드 정의
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberController doProcess()");
		//가상주소 http://localhost:8080/Since240623/insert.me에서 가상주소 뽑기
		String sPath = request.getServletPath();
		System.out.println("가상주소 뽑아오기 : " + sPath);
		//주소 매핑 → 가상 주소 비교 → 실제 페이지 연결
		if(sPath.equals("/insert.me")) {
			System.out.println("가상주소 일치 여부 : " + sPath.equals("/insert.me"));
			//페이지 이동 방식
			//1)실제 페이지 member/insert.jsp 웹 방식 주소가 변경되면서 웹 이동(하이퍼링크)
			//response.sendRedirect("member/insert.jsp");
			//2) 실제 페이지 member/insert.jsp 주소 변경 없이 이동(특이한 이동방식) → 화면만 바뀜. 액션태그<jsp:forward> 같은 이동 방식
			// 	 → 이동할 때 request, response 값을 들고 이동. 주소는 그대로 유지되면서 화면만 바뀜
			dispatcher = request.getRequestDispatcher("member/insert.jsp");
			dispatcher.forward(request, response);
		}
		if(sPath.equals("/insertPro.me")) {
			memberService.insertMember(request);
			
			//로그인 페이지로 이동
			//가상주소 login.me 주소가 변경되면서 이동(하이퍼링크)
			response.sendRedirect("login.me");
		}
		if(sPath.equals("/login.me")) {
			System.out.println("가상주소 일치 여부 : " + sPath.equals("/login.me"));
			dispatcher = request.getRequestDispatcher("member/login.jsp");
			dispatcher.forward(request, response);
		}
		if(sPath.equals("/loginPro.me")) {
			boolean result = memberService.userCheck(request);
			String id = request.getParameter("id");
			if(result==true) {
				session = request.getSession();
				session.setAttribute("id",id);
				response.sendRedirect("main.me");
			} else {
				response.sendRedirect("login.me");
			}
		}
		if(sPath.equals("/main.me")){
			System.out.println("가상주소 일치 여부 : " + sPath.equals("/main.me"));
			dispatcher = request.getRequestDispatcher("member/main.jsp");
			dispatcher.forward(request, response);
		}
		if(sPath.equals("/logout.me")) {
			System.out.println("가상주소 일치 여부 : " + sPath.equals("/logout.me"));
			session = request.getSession();
			session.invalidate();
			response.sendRedirect("login.me");
		}
		// → MemberService 객체 생성 → 
		//→ MemberDTO getMember(id) 메서드 호출 → request에 MemberDTO, memberDTO 담아서 → member/info.jsp 주소 변경없이 이동
		if(sPath.equals("/info.me")) {
			session = request.getSession();
			String id = (String)session.getAttribute("id"); //session값(로그인 표시값) 가져오기(String id 변수에 저장)
			
			MemberDTO memberDTO = memberService.getMember(id);
			request.setAttribute("memberDTO", memberDTO); //request에 "memberDTO",memberDTO를 담아서 이동
			
			System.out.println("가상주소 일치 여부 : " + sPath.equals("/info.me"));
			dispatcher = request.getRequestDispatcher("member/info.jsp");
			dispatcher.forward(request, response);
		}
		if(sPath.equals("/update.me")) {
			session = request.getSession(); //세션값 가져오기 → String id 변수에 저장
			String id = (String)session.getAttribute("id");
			MemberDTO memberDTO = memberService.getMember(id);
			request.setAttribute("memberDTO", memberDTO); //request에 "memberDTO",memberDTO에 담아서 이동
			
			dispatcher = request.getRequestDispatcher("member/update.jsp");
			dispatcher.forward(request, response);
		}
		if(sPath.equals("/updatePro.me")) {
			request.setCharacterEncoding("utf-8");
			
			boolean result = memberService.userCheck(request);
			if(result==true) {
				memberService.updateMember(request);
				response.sendRedirect("main.me");
			} else {
				response.sendRedirect("update.me");
			}
		}
		if(sPath.equals("/delete.me")) {
			dispatcher = request.getRequestDispatcher("member/delete.jsp");
			dispatcher.forward(request, response);
		}
		if(sPath.equals("/deletePro.me")) {
			request.setCharacterEncoding("utf-8");
			
			boolean result = memberService.userCheck(request);
			if(result==true) {
				memberService.deleteMember(request);
				session = request.getSession();
				session.invalidate();
				response.sendRedirect("main.me");
			} else {
				response.sendRedirect("delete.me");
			}
		}
		if(sPath.equals("/list.me")) {
			dispatcher = request.getRequestDispatcher("member/list.jsp");
			dispatcher.forward(request, response);
		}
			
		
	}//
	
	
	
	
}
