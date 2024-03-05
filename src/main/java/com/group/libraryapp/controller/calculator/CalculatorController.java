package com.group.libraryapp.controller.calculator;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;

@RestController
public class CalculatorController {
	@GetMapping("/add")
	public int addTwoNumbers(CalculatorAddRequest request) {
		return request.getNumber1() + request.getNumber2();
	}
}
