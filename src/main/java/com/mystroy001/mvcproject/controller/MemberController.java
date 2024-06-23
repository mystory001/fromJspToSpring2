package com.mystroy001.mvcproject.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			//페이지 이동 방식
			//1)실제 페이지 member/insert.jsp 웹 방식 주소가 변경되면서 웹 이동(하이퍼링크)
			//response.sendRedirect("member/insert.jsp");
			//2) 실제 페이지 member/insert.jsp 주소 변경 없이 이동(특이한 이동방식) → 화면만 바뀜. 액션태그<jsp:forward> 같은 이동 방식
			// 	 → 이동할 때 request, response 값을 들고 이동. 주소는 그대로 유지되면서 화면만 바뀜
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/insert.jsp");
			dispatcher.forward(request, response);
		}
		if(sPath.equals("/insertPro.me")) {
			MemberService memberService = new MemberService();
			memberService.insertMember(request);
			
			//로그인 페이지로 이동
			//가상주소 login.me 주소가 변경되면서 이동(하이퍼링크)
			response.sendRedirect("login.me");
		}
		if(sPath.equals("/login.me")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/login.jsp");
			dispatcher.forward(request, response);
		}
		if(sPath.equals("/loginPro.me")) {
			MemberService memberService = new MemberService();
			boolean result = memberService.userCheck(request);
			String id = request.getParameter("id");
			if(result==true) {
				HttpSession session = request.getSession();
				session.setAttribute("id",id);
				response.sendRedirect("main.me");
			} else {
				response.sendRedirect("login.me");
			}
		}
		if(sPath.equals("/main.me")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/main.jsp");
			dispatcher.forward(request, response);
		}
		
		
		
	}//
	
	
	
	
}
