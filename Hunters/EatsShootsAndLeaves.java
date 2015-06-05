package Hunters;

import java.util.Random;

import Mechanics.Hunter;
import Mechanics.Move;
import Mechanics.Direction;
import Mechanics.Room;

public class EatsShootsAndLeaves extends Hunter {

    private static Direction [] allDirections = { Direction.LEFT, Direction.RIGHT, Direction.BACK, Direction.HERE };
    private static Direction [] movePath = { Direction.LEFT, Direction.RIGHT, Direction.LEFT, Direction.BACK, Direction.RIGHT, Direction.BACK };

    private static int numGames = 0;
    private static int totalLife = 0;

    private static class RoomInfo  {

        public boolean hasWumpus = false;
        public boolean hasLocalHunter = false;
        public boolean hasNeighborHunter = false;
        public boolean hasCorpse = false;
        public boolean hasArrow = false;
        public RoomInfo(Room r) {
            boolean [] status = r.getInfo();
            hasWumpus = status[0];
            hasNeighborHunter = status[1];
            hasCorpse = status[2];
            hasArrow = status[3];
            hasLocalHunter = status[4];
        }

        public String toString() {
            return new String("Status: "
                              + (hasWumpus ? "Wumpus " : "")
                              + (hasNeighborHunter ? "Close Hunter " : "")
                              + (hasCorpse ? "Corpse " : "")
                              + (hasArrow ? "Arrow " : "")
                              + (hasLocalHunter ? "Local Hunter " : ""));
        }
    }

    int turnsAlive = 0;
    int shots = 0, moves = 0;

    public EatsShootsAndLeaves(){
        name = "Eats Shoots and Leaves";
    }

    public void newGame() {

        totalLife += turnsAlive;
        numGames++;

        turnsAlive = shots = moves = 0;
    }

    public void getResponse(boolean[] status){

        turnsAlive++;

        RoomInfo info = new RoomInfo(this.getRoom());
        if (info.hasNeighborHunter || info.hasWumpus) {
            nextMove = Move.SHOOT;
            nextDirection = allDirections[shots++ % 3];
        } else {
            nextMove = Move.MOVE;
            nextDirection = movePath[moves++ % movePath.length];
        }
    }
}