import { Component, OnInit } from '@angular/core';
import { faHeart } from '@fortawesome/free-regular-svg-icons';
import { JwtHelperService} from '@auth0/angular-jwt';

@Component({
  selector: 'app-page-wrapper',
  templateUrl: './page-wrapper.component.html',
  styleUrls: ['./page-wrapper.component.scss']
})
export class PageWrapperComponent implements OnInit {

  faHeart = faHeart;

  constructor() { }

  ngOnInit(): void {
  }

  public jwtDecode(): any {
    const jwtHelper = new JwtHelperService();
    const token = localStorage.getItem('authToken')
    return jwtHelper.decodeToken(token);
  }

  logoutUser() {
    localStorage.removeItem('authToken');
  }
}
