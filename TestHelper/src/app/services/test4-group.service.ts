import {Injectable} from '@angular/core';
import {AppComponent} from '../app.component';
import {Observable} from '../../../node_modules/rxjs';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';

@Injectable()
export class Test4GroupService {

  constructor(private http: HttpClient) {
  }


  getTestGroup(link: string): Observable<any> {
    const httpHeaders: HttpHeaders = new HttpHeaders();
    httpHeaders.append('Content-Type', 'application/json');
    const params = new HttpParams()
      .set('link', link);
    return this.http.get(AppComponent.API_URL + '/test4Group/getTests', {headers: httpHeaders, params: params});
  }

}
