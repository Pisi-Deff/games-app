import {GameBasic} from '../common/game-basic';
import {GameTag} from '../common/game-tag';
import {Slice} from '../../shared/slice';
import {GameTagging} from '../common/game-tagging';

export interface GameDetailed extends GameBasic {
	description: string;
	tags: Slice<GameTag>;
	dudeTaggings: GameTagging[];
}
