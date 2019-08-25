import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../auth.service';
import {first} from 'rxjs/operators';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
	form: FormGroup;
	hidePass = true;
	loggingIn = false;
	error: string = null;

	constructor(
		private formBuilder: FormBuilder,
		private router: Router,
		private authSvc: AuthService
	) {
		this.form = this.formBuilder.group({
			email: ['', [Validators.required, Validators.email]],
			pass: ['', Validators.required],
		}, {
			updateOn: 'submit',
		});
	}

	get f() {
		return this.form.controls;
	}

	login() {
		if (this.form.invalid) {
			return;
		}

		this.loggingIn = true;
		this.authSvc.login(this.f.email.value, this.f.pass.value)
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
	}

}
