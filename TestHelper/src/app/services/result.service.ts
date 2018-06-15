import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {AppComponent} from '../app.component';
import {User} from '../model/User';
import {Observable} from 'rxjs/Observable';
import {Test} from '../model/Test';

@Injectable()
export class ResultService {

  constructor(private http: HttpClient) { }

  getStudentTest(student: User): Observable<any> {
    const httpHeaders: HttpHeaders = new HttpHeaders();
    httpHeaders.append('Content-Type', 'application/json');
    const params = new HttpParams()
      .set('user', student.idUser)
    const options = {headers: httpHeaders, params: params};
    return this.http.get(AppComponent.API_URL + '/result/getStudentTest', options);
  }

  getStudentResult(student: User, idTest: string): Observable<any> {
    const httpHeaders: HttpHeaders = new HttpHeaders();
    httpHeaders.append('Content-Type', 'application/json');
    const params = new HttpParams()
      .set('user', student.idUser)
      .set('idTest', idTest);
    const options = {headers: httpHeaders, params: params};
    return this.http.get(AppComponent.API_URL + '/result/getStudentResult', options);
  }


}
