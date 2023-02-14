package com.springexample.springioc;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component("timeService") // <bean id="timeService" or @Bean ... timeService()와 같은 역할
public class MyTimeService implements TimeService {

	//SimpleDateFormat : 날짜 데이터를 지정된 형식의 문자열로 변환
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	
	public void setFormat(SimpleDateFormat format) {
		this.format = format;
	}

	public String getTimeString() {
		
		return format.format(new Date());
		
	}


	
	
}





