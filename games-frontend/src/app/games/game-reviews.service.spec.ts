import {TestBed} from '@angular/core/testing';

import {GameReviewsService} from './game-reviews.service';

describe('GameReviewsService', () => {
	beforeEach(() => TestBed.configureTestingModule({}));

	it('should be created', () => {
		const service: GameReviewsService = TestBed.get(GameReviewsService);
		expect(service).toBeTruthy();
	});
});
