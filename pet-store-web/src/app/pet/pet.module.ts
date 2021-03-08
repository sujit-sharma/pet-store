import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PetMainComponent } from './pet-main/pet-main.component';
import { PetCreateComponent } from './pet-create/pet-create.component';
import { PetListComponent } from './pet-list/pet-list.component';
import { PetDetailComponent } from './pet-detail/pet-detail.component';
import {PetRoutingModule} from "./pet-routing.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";



@NgModule({
  declarations: [PetMainComponent, PetCreateComponent, PetListComponent, PetDetailComponent],
  imports: [
    CommonModule,
    PetRoutingModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class PetModule { }
