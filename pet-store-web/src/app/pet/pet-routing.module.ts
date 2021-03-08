import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PetMainComponent} from "./pet-main/pet-main.component";
import {PetCreateComponent} from "./pet-create/pet-create.component";
import {PetListComponent} from "./pet-list/pet-list.component";
import { PetDetailComponent } from "./pet-detail/pet-detail.component";

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
        path: 'list',
        component: PetListComponent
      },
      {
        path: 'details/:id',
        component: PetDetailComponent
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
