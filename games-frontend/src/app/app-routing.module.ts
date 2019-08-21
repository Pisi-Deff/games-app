import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {AuthGuard} from './auth/auth.guard';
import {MainUiComponent} from './main-ui/main-ui.component';

const routes: Routes = [
	{
		path: '',
		component: MainUiComponent,
		canLoad: [AuthGuard],
		canActivate: [AuthGuard],
		children: [
			{
				path: 'games',
				loadChildren: () => import('./games/games.module').then(mod => mod.GamesModule),
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
