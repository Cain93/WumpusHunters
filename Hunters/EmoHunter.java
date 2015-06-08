package Hunters;

import Mechanics.Direction;
import Mechanics.Hunter;
import Mechanics.Move;

public class EmoHunter extends Hunter {

	public EmoHunter() {
		name = "Emo";
	}
	
	public void getResponse(boolean[] status){
		nextMove = Move.SHOOT;
		nextDirection = Direction.HERE;
	}

}
