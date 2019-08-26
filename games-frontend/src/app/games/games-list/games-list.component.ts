import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {GamesService} from '../games.service';
import {MatPaginator, MatSort} from '@angular/material';
import {GameListItem} from './game-list-item';
import {merge, Observable, of} from 'rxjs';
import {catchError, map, startWith, switchMap} from 'rxjs/operators';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
	templateUrl: './games-list.component.html',
	styleUrls: ['./games-list.component.scss']
})
export class GamesListComponent implements AfterViewInit {
	displayedColumns: string[] = ['name', 'releaseDate', 'topTags', 'actions'];
	data: GameListItem[] = [];

	resultsLength = 0;
	loading = true;
	error = false;

	@ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;
	@ViewChild(MatSort, {static: false}) sort: MatSort;

	constructor(private gamesSvc: GamesService, private router: Router, private route: ActivatedRoute) {}

	ngAfterViewInit() {
		this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);

		merge(this.sort.sortChange, this.paginator.page)
			.pipe(
				startWith({}),
				switchMap(() => this.loadData()),
			).subscribe(data => this.data = data);
	}

	private loadData(): Observable<GameListItem[]> {
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

	refreshData() {
		this.loadData().subscribe(data => this.data = data);
	}

	openDetails(id: number) {
		this.router.navigate([id], {relativeTo: this.route});
	}
}
