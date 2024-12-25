import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { StudentsService } from '../../service/students.service';
import { Student } from '../../interface/Student';
import { ReactiveFormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-add-student',
  standalone: true,
  imports: [ReactiveFormsModule ,NgIf,RouterLink],
  templateUrl: './add-student.component.html',
})
export class AddStudentComponent {
  studentForm: FormGroup;
  successMessage: string | null = null;
  errorMessage: string | null = null;

  constructor(private fb: FormBuilder, private studentsService: StudentsService) {
    this.studentForm = this.fb.group({
      firstName: ['', [Validators.required, Validators.minLength(2)]],
      lastName: ['', [Validators.required, Validators.minLength(2)]],
    });
  }

  onSubmit(): void {
    if (this.studentForm.valid) {
      const student: Student = {
        id: null,
        firstName: this.studentForm.value.firstName,
        lastName: this.studentForm.value.lastName,
        absences: null,
      };

      this.studentsService.createStudent(student).subscribe({
        next: () => {
          this.successMessage = 'Student added successfully!';
          this.errorMessage = null;
          this.studentForm.reset();
        },
        error: (err:any) => {
          this.successMessage = null;
          this.errorMessage = 'Failed to add student.';
          console.error(err);
        },
      });
    }
  }
}
