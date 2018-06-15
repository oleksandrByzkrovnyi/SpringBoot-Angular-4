import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AppComponent} from '../app.component';
import {Observable} from '../../../node_modules/rxjs';
import {User} from '../model/User';
import {Test} from '../model/Test';

@Injectable()
export class UploadService {

  constructor(private http: HttpClient) {
  }

  upload(file: any, student: User, test: Test): Observable<any> {
    const fd = new FormData();
    fd.append('image', file, file.name);
    fd.append('student', student.idUser);
    fd.append('test', String(test.idTest));
    return this.http.post(AppComponent.API_URL + '/check/sendFile', fd);
  }


}
