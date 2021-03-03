import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-salary-slider',
  templateUrl: './salary-slider.component.html',
  styleUrls: ['./salary-slider.component.css']
})
export class SalarySliderComponent implements OnInit {

  salary: number;

  constructor() { }

  ngOnInit(): void {
  }

  formatLabel(value: number) {
    if (value >= 1000) {
      return Math.round(value / 1000) + 'k';
    }

    return value;
  }

}
