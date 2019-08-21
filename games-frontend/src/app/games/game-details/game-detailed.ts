import {GameBasic} from '../common/game-basic';
import {GameTag} from '../common/game-tag';
import {Slice} from '../../shared/slice';

export class GameDetailed extends GameBasic {
	description: string;
	tags: Slice<GameTag>;
}
