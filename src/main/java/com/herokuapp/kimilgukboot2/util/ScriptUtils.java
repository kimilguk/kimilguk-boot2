package com.herokuapp.kimilgukboot2.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ScriptUtils {
	//HttpServletResponse은 Http로 통신할 때 필요한 내장된 기능 중 응답에 사용하는 클래스를 말한다.
	//CRUD 완료 메세지 출력 공통 코드 처리
	public static void init(HttpServletResponse response) {
		response.setContentType("text/html; charset=utf-8");//html형식
		response.setCharacterEncoding("utf-8");//한글깨짐방지
	}
	//CRUD 완료 메세지 출력 및 페이지 이동처리
	public static void alertAndMovePage(HttpServletResponse response, String alertText, String nextPage) throws IOException {
		init(response);
		PrintWriter out = response.getWriter();//스트림 방식으로 출력 객체생성:예외처리필수
		out.println("<script>alert('"+alertText+"');location.href='"+nextPage+"';</script>");
		out.flush();//객체삭제
	}
}
