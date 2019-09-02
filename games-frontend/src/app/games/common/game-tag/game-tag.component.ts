import {Component, EventEmitter, Input, Output} from '@angular/core';

import {GameTag} from '../game-tag';

@Component({
	selector: 'app-game-tag',
	templateUrl: './game-tag.component.html',
	styleUrls: ['./game-tag.component.scss']
})
export class GameTagComponent {
	private static suffixes = ['K', 'M', 'B'];

	@Input()
	tag: GameTag;

	@Input()
	selected = false;

	@Input()
	disabled = false;

	@Output()
	click = new EventEmitter<MouseEvent>();

	constructor() {}

	formatCounter() {
		let counter = this.tag.counter;
		let suffix = '';

		let attempt = 0;
		while (counter > 1000 && attempt < GameTagComponent.suffixes.length) {
			counter = Math.floor(counter / 1000);
			suffix = GameTagComponent.suffixes[attempt++];
		}

		return counter + suffix;
	}

	handleClick(event: MouseEvent) {
		this.click.emit(event);
	}
}
