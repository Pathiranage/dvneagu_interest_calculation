import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {EsResponse} from '../util/es-response';
import {AuthenticationService} from '../services/authentication.service';
import {Router} from '@angular/router';

@Component({
    selector: 'app-change-password',
    templateUrl: './change-password.component.html',
    styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {
    changePasswordFormGroup: FormGroup;

    constructor(private authService: AuthenticationService, private router: Router) {
    }

    ngOnInit() {
        this.changePasswordFormGroup = new FormGroup({
            userName: new FormControl('', [Validators.required]),
            currentPassword: new FormControl('', [Validators.required]),
            password: new FormControl('', [Validators.required]),
        });
    }

    changePassword() {
        const userName = this.changePasswordFormGroup.value.userName;
        const currentPassword = this.changePasswordFormGroup.value.currentPassword;
        const password = this.changePasswordFormGroup.value.password;
        this.authService.changePassword(userName, currentPassword, password)
            .subscribe((res: EsResponse) => {
                if (res.status === 1) {
                    alert(res.message);
                    this.authService.logOut();
                } else {
                    alert(res.message);
                }
            });
    }
}
