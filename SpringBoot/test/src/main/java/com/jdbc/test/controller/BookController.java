package com.jdbc.test.controller;

import com.jdbc.test.entities.Author;
import com.jdbc.test.entities.Book;
import com.jdbc.test.metiers.BookMetier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
public class BookController {
  private final BookMetier bookMetier;

  public BookController(BookMetier bookMetier) {
    this.bookMetier = bookMetier;
  }

  @GetMapping(value = "/books")
  public List<Book> getbooks(){
    return bookMetier.findAllBooks();
  }

  @GetMapping(value = "/book/{id}")
  public Book getbook(@PathVariable String id){
    return bookMetier.findBookById(id);
  }

  @GetMapping(value = "/book")
  public Book getbook(@RequestParam Author author){
    return bookMetier.findBookById();
  }

}
