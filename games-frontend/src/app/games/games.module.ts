import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {GamesListComponent} from './games-list/games-list.component';
import {GameDetailsComponent} from './game-details/game-details.component';
import {GamesService} from './games.service';
import {SharedModule} from '../shared/shared.module';
import {GameTagComponent} from './common/game-tag/game-tag.component';
import {NewGameComponent} from './new-game/new-game.component';
import {GameReviewComponent} from './game-details/game-review/game-review.component';
import {GameReviewsComponent} from './game-details/game-reviews/game-reviews.component';
import {GameTagsComponent} from './game-details/game-tags/game-tags.component';
import {GameReviewsService} from './game-reviews.service';
import {GameTaggingsService} from './game-taggings.service';
import {TagsService} from './tags.service';
import {GameReviewEditComponent} from './game-details/game-review-edit/game-review-edit.component';
import {StarsRatingComponent} from './common/stars-rating/stars-rating.component';

const routes: Routes = [
	{path: '', component: GamesListComponent},
	{path: 'new', component: NewGameComponent},
	{path: ':id', component: GameDetailsComponent},
];

@NgModule({
	declarations: [
		GamesListComponent,
		GameDetailsComponent,
		GameTagComponent,
		NewGameComponent,
		GameReviewComponent,
		GameReviewsComponent,
		GameTagsComponent,
		GameReviewEditComponent,
		StarsRatingComponent,
	],
	entryComponents: [
		GameReviewEditComponent,
	],
	imports: [
		SharedModule,
		RouterModule.forChild(routes),
	],
	providers: [
		GamesService,
		GameReviewsService,
		GameTaggingsService,
		TagsService,
	],
})
export class GamesModule {}
