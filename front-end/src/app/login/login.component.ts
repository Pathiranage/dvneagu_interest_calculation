import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../services/authentication.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {EsResponse} from '../util/es-response';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    public loginFormGroup: FormGroup;

    constructor(private authService: AuthenticationService, private router: Router) {
        if (this.authService.isUserLoggedIn()) {
            this.router.navigate(['']);
        }
    }

    ngOnInit() {
        this.loginFormGroup = new FormGroup({
            userName: new FormControl('', [Validators.required]),
            password: new FormControl('', [Validators.required]),
        });
    }

    authenticate(username, password) {
        // if (username === 'kalana' && password === '123') {
        //     sessionStorage.setItem('username', username);
        //     return true;
        // } else {
        //     return false;
        // }

    }

    login() {
        const userName = this.loginFormGroup.value.userName;
        const password = this.loginFormGroup.value.password;
        this.authService.authenticate(userName, password)
            .subscribe((res: EsResponse) => {
                if (res.status === 1) {
                    sessionStorage.setItem('userName', userName);
                    sessionStorage.setItem('userRole', res.data.userType);
                    this.router.navigate(['']);
                } else {
                    alert(res.message);
                }
            });
    }
}
