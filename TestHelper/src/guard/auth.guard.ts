import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import {AuthService} from '../app/services/auth.service';

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private user: AuthService, private router: Router) {
  }

  canActivate(next: ActivatedRouteSnapshot,
              state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

    if (!localStorage.getItem('currentUser')) {
      this.router.navigate(['/']);
    }
    return true;
  }
}
