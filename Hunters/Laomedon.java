package Hunters;
import Mechanics.Direction;
import Mechanics.Hunter;
import Mechanics.Move;
public class Laomedon extends Hunter {
    private enum status
    {
        START,
        SEARCHED_LEFT,
        SEARCHED_RIGHT,
        INITIAL_CORPSE_LEFT,
        INITIAL_CORPSE_RIGHT,
        SMELT_CORPSE,
        CORPSE_BEHIND,
        CORPSE_LEFT
    }

    status myState;
    public Laomedon() {
        this.name = "Laomedon";
    }
    @Override
    public void getResponse(boolean[] status) {
        boolean wumpusNear = status[0];
        boolean hunterNear = status[1];
        boolean corpseNear = status[2];
        boolean arrowNear = status[3];
        boolean hunterHere = status[4];
        switch (myState) {
        case CORPSE_BEHIND:
            if(wumpusNear)
            {
                this.nextDirection = Direction.BACK;
                this.nextMove = Move.SHOOT;
            }
            else
            {
                this.nextDirection = Direction.HERE;
                this.nextMove = Move.MOVE;
            }
            break;
        case CORPSE_LEFT:
            if(wumpusNear)
            {
                this.nextDirection = Direction.LEFT;
                this.nextMove = Move.SHOOT;
            }
            else
            {
                this.nextDirection = Direction.HERE;
                this.nextMove = Move.MOVE;
            }
            break;
        case INITIAL_CORPSE_LEFT:
            if(corpseNear)
            {
                this.nextDirection = Direction.RIGHT;
                this.nextMove = Move.MOVE;
                this.myState = Laomedon.status.INITIAL_CORPSE_RIGHT;
            }
            else
            {
                this.nextDirection = Direction.BACK;
                this.nextMove = Move.MOVE;
                this.myState = Laomedon.status.SEARCHED_LEFT;
            }
            break;
        case INITIAL_CORPSE_RIGHT:
            if(corpseNear)
            {
                this.nextDirection = Direction.LEFT;
                this.nextMove = Move.MOVE;
                myState = Laomedon.status.INITIAL_CORPSE_LEFT;
            }
            else
            {
                this.nextDirection = Direction.BACK;
                this.nextMove = Move.MOVE;
                this.myState = Laomedon.status.SEARCHED_LEFT;
            }
            break;
        case SEARCHED_LEFT:
            if(corpseNear)
            {
                this.nextDirection = Direction.LEFT;
                this.nextMove = Move.MOVE;
                this.myState = Laomedon.status.SMELT_CORPSE;
            }
            else
            {
                this.nextDirection = Direction.RIGHT;
                this.nextMove = Move.MOVE;
                this.myState = Laomedon.status.SEARCHED_RIGHT;
            }
            break;
        case SEARCHED_RIGHT:
            if(corpseNear)
            {
                this.nextDirection = Direction.LEFT;
                this.nextMove = Move.MOVE;
                this.myState = Laomedon.status.SMELT_CORPSE;
            }
            else
            {
                this.nextDirection = Direction.LEFT;
                this.nextMove = Move.MOVE;
                this.myState = Laomedon.status.SEARCHED_LEFT;
            }
            break;
        case SMELT_CORPSE:
            if(corpseNear)
            {
                this.nextDirection = Direction.BACK;
                this.nextMove = Move.MOVE;
                this.myState = Laomedon.status.CORPSE_BEHIND;
            }
            else
            {
                this.nextDirection = Direction.BACK;
                this.nextMove = Move.MOVE;
                this.myState = Laomedon.status.CORPSE_LEFT;
            }
            break;
        case START:
            if(corpseNear)
            {
                this.nextDirection = Direction.LEFT;
                this.nextMove = Move.MOVE;
                this.myState = Laomedon.status.INITIAL_CORPSE_LEFT;
            }
            else
            {
                this.nextDirection = Direction.LEFT;
                this.nextMove = Move.MOVE;
                this.myState = Laomedon.status.SEARCHED_LEFT;
            }
            break;
        }
    }

    @Override
    public void newGame() {

        super.newGame();
        myState = status.START;
    }
}