import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Dude} from './dude';

@Injectable({
	providedIn: 'root'
})
export class DudesService {
	constructor(private http: HttpClient) {}

	get(uuid: string): Observable<Dude> {
		return this.http.get<Dude>(`${environment.apiURI}/dudes/${uuid}`);
	}
}
