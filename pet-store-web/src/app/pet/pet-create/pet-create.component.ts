import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {PetEntity} from "../../services/entities";
import {PetService} from "../../services/pet.service";

@Component({
  selector: 'app-pet-create',
  templateUrl: './pet-create.component.html',
  styleUrls: ['./pet-create.component.scss']
})
export class PetCreateComponent implements OnInit {
  createPetForm: FormGroup;
  statusList: string[] = ['Select One','AVAILABLE', 'PENDING','SOLD'];

  constructor(private fb: FormBuilder,
              private router: Router,
              private petService: PetService
  ) {  }


  ngOnInit(): void {
    this.createPetForm = this.fb.group({
      'petName': this.fb.control('', [Validators.required]),
      'petCategory': this.fb.control('', [Validators.required]),
      'photoUrl': this.fb.control('', ),
      'tag': this.fb.control('', ),
      'status': this.fb.control('', [Validators.required]),
    });
  }

  async submitForm($event) {
    const value = this.createPetForm.value;
    console.log('From received ' +JSON.stringify(this.createPetForm.value));

    if (this.createPetForm.valid) {

      const petEntity: PetEntity = await this.petService.createPet(
        {
          name: value.petName,
          category: {
            "name": value.petCategory
          },
          photoUrls: [value.photoUrl],
          status: value.status,
          tags: [{
            "name": value.tag
          }],

        }).toPromise();
      alert(`Pet created with id: ${petEntity.id}`);
    }
    else {
      alert('Invalid from fillUp. Please enter valid data')
      console.log('You Enter :' + value.category + value.name + value.status + value.tags + value.photoUrls )
    }
  }
}
