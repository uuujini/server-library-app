package com.group.libraryapp.controller.user;

import java.util.ArrayList;
import java.util.List;

import com.group.libraryapp.domain.User;
import com.group.libraryapp.dto.request.user.UserCreateRequest;
import com.group.libraryapp.dto.response.UserResponse;

import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/user") // GET /user
	public List<UserResponse> getUsers() {
		List<UserResponse> responses = new ArrayList<>();
		for (int i = 0; i < users.size(); i++) {
			responses.add(new UserResponse(i + 1, users.get(i).getName(), users.get(i).getAge()));
		}
		return responses;
	}

}
