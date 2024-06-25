package com.mystroy001.mvcproject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mystroy001.mvcproject.domain.MemberDTO;

public class MemberDAO {
	
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public void dbClose() {
		if(rs!=null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(connection!=null) {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//디비 연결하는 getConnection() 메서드 정의
	public Connection getConnection() {
		Connection connection = null;
		try {
			//Class.forName("com.mysql.cj.jdbc.Driver");
			
			//String url = "jdbc:mysql://localhost:3306/jspdb1?serverTimezone=Asia/Seoul";
			//String user = "root";
			//String password = "1234";
			//connection = DriverManager.getConnection(url, user, password);
			
			//커넥션 풀(Connection Pool(CP) : 서버단에서 연결정보를 저장)
			//데이터베이스와 연결된 Connection 객체를 미리 생성하여 풀(Pool) 기억공간에 저장 → 필요할 때마다 풀에 접근해서 Connection 객체를 사용하고 끝나면 반환
			//톰캣에서 제공하는 DBCP(Database Connection Pool) 사용
			//META-INF 폴더에 context.xml 파일을 만들고 커넥션 풀 디비 연결 정보 정의
			//DAO에서 디비연결자원의 이름을 불러서 사용
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MysqlDB");
			connection = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return connection;
	}

	public void insertMember(MemberDTO memberDTO) {
		System.out.println("MemberDAO insertMember()");
		System.out.println("전달받은 아이디 : " + memberDTO.getId());
		System.out.println("전달받은 비밀번호 : " + memberDTO.getPw());
		System.out.println("전달받은 이름 : " + memberDTO.getName());
		System.out.println("전달받은 시간 : " + memberDTO.getDate());

		try {
			Connection connection = getConnection();
			
			String sql = "insert into members(id,pw,name,date) values(?,?,?,?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, memberDTO.getId());
			pstmt.setString(2, memberDTO.getPw());
			pstmt.setString(3, memberDTO.getName());
			pstmt.setTimestamp(4, memberDTO.getDate());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}//
	
	public boolean userCheck(String id, String pw) {
		System.out.println("MemberDAO userCheck()");
		boolean result = false;
		
		try {
		Connection connection = getConnection();
		
		String sql = "select * from members where id=? and pw=?";
		pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, pw);

		//SQL 구문 실행 → 실행 후 결과 저장(select) → ResultSet 내장객체 저장
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()){
			System.out.println("로그인 성공");
			System.out.println("입력한 아이디 : " + id);
			System.out.println("입력한 비밀번호 : " + pw);
			result = true;
		}else{
			System.out.println("로그인 실패");
			System.out.println("입력한 아이디 : " + id);
			System.out.println("입력한 비밀번호 : " + pw);
			result = false;
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}//
	
	public MemberDTO getMember(String id) {
		System.out.println("MemberDAO getMember()");
		MemberDTO memberDTO = null;
		try {
			Connection connection = getConnection();
			
			String sql = "select * from members where id=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				memberDTO = new MemberDTO();
				memberDTO.setId(rs.getString("id"));
				memberDTO.setPw(rs.getString("pw"));
				memberDTO.setName(rs.getString("name"));
				memberDTO.setDate(rs.getTimestamp("date"));
			} else {
				System.out.println("잘못된 접근");
				memberDTO = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return memberDTO;
	}//
	
	public void updateMember(MemberDTO memberDTO) {
		System.out.println("MemberDAO updateMember()");
		System.out.println("memberDTO 주소 : " + memberDTO);
		System.out.println("전달 받은 아이디 : " + memberDTO.getId());
		System.out.println("전달 받은 비밀번호 : " + memberDTO.getPw());
		System.out.println("전달 받은 이름 : " + memberDTO.getName());
		System.out.println("전달 받은 시간 : " + memberDTO.getDate());
		
		try {
			Connection connection = getConnection();
			String sql = "update members set name=? where id=?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, memberDTO.getName());
			pstmt.setString(2, memberDTO.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//
	
	public void deleteMember(String id) {
		try {
			Connection connection = getConnection();
			String sql = "delete from members where id=?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<MemberDTO> getMemberList(){
		ArrayList<MemberDTO> memberList = new ArrayList<MemberDTO>();
		try {
			Connection connection = getConnection();
			String sql = "select * from members";
			PreparedStatement pstmt = connection.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				//한 회원의 정보를 저장할 용기 memberDTO 객체 생성
				MemberDTO memberDTO = new MemberDTO();
				System.out.println("memberDTO 주소 : " + memberDTO);
				//memberDTO 멤버변수에 열에 접근해서 가져온 데이터를 저장
				memberDTO.setId(rs.getString("id"));
				memberDTO.setPw(rs.getString("pw"));
				memberDTO.setName(rs.getString("name"));
				memberDTO.setDate(rs.getTimestamp("date"));
				//배열 한 칸에 저장
				memberList.add(memberDTO);
			}
			System.out.println("memberList 주소 : " + memberList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberList;
	}
	
}
