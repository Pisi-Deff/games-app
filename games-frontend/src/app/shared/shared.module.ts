import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {
	MatAutocompleteModule,
	MatBadgeModule,
	MatButtonModule,
	MatCardModule,
	MatChipsModule,
	MatDatepickerModule,
	MatFormFieldModule,
	MatIconModule,
	MatInputModule,
	MatMenuModule,
	MatPaginatorModule,
	MatProgressSpinnerModule,
	MatSortModule,
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

		MatAutocompleteModule,
		MatBadgeModule,
		MatButtonModule,
		MatCardModule,
		MatChipsModule,
		MatDatepickerModule,
		MatFormFieldModule,
		MatIconModule,
		MatInputModule,
		MatMenuModule,
		MatPaginatorModule,
		MatProgressSpinnerModule,
		MatSortModule,
		MatTableModule,
		MatToolbarModule,
	],
})
export class SharedModule {
}
