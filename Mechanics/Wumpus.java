package Mechanics;
import java.util.Random;


public class Wumpus extends Occupant {

	protected Room room;
	private boolean enraged = false;
	private int daysLeft = 5;
	final boolean debugRage = false;
	protected int kills = 0;
	
	public Wumpus(Room r) {
		room = r;
		room.occupants.add(this);
		type = "Wumpus";
	}
	
	void respond(){
		Random rand = new Random();
		
		
		//how angry is wumpus?
		if(enraged){ 
			angryRespond(false);
			return;
		}
		
		//Wumpus is shot, maybe he dies, maybe he eats ALL the peeps
			if(!alive){
				if(rand.nextInt() % 5 == 0 || debugRage){
					//System.out.println("Wumpus is enraged!");
					enraged = true;
					alive = true;
					panic();
					return;
				}
				else{ return; }
			}
		
		
		//Shooting?
		for(Room r : room.neighbors){
			if(r.isShotInto){
				panic();
			}
		}
			
		//Corpses? Wumpus likes corpseses
		for(Room r : room.neighbors){
			for(Occupant o : r.occupants){
				
				if(o.type.equals("Corpse")){
					
					move(r);
					return;
				}
			}
		}
		
		
		
		//Maybe Wumpus is bored, Wumpus go find food
		
		if(rand.nextInt() % 4 == 0) { panic(); }
		else{move(room);}
		
		
		
	}
	
	private void angryRespond(boolean secondMove){
		
		if(!alive){ return; }
		
		//If he smells anyone next to him, eat them
		for(Room r : room.neighbors){
			for(Occupant o : r.occupants){
				if(o.type.equals("Corpse")){
					move(r);
				}
			}
		}
		
		//Otherwise, roam.
		panic();
		//Angry wumpus move twice as much
		if(!secondMove){ angryRespond(true);}
		
		
		//Wumpus is losing blood, wumpus can't last forever
		if(--daysLeft < 0){
			alive = false;
		}
	}
	
	private void panic(){
		Random rand = new Random();
		move(room.neighbors[rand.nextInt(3)]);
	}
	
	private void move(Room r){
		//Eat ALL the peoples
		for(Occupant o : room.occupants){
			if(o.type.equals("Hunter")){
				o.alive = false;
				o.type = "Corpse";
				kills++;
			}
		}
		
		//change rooms
		room = r;
		
		//Eat ALL the peoples
		for(Occupant o : room.occupants){
			if(!o.type.equals("Wumpus")){
				o.alive = false;
				o.type = "Corpse";
			}
		}
	}

}
