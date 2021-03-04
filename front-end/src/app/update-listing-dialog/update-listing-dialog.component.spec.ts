import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateListingDialogComponent } from './update-listing-dialog.component';

describe('UpdateListingDialogComponent', () => {
  let component: UpdateListingDialogComponent;
  let fixture: ComponentFixture<UpdateListingDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateListingDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateListingDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
