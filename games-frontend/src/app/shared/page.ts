import {Slice} from './slice';

export class Page<T> extends Slice<T> {
	totalPages: number;
	totalElements: number;
}
