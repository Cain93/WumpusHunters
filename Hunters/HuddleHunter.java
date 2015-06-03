package Hunters;

import Mechanics.Direction;
import Mechanics.Hunter;
import Mechanics.Move;

public class HuddleHunter extends Hunter {

	public HuddleHunter() {
		name = "Huddle";
	}
	
	public void getResponse(boolean[] status){
		nextMove = Move.MOVE;
		nextDirection = Direction.HERE;
	}

}
