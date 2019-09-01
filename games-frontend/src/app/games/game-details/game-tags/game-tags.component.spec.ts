import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {GameTagsComponent} from './game-tags.component';

describe('GameTagsComponent', () => {
	let component: GameTagsComponent;
	let fixture: ComponentFixture<GameTagsComponent>;

	beforeEach(async(() => {
		TestBed.configureTestingModule({
			declarations: [GameTagsComponent]
		})
			.compileComponents();
	}));

	beforeEach(() => {
		fixture = TestBed.createComponent(GameTagsComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});
});
