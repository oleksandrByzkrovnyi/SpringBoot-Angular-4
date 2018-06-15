import {Component, OnInit} from '@angular/core';
import {User} from '../../model/User';
import {AuthService} from '../../services/auth.service';
import {GroupsService} from '../../services/groups.service';
import {UserService} from '../../services/user.service';
import {LinkService} from '../../services/link.service';
import {MatDialog} from '@angular/material';
import {EditProfileComponent} from '../../edit-profile/edit-profile.component';

@Component({
  selector: 'app-teacher-main',
  templateUrl: './teacher-main.component.html',
  styleUrls: ['./teacher-main.component.css']
})
export class TeacherMainComponent implements OnInit {

  disableGroups: Array<DataDisabledUser>;
  emptyMessage: string;

  constructor(private user: User, private authService: AuthService,
              private groupService: GroupsService,
              private userService: UserService,
              private linkService: LinkService,
              public dialog: MatDialog) {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    this.getAllDisableStudents(this.user);
  }

  editProfile(): void {
     this.dialog.open(EditProfileComponent, {
      width: '350px',
      data: { user: this.user }
    });
  }


  logOut() {
    this.authService.logOut();
    console.log(localStorage.getItem('currentUser'));
  }

  getAllDisableStudents(user: User) {
    this.groupService.getAllDisableStudent(user).subscribe(resp => {
      this.disableGroups = resp;
      this.emptyMessage = 'Не підтверджених студентів не має';
      console.log(this.disableGroups);
    });
  }

  acceptStudent(disableStudent: DataDisabledUser) {
    this.groupService.accessGroup(disableStudent).subscribe(data => this.getAllDisableStudents(this.user));
  }

  denied(disableStudent: DataDisabledUser) {
    this.groupService.deniedStudent(disableStudent).subscribe(data => this.getAllDisableStudents(this.user));
  }
}

export class DataDisabledUser {
  public nameGroup: string;
  public nameStudent: string;
  public idStudent: string;
  public link: string;

}
