import { Routes } from '@angular/router';
import { StudentslistComponent } from './comp/studentslist/studentslist.component';
import { ListAbsenceOfStudentComponent } from './comp/list-absence-of-student/list-absence-of-student.component';
import { AddStudentComponent } from './comp/add-student/add-student.component';
import { AddAbsenceComponent } from './comp/add-absence/add-absence.component';

export const routes: Routes = [

  {
    path: '',  // Default path
    redirectTo: '/student/list',  // Redirects to the student list page
    pathMatch: 'full'  // Ensure the default path fully matches
  },
  {
    path: 'student/list',
    component: StudentslistComponent
  },
  {
    path: 'student/absences',
    component: ListAbsenceOfStudentComponent
  },
  {
    path: 'student/add',
    component: AddStudentComponent
  },
  {
    path: 'student/:id/absences',
    component: ListAbsenceOfStudentComponent
  },
  {
    path: 'student/absences/add/:id',
    component: AddAbsenceComponent
  }

];
