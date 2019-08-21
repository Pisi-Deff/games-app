import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {
	MatAutocompleteModule,
	MatButtonModule, MatCardModule,
	MatDatepickerModule,
	MatFormFieldModule,
	MatIconModule,
	MatInputModule,
	MatProgressSpinnerModule,
	MatTableModule,
	MatToolbarModule
} from '@angular/material';

@NgModule({
	declarations: [],
	imports: [
		CommonModule,
	],
	exports: [
		CommonModule,
		FormsModule,

		MatFormFieldModule,
		MatAutocompleteModule,
		MatButtonModule,
		MatDatepickerModule,
		MatTableModule,
		MatInputModule,
		MatIconModule,
		MatToolbarModule,
		MatCardModule,
		MatProgressSpinnerModule,
	],
})
export class SharedModule {
}
