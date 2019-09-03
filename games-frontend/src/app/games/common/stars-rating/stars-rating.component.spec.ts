import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {StarsRatingComponent} from './stars-rating.component';

describe('StarsRatingComponent', () => {
	let component: StarsRatingComponent;
	let fixture: ComponentFixture<StarsRatingComponent>;

	beforeEach(async(() => {
		TestBed.configureTestingModule({
			declarations: [StarsRatingComponent]
		})
			.compileComponents();
	}));

	beforeEach(() => {
		fixture = TestBed.createComponent(StarsRatingComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});
});
