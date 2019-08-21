import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {GameListItem} from './games-list/game-list-item';
import {GameDetailed} from './game-details/game-detailed';

@Injectable({
	providedIn: 'root'
})
export class GamesService {
	constructor(private http: HttpClient) {}

	list(page: number, size: number): Observable<GameListItem[]> {
		// TODO: sorting params n stuff
		return null;
	}

	getDetailed(id: number): GameDetailed {
		return null;
	}
}
