package com.jdbc.test.controller;

import com.jdbc.test.entities.Author;
import com.jdbc.test.metiers.AuthorMetier;
import com.jdbc.test.metiers.implementation.AuthorMetierImp;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {
  private final AuthorMetier authorMetier;

  public AuthorController(AuthorMetier authorMetier) {
    this.authorMetier = authorMetier;
  }

  @GetMapping(value = "/authors")
  public List<Author> getAuthors() {
    return authorMetier.findAllAuthors();
  }


  @GetMapping(value = "/author/{id}")
  public Author getAuthor(@PathVariable Long id) {
    return authorMetier.findAuthorById(id);
  }

  @PostMapping(value = "/author")
  public Author addAuthor(@RequestBody Author author) {
    return authorMetier.save(author);
  }
}
