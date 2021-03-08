import { Component, OnInit } from '@angular/core';
import {PetService} from "../../services/pet.service";
import { PetEntity } from "../../services/entities";
import { Router } from "@angular/router";

@Component({
  selector: 'app-pet-list',
  templateUrl: './pet-list.component.html',
  styleUrls: ['./pet-list.component.scss']
})
export class PetListComponent implements OnInit {
  pets: PetEntity[] = [];

  constructor(private petService: PetService,
              private router: Router ) { }

  ngOnInit(): void {
    this.getAllPets();
  }
getAllPets(): void {
    this.petService.listPet()
      .subscribe(pets => {
        this.pets = pets;
        console.log('PetCreateComponent:' +'available pets ' +pets);
      });
}

  petDetails(id: number) {
    this.router.navigate(['pages/pet/details/:'+id]).then(r => console.log('navigate to pet details '));
  }
}
