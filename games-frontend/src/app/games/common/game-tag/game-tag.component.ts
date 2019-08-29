import {Component, Input} from '@angular/core';
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
}
