import {NgModule} from '@angular/core';
import {Route, RouterModule} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {SharedModule} from '../shared/shared.module';
import {AuthService} from './auth.service';
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {JwtInterceptor} from './jwt.interceptor';
import {ErrorInterceptor} from './error.interceptor';

const routes: Route[] = [
	{path: 'login', component: LoginComponent}, // TODO: block login if user is authenticated
];

@NgModule({
	declarations: [LoginComponent],
	imports: [
		SharedModule,
		RouterModule.forChild(routes),
	],
	providers: [
		{provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
		{provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true},
		AuthService,
	],
})
export class AuthModule {}
