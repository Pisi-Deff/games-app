import {Injectable} from '@angular/core';
import {
	HttpRequest,
	HttpHandler,
	HttpEvent,
	HttpInterceptor
} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {AuthService} from './auth.service';
import {environment} from '../../environments/environment';
import {catchError} from 'rxjs/operators';
import {Router} from '@angular/router';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
	constructor(private authSvc: AuthService, private router: Router) {}

	intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
		return next.handle(request).pipe(catchError(err => {
			// TODO: 403 might also just mean current user has no permissions for given resource? need to figure out spring part
			if (err.status === 403 && this.authSvc.token && request.url.startsWith(environment.apiURI)) {
				// TODO: how to save filled form data?
				this.authSvc.logout();
			}

			return throwError(err);
		}));
	}
}
