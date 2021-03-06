import {AfterViewInit, Component, Input, OnChanges, SimpleChanges, ViewChild} from '@angular/core';
import {Observable, of} from 'rxjs';
import {MatDialog, MatPaginator} from '@angular/material';
import {catchError, map, switchMap} from 'rxjs/operators';

import {GameReview} from '../../common/game-review';
import {Page} from '../../../shared/page';
import {GameReviewsService} from '../../game-reviews.service';
import {DialogData, DialogResult, GameReviewEditComponent} from '../game-review-edit/game-review-edit.component';

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
	gameId: number;

	@Input()
	initialReviews: Page<GameReview>;

	@Input()
	dudeReview?: GameReview;

	@ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;

	constructor(
		private reviewSvc: GameReviewsService,
		private dialog: MatDialog,
	) {}

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

		return this.reviewSvc.getReviews(this.gameId, this.paginator.pageIndex)
			.pipe(
				map(page => {
					this.loading = false;
					this.reviewsCount = page.totalElements;

					return page.content;
				}),
				catchError(() => {
					this.loading = false;
					// TODO: show snackbar with error

					return of([]);
				}),
			);
	}

	openReviewDialog() {
		const dialogData: DialogData = {
			gameId: this.gameId,
			review: this.dudeReview,
		};

		const dialogRef = this.dialog.open(GameReviewEditComponent, {data: dialogData});

		dialogRef.afterClosed()
			.subscribe((result: DialogResult) => {
				if (!result) {
					return;
				}

				this.dudeReview = result.review || null;
				this.loadData()
					.subscribe(data => this.reviews = data);
			});
	}
}
