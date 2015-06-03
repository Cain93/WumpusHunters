package Mechanics;


public class Hunter extends Occupant {

	protected Move nextMove;
	protected Direction nextDirection;
	private Room room;
	private Room prevRoom;
	public String name;
	
	public Hunter(){
		type = "Hunter";
		name = "Unnamed";
	};
	
	/*public Hunter(Room r) {
		type = "Hunter";
		room = r;
		room.occupants.add(this);
	}
	*/
	public void getResponse(boolean[] status){
		nextMove = Move.MOVE;
		nextDirection = Direction.HERE;
	}
	
	protected void setRoom(Room r){
		prevRoom = room;
		room = r;
		if(prevRoom != null){ prevRoom.occupants.remove(this); }
		
		room.occupants.add(this);
	}
	
	protected Room getRoom(){
		return room;
	}
	
	protected Room getPrev(){
		return prevRoom;
	}
		
	

}
