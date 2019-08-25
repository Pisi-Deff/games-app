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

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
	constructor(private authSvc: AuthService) {}

	intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
		return next.handle(request).pipe(catchError(err => {
			if (err.status === 403 && this.authSvc.token && request.url.startsWith(environment.apiURI)) {
				this.authSvc.logout();
				location.reload(); // TODO: find better option. this wipes all data stored in memory like filled form data
			}

			return throwError(err);
		}));
	}
}
