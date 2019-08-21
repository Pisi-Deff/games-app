import {NgModule} from '@angular/core';
import {Route, RouterModule} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {SharedModule} from '../shared/shared.module';

const routes: Route[] = [
	{path: 'login', component: LoginComponent},
];

@NgModule({
	declarations: [LoginComponent],
	imports: [
		SharedModule,
		RouterModule.forChild(routes),
	]
})
export class AuthModule {}
