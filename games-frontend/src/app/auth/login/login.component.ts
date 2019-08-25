import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../auth.service';
import {first} from 'rxjs/operators';

@Component({
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
	hidePass = true;
	credentials = {email: '', password: ''};
	loggingIn = false;
	error: string = null;

	constructor(private router: Router, private authSvc: AuthService) {}

	login() {
		// TODO: validate fields
		this.loggingIn = true;
		this.authSvc.login(this.credentials.email, this.credentials.password)
			.pipe(first())
			.subscribe((result) => {
				const {success, error} = result;
				this.loggingIn = false;

				if (success) {
					this.error = null;
					this.router.navigateByUrl('/'); // TODO: redirect to attempted uri
				} else {
					this.error = error || 'unknown';
				}
			});
		return false;
	}

}
