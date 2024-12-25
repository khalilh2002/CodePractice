package com.lsi.lab4.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Absence implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Temporal(TemporalType.DATE)
  private Date date;

  private Boolean haveReason = false;

  @Column(nullable = true)
  private String reason;


  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "student_id")
  private Student student;

  // Getters and setters
}
