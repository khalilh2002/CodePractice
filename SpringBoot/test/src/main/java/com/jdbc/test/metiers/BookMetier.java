package com.jdbc.test.metiers;

import com.jdbc.test.entities.Author;
import com.jdbc.test.entities.Book;

import java.util.List;

public interface BookMetier {
  Book findBookById(String isbn);
  List<Book> findAllBooks();
  Book save(Book Book);
  List<Book> findBooksByAuthor(Author author);

}
