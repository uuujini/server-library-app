package com.group.libraryapp.repository.book;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.request.book.BookLoanRequest;
import com.group.libraryapp.dto.request.book.BookCreateRequest;
import com.group.libraryapp.dto.request.book.BookReturnRequest;
import com.group.libraryapp.repository.BookRepository;

@Service
public class BookService {

	private final BookRepository bookRepository;
	private final UserLoanHistoryRepository userLoanHistoryRepository;
	private final UserRepository userRepository;

	public BookService(BookRepository bookRepository, UserLoanHistoryRepository userLoanHistoryRepository,
		UserRepository userRepository) {
		this.bookRepository = bookRepository;
		this.userLoanHistoryRepository = userLoanHistoryRepository;
		this.userRepository = userRepository;
	}

	@Transactional
	public void saveBook(BookCreateRequest request) {
		bookRepository.save(new Book(request.getName()));
	}

	@Transactional
	public void loanBook(BookLoanRequest request) {
		// 1. 책 정보를 가져온다.
		Book book = bookRepository.findByName(request.getBookName())
			.orElseThrow(IllegalArgumentException::new);

		// 2. 대출 기록 정보를 확인하여 대출 중인지 확인한다.
		// 3. 만약 대출 중이면 예외를 발생시킨다.
		if (userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)) {
			throw new IllegalArgumentException("이미 대출되어 있는 책입니다.");
		}

		// 4. 유저 정보를 가져온다.
		User user = userRepository.findByName(request.getUserName())
			.orElseThrow(IllegalArgumentException::new);
		user.loanBook(book.getName());
	}

	@Transactional
	public void returnBook(BookReturnRequest request) {
		User user = userRepository.findByName(request.getUserName())
			.orElseThrow(IllegalArgumentException::new);

		user.returnBook(request.getBookName());
	}

}