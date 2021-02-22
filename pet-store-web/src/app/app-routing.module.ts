import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DashboardComponent} from "./dashboard/dashboard.component";
import {OrderModule} from "./order/order.module";
import {StoreModule} from "./store/store.module";
import {UserModule} from "./user/user.module";

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
    path: 'store',
    loadChildren: () => StoreModule
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
