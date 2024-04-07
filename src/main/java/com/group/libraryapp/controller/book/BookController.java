package com.group.libraryapp.controller.book;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.group.libraryapp.dto.request.BookLoanRequest;
import com.group.libraryapp.dto.request.book.BookCreateRequest;
import com.group.libraryapp.repository.book.BookService;

@RestController
public class BookController {

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@PostMapping("/book")
	public void saveBook(@RequestBody BookCreateRequest request) {
		bookService.saveBook(request);
	}

	@PostMapping("/book/loan")
	public void loanBook(@RequestBody BookLoanRequest request) {
		bookService.loanBook(request);
	}

}
