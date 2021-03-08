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
    const token = localStorage.getItem('authToken');
    return jwtHelper.decodeToken(token);
  }
  public userAuthorities(): any {
    const parsedToken = this.jwtDecode();
    const jsonAuthorities = JSON.stringify(parsedToken.authorities );
    let auth  = 'Authorities: ';
    auth += jsonAuthorities.substr(15, 10);
    auth += ', ';
    auth += jsonAuthorities.substr(42,8);
    return auth;
  }

  logoutUser() {
    localStorage.removeItem('authToken');
  }
}
