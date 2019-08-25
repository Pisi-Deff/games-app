import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
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
		ReactiveFormsModule,

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
