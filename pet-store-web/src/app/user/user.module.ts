import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserMainComponent } from './user-main/user-main.component';


@NgModule({
  declarations: [UserMainComponent],
  imports: [
    CommonModule,
    UserRoutingModule
  ]
})
export class UserModule { }
