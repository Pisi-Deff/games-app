import {Component} from '@angular/core';
import {GamesService} from '../games.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {GameCreation} from './game-creation';
import {first} from 'rxjs/operators';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
	templateUrl: './new-game.component.html',
	styleUrls: ['./new-game.component.scss']
})
export class NewGameComponent {
	form: FormGroup;
	creating = false;
	error: string = null;

	constructor(
		private formBuilder: FormBuilder,
		private gamesSvc: GamesService,
		private router: Router,
		private route: ActivatedRoute,
	) {
		this.form = this.formBuilder.group({
			name: ['', Validators.required],
			description: new FormControl(),
			releaseDate: ['', Validators.required],
		}, {
			updateOn: 'submit',
		});
	}

	get f() {
		return this.form.controls;
	}

	create() {
		if (this.form.invalid) {
			return;
		}

		const game: GameCreation = {
			name: this.f.name.value,
			description: this.f.description.value || null,
			releaseDate: this.f.releaseDate.value.format('YYYY-MM-DD'),
		};

		this.creating = true;
		this.gamesSvc.create(game)
			.pipe(first())
			.subscribe(result => {
				this.creating = false;
				if (result.id) {
					this.router.navigate([`../${result.id}`], {relativeTo: this.route});
				} else {
					this.error = 'unexpected-error';
				}
			}, error => {
				this.creating = false;
				this.error = 'unexpected-error';
			});
	}
}
