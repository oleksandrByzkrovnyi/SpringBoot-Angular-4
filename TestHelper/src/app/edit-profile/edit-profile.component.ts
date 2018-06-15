import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatSnackBar} from '@angular/material';
import {FormControl, Validators} from '@angular/forms';
import {UserService} from '../services/user.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private userService: UserService, private  snackBar: MatSnackBar ) {
  }

  oldPassControl = new FormControl('', [
    Validators.required,
    Validators.minLength(8),
  ]);
  newPassControl = new FormControl('', [
    Validators.required,
    Validators.minLength(8)
  ]);
  ConfirmPass = new FormControl('', [
    Validators.required,
    Validators.minLength(8)
  ]);
  emailControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);
  lastNameControl = new FormControl('', [
    Validators.minLength(3),
  ]);
  firstNameControl = new FormControl('', [
    Validators.minLength(2),
  ]);


  lastName: string;
  firstName: string;

  oldPass: string;
  newPass: string;
  confirm: string;

  newEmail: string;

  user = this.data.user;

  ngOnInit() {
  }

  editProfile() {
    if (this.lastName != null) {
      this.user.lastName = this.lastName;
    }
    if (this.firstName != null) {
      this.user.firstName = this.firstName;
    }
    if (this.confirm != null) {
      this.user.password = this.confirm;
    }
    if (this.newEmail != null) {
      this.user.email = this.newEmail;
    }

    this.userService.editProfile(this.user).subscribe(data => {
      console.log(data);
      this.snackBar.open('Ваш профіль тепер бездоганний', 'Дякую', {
        duration: 2000
      });
    });
  }

}

