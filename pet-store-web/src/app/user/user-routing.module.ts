import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UserListComponent} from "./user-list/user-list.component";
import {UserMainComponent} from "./user-main/user-main.component";

const routes: Routes = [
  {
    path: '',
    component: UserMainComponent,
    children: [
      {
        path: 'list',
        component: UserListComponent
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
export class UserRoutingModule { }
