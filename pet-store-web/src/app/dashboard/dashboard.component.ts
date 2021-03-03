import { Component, OnInit } from '@angular/core';
import {faHeart} from "@fortawesome/free-regular-svg-icons";
import {PetService} from "../services/pet.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  faHeart = faHeart;
  public response: any;

  constructor(private petService: PetService,
              private router: Router
  ) { }

  ngOnInit(): void {
    console.log("Requesting backend for listing all pets")
    this.response = this.petService.listPet();

  }

}
