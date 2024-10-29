package com.tp.t10.dao;

import com.tp.t10.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepository extends JpaRepository<Employe , Long> {
}
