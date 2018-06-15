import {Component, OnInit} from '@angular/core';
import {LinkService} from '../../../services/link.service';
import {User} from '../../../model/User';
import {Link} from '../../../model/Link';

@Component({
  selector: 'app-group-info',
  templateUrl: './group-info.component.html',
  styleUrls: ['./group-info.component.css']
})
export class GroupInfoComponent implements OnInit {
  students: User[];
  linkInfo: Link;

  constructor(private linkService: LinkService, link: Link) {
  }


  ngOnInit() {

  }


  getInfo(link: Link) {
    this.linkService.getGroupInfo(link).subscribe(data => this.linkInfo = data);
  }
}
