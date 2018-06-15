import {Injectable, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import 'rxjs/add/operator/map';
import {User} from '../model/User';
import {AuthService} from './auth.service';
import {AppComponent} from '../app.component';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {DataDisabledUser} from '../teacher/teacher-main/teacher-main.component';
import {Link} from '../model/Link';

@Injectable()
export class GroupsService implements OnInit {

  ngOnInit() {

  }

  constructor(private http: HttpClient, private user: User, private auth: AuthService) {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
  }

  getAllGroups(user: User): Observable<any> {
    const httpHeaders: HttpHeaders = new HttpHeaders();
    httpHeaders.append('Content-Type', 'application/json');
    const options = {headers: httpHeaders};
    return this.http.post(AppComponent.API_URL + '/groups/getAllGroups', user, options);
  }

  deleteStudentFromGroup(user: User, link: Link): Observable<any> {
    const httpHeaders: HttpHeaders = new HttpHeaders();
    httpHeaders.append('Content-Type', 'multipart/form-data');

    const fd = new FormData();
    fd.append('user', user.idUser);
    fd.append('link', link.link);
    const options = {headers: httpHeaders};

    return this.http.post(AppComponent.API_URL + '/groups/deleteStudents', fd, options);

  }

  addToGroup(user: User, link: string): Observable<any> {
    const httpHeaders: HttpHeaders = new HttpHeaders();
    httpHeaders.append('Content-Type', 'multipart/form-data');
    const options = {headers: httpHeaders};

    const fd = new FormData();
    fd.append('user', user.idUser);
    fd.append('link', link);
    return this.http.post(AppComponent.API_URL + '/groups/addToGroup', fd, options);
  }

  getAllDisableStudent(user: User): Observable<any> {
    const httpHeaders: HttpHeaders = new HttpHeaders();
    httpHeaders.append('Content-Type', 'application/json');
    const params = new HttpParams()
      .set('user', user.idUser);
    return this.http.get(AppComponent.API_URL + '/groups/getDisableStudent', {headers: httpHeaders, params: params});
  }

  accessGroup(dissable: DataDisabledUser) {
    const httpHeaders: HttpHeaders = new HttpHeaders();
    httpHeaders.append('Content-Type', 'multipart/form-data');
    const options = {headers: httpHeaders};

    const fd = new FormData();
    fd.append('user', dissable.idStudent);
    fd.append('link', dissable.link);
    return this.http.post(AppComponent.API_URL + '/groups/acceptStudent', fd, options);

  }

  deniedStudent(dissable: DataDisabledUser) {
    const httpHeaders: HttpHeaders = new HttpHeaders();
    httpHeaders.append('Content-Type', 'multipart/form-data');
    const options = {headers: httpHeaders};

    const fd = new FormData();
    fd.append('user', dissable.idStudent);
    fd.append('link', dissable.link);
    return this.http.post(AppComponent.API_URL + '/groups/deniedStudent', fd, options);

  }


}
