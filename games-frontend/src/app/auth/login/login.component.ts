import {Component} from '@angular/core';
import {Router} from '@angular/router';

@Component({
	templateUrl: './login.component.html',
})
export class LoginComponent {
	hidePass = true;
	credentials = {username: '', password: ''};
	loggingIn = false;
	error = false;

	constructor(private router: Router) {}

	login() {
		// TODO: check creds
		this.router.navigateByUrl('/');
		return false;
	}

}
