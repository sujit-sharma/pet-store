import { Component, OnInit } from '@angular/core';
import { Router} from "@angular/router";
import { Location } from "@angular/common";

@Component({
  selector: 'app-pet-main',
  templateUrl: './pet-main.component.html',
  styleUrls: ['./pet-main.component.scss']
})
export class PetMainComponent implements OnInit {

  constructor(private router: Router,
              private location: Location
              ) { }

  ngOnInit(): void {
  }

  goBack(): void {
    this.location.back();
  }
}
