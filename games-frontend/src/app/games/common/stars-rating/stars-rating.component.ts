import {Component, EventEmitter, Input, OnChanges, Output, SimpleChanges} from '@angular/core';

@Component({
	selector: 'app-stars-rating',
	templateUrl: './stars-rating.component.html',
	styleUrls: ['./stars-rating.component.scss']
})
export class StarsRatingComponent implements OnChanges {
	fullStars: number;
	halfStar: boolean;
	emptyStars: number;

	@Input()
	score: number;

	@Input()
	maxScore = 5;

	@Input()
	tooltips: 'individual' | 'combined' = 'combined';

	@Output()
	onScoreSet = new EventEmitter<number>();

	constructor() {}

	ngOnChanges(changes: SimpleChanges): void {
		if (changes.score) {
			const rounded = Math.round(changes.score.currentValue * 2) / 2;
			this.fullStars = Math.floor(rounded);
			this.halfStar = rounded - this.fullStars > 0;
			this.emptyStars = this.maxScore - this.fullStars - (this.halfStar ? 1 : 0);
		}
	}

	setScore(i: number) {
		this.onScoreSet.next(i);
	}
}
