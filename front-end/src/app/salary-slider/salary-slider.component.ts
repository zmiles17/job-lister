import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-salary-slider',
  templateUrl: './salary-slider.component.html',
  styleUrls: ['./salary-slider.component.css']
})
export class SalarySliderComponent implements OnInit {

  salary: number;

  @Output() salaryChanged: EventEmitter<number> = new EventEmitter<number>();

  constructor() { }

  ngOnInit(): void {
  }

  

  updateSalary() {
    this.salaryChanged.emit(this.salary);
  }

}
