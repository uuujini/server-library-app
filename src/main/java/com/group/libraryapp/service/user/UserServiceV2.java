package com.group.libraryapp.service.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.request.UserUpdateRequest;
import com.group.libraryapp.dto.request.user.UserCreateRequest;
import com.group.libraryapp.dto.response.UserResponse;

@Service
public class UserServiceV2 {

	private final UserRepository userRepository;

	public UserServiceV2(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void saveUser(UserCreateRequest request){
		User u = userRepository.save(new User(request.getName(), request.getAge()));
	}

	public List<UserResponse> getUsers() {
		return userRepository.findAll().stream()
			.map(UserResponse::new)
			.collect(Collectors.toList());
	}

	public void updateUser(UserUpdateRequest request) {
		// select * from user id = ?;
		// Optional<User>
		// User 있다면 user로 들어온다.
		User user = userRepository.findById(request.getId())
			.orElseThrow(IllegalArgumentException::new);
		user.updateName(request.getName());
		userRepository.save(user);
	}

}
