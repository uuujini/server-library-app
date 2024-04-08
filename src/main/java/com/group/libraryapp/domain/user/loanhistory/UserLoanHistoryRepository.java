package com.group.libraryapp.domain.user.loanhistory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {
	// Select * from user_loan_history where book_name = ? and is_return = ?
	boolean existsByBookNameAndIsReturn(String name, boolean isReturn);

}
