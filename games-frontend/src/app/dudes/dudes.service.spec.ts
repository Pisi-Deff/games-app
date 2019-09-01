import {TestBed} from '@angular/core/testing';

import {DudesService} from './dudes.service';

describe('DudesService', () => {
	beforeEach(() => TestBed.configureTestingModule({}));

	it('should be created', () => {
		const service: DudesService = TestBed.get(DudesService);
		expect(service).toBeTruthy();
	});
});
