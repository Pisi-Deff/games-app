import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {GameDetailed} from './game-detailed';
import {GamesService} from '../games.service';
import {switchMap} from 'rxjs/operators';
import {Observable} from 'rxjs';

@Component({
	templateUrl: './game-details.component.html',
	styleUrls: ['./game-details.component.scss']
})
export class GameDetailsComponent implements OnInit {
	game: Observable<GameDetailed>;

	constructor(private gameSvc: GamesService, private route: ActivatedRoute) {}

	ngOnInit() {
		this.game = this.route.paramMap.pipe(
			switchMap(params => this.gameSvc.get(+params.get('id')))
		);
	}

}
