package com.jdbc.test.domain;

import lombok.Getter;
import lombok.Setter;

public class Book {
  @Setter
  @Getter
  private String title;

  @Getter
  @Setter
  private String isbn;

  @Getter
  @Setter
  private Long author_id;



}
