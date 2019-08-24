import {Component} from '@angular/core';
import {Router} from '@angular/router';

@Component({
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
	hidePass = true;
	credentials = {username: '', password: ''};
	loggingIn = false;
	error: string = null;

	constructor(private router: Router) {}

	login() {
		// TODO: check creds
		this.router.navigateByUrl('/');
		return false;
	}

}
