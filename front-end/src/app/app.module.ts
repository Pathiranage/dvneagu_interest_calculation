import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MaterialModule} from './material/material.module';
import {NavbarComponent} from './navbar/navbar.component';
import {LoginComponent} from './login/login.component';
import {DatepickerComponent} from './datepicker/datepicker.component';
import {MainPageComponent} from './main-page/main-page.component';
import {InputsComponent} from './inputs/inputs.component';
import {BackupComponent} from './backup/backup.component';
import {HttpClientModule} from '@angular/common/http';
import {ReactiveFormsModule} from '@angular/forms';
import {SignUpComponent} from './sign-up/sign-up.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import { ChangePasswordComponent } from './change-password/change-password.component';

@NgModule({
    declarations: [
        AppComponent,
        NavbarComponent,
        LoginComponent,
        DatepickerComponent,
        MainPageComponent,
        InputsComponent,
        BackupComponent,
        SignUpComponent,
        DashboardComponent,
        ChangePasswordComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        MaterialModule,
        HttpClientModule,
        ReactiveFormsModule,
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
