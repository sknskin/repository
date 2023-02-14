package com.examplesweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/process-data2" })
public class ProcessData2Servlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("utf-8");
		
		String sDan = req.getParameter("dan"); // 요청 데이터는 언제나 String
		int dan = Integer.parseInt(sDan); // 문자열 -> 정수
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();		
		out.println("<html>");
		out.println("<head>");
		out.printf("<title>구구단(%d)</title>\n", dan);
		out.println("</head>");
		out.println("<body>");
		out.println("<table border='1' style='width:300px;margin:0 auto'>");
		for (int i = 1; i < 10; i++) {
			out.printf("<tr><th>%d x %d = %2d</th></tr>\n", dan, i, dan * i);
		}
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
		
	}

}












