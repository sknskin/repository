package com.springexample.mvc.dto;

import lombok.Data;

@Data // 모든 필드에 대해 getter, setter를 자동 구현
public class Person {
	
	private String name;
	private String email;
	private String phone;
	private int age;

}
