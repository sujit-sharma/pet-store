import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DashboardComponent} from "./dashboard/dashboard.component";
import {OrderModule} from "./order/order.module";
import {UserModule} from "./user/user.module";
import {PetModule} from "./pet/pet.module";
import {PageWrapperComponent} from "./page-wrapper/page-wrapper.component";
import {SignupComponent} from "./signup/signup.component";
import {SigninComponent} from "./signin/signin.component";

const routes: Routes = [
  {
    path: 'pages',
    component: PageWrapperComponent,
    children: [
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
    ]
  },
  {
    path: 'signup',
    component: SignupComponent
  },
  {
    path: 'signin',
    component: SigninComponent
  },
  {
    path: "**",
    pathMatch: 'full',
    redirectTo: 'signin'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
