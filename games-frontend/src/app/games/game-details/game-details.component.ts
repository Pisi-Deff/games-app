import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {switchMap, takeUntil} from 'rxjs/operators';
import {Subject} from 'rxjs';
import * as moment from 'moment';

import {GameDetailed} from './game-detailed';
import {GamesService} from '../games.service';

@Component({
	templateUrl: './game-details.component.html',
	styleUrls: ['./game-details.component.scss']
})
export class GameDetailsComponent implements OnInit, OnDestroy {
	game: GameDetailed;
	releaseDate: string;
	tmpReleaseDate2 = moment().format('LL');

	private ngUnsubscribe: Subject<any> = new Subject();

	constructor(private gameSvc: GamesService, private route: ActivatedRoute) {}

	ngOnInit() {
		this.route.paramMap
			.pipe(
				takeUntil(this.ngUnsubscribe),
				switchMap(params => this.gameSvc.get(+params.get('id')))
			)
			.subscribe(game => {
				this.game = game;
				this.releaseDate = moment(game.releaseDate).format('LL');
			});
	}

	ngOnDestroy() {
		this.ngUnsubscribe.next();
		this.ngUnsubscribe.complete();
	}
}
