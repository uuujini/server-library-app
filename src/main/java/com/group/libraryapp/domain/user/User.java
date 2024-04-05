package com.group.libraryapp.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id = null;

	@Column(nullable = false, length = 20) // name varchar(20)
	private String name;
	private Integer age;

	protected User() {}; // JPA를 사용하기 위해서는 기본 생성자가 꼭 필요함, protected도 가능.

	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;
	}

	public User(String name, Integer age) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다", name));
		}
		this.name = name;
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void updateName(String name) {
		this.name = name;
	}
}
