import {Dude} from '../../shared/dude';

export interface GameReview {
	id: number;
	dude: Dude;
	score: number;
	review: string;
	reviewDate: string;
}
