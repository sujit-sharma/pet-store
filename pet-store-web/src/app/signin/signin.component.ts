import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent implements OnInit {

  form: FormGroup;

  constructor(private fb: FormBuilder,
              private userService: UserService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      'username': this.fb.control('', [Validators.required]),
      'password': this.fb.control('', [Validators.required]),
    });
  }

  async submitForm() {
    if (this.form.valid) {
      const response = await this.userService.login(this.form.value).toPromise();
      localStorage.setItem('authToken', response.token);
      this.router.navigate(['/pages/dashboard']).then();
    }
  }

}
