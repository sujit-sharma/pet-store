import { Component, OnInit } from '@angular/core';
import { Login } from "../login";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  constructor() { }

  submitted = false;

  onSubmit() { this.submitted = true; }

  ngOnInit(): void {
  }

}
