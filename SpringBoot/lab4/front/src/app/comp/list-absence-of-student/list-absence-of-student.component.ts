import { Absence } from './../../interface/Absence';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { StudentsService } from '../../service/students.service';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-list-absence-of-student',
  standalone: true,
  imports: [NgIf , NgFor,RouterLink],
  templateUrl: './list-absence-of-student.component.html',
})
export class ListAbsenceOfStudentComponent implements OnInit {
  absences: Absence[] = [];
  studentId: number | null = null;
  errorMessage: string | null = null;
  successMessage: string | null = null;

  constructor(
    private studentsService: StudentsService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // Get the student ID from the route parameters
    this.route.paramMap.subscribe((params) => {
      const id = params.get('id');
      if (id) {
        this.studentId = +id;
        this.loadAbsences();
      } else {
        this.errorMessage = 'Invalid student ID.';
      }
    });
  }

  loadAbsences(): void {
    if (this.studentId !== null) {
      this.studentsService.getStudentAbsences(this.studentId).subscribe({
        next: (absences) => {
          this.absences = absences;
          this.errorMessage = null;
        },
        error: (err) => {
          this.errorMessage = 'Failed to load absences.';
          console.error(err);
        },
      });
    }
  }

  deleteAbsence(absenceId:number): void {

    const confirmDelete = window.confirm(`WARNING: Are you sure you want to delete the absence with ID ${absenceId}?`);
    if (confirmDelete) {
      this.studentsService.deleteAbsence(absenceId).subscribe({
        next: () => {
          this.successMessage = 'Absence deleted successfully!';
          this.errorMessage = null;
        },
        error: () => {
          this.successMessage = null;
          this.errorMessage = 'Failed to delete the absence.';
        }
      });
    }
    return


  }
}
