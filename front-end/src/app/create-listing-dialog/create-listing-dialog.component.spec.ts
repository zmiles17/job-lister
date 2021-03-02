import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateListingDialogComponent } from './create-listing-dialog.component';

describe('CreateListingDialogComponent', () => {
  let component: CreateListingDialogComponent;
  let fixture: ComponentFixture<CreateListingDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateListingDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateListingDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
