import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {GameDetailed} from './game-detailed';
import {GamesService} from '../games.service';
import {switchMap, takeUntil} from 'rxjs/operators';
import {Subject} from 'rxjs';
import {GameTag} from '../common/game-tag';
import * as moment from 'moment';

@Component({
	templateUrl: './game-details.component.html',
	styleUrls: ['./game-details.component.scss']
})
export class GameDetailsComponent implements OnInit, OnDestroy {
	game: GameDetailed;
	tags: GameTag[];
	tagsNextPage = 1;
	hasMoreTags: boolean;
	loadingMoreTags = false;
	releaseDate: string;
	tmpReleaseDate2 = moment().format('LL');

	private ngUnsubscribe: Subject<any> = new Subject();

	constructor(private gameSvc: GamesService, private route: ActivatedRoute) {}

	ngOnInit() {
		this.route.paramMap
			.pipe(
				takeUntil(this.ngUnsubscribe),
				switchMap(params => this.gameSvc.get(+params.get('id')))
			)
			.subscribe(game => {
				this.game = game;
				this.tags = game.tags.content;
				this.tagsNextPage = game.tags.pageable.pageNumber + 1;
				this.hasMoreTags = !game.tags.last;
				this.releaseDate = moment(game.releaseDate).format('LL');
			});
	}

	isSelected(tag): boolean {
		return this.game.dudeTaggings.find(gt => gt.tagName === tag.tagName) != null;
	}

	loadMoreTags() {
		this.loadingMoreTags = true;
		this.gameSvc.getTags(this.game.id, this.tagsNextPage)
			.subscribe(tagsSlice => {
				this.tagsNextPage = tagsSlice.pageable.pageNumber + 1;
				this.hasMoreTags = !tagsSlice.last;
				this.mergeTags(tagsSlice.content);
			}, error => {
				// TODO: show snackbar with error
			}, () => {
				this.loadingMoreTags = false;
			});
	}

	ngOnDestroy() {
		this.ngUnsubscribe.next();
		this.ngUnsubscribe.complete();
	}

	private mergeTags(extraTags: GameTag[]) {
		this.tags = [
			...this.tags,
			...extraTags.filter(
				extraTag => this.tags.find(t => t.tagId === extraTag.tagId) == null
			),
		];
	}
}
