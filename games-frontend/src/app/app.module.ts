import {BrowserModule} from '@angular/platform-browser';
import {LOCALE_ID, NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AuthModule} from './auth/auth.module';
import {SharedModule} from './shared/shared.module';
import {MainUiComponent} from './main-ui/main-ui.component';
import {PageNotFoundComponent} from './errors/page-not-found/page-not-found.component';
import {HttpClientModule} from '@angular/common/http';
import {registerLocaleData} from '@angular/common';
import locale from '@angular/common/locales/en-GB';

registerLocaleData(locale);

@NgModule({
	declarations: [
		AppComponent,
		MainUiComponent,
		PageNotFoundComponent
	],
	imports: [
		BrowserModule,
		HttpClientModule,
		BrowserAnimationsModule, // TOOD: can this be moved to shared?
		SharedModule,
		AuthModule,
		AppRoutingModule,
	],
	providers: [
		{provide: LOCALE_ID, useValue: 'en-GB'},
	],
	bootstrap: [AppComponent]
})
export class AppModule {
}
