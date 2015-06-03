package Hunters;

import java.util.Random;

import Mechanics.Hunter;
import Mechanics.Move;
import Mechanics.Direction;

public class TriggerHappyHunter extends Hunter {

	int shots = 0;
	
	public TriggerHappyHunter(){
		name = "Trig";
	}

	public void getResponse(boolean[] status){
	
	System.out.println("Shots = " + shots);
	switch(shots){
	case 0 :
		if(status[0] || status[1]){
			System.out.println("Sensed something");
			System.out.println("Shooting left");
			nextMove = Move.SHOOT;
			nextDirection = Direction.LEFT;
			shots++;
		}
		else{ wander(); }
		break;
	case 1 : 
		System.out.println("Shooting right");
		nextMove = Move.SHOOT;
		nextDirection = Direction.RIGHT;
		shots++;
		break;
	case 2 :
		System.out.println("Shooting back");
		nextMove = Move.SHOOT;
		nextDirection = Direction.BACK;
		shots = 0;
		break;
	default:
		nextMove = Move.MOVE;
		nextDirection = Direction.HERE;
	
	}
	}
	
	private void wander(){
		System.out.println("Wandering");
		Random rand = new Random();
		nextMove = Move.MOVE;
		int r = rand.nextInt(3);
		System.out.println("r = " + r);
		switch(r % 3){
		case 0 :
			nextDirection = Direction.LEFT;
			break;
		case 1 : 
			nextDirection = Direction.RIGHT;
			break;
		case 2 :
			nextDirection = Direction.BACK;
			break;
		}
		
	}
	
	

}
