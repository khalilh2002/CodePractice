package com.lsi.lab4.service;


import com.lsi.lab4.entity.Absence;
import com.lsi.lab4.repository.AbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbsenceService {
  @Autowired
  private AbsenceRepository absenceRepository;

  public List<Absence> getAllAbsences() {
    return absenceRepository.findAll();
  }

  public List<Absence> getAbsencesByStudentId(Long studentId) {
    return absenceRepository.findByStudentId(studentId);
  }

  public Absence saveAbsence(Absence absence) {
    return absenceRepository.save(absence);
  }

  public void deleteAbsence(Long id) {
    absenceRepository.deleteById(id);
  }
}
