package com.jdbc.test.metiers;

import com.jdbc.test.entities.Author;

import java.util.List;

public interface AuthorMetier {

  Author findAuthorById(Long id);
  List<Author> findAllAuthors();
  Author save(Author author);
}
