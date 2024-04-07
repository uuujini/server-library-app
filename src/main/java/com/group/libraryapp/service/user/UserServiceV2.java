package com.group.libraryapp.service.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.request.user.UserUpdateRequest;
import com.group.libraryapp.dto.request.user.UserCreateRequest;
import com.group.libraryapp.dto.response.UserResponse;

@Service
public class UserServiceV2 {

	private final UserRepository userRepository;

	public UserServiceV2(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// 아래 있는 함수가 시작될 때 start transaction;
	// 함수가 예외 없이 잘 끝나면 commit;
	// 함수에 문제가 생기면 rollback;
	@Transactional
	public void saveUser(UserCreateRequest request){
		User u = userRepository.save(new User(request.getName(), request.getAge()));
	}

	@Transactional(readOnly = true)
	public List<UserResponse> getUsers() {
		return userRepository.findAll().stream()
			.map(UserResponse::new)
			.collect(Collectors.toList());
	}

	@Transactional
	public void updateUser(UserUpdateRequest request) {
		// select * from user id = ?;
		// Optional<User>
		// User 있다면 user로 들어온다.
		User user = userRepository.findById(request.getId())
			.orElseThrow(IllegalArgumentException::new);
		user.updateName(request.getName());
		userRepository.save(user);
	}

	@Transactional
	public void deleteUser(String name) {
		// SELECT * FROM user WHERE name = ?
		// Optional<User> user = userRepository.findByName(name);
		// if (user == null) {
		// 	throw new IllegalArgumentException();
		// }
		// userRepository.delete(user);
	}

}
