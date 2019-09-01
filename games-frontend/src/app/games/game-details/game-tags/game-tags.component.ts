import {Component, Input, OnChanges, OnDestroy, SimpleChanges} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Subject} from 'rxjs';

import {GameTag} from '../../common/game-tag';
import {Slice} from '../../../shared/slice';
import {GameTagging} from '../../common/game-tagging';
import {GamesService} from '../../games.service';
import {GameTaggingsService} from '../../game-taggings.service';

@Component({
	selector: 'app-game-tags',
	templateUrl: './game-tags.component.html',
	styleUrls: ['./game-tags.component.scss'],
})
export class GameTagsComponent implements OnDestroy, OnChanges {
	@Input()
	initialTags: Slice<GameTag>;

	@Input()
	dudeTaggings: GameTagging[];

	@Input()
	gameId: number;

	tags: GameTag[];
	nextPage = 1;
	hasMore: boolean;
	loading = false;

	private ngUnsubscribe: Subject<any> = new Subject();

	constructor(private gameSvc: GamesService, private taggingSvc: GameTaggingsService, private route: ActivatedRoute) {}

	ngOnChanges(changes: SimpleChanges) {
		if (changes.initialTags) {
			this.tags = this.initialTags.content;
			this.nextPage = this.initialTags.pageable.pageNumber + 1;
			this.hasMore = !this.initialTags.last;
		}
	}

	isSelected(tag): boolean {
		return this.dudeTaggings.find(gt => gt.tagName === tag.tagName) != null;
	}

	loadMoreTags() {
		this.loading = true;
		this.gameSvc.getTags(this.gameId, this.nextPage)
			.subscribe(tagsSlice => {
				this.nextPage = tagsSlice.pageable.pageNumber + 1;
				this.hasMore = !tagsSlice.last;
				this.mergeTags(tagsSlice.content);
			}, error => {
				// TODO: show snackbar with error
			}, () => {
				this.loading = false;
			});
	}

	private mergeTags(extraTags: GameTag[]) {
		this.tags = [
			...this.tags,
			...extraTags.filter(
				extraTag => this.tags.find(t => t.tagId === extraTag.tagId) == null
			),
		];
	}

	ngOnDestroy() {
		this.ngUnsubscribe.next();
		this.ngUnsubscribe.complete();
	}
}
