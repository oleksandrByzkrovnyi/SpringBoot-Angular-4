import {Injectable, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {User} from '../model/User';
import {AuthService} from './auth.service';
import {Observable} from '../../../node_modules/rxjs';
import {AppComponent} from '../app.component';
import {Link} from '../model/Link';

@Injectable()
export class LinkService implements OnInit {
  ngOnInit() {

  }

  constructor(private http: HttpClient, private user: User, private auth: AuthService, private newLink: Link) {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
  }

  getGroups(user: User): Observable<any> {
    const httpHeaders: HttpHeaders = new HttpHeaders();
    httpHeaders.append('Content-Type', 'application/json');
    const options = {headers: httpHeaders};
    return this.http.post(AppComponent.API_URL + '/links/getAllGroupsByTeacher', user, options);
  }

  getAllGroups(): Observable<any> {
    const httpHeaders: HttpHeaders = new HttpHeaders();
    httpHeaders.append('Content-Type', 'application/json');
    const options = {headers: httpHeaders};
    return this.http.get(AppComponent.API_URL + '/links/getAllGroups', options);
  }

  addGroup(link: Link): Observable<any> {
    const httpHeaders: HttpHeaders = new HttpHeaders();
    httpHeaders.append('Content-Type', 'application/json');
    const options = {headers: httpHeaders};
    return this.http.post(AppComponent.API_URL + '/links/addGroup', link, options);
  }

  getGroupInfo(link: Link): Observable<any> {
    const httpHeaders: HttpHeaders = new HttpHeaders();
    httpHeaders.append('Content-Type', 'application/json');
    const options = {headers: httpHeaders};
    return this.http.post(AppComponent.API_URL + '/links/getGroupInfo', link, options);
  }

  getStudents(idTeacher: string, link: string): Observable<any> {
    const httpHeaders: HttpHeaders = new HttpHeaders();
    httpHeaders.append('Content-Type', 'application/json');
    const params = new HttpParams()
      .set('user', idTeacher)
      .set('link', link);
    const options = {headers: httpHeaders, params: params};
    return this.http.get(AppComponent.API_URL + '/students/getStudents', options);
  }

  delete(link: Link): Observable<any> {
    const httpHeaders: HttpHeaders = new HttpHeaders();
    httpHeaders.append('Content-Type', 'application/json');
    const options = {headers: httpHeaders};
    return this.http.post(AppComponent.API_URL + '/links/delete', link, options);
  }
}
