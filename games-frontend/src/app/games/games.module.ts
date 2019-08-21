import {NgModule} from '@angular/core';

import {GamesListComponent} from './games-list/games-list.component';
import {GameDetailsComponent} from './game-details/game-details.component';
import {GamesService} from './games.service';
import {RouterModule, Routes} from '@angular/router';
import {SharedModule} from '../shared/shared.module';

const routes: Routes = [
	{path: '', component: GamesListComponent},
	{path: ':id', component: GameDetailsComponent},
];

@NgModule({
	declarations: [
		GamesListComponent,
		GameDetailsComponent,
	],
	imports: [
		SharedModule,
		RouterModule.forChild(routes),
	],
	providers: [
		GamesService,
	],
})
export class GamesModule {
}
