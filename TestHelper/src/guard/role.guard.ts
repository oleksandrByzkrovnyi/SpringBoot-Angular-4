import { Injectable } from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import { Observable } from 'rxjs/Observable';
import {User} from '../app/model/User';

@Injectable()
export class RoleGuard implements CanActivate {

  constructor(private loginedUser: User, private router: Router){}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

    const expectedRole = next.data.expectedRole;

    this.loginedUser = JSON.parse(localStorage.getItem('currentUser'));
    if( this.loginedUser.position !== expectedRole ){
      this.router.navigate(['/']);
      return false;
    }
    return true;
  }
}
