import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {InputsComponent} from './inputs/inputs.component';
import {SignUpComponent} from './sign-up/sign-up.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {AuthGaurdService} from './services/auth-gaurd.service';
import {ChangePasswordComponent} from './change-password/change-password.component';

const routes: Routes = [
    {path: '', component: InputsComponent, canActivate: [AuthGaurdService]},
    {path: 'login', component: LoginComponent},
    {path: 'sign-up', component: SignUpComponent},
    {path: 'dashboard', component: DashboardComponent, canActivate: [AuthGaurdService]},
    {path: 'change-password', component: ChangePasswordComponent, canActivate: [AuthGaurdService]}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
