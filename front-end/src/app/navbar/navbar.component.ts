import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from '../services/authentication.service';

@Component({
    selector: 'app-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
    isAdmin = false;

    constructor(
        private router: Router,
        private authService: AuthenticationService,
    ) {

    }

    ngOnInit() {
        this.isAdmin = sessionStorage.getItem('userRole') === 'ROLE_ADMIN';
    }

    navigateToLogin() {
        this.router.navigate(['/login']);
    }

    navigateToSignUp() {
        this.router.navigate(['/sign-up']);
    }

    logout() {
        this.authService.logOut();
    }
}
