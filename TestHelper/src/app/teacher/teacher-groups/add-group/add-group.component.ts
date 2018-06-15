import {Component, Inject, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {GroupsService} from '../../../services/groups.service';
import {Link} from '../../../model/Link';
import {UUID} from 'angular2-uuid';
import {User} from '../../../model/User';
import {LinkService} from '../../../services/link.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';

@Component({
  selector: 'app-add-group',
  templateUrl: './add-group.component.html',
  styleUrls: ['./add-group.component.css']
})
export class AddGroupComponent implements OnInit {


  user: User;
  name: string;

  constructor(private http: HttpClient,
              private groupService: GroupsService,
              private link: Link, private uuid: UUID,
              private linkService: LinkService,
              public dialogRef: MatDialogRef<AddGroupComponent>,
              @Inject(MAT_DIALOG_DATA) private data) {


    this.user = JSON.parse(localStorage.getItem('currentUser'));

  }


  ngOnInit() {
  }


  createGroup() {
    this.dialogRef.close(this.name);
    this.link.name = this.name;
    this.link.link = UUID.UUID();
    this.link.idTeacher = this.user.idUser;
    this.linkService.addGroup(this.link).subscribe(data => this.link = data);
    this.linkService.getGroups(this.user);
  }


}
