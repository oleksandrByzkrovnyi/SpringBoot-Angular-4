import {Component, OnInit} from '@angular/core';
import {User} from '../../model/User';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-teacher-test',
  templateUrl: './teacher-test.component.html',
  styleUrls: ['./teacher-test.component.css']
})
export class TeacherTestComponent implements OnInit {

  constructor(private user: User, private authService: AuthService) {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
  }

  logOut() {
    this.authService.logOut();
  }

}
