import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

import {GameTagging} from './common/game-tagging';
import {environment} from '../../environments/environment';
import {GameTag} from './common/game-tag';

@Injectable({
	providedIn: 'root'
})
export class GameTaggingsService {
	constructor(private http: HttpClient) {}

	add(gameId: number, name: string): Observable<AddGameTaggingResponse> {
		return this.http.post<AddGameTaggingResponse>(`${environment.apiURI}/games/${gameId}/taggings`, {name});
	}

	delete(gameId: number, taggingId: number): Observable<{}> {
		return this.http.delete(`${environment.apiURI}/games/${gameId}/taggings/${taggingId}`);
	}
}

export interface AddGameTaggingResponse {
	tagging: GameTagging;
	tag: GameTag;
}
