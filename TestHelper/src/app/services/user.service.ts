import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule, HttpHeaders, HttpParams} from '@angular/common/http';
import {AppComponent} from '../app.component';
import {Observable} from 'rxjs/Observable';
import {User} from '../model/User';

@Injectable()
export class UserService {

  constructor(private http: HttpClient) { }

  getStudents(): Observable<any> {
    const httpHeaders: HttpHeaders = new HttpHeaders();
    httpHeaders.append('Content-Type', 'application/json');
    const options = {headers: httpHeaders};
    return this.http.get(AppComponent.API_URL + '/students/getAllStudent', options);
  }

  editProfile(user: User): Observable<any> {
    const httpHeaders: HttpHeaders = new HttpHeaders();
    httpHeaders.append('Content-Type', 'application/json');

    return this.http.post(AppComponent.API_URL + '/account/editProfile', user , { headers: httpHeaders});
  }
}
