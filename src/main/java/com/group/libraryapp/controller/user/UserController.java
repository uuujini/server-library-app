package com.group.libraryapp.controller.user;

import java.util.ArrayList;
import java.util.List;

import com.group.libraryapp.domain.User;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	private final List<User> users = new ArrayList<>();

	@PostMapping("/user") // POST /user
	public void saveUser(@RequestBody UserCreateRequest request) {
		users.add(new User(request.getName(), request.getAge()));
	}

}
