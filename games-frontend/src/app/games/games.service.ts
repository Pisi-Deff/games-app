import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpParams} from '@angular/common/http';
import {GameListItem} from './games-list/game-list-item';
import {GameDetailed} from './game-details/game-detailed';
import {SortDirection} from '@angular/material';
import {Page} from '../shared/page';
import {environment} from '../../environments/environment';

@Injectable({
	providedIn: 'root'
})
export class GamesService {
	constructor(private http: HttpClient) {}

	list(
		page: number, size: number,
		sortColumn: string, sortDirection: SortDirection,
	): Observable<Page<GameListItem>> {
		let params: HttpParams = new HttpParams();

		if (page) {
			params = params.set('page', String(page));
		}
		if (size) {
			params = params.set('size', String(size));
		}
		if (sortColumn && sortDirection) {
			// NOTE: backend supports multiple sort params
			params = params.append('sort', `${sortColumn},${sortDirection}`);
		}

		return this.http.get<Page<GameListItem>>(`${environment.apiURI}/games`, {params});
	}

	getDetailed(id: number): GameDetailed {
		return null;
	}
}
