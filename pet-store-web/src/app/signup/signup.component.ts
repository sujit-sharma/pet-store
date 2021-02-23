import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../services/user.service";
import {UserEntity} from "../services/entities";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {

  form: FormGroup;

  constructor(private fb: FormBuilder,
              private userService: UserService) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      'username' : this.fb.control('', [Validators.required]),
      'firstName' : this.fb.control('', [Validators.required]),
      'lastName' : this.fb.control('', [Validators.required]),
      'email' : this.fb.control('', [Validators.required, Validators.email]),
      'phone' : this.fb.control('', []),
      'password' : this.fb.control('', [Validators.required]),
      'confirmPassword' : this.fb.control('', [Validators.required]),
      'authorities' : this.fb.control('PET_USER|STORE_USER', [Validators.required]),
    });
  }

  async submitForm($event) {
    if (this.form.valid) {
      const value = this.form.value;

      const entity: UserEntity = await this.userService.createUser(
        {
          firstName: value.firstName,
          lastName: value.lastName,
          password: value.password,
          username: value.username,
          phone: value.phone,
          authorities: value.authorities.split('|')
        }).toPromise();

      alert(`User created with id: ${entity.id}`)

    } else {
      alert("Your submitted form has invalid values.")
    }
  }
}
