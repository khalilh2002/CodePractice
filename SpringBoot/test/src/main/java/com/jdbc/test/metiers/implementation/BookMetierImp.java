package com.jdbc.test.metiers.implementation;

import com.jdbc.test.entities.Author;
import com.jdbc.test.entities.Book;
import com.jdbc.test.metiers.BookMetier;
import com.jdbc.test.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookMetierImp implements BookMetier {

  private final BookRepository bookRepository;

  BookMetierImp(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public Book findBookById(String isbn) {
    return bookRepository.findById(isbn).orElse(null);
  }

  @Override
  public List<Book> findAllBooks() {
    return bookRepository.findAll();
  }

  @Override
  public Book save(Book book) {
    return bookRepository.save(book);
  }

  @Override
  public List<Book> findBooksByAuthor(Author author) {
    return bookRepository.findBy();
  }
}
