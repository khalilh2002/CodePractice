import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListAbsenceOfStudentComponent } from './list-absence-of-student.component';

describe('ListAbsenceOfStudentComponent', () => {
  let component: ListAbsenceOfStudentComponent;
  let fixture: ComponentFixture<ListAbsenceOfStudentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListAbsenceOfStudentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListAbsenceOfStudentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
