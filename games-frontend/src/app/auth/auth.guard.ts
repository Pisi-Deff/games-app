import {Injectable} from '@angular/core';
import {
	CanActivate,
	CanActivateChild,
	CanLoad,
	Route,
	UrlSegment,
	ActivatedRouteSnapshot,
	RouterStateSnapshot,
	UrlTree, Router
} from '@angular/router';
import {Observable} from 'rxjs';
import {AuthService} from './auth.service';

@Injectable({
	providedIn: 'root'
})
export class AuthGuard implements CanActivate, CanActivateChild, CanLoad {
	constructor(private router: Router, private authSvc: AuthService) {}

	canActivate(
		next: ActivatedRouteSnapshot,
		state: RouterStateSnapshot
	): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
		const url: string = state.url;
		return this.checkLogin(url);
	}

	canActivateChild(
		next: ActivatedRouteSnapshot,
		state: RouterStateSnapshot
	): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
		return this.canActivate(next, state);
	}

	canLoad(
		route: Route,
		segments: UrlSegment[]
	): Observable<boolean> | Promise<boolean> | boolean {
		const url = `/${route.path}`;
		return this.checkLogin(url);
	}

	checkLogin(url: string): boolean {
		if (this.authSvc.token) {
			return true;
		}

		// TODO: Store the attempted URL for redirecting
		// this.authService.redirectUrl = url;
		this.router.navigate(['/login']);
		return false;
	}
}
