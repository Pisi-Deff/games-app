import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {of, Observable} from 'rxjs';

import {environment} from '../../environments/environment';
import {Slice} from '../shared/slice';
import {map} from 'rxjs/operators';

@Injectable({
	providedIn: 'root'
})
export class TagsService {
	constructor(private http: HttpClient) {}

	search(name: string): Observable<string[]> {
		if (name.length < 2) {
			return of([]);
		}

		return this.http.get<Slice<string>>(`${environment.apiURI}/tags`, {
			params: {name},
		}).pipe(
			map(result => result.content)
		);
	}
}
