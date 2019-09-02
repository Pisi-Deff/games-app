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
	tagsToggling: GameTag[];

	private ngUnsubscribe: Subject<any> = new Subject();

	constructor(private gameSvc: GamesService, private taggingSvc: GameTaggingsService, private route: ActivatedRoute) {}

	ngOnChanges(changes: SimpleChanges) {
		if (changes.initialTags) {
			this.tags = this.initialTags.content;
			this.nextPage = this.initialTags.pageable.pageNumber + 1;
			this.hasMore = !this.initialTags.last;
		}
	}

	ngOnDestroy() {
		this.ngUnsubscribe.next();
		this.ngUnsubscribe.complete();
	}

	isSelectedTag(tag: GameTag): boolean {
		return this.getTagging(tag.tagName) != null;
	}

	getTagging(name: string): GameTagging {
		return this.dudeTaggings.find(dt => dt.tagName.trim().toLowerCase() === name.trim().toLowerCase());
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

	toggleTagging(tag: GameTag) {
		const existingTagging = this.getTagging(tag.tagName);

		// TODO: pre-do change on UI & revert in case of error instead of waiting for request to finish
		// TODO: disable tag while request in pending

		if (existingTagging) {
			this.taggingSvc.delete(this.gameId, existingTagging.id)
				.subscribe(() => {
					this.dudeTaggings = this.dudeTaggings.filter(dt => dt !== existingTagging);

					if (tag.counter === 1) {
						this.tags = this.tags.filter(t => t !== tag);
					} else {
						tag.counter--;
						this.tags = [...this.tags];
						this.tags.sort(this.compareTags);
					}
				}, () => {
					// TODO: snackbar error

				});
		} else {
			this.taggingSvc.add(this.gameId, tag.tagName)
				.subscribe(response => {
					this.tags = this.tags.filter(t => t !== tag);
					this.tags.push(response.tag);
					this.tags.sort(this.compareTags);

					this.dudeTaggings = [
						...this.dudeTaggings,
						response.tagging,
					];
				}, () => {
					// TODO: snackbar error

				});
		}
	}

	private compareTags(a: GameTag, b: GameTag): number {
		// counter DESC
		if (a.counter > b.counter) {
			return -1;
		}
		if (b.counter > a.counter) {
			return 1;
		}

		// tagName ignoreCase ASC
		if (a.tagName.toUpperCase() > b.tagName.toUpperCase()) {
			return 1;
		}
		if (b.tagName.toUpperCase() > a.tagName.toUpperCase()) {
			return -1;
		}

		return 0;
	}
}
