import { Component, OnInit } from '@angular/core';
import { Router} from "@angular/router";

@Component({
  selector: 'app-pet-main',
  templateUrl: './pet-main.component.html',
  styleUrls: ['./pet-main.component.scss']
})
export class PetMainComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

}
