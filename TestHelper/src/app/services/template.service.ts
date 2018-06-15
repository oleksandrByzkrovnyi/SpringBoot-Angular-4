import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {User} from '../model/User';
import {AppComponent} from '../app.component';
import {Template} from '../model/Template';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class TemplateService {

  constructor(private http: HttpClient, private user: User) {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
  }


  addTemplate(template: Template) {
    const httpHeaders: HttpHeaders = new HttpHeaders();
    httpHeaders.append('Content-Type', 'application/json');
    const options = {headers: httpHeaders};
    return this.http.post(AppComponent.API_URL + '/templates/addTemplate', template, options);
  }

  getTemplates(user: User): Observable<any> {
    const httpHeaders: HttpHeaders = new HttpHeaders();
    httpHeaders.append('Content-Type', 'application/json');
    const options = {headers: httpHeaders};
    return this.http.post(AppComponent.API_URL + '/templates/getTemplates', user, options);
  }

  deleteTemplate(template: Template): Observable<any> {
    const httpHeaders: HttpHeaders = new HttpHeaders();
    httpHeaders.append('Content-Type', 'application/json');
    const options = {headers: httpHeaders};
    return this.http.post(AppComponent.API_URL + '/templates/deleteTemplate', template, options);
  }

}
