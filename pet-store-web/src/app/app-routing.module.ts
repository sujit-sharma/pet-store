import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DashboardComponent} from "./dashboard/dashboard.component";
import {OrderModule} from "./order/order.module";
import {UserModule} from "./user/user.module";
import {PetModule} from "./pet/pet.module";

const routes: Routes = [
  {
    path: 'dashboard',
    component: DashboardComponent
  },
  {
    path: 'order',
    loadChildren: () => OrderModule
  },
  {
    path: 'pet',
    loadChildren: () => PetModule
  },
  {
    path: 'user',
    loadChildren: () => UserModule
  },
  {
    path: "**",
    pathMatch: 'full',
    redirectTo: 'dashboard'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
