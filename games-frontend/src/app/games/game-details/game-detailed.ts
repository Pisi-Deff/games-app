import {GameBasic} from '../common/game-basic';
import {GameTag} from '../common/game-tag';
import {Slice} from '../../shared/slice';
import {Page} from '../../shared/page';
import {GameTagging} from '../common/game-tagging';
import {GameReview} from '../common/game-review';

export interface GameDetailed extends GameBasic {
	description: string;
	tags: Slice<GameTag>;
	dudeTaggings: GameTagging[];
	reviews: Page<GameReview>;
	dudeReview: GameReview;
}
