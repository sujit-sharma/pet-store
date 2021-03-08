import { Component, OnInit } from '@angular/core';
import { Location } from "@angular/common";

@Component({
  selector: 'app-order-main',
  templateUrl: './order-main.component.html',
  styleUrls: ['./order-main.component.scss']
})
export class OrderMainComponent implements OnInit {

  constructor(private location: Location ) { }

  ngOnInit(): void {
  }

  goBack() {
    this.location.back();
  }
}
