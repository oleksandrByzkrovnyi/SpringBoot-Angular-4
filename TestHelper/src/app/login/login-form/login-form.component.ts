import {Component, OnInit} from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
import {User} from '../../model/User';
import {AuthService} from '../../services/auth.service';
import {Router} from '@angular/router';
import {MatSnackBar} from '@angular/material';


@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {
  user: User = new User();
  loginedUser: User = new User();
  errorMessage: string;
  email = new FormControl('', [Validators.required, Validators.email]);

  constructor(private authService: AuthService, private router: Router, public snackBar: MatSnackBar) {
  }

  login() {
    this.authService.logIn(this.user)
      .subscribe(data => {
          this.loginedUser = JSON.parse(localStorage.getItem('currentUser'));

          if (this.loginedUser.position === 'STUDENT') {
            this.router.navigate(['/main_student']);
          } else {
            this.router.navigate(['/main_teacher']);
          }


        }, err => {
          this.errorMessage = 'error :  Username or password is incorrect';
          this.openSnackBar();
        }
      );
  }


  ngOnInit(): void {

  }

  getErrorMessage() {
    return this.email.hasError('required') ? 'You must enter a value' :
      this.email.hasError('email') ? 'Not a valid email' :
        '';
  }

  openSnackBar() {
    this.snackBar.open('Логін або пароль не вірні', 'Спробувати ще', {
      duration: 2000,
    });
  }
}





