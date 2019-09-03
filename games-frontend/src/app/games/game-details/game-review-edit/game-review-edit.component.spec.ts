import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {GameReviewEditComponent} from './game-review-edit.component';

describe('GameReviewEditComponent', () => {
	let component: GameReviewEditComponent;
	let fixture: ComponentFixture<GameReviewEditComponent>;

	beforeEach(async(() => {
		TestBed.configureTestingModule({
			declarations: [GameReviewEditComponent]
		})
			.compileComponents();
	}));

	beforeEach(() => {
		fixture = TestBed.createComponent(GameReviewEditComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});
});
