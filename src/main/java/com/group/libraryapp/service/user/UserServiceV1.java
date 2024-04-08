package com.group.libraryapp.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.group.libraryapp.dto.request.user.UserUpdateRequest;
import com.group.libraryapp.dto.request.user.UserCreateRequest;
import com.group.libraryapp.dto.response.UserResponse;
import com.group.libraryapp.repository.user.UserJdbcRepository;

@Service
public class UserServiceV1 {

	private final UserJdbcRepository userRepository;

	public UserServiceV1(UserJdbcRepository userRepository){
		this.userRepository = userRepository;
	}

	public void saveUser(UserCreateRequest request) {
		userRepository.saveUser(request.getName(), request.getAge());
	}

	public List<UserResponse> getUsers() {
		return userRepository.getUsers();
	}

	public void updateUser(UserUpdateRequest request) {
		if (userRepository.isUserNotExist(request.getId())) {
			throw new IllegalArgumentException();
		}

		userRepository.updateUserName(request.getName(), request.getId());
	}

	public void deleteUser(String name) {
		if (userRepository.isUserNotExist(name)) {
			throw new IllegalArgumentException();
		}

		userRepository.deleteUser(name);
	}

}
