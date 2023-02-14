package com.demoweb.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

// @WebListener
public class DemoWebListener implements ServletContextListener, HttpSessionListener {
    
    public void contextInitialized(ServletContextEvent sce)  {    	
    	System.out.println("웹 애플리케이션 시작");
    	ServletContext application = sce.getServletContext();
    	String param = application.getInitParameter("config-file"); // web.xml 파일에 등록된 param 정보 조회
    	System.out.println("Param info : " + param);
    	application.setAttribute("current_count", 0);
    	application.setAttribute("total_count", 0);
    }
    public void sessionCreated(HttpSessionEvent se)  { 
    	System.out.println("세션 시작");
    	// application 객체에 접속자 카운트 증가해서 저장 ( 현재 접속자, 누적 접속자 )
    	ServletContext application = se.getSession().getServletContext();
    	int currentCount = (int)application.getAttribute("current_count");
    	application.setAttribute("current_count", currentCount + 1);
    	int totalCount = (int)application.getAttribute("total_count");
    	application.setAttribute("total_count", totalCount + 1);
    }
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	System.out.println("세션 종료");
    	// application 객체에 접속자 카운트 감소해서 저장 ( 현재 접속자 )
    	ServletContext application = se.getSession().getServletContext();
    	int currentCount = (int)application.getAttribute("current_count");
    	application.setAttribute("current_count", currentCount + 1);
    }
    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("웹 애플리케이션 종료");
    }

    
	
}
