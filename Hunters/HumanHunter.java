package Hunters;
import java.util.Scanner;

import Mechanics.Direction;
import Mechanics.Hunter;
import Mechanics.Move;



public class HumanHunter extends Hunter {

	Scanner scan = new Scanner(System.in);
	
	
	
	public void getResponse(boolean[] status){
		
		if(status[0]){ System.out.println("You smell a wumpus");}
		if(status[1]){ System.out.println("You hear another hunter");}
		if(status[2]){ System.out.println("You smell a corpse");}
		if(status[3]){ System.out.println("You hear an arrow clatter");}
		
		System.out.println("Next move?");
		String move = scan.nextLine();
		if(move.equals("Shoot")){
			nextMove = Move.SHOOT;
		}
		else{ nextMove = Move.MOVE; }
		
		System.out.println("Direction?");
		String dir = scan.nextLine();
		switch(dir){
		case "Left" :
			nextDirection = Direction.LEFT;
			break;
		case "Right" :
			nextDirection = Direction.RIGHT;
			break;
		case "Back" :
			nextDirection = Direction.BACK;
			break;
		default:
			nextDirection = Direction.HERE;
			break;	
		}
		
		
	}

}
