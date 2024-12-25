import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from './../../environments/environment';
import { Student } from "../interface/Student"
import { Absence } from '../interface/Absence';



@Injectable({
  providedIn: 'root'
})
export class StudentsService {
  private baseUrlStudent = `${environment.apiUrl}/students`;
  private baseUrlAbsence = `${environment.apiUrl}/absences`;

  constructor(private http: HttpClient) {}

  // Fetch all students
  getStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(this.baseUrlStudent);
  }

  // Fetch a specific student by ID
  getStudentById(studentId: number): Observable<Student> {
    return this.http.get<Student>(`${this.baseUrlStudent}/${studentId}`);
  }

  // Fetch absences for a specific student
  getStudentAbsences(studentId: number): Observable<Absence[]> {
    return this.http.get<Absence[]>(`${this.baseUrlStudent}/${studentId}/absences`);
  }

  // Create a new student
  createStudent(student: Student): Observable<Student> {
    return this.http.post<Student>(this.baseUrlStudent, student);
  }

  // Update a student
  updateStudent(studentId: number, student: Student): Observable<Student> {
    return this.http.put<Student>(`${this.baseUrlStudent}/${studentId}`, student);
  }

  // Delete a student
  deleteStudent(studentId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrlStudent}/${studentId}`);
  }

  // Create a new absence for a specific student
  createAbsence(studentId: number, absence: Absence): Observable<Absence> {
    return this.http.post<Absence>(`${this.baseUrlStudent}/${studentId}/absences`, absence);
  }

  // Fetch all absences
  getAllAbsence(): Observable<Absence[]> {
    return this.http.get<Absence[]>(this.baseUrlAbsence);
  }
    // Delete a student
    deleteAbsence(absenceId: number): Observable<void> {
      return this.http.delete<void>(`${this.baseUrlAbsence}/${absenceId}`);
    }
}
