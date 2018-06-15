import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-login',
  template: '<app-header></app-header><app-login-form></app-login-form>',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor() {
  }

  ngOnInit() {
  }

}
