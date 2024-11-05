package com.jdbc.test.metiers.implementation;

import com.jdbc.test.entities.Author;
import com.jdbc.test.metiers.AuthorMetier;
import com.jdbc.test.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorMetierImp implements AuthorMetier {

  private final  AuthorRepository authorRepository;
  AuthorMetierImp( AuthorRepository authorRepository){
    this.authorRepository = authorRepository;
  }

  @Override
  public Author findAuthorById(Long id) {
    return authorRepository.findById(id).get();
  }

  @Override
  public List<Author> findAllAuthors() {
    return authorRepository.findAll();
  }

  @Override
  public Author save(Author author) {
    return authorRepository.save(author);
  }
}
