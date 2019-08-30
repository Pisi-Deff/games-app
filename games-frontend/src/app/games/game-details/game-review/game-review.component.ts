import {Component, Input, OnInit} from '@angular/core';
import {GameReview} from '../../common/game-review';
import * as moment from 'moment';

@Component({
	selector: 'app-game-review',
	templateUrl: './game-review.component.html',
	styleUrls: ['./game-review.component.scss']
})
export class GameReviewComponent implements OnInit {
	@Input()
	review: GameReview;

	reviewDate: string;

	constructor() {}

	ngOnInit() {
		this.reviewDate = moment(this.review.reviewDate).format('LLL');
	}
}
