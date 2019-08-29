import {Slice} from './slice';

export interface Page<T> extends Slice<T> {
	totalPages: number;
	totalElements: number;
}
