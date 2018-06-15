import {Component, OnInit} from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
import {LinkService} from '../../services/link.service';
import {GroupsService} from '../../services/groups.service';
import {User} from '../../model/User';
import {Link} from '../../model/Link';
import {MatSnackBar} from '@angular/material';

@Component({
  selector: 'app-add-to-group',
  templateUrl: './add-to-group.component.html',
  styleUrls: ['./add-to-group.component.css']
})
export class AddToGroupComponent implements OnInit {
  user: User;
  links: Link[];
  group: string;
  link: string;
  isValid: boolean;
  studentToGroup = new FormControl('', [
    Validators.required,
    Validators.minLength(36)
  ]);

  constructor(private groupService: GroupsService, private linkService: LinkService, public snackBar: MatSnackBar) {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    this.getAllLinks();
  }

  addToGroup() {
    this.groupService.addToGroup(this.user, this.link).subscribe(data => {
        if (data === null) {
          this.snackBar.open('Алло, ви вже у цій групі', 'Уууу', {duration: 2000});
        } else {
          this.snackBar.open('Ви успішно додались до групи', '', {duration: 2000});
        }
    }

    );
  }

  getAllLinks() {
    this.linkService.getAllGroups().subscribe(data => this.links = data);
  }

  searchLink() {
    for (const item of this.links) {
      if (item.link === this.link) {
        this.group = item.name;
        this.isValid = true;
        break;
      } else {
        this.group = 'Ваш лінк не валідний';
        this.isValid = false;
      }
    }
  }

}
