import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { StudentsService } from '../../service/students.service';
import { Absence } from '../../interface/Absence';
import { ReactiveFormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-add-absence',
  standalone: true,
  imports: [ReactiveFormsModule , NgIf , RouterLink],
  templateUrl: './add-absence.component.html',
})
export class AddAbsenceComponent implements OnInit {
  absenceForm: FormGroup;
  studentId: number | null = null;
  successMessage: string | null = null;
  errorMessage: string | null = null;

  constructor(
    private fb: FormBuilder,
    private studentsService: StudentsService,
    private route: ActivatedRoute
  ) {
    this.absenceForm = this.fb.group({
      date: ['', Validators.required],
      haveReason: [false, Validators.required],
      reason: ['']
    });
  }

  ngOnInit(): void {
    // Get student ID from the route parameters
    this.route.paramMap.subscribe((params) => {
      const id = params.get('id');
      if (id) {
        this.studentId = +id;
      } else {
        this.errorMessage = 'Invalid student ID.';
      }
    });
  }

  onSubmit(): void {
    if (this.absenceForm.valid && this.studentId !== null) {
      const absence: Absence = {
        id: null,
        date: this.absenceForm.value.date,
        haveReason: this.absenceForm.value.haveReason,
        reason: this.absenceForm.value.reason,
        student: this.studentId
      };

      this.studentsService.createAbsence(this.studentId, absence).subscribe({
        next: () => {
          this.successMessage = 'Absence added successfully!';
          this.errorMessage = null;
          this.absenceForm.reset({ haveReason: false }); // Reset form, defaulting haveReason to false
        },
        error: (err) => {
          this.successMessage = null;
          this.errorMessage = 'Failed to add absence.';
          console.error(err);
        }
      });
    }
  }
}
