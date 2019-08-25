import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GameTagComponent } from './game-tag.component';

describe('GameTagComponent', () => {
	let component: GameTagComponent;
	let fixture: ComponentFixture<GameTagComponent>;

	beforeEach(async(() => {
		TestBed.configureTestingModule({
			declarations: [ GameTagComponent ]
		})
		.compileComponents();
	}));

	beforeEach(() => {
		fixture = TestBed.createComponent(GameTagComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});
});
