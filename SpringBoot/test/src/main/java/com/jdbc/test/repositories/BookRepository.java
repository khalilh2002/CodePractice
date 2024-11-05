package com.jdbc.test.repositories;

import com.jdbc.test.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
