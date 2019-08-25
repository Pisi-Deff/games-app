import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {GamesService} from '../games.service';
import {MatPaginator, MatSort} from '@angular/material';
import {GameListItem} from './game-list-item';
import {merge, Observable, of} from 'rxjs';
import {catchError, map, startWith, switchMap} from 'rxjs/operators';

@Component({
	templateUrl: './games-list.component.html',
	styleUrls: ['./games-list.component.scss']
})
export class GamesListComponent implements AfterViewInit {
	displayedColumns: string[] = ['name', 'releaseDate', 'topTags'];
	data: GameListItem[] = [];

	resultsLength = 0;
	loading = true;
	error = false;

	@ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;
	@ViewChild(MatSort, {static: false}) sort: MatSort;

	constructor(private gamesSvc: GamesService) {}

	ngAfterViewInit() {
		this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);

		merge(this.sort.sortChange, this.paginator.page)
			.pipe(
				startWith({}),
				switchMap(() => this.loadData()),
			).subscribe(data => this.data = data);
	}

	loadData(): Observable<GameListItem[]> {
		this.loading = true;

		return this.gamesSvc.list(
			this.paginator.pageIndex, this.paginator.pageSize,
			this.sort.active, this.sort.direction,
		).pipe(
			map(data => {
				this.loading = false;
				this.resultsLength = data.totalElements;

				return data.content;
			}),
			catchError(() => {
				this.loading = false;
				this.error = true;
				return of([]);
			}),
		);
	}
}
