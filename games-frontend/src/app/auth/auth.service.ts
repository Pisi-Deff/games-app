import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable, of} from 'rxjs';
import {environment} from '../../environments/environment';
import {catchError, map} from 'rxjs/operators';

@Injectable({
	providedIn: 'root'
})
export class AuthService {
	private dudeData: BehaviorSubject<{
		token: string,
		uuid: string,
		name: string,
	}> = new BehaviorSubject(null);

	constructor(private http: HttpClient) {}

	login(email: string, password: string): Observable<{
		success: boolean,
		error?: string,
	}> {
		const formData = new FormData();
		formData.append('email', email.trim());
		formData.append('password', password.trim());

		return this.http.post<{
			uuid: string,
			displayName: string,
		}>(
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
	}

	get token(): string {
		const data = this.dudeData.value;
		return data && data.token || null;
	}
}
