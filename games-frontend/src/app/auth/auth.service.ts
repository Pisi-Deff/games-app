import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {BehaviorSubject, Observable, of, Subscription} from 'rxjs';
import {catchError, map} from 'rxjs/operators';

import {environment} from '../../environments/environment';
import {Dude} from '../shared/dude';

interface DudeData {
	token: string;
	uuid: string;
	name: string;
}

@Injectable({
	providedIn: 'root'
})
export class AuthService {
	static readonly LS_KEY_DUDE_DATA = 'dudeData';

	private dudeData: BehaviorSubject<DudeData> = new BehaviorSubject(AuthService.dudeDataLS);

	constructor(private http: HttpClient, private router: Router) {
		this.dudeData.asObservable().subscribe(data => AuthService.dudeDataLS = data);
	}

	login(email: string, password: string): Observable<{
		success: boolean,
		error?: string,
	}> {
		const formData = new FormData();
		formData.append('email', email.trim());
		formData.append('password', password.trim());

		return this.http.post<Dude>(
			`${environment.apiURI}/authenticate`,
			formData,
			{
				observe: 'response',
			},
		).pipe(
			map(resp => {
				const auth = resp.headers.get('Authorization');
				if (auth == null || !auth.startsWith('Bearer ')) {
					return {
						success: false,
					};
				}

				this.dudeData.next({
					token: auth.split(' ')[1],
					uuid: resp.body && resp.body.uuid || null,
					name: resp.body && resp.body.displayName || null,
				});

				return {
					success: true,
				};
			}),
			catchError(err => {
				if (!(err.error instanceof ErrorEvent) && err.status === 403) {
					return of({
						success: false,
						error: 'invalid-credentials',
					});
				}

				return of({
					success: false,
				});
			}));
	}

	logout() {
		this.dudeData.next(null);
		this.router.navigate(['/login']); // TODO: figure out why navigating to '/' doesn't work
	}

	get token(): string {
		const data = this.dudeData.value;
		return data && data.token || null;
	}

	dudeDataSub(cb: (value: DudeData) => void): Subscription {
		return this.dudeData.subscribe(cb);
	}

	private static get dudeDataLS() {
		const data = localStorage.getItem(AuthService.LS_KEY_DUDE_DATA);
		return data && JSON.parse(data) || null;
	}

	private static set dudeDataLS(dudeData: DudeData) {
		if (dudeData == null) {
			localStorage.removeItem(AuthService.LS_KEY_DUDE_DATA);
		} else {
			localStorage.setItem(AuthService.LS_KEY_DUDE_DATA, JSON.stringify(dudeData));
		}
	}
}
