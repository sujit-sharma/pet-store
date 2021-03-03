import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.scss']
})
export class LogoutComponent implements OnInit {
  logoutForm: FormGroup;

  constructor( private fb: FormBuilder,
               private userService: UserService,
               private router: Router) {

  }

  ngOnInit(): void {
    this.logoutForm = this.fb.group( {

    });
  }

}
