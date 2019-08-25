import {Component, OnDestroy} from '@angular/core';
import {AuthService} from '../auth/auth.service';
import {Subscription} from 'rxjs';

@Component({
	selector: 'app-main-ui',
	templateUrl: './main-ui.component.html',
	styleUrls: ['./main-ui.component.scss']
})
export class MainUiComponent implements OnDestroy {
	dudeName: string;
	dudeDataSub: Subscription;

	constructor(private authSvc: AuthService) {
		this.dudeDataSub = this.authSvc.dudeDataSub(
			data => this.dudeName = data && data.name || '');
	}

	ngOnDestroy() {
		this.dudeDataSub.unsubscribe();
	}

	logout() {
		this.dudeDataSub.unsubscribe();
		this.authSvc.logout();
	}
}
