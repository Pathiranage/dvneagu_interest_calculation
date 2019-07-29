import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {AuthenticationService} from './authentication.service';

@Injectable({
    providedIn: 'root'
})
export class AuthGaurdService implements CanActivate {

    constructor(private router: Router,
                private authService: AuthenticationService) {
    }

    // tslint:disable-next-line:max-line-length
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
        if (this.authService.isUserLoggedIn()) {
            return true;
        }

        this.router.navigate(['login']);
        return false;
    }
}
