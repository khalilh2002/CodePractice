package com.lsi.lab4.repository;

import com.lsi.lab4.entity.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AbsenceRepository extends JpaRepository<Absence, Long> {
  @Query("SELECT a FROM Absence a WHERE a.student.id = ?1")
  List<Absence> findByStudentId(Long studentId);
}
