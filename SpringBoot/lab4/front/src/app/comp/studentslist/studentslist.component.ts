import { Component } from '@angular/core';
import { StudentsService } from '../../service/students.service';
import { Student } from '../../interface/Student';
import { NgFor } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-studentslist',
  standalone: true,
  imports: [ NgFor , RouterLink],
  templateUrl: './studentslist.component.html',
})
export class StudentslistComponent {
  students: Student[] = [];

  constructor(private studentsService: StudentsService) {}

  ngOnInit() {
    this.getStudents();
  }

  getStudents() {
    this.studentsService.getStudents().subscribe(
      (data: Student[]) => {
        console.log(data[0].absences)
        this.students = data;
      },
      (error: any) => {
        console.error('Error fetching students', error);
      }
    );
  }

  getAbsenceCount(student: Student): number {
    return student.absences ? student.absences.length : 0;
  }
}
