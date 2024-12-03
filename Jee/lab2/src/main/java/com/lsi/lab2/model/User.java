package com.lsi.lab2.model;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "users") // Avoid using reserved keywords like "USER"
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true, nullable = false)
  private String username;
  @Column(unique = false, nullable = false)
  private String password;
  @Column(unique = true, nullable = false)
  private String email;

  @OneToOne(cascade = {CascadeType.PERSIST , CascadeType.MERGE, CascadeType.REFRESH})
  @JoinColumn(name = "cart_id")
  private Cart cart;




  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;


  @PrePersist
  private void onCreate() {
    createdAt = LocalDateTime.now();
    updatedAt = LocalDateTime.now();
  }

  @PreUpdate
  private void onUpdate() {
    updatedAt = LocalDateTime.now();
  }

  @Override
  public String toString() {
    return id+" username "+username + " password "+password;
  }
}
