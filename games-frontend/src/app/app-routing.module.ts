import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {AuthGuard} from './auth/auth.guard';
import {MainUiComponent} from './main-ui/main-ui.component';
import {PageNotFoundComponent} from './errors/page-not-found/page-not-found.component';

const routes: Routes = [
	{
		path: '',
		component: MainUiComponent,
		canActivate: [AuthGuard],
		children: [
			{
				path: '',
				redirectTo: '/games',
				pathMatch: 'full',
			},
			{
				path: 'games',
				loadChildren: () => import('./games/games.module').then(mod => mod.GamesModule),
			},
			{
				path: 'dudes',
				loadChildren: () => import('./dudes/dudes.module').then(mod => mod.DudesModule),
			},
			{
				path: '**',
				component: PageNotFoundComponent,
			},
		],
	},
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule {
}
