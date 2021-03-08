import { Component, OnInit } from '@angular/core';
import { PetService } from "../../services/pet.service";
import {PetEntity} from "../../services/entities";
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: 'app-pet-detail',
  templateUrl: './pet-detail.component.html',
  styleUrls: ['./pet-detail.component.scss']
})
export class PetDetailComponent implements OnInit {
  public pet: PetEntity | undefined ;

  constructor(private petService: PetService,
              private route: ActivatedRoute ) { }

  ngOnInit(): void {
    this.getPetDetails();
  }
  public getPetDetails(): void {
    const stringId = this.route.snapshot.paramMap.get('id');
    let id: number = +stringId.slice(1);
    console.log('finding pet details by id' +id);
    this.petService.findPetById(id)
      .subscribe(petdetail => {
        this.pet = petdetail;
        console.log('subscribe to pet service');
      });
  }

}
