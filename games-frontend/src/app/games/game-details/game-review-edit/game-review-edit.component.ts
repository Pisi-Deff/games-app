import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';

import {GameReview} from '../../common/game-review';
import {GameReviewsService} from '../../game-reviews.service';
import {Observable} from 'rxjs';

export interface DialogData {
	gameId: number;
	review?: GameReview;
}

export interface DialogResult {
	review?: GameReview;
	deleted?: boolean;
}

@Component({
	selector: 'app-game-review-edit',
	templateUrl: './game-review-edit.component.html',
	styleUrls: ['./game-review-edit.component.scss']
})
export class GameReviewEditComponent {
	existing = false;
	submitting = false;
	deleting = false;

	score = 0;
	review = '';

	constructor(
		@Inject(MAT_DIALOG_DATA)
		private data: DialogData,

		private dialogRef: MatDialogRef<GameReviewEditComponent>,
		private gameReviewSvc: GameReviewsService,
	) {
		if (data.review && data.review.id) {
			this.existing = true;
			this.score = data.review.score;
			this.review = data.review.review;
		}
	}

	submit() {
		if (this.score < 1 || this.score > 5) {
			// TODO: show error
			return;
		}

		this.submitting = true;

		let obs: Observable<GameReview>;
		if (this.existing) {
			obs = this.gameReviewSvc.edit(this.data.gameId, this.data.review.id, this.score, this.review);
		} else {
			obs = this.gameReviewSvc.add(this.data.gameId, this.score, this.review);
		}

		obs
			.subscribe(gr => {
				this.dialogRef.close({
					review: gr
				});
			}, error => {
				this.submitting = false;
				// TODO
			});
	}

	delete() {
		if (!this.existing) {
			return;
		}

		this.deleting = true;

		this.gameReviewSvc.delete(this.data.gameId, this.data.review.id)
			.subscribe(() => {
				this.dialogRef.close({
					deleted: true,
				});
			}, error => {
				this.deleting = false;
				// TODO
			});
	}
}
