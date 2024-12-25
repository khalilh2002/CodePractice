
package com.lsi.lab4.controller;

import com.lsi.lab4.controller.request.AbsenceRequest;
import com.lsi.lab4.controller.request.StudentRequest;
import com.lsi.lab4.entity.Student;
import com.lsi.lab4.entity.Absence;
import com.lsi.lab4.service.StudentService;
import com.lsi.lab4.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentController {

  @Autowired
  private StudentService studentService;

  @Autowired
  private AbsenceService absenceService;

  // Student CRUD
  @GetMapping("/test")
  public String test() {
    return "test is runing";
  }

  @GetMapping("/students")
  public List<Student> getAllStudents() {
    return studentService.getAllStudents();
  }

  @GetMapping("/students/{id}")
  public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
    Optional<Student> student = studentService.getStudentById(id);
    return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping("/students")
  public Student createStudent(@RequestBody StudentRequest studentRequest) {
    Student student = Student.builder()
      .firstName(studentRequest.firstName())
      .lastName(studentRequest.lastName())
      .build();
    return studentService.saveStudent(student);
  }

  @PutMapping("/students/{id}")
  public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody StudentRequest studentDetails) {
    Optional<Student> student = studentService.getStudentById(id);
    if (student.isPresent()) {
      Student existingStudent = student.get();
      existingStudent.setFirstName(studentDetails.firstName());
      existingStudent.setLastName(studentDetails.lastName());
      return ResponseEntity.ok(studentService.saveStudent(existingStudent));
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/students/{id}")
  public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
    studentService.deleteStudent(id);
    return ResponseEntity.noContent().build();
  }

  // Absence CRUD

  @GetMapping("/absences")
  public List<Absence> getAllAbsences() {
    return absenceService.getAllAbsences();
  }

  @GetMapping("/students/{studentId}/absences")
  public List<Absence> getAbsencesByStudentId(@PathVariable Long studentId) {
    return absenceService.getAbsencesByStudentId(studentId);
  }

  @PostMapping("/students/{studentId}/absences")
  public ResponseEntity<Absence> createAbsence(@PathVariable Long studentId, @RequestBody AbsenceRequest absenceRequest) {
    Optional<Student> student = studentService.getStudentById(studentId);
    if (student.isPresent()) {
      Date date = absenceRequest.date() != null ? absenceRequest.date() : new Date();
      Boolean haveReason = absenceRequest.haveReason() != null ? absenceRequest.haveReason() : false;

      Absence absence = Absence.builder()
        .student(student.get())
        .date(date)
        .haveReason(haveReason)
        .reason(absenceRequest.reason())
        .build();

      return ResponseEntity.ok(absenceService.saveAbsence(absence));
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/absences/{id}")
  public ResponseEntity<Void> deleteAbsence(@PathVariable Long id) {
    absenceService.deleteAbsence(id);
    return ResponseEntity.noContent().build();
  }
}
