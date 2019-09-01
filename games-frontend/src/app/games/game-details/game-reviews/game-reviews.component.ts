import {AfterViewInit, Component, Input, OnChanges, SimpleChanges, ViewChild} from '@angular/core';
import {Observable} from 'rxjs';
import {MatPaginator} from '@angular/material';
import {switchMap} from 'rxjs/operators';

import {GameReview} from '../../common/game-review';
import {GamesService} from '../../games.service';
import {Page} from '../../../shared/page';

@Component({
	selector: 'app-game-reviews',
	templateUrl: './game-reviews.component.html',
	styleUrls: ['./game-reviews.component.scss']
})
export class GameReviewsComponent implements AfterViewInit, OnChanges {
	reviews: GameReview[] = [];
	reviewsCount = 0;
	loading = true;
	error = false; // TODO: implement

	@Input()
	initialReviews: Page<GameReview>;

	@ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;

	constructor(private gameSvc: GamesService) {}

	ngOnChanges(changes: SimpleChanges) {
		if (changes.initialReviews) {
			this.reviews = this.initialReviews.content;
			this.reviewsCount = this.initialReviews.totalElements;
		}
	}

	ngAfterViewInit() {
		this.paginator.page
			.pipe(
				switchMap(() => this.loadData()),
			).subscribe(data => this.reviews = data);
	}

	private loadData(): Observable<GameReview[]> {
		this.loading = true;

		// TODO: implement
		return null;
	}
}
