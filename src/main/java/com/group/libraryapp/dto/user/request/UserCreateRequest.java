package com.group.libraryapp.dto.user.request;

public class UserCreateRequest {
	private String name;
	private Integer age; // 대문자 Integer -> null 표현 가능

	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;
	}

}
