import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {DudeProfileComponent} from './dude-profile/dude-profile.component';
import {SharedModule} from '../shared/shared.module';
import {DudesService} from './dudes.service';

const routes: Routes = [
	{path: ':uuid', component: DudeProfileComponent},
];

@NgModule({
	declarations: [
		DudeProfileComponent,
	],
	imports: [
		SharedModule,
		RouterModule.forChild(routes),
	],
	providers: [
		DudesService,
	],
})
export class DudesModule {}
