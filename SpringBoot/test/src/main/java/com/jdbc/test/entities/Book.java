package com.jdbc.test.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "books")
@Data
public class Book implements Serializable {
  @Id
  private String isbn ;
  private String title ;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "author_id")
  private Author author ;
}
