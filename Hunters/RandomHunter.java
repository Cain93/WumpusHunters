package Hunters;
import java.util.Random;

import Mechanics.Direction;
import Mechanics.Hunter;
import Mechanics.Move;



public class RandomHunter extends Hunter {

	
	

	public void getResponse(boolean[] status){
		Random rand = new Random();
		if(rand.nextInt() % 2 == 0){
			nextMove = Move.MOVE;
		}
		else{nextMove = Move.SHOOT;}
		
		int r = rand.nextInt();
		if(r % 3 == 0){
			nextDirection = Direction.RIGHT;
		}
		else if(r % 3 == 1){
			nextDirection = Direction.LEFT;
		}
		else{ nextDirection = Direction.BACK;}
	}

}
