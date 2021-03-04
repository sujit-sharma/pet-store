import { Component, OnInit } from '@angular/core';
import {faHeart} from "@fortawesome/free-regular-svg-icons";
import {PetService} from "../services/pet.service";
import {Router} from "@angular/router";
import {PetEntity} from "../services/entities";


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  faHeart = faHeart;
  pets: PetEntity[] = [];

  constructor(private petService: PetService,
              private router: Router
  ) { }

  ngOnInit(): void {

    this.getPets();

  }
  getPets(): void {
    console.log(" Dashboard component:  Requesting to pet service pets")
    this.petService.listPet()
      .subscribe(petList=> {
        this.pets = petList;
        console.log('Dashboard component: petList is ' + petList.length);
      });
    console.log('Dashboard component: '+this.pets.length +' pets fetched successfully' + this.pets);
  }
}
