package Mechanics;
import java.util.LinkedList;
import java.util.Random;


public class HuntWumpuses {
	
	static int NUM_DAYS = 100;
	static int days = 0;
	static Room[] map = new Room[20];
	static Hunter[] hunters;
	static int livingHunters;
	static Wumpus wumpus;
	static int arrowKills = 0;
	static int wumpusVictory = 0;
	static int starvations = 0;

	public static int[] play(Hunter[] hunters){
		
		HuntWumpuses.hunters = hunters;
		arrowKills = 0;
		starvations = 0;
		wumpusVictory = 0;
		
		createMap();
		//printMap();
		initiatePlayers();
		//debug();
		while(!gameOver()){
			
			informHunters();
			clearArrows();
			makeMoves();
			evaluateArrows();
			wumpus.respond();
			evaluateDeaths();
			days++;
			//debug();
		}
		
		return results();
	}
	
	private static void informHunters(){
		for(Hunter h: hunters){
			if(h.alive){h.getResponse(h.getRoom().getInfo());}
		}
	}
	
	private static void makeMoves(){
		LinkedList<Hunter> movers = new LinkedList<Hunter>();
		LinkedList<Hunter> shooters = new LinkedList<Hunter>();
		for(Hunter h : hunters){
			if(h.alive == false){
				continue;
			}
			if(h.nextMove == Move.MOVE){
				movers.add(h);
			}
			else{ shooters.add(h); }
		}
		for( Hunter h : movers){
			if(h.nextDirection == Direction.HERE){
				continue;
			}
			
			int prevRoom = 0;
			for(int i = 0; i < 3; i++){
				if(h.getPrev() == h.getRoom().neighbors[i]){
					prevRoom = i;
					break;
				}
			}
			Room destination;
			int modifier = 0;
			
			switch(h.nextDirection){
			case BACK:
				modifier = 0;
				break;
			case HERE:
				break;
			case LEFT:
				modifier = -1;
				break;
			case RIGHT:
				modifier = 1;
				break;
			default:
				break;
			}
			
			destination = h.getRoom().neighbors[(prevRoom + modifier + 3) % 3];
			h.setRoom(destination);
			
			
			
		}//end movers
		for(Hunter h : shooters){
			if(h.nextDirection == Direction.HERE){
				h.getRoom().isShotInto = true;
			}
			
			int prevRoom = 0;
			for(int i = 0; i < 3; i++){
				if(h.getPrev() == h.getRoom().neighbors[i]){
					prevRoom = i;
					break;
				}
			}
			Room destination;
			int modifier = 0;
			
			switch(h.nextDirection){
			case BACK:
				modifier = 0;
				break;
			case HERE:
				break;
			case LEFT:
				modifier = -1;
				break;
			case RIGHT:
				modifier = 1;
				break;
			default:
				break;
			}
			
			destination = h.getRoom().neighbors[(prevRoom + modifier + 3) % 3];
			destination.isShotInto = true;
		}
		
		
		
		
		
	}
	
	private static void evaluateArrows(){
		for(Room r : map){
			if(r.isShotInto && r.occupants.size() > 0){
				Random rand = new Random();
				int dead = rand.nextInt(r.occupants.size());
				Occupant killed = r.occupants.get(dead);
				killed.alive = false;
				if(killed.type.equals("Hunter")){ arrowKills++;}
				killed.type = "Corpse";
			}
		}
	}
	
	private static void clearArrows(){
		for(Room r : map){
			r.isShotInto = false;
		}
	}
	
	private static void evaluateDeaths(){
		livingHunters = 0;
		for(Hunter h : hunters){
			if(h.alive){ livingHunters++; }
		}
	}
	
	private static boolean gameOver(){
		if(livingHunters <= 0){
			wumpusVictory = 1;
			return true;
		}
		if(!wumpus.alive){
			return true;
		}
		if(days > NUM_DAYS){
			for(Hunter h : hunters){
				//System.out.println("Starvation!");
				if(h.alive){ starvations++; }
				h.alive = false;
			}
			wumpusVictory = 1;
			return true;
		}
		return false;
	}
	
	private static void createMap(){
		for(int i = 0; i < map.length; i++){
			map[i] = new Room(i);
		}
		
		//connect outer ring
		for(int i = 0; i < 5; i++){
			map[i].addNeighbor(map[(i + 1)%5]);
			map[i].addNeighbor(map[(i - 1 + 5)%5]);
			map[i].addNeighbor(map[5 + i*2]);
		}
		//connect middle ring
		for(int i = 5; i < 15; i++){
			map[i].addNeighbor(map[(i - 5 + 1 + 10)%10 + 5]);
			map[i].addNeighbor(map[(i - 5 - 1 + 10)%10 + 5]);
			if(i%2 ==1){
				map[i].addNeighbor(map[(i - 5)/2]);
			}
			else{
				map[i].addNeighbor(map[(i - 6)/2 + 15]);
			}
		}
		//connect inner ring
		for(int i = 0; i < 5; i++){
			map[i + 15].addNeighbor(map[(i + 1)%5 + 15]);
			map[i + 15].addNeighbor(map[(i - 1)%5 + 15]);
			map[i + 15].addNeighbor(map[i*2 + 6]);
		}
		
		
	}
	
	private static void initiatePlayers(){
		Random rand = new Random();
		
		for(int i = 0; i < hunters.length; i++){
			int r = rand.nextInt(20);
			while(!map[r].occupants.isEmpty()){ r = rand.nextInt(20); }
			hunters[i].setRoom(map[r]);
			hunters[i].newGame();
			
		}
		
		int r = rand.nextInt(20);
		while(!map[r].occupants.isEmpty()){ r = rand.nextInt(20); }
		wumpus = new Wumpus(map[r]);
		livingHunters = hunters.length;
	}
	
	static void printMap(){
		for(Room r : map){
			System.out.println("Room " + r.number + " = {" + r.neighbors[0].number + "," + r.neighbors[1].number + "," + r.neighbors[2].number);
		}
	}
	
	static void debug(){
		System.out.println("Day " + days);
		System.out.print("\tWumpus ");
		if(!wumpus.alive){ 
			System.out.print("(dead)");
		}
		System.out.println(" = " + wumpus.room.number);
		for(Hunter h : hunters){
			System.out.print("\t" + h.name);
			if(!h.alive){ 
				System.out.print("(dead)");
			}
			System.out.println(" = " + h.getRoom().number);
		}
	}
	
	static int[] results(){
		
		int[] scores = new int[hunters.length + 4];
		//System.out.println("Results");
		for(int i = 0; i < hunters.length; i++){
			Hunter h = hunters[i];
			scores[i] = 0;
			//System.out.print("Hunter " + h.name + " : ");
			if(h.alive){ 
				//System.out.println(" Alive, score = " + (60 / livingHunters));
				scores[i] = 60 / livingHunters;
			}
			//else{ System.out.println(" Dead"); }
		}
		
		days = 0;
		for(Hunter h: hunters){
			h.alive = true;
		}
		scores[4] = arrowKills;
		scores[5] = starvations;
		scores[6] = wumpus.kills;
		scores[7] = wumpusVictory;
		return scores;
	}
	
	
}
