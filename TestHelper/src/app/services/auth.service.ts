import {Injectable} from '@angular/core';
import {Headers, Http, RequestOptions, Response} from '@angular/http';
import {User} from '../model/User';
import 'rxjs/add/operator/map';
import {AppComponent} from '../app.component';
import {Md5} from 'ts-md5/dist/md5';
import {Router} from '@angular/router';

@Injectable()
export class AuthService {
  public token: string;
  public isLoggedIn: boolean;


  constructor(public http: Http, private router: Router) {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.token = currentUser && currentUser.token;
    this.isLoggedIn = false;
  }

  public logIn(user: User) {

    const headers = new Headers();
    headers.append('Accept', 'application/json');
    const pass = <string>Md5.hashStr(user.password);
    const base64Credential: string = btoa(user.email + ':' + pass);
    headers.append('Authorization', 'Basic ' + base64Credential);

    const options = new RequestOptions();
    options.headers = headers;

    return this.http.get(AppComponent.API_URL + '/account/login', options)
      .map((response: Response) => {
        let token = response.json() && response.json().token;
        this.token = token;
        user = <User>response.json().principal;
        if (user) {
          localStorage.setItem('currentUser', JSON.stringify(user));
          this.isLoggedIn = true;
        }
      });
  }


  logOut() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.isLoggedIn = false;
    this.router.navigate(['/']);
  }

}
