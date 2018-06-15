import {Injectable} from '@angular/core';
import {User} from '../model/User';
import {AppComponent} from '../app.component';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';


@Injectable()
export class AccountService {

  constructor(public http: HttpClient) {
  }


  createAccount(user: User): Observable<User> {
    const httpHeaders: HttpHeaders = new HttpHeaders();
    httpHeaders.append('Content-Type', 'application/json');
    const options = {headers: httpHeaders};
    return this.http.post<User>(AppComponent.API_URL + '/account/register', user, options);

  }

}
