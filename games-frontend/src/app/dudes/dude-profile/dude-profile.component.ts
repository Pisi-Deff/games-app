import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Observable, Subject} from 'rxjs';

import {Dude} from '../../shared/dude';
import {DudesService} from '../dudes.service';
import {switchMap, takeUntil} from 'rxjs/operators';

@Component({
	selector: 'app-dude-profile',
	templateUrl: './dude-profile.component.html',
	styleUrls: ['./dude-profile.component.scss']
})
export class DudeProfileComponent implements OnInit, OnDestroy {
	dude: Observable<Dude>;

	private ngUnsubscribe: Subject<any> = new Subject();

	constructor(private dudeSvc: DudesService, private route: ActivatedRoute) {}

	ngOnInit() {
		this.dude = this.route.paramMap
			.pipe(
				takeUntil(this.ngUnsubscribe),
				switchMap(params => this.dudeSvc.get(params.get('uuid')))
			);
	}

	ngOnDestroy() {
		this.ngUnsubscribe.next();
		this.ngUnsubscribe.complete();
	}
}
