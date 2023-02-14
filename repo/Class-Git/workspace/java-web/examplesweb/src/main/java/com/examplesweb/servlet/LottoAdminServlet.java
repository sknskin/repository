package com.examplesweb.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examplesweb.dto.LottoDto;
import com.examplesweb.service.LottoService;

@WebServlet(urlPatterns = { "/lotto-admin" })
public class LottoAdminServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 요청 처리
		LottoService lottoService = new LottoService();
		List<LottoDto> lottos = lottoService.findAllLotto();
		// 2. JSP에서 사용하도록 데이터 request에 저장
		req.setAttribute("lottos", lottos);
		// 3. JSP로 이동
		RequestDispatcher rd = req.getRequestDispatcher("lotto-admin.jsp");
		rd.forward(req, resp); // 지정된 경로로 forward 이동
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		LottoDto lottoDto = new LottoDto();
		lottoDto.setNo1( Integer.parseInt(req.getParameter("no1")) );
		lottoDto.setNo2( Integer.parseInt(req.getParameter("no2")) );
		lottoDto.setNo3( Integer.parseInt(req.getParameter("no3")) );
		lottoDto.setNo4( Integer.parseInt(req.getParameter("no4")) );
		lottoDto.setNo5( Integer.parseInt(req.getParameter("no5")) );
		lottoDto.setNo6( Integer.parseInt(req.getParameter("no6")) );
		
		LottoService lottoService = new LottoService();
		lottoService.registerLotto(lottoDto);
		
	}

}












