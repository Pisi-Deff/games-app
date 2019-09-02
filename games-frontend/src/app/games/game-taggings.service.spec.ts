import {TestBed} from '@angular/core/testing';

import {GameTaggingsService} from './game-taggings.service';

describe('GameTaggingsService', () => {
	beforeEach(() => TestBed.configureTestingModule({}));

	it('should be created', () => {
		const service: GameTaggingsService = TestBed.get(GameTaggingsService);
		expect(service).toBeTruthy();
	});
});
