package Mechanics;
import java.util.LinkedList;

//Defines a room, holds identifier number, neighbors and occupants
public class Room {
	
	int number;
	Room[] neighbors = new Room[3];
	LinkedList<Occupant> occupants = new LinkedList<Occupant>();
	boolean isShotInto = false; 

	public Room(int num) {
		number = num;
	}
	
	public void addNeighbor(Room r){
		for(int i = 0; i < neighbors.length; i++){
			if(neighbors[i] == null){
				neighbors[i] = r;
				//r.addNeighbor(this);
				return;
			}
		}
		
		System.out.println("Failed to add neighbor to " + number + ", too many neighbors");
	}
	
	public boolean[] getInfo(){
		// 0 = wumpus
		// 1 = person
		// 2 = corpse
		// 3 = arrow
		// 4 = person in same room
		
		boolean[] status = {false, false, false, false, false};
		for(Occupant o : occupants){
			if(o.type.equals("Wumpus")){ status[0] = true; }
			if(o.type.equals("Hunter")){ status[4] = true; }
			if(o.type.equals("Corpse")){ status[2] = true; }
			if(isShotInto){ status[3] = true; }
		}
		
		for(Room r : neighbors){
			for(Occupant o : r.occupants){
				if(o.type.equals("Wumpus")){ status[0] = true; }
				if(o.type.equals("Hunter")){ status[1] = true; }
				if(o.type.equals("Corpse")){ status[2] = true; }
				if(isShotInto){ status[3] = true; }
			}
		}
		
		return status;
		
	}

}

