import {Injectable} from '@angular/core';
import {AppConfigService} from './app-config.service';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {tap} from 'rxjs/operators';
import {Router} from '@angular/router';

const httpOptions = {
    headers: new HttpHeaders(),
    withCredentials: true
};

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {

    private userLoginEndPoint = 'user/login';
    private userCPEndPoint = 'user/change-password';

    constructor(private appConfig: AppConfigService,
                private router: Router,
                private http: HttpClient) {
    }

    authenticate(userName: string, password: string) {
        const url = this.appConfig.BASE_URL + this.appConfig.URL_SEPARATOR + this.userLoginEndPoint;
        let headers: HttpHeaders = new HttpHeaders();
        headers = headers.append('Content-Type', 'application/json');
        headers = headers.append('userName', userName);
        headers = headers.append('password', password);
        httpOptions.headers = headers;

        return this.http.post(url, '', httpOptions)
            .pipe(
                tap(res => {
                    return res;
                })
            );
    }

    isUserLoggedIn() {
        const user = sessionStorage.getItem('userName');
        return !(user === null);
    }

    logOut() {
        sessionStorage.removeItem('userName');
        sessionStorage.removeItem('userRole');
        this.router.navigate(['login']);
    }

    changePassword(userName: string, currentPassword: string, password: string) {
        const url = this.appConfig.BASE_URL + this.appConfig.URL_SEPARATOR + this.userCPEndPoint;
        let headers: HttpHeaders = new HttpHeaders();
        headers = headers.append('Content-Type', 'application/json');
        headers = headers.append('userName', userName);
        headers = headers.append('currentPassword', currentPassword);
        headers = headers.append('password', password);
        httpOptions.headers = headers;

        return this.http.post(url, '', httpOptions)
            .pipe(
                tap(res => {
                    return res;
                })
            );
    }
}
