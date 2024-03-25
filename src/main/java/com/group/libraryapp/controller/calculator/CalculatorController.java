package com.group.libraryapp.controller.calculator;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiplyRequest;

@RestController
public class CalculatorController {
	@GetMapping("/add") // GET /add
	public int addTwoNumbers(CalculatorAddRequest request) {
		return request.getNumber1() + request.getNumber2();
	}

	@PostMapping("/multiply") // POST /multiply * POST 인 경우 @RequestBody 필수! POST-Body 넘겨 주기
	public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request) {
		return request.getNumber1() * request.getNumber2();
	}

}
