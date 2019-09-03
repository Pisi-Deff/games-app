import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

import {Page} from '../shared/page';
import {GameReview} from './common/game-review';
import {environment} from '../../environments/environment';

@Injectable({
	providedIn: 'root'
})
export class GameReviewsService {
	constructor(private http: HttpClient) {}

	getReviews(gameId: number, page: number): Observable<Page<GameReview>> {
		return this.http.get<Page<GameReview>>(`${environment.apiURI}/games/${gameId}/reviews?page=${page}`);
	}

	add(gameId: number, score: number, review: string): Observable<GameReview> {
		return this.http.post<GameReview>(`${environment.apiURI}/games/${gameId}/reviews`, {
			score,
			review,
		});
	}

	edit(
		gameId: number,
		reviewId: number,
		score: number,
		review: string,
	): Observable<GameReview> {
		return this.http.patch<GameReview>(`${environment.apiURI}/games/${gameId}/reviews/${reviewId}`, {
			score,
			review,
		});
	}

	delete(
		gameId: number,
		reviewId: number,
	): Observable<{}> {
		return this.http.delete<{}>(`${environment.apiURI}/games/${gameId}/reviews/${reviewId}`);
	}
}
