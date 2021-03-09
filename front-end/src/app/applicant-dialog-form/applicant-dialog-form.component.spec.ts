import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplicantDialogFormComponent } from './applicant-dialog-form.component';

describe('ApplicantDialogFormComponent', () => {
  let component: ApplicantDialogFormComponent;
  let fixture: ComponentFixture<ApplicantDialogFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApplicantDialogFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApplicantDialogFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
