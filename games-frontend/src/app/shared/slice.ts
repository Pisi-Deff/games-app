export interface Slice<T> {
	content: T[];
	pageable: {
		offset: number,
		pageSize: number,
		pageNumber: number,
		paged: boolean,
		unpaged: boolean,
	};
	number: boolean;
	size: number;
	sort: {
		sorted: boolean,
		unsorted: boolean,
		empty: boolean,
	};
	numberOfElements: number;
	first: boolean;
	last: boolean;
	empty: boolean;
}
