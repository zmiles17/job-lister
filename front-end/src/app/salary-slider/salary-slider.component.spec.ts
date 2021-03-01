import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SalarySliderComponent } from './salary-slider.component';

describe('SalarySliderComponent', () => {
  let component: SalarySliderComponent;
  let fixture: ComponentFixture<SalarySliderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SalarySliderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SalarySliderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
