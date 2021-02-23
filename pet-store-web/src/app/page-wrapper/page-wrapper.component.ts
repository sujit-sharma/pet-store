import { Component, OnInit } from '@angular/core';
import { faHeart } from '@fortawesome/free-regular-svg-icons';

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

}
