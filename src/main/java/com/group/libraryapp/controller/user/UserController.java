package com.group.libraryapp.controller.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.group.libraryapp.dto.request.UserUpdateRequest;
import com.group.libraryapp.dto.request.user.UserCreateRequest;
import com.group.libraryapp.dto.response.UserResponse;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	// private final List<User> users = new ArrayList<>();

	private final JdbcTemplate jdbcTemplate;

	public UserController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@PostMapping("/user") // POST /user
	public void saveUser(@RequestBody UserCreateRequest request) {
		// users.add(new User(request.getName(), request.getAge()));
		String sql = "INSERT INTO user(name, age) VALUES (?, ?)";
		jdbcTemplate.update(sql, request.getName(), request.getAge());
	}

	@GetMapping("/user") // GET /user
	public List<UserResponse> getUsers() {
		// 	List<UserResponse> responses = new ArrayList<>();
		// 	for (int i = 0; i < users.size(); i++) {
		// 		responses.add(new UserResponse(i + 1, users.get(i).getName(), users.get(i).getAge()));
		// 	}
		// 	return responses;
		String sql = "SELECT * FROM user";
		return jdbcTemplate.query(sql, new RowMapper<UserResponse>() {
			@Override
			public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
				long id = rs.getLong("id");
				String name = rs.getString(("name"));
				int age = rs.getInt("age");
				return new UserResponse(id, name, age);
			}
		});
	}

	@PutMapping("/user")
	public void updateUser(@RequestBody UserUpdateRequest request) {
		String sql = "UPDATE user SET name = ? WHERE id = ?";
		jdbcTemplate.update(sql, request.getName(), request.getId());
	}

}