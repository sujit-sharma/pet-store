import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {OrderCreateComponent} from "./order-create/order-create.component";
import {OrderListComponent} from "./order-list/order-list.component";
import {OrderDetailComponent} from "./order-detail/order-detail.component";
import {OrderMainComponent} from "./order-main/order-main.component";

const routes: Routes = [
  {
    path: '',
    component: OrderMainComponent,
    children: [
      {
        path: 'list',
        component: OrderListComponent
      },
      {
        path: 'create',
        component: OrderCreateComponent
      },
      {
        path: ':id',
        component: OrderDetailComponent
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
export class OrderRoutingModule { }
