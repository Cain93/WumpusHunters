package Hunters;

import Mechanics.Direction;
import Mechanics.Hunter;
import Mechanics.Move;


public class ParanoidHunter extends Hunter {

	boolean runningAway = true;
	
	public ParanoidHunter() {
		name = "Paranoid";
	}

	
	
	public void getResponse(boolean[] status){
		//If he senses anything wrong, he runs away then fires behind him to avoid chasers
		if(status[0] || status[1] || status[2] || status[3]){
			nextMove = Move.MOVE;
			nextDirection = Direction.BACK;
			runningAway = true;
			return;
		}
		
		if(runningAway){
			nextMove = Move.SHOOT;
			nextDirection = Direction.BACK;
			runningAway = false;
		}
		
		nextMove = Move.MOVE;
		nextDirection = Direction.LEFT;
		
	}
	
	

}
