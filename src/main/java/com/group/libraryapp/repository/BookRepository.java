package com.group.libraryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group.libraryapp.domain.book.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
