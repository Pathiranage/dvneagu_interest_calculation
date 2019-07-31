import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../services/authentication.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';

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
            userName: new FormControl('admin', [Validators.required]),
            password: new FormControl('admin', [Validators.required]),
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
        console.log(password);
        sessionStorage.setItem('userName', userName);
        sessionStorage.setItem('userRole', 'ROLE_ADMIN');
        this.authService.authenticate(userName, password)
            .subscribe(res => {
                console.log(res);
            });
        this.router.navigate(['']);
    }
}
