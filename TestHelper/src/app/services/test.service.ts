import {Injectable} from '@angular/core';
import {User} from '../model/User';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {AppComponent} from '../app.component';
import {Observable} from '../../../node_modules/rxjs';
import {Template} from '../model/Template';


@Injectable()
export class TestService {

  constructor(private http: HttpClient, private user: User) {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
  }

  getTeacherTest(user: User, group: string) {
    const httpHeaders: HttpHeaders = new HttpHeaders();
    httpHeaders.append('Content-Type', 'application/json');
    const options = {headers: httpHeaders};
    const params = new HttpParams()
      .set('user', user.idUser)
      .set('group', group);
    return this.http.get(AppComponent.API_URL + '/tests/getTeacherTest', {headers: httpHeaders, params: params});
  }

  addOldTest(idGroup: string, idTest: string) {
    const httpHeaders: HttpHeaders = new HttpHeaders();
    httpHeaders.append('Content-Type', 'application/json');
    const params = new HttpParams()
      .set('idGroup', idGroup)
      .set('idTest', String(idTest));
    return this.http.get(AppComponent.API_URL + '/tests/addOldTest', {headers: httpHeaders, params: params});
  }

  deleteGroupTest(idGroup: string, idTest: string) {
    const httpHeaders: HttpHeaders = new HttpHeaders();
    httpHeaders.append('Content-Type', 'application/json');
    const params = new HttpParams()
      .set('idGroup', idGroup)
      .set('idTest', String(idTest));
    return this.http.get(AppComponent.API_URL + '/test4Group/deleteGroupTest', {headers: httpHeaders, params: params});
  }

  addTest(idTest: string, name: string, templates: Template): Observable<any> {

    const httpHeaders: HttpHeaders = new HttpHeaders();
    httpHeaders.append('Content-Type', 'application/json');

    const params = new HttpParams()
      .set('idTest', idTest)
      .set('name', name)
      .set('template', String(templates.idTemplate));
    const options = {headers: httpHeaders, params: params};

    return this.http.get(AppComponent.API_URL + '/tests/add', options);
  }

}
