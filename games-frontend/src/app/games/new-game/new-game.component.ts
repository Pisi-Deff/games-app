import {Component} from '@angular/core';
import {GamesService} from '../games.service';

@Component({
	templateUrl: './new-game.component.html',
	styleUrls: ['./new-game.component.scss']
})
export class NewGameComponent {
	constructor(private gamesSvc: GamesService) {}
}
