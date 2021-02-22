import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PetMainComponent} from "./pet-main/pet-main.component";
import {PetCreateComponent} from "./pet-create/pet-create.component";

const routes: Routes = [
  {
    path: '',
    component: PetMainComponent,
    children: [
      {
        path: 'create',
        component: PetCreateComponent
      },

      {
        path: '**',
        pathMatch: 'full',
        redirectTo: 'list'
      }

    ]
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PetRoutingModule { }
