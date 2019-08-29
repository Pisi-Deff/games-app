import {GameBasic} from '../common/game-basic';
import {GameTag} from '../common/game-tag';

export interface GameListItem extends GameBasic {
	topTags: GameTag[];
}
