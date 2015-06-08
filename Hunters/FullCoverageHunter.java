package Hunters;

import Mechanics.*;

public class FullCoverageHunter extends Hunter {

    private int state;
    private boolean headLeft;

    public FullCoverageHunter(){

        name = "FullCoverageHunter";
        state = 0;
        headLeft = false;

    }

    public void newGame() {
        state = 0;
        headLeft = false;
    }


    public void getResponse(boolean[] status){

        // disregard state, shove out (in an alternating fashion!) and light 'em up!
        switch( state ){

        case 0: // move out, change alternation state
            nextMove = Move.MOVE;
            if(headLeft) nextDirection = Direction.LEFT;
            else         nextDirection = Direction.RIGHT;
            state++;
            headLeft = !headLeft;
            break;
        case 1: // shoot into non-exit path
            nextMove = Move.SHOOT;
            if(headLeft) nextDirection = Direction.RIGHT;
            else         nextDirection = Direction.LEFT;
            state++;
            break;
        case 2: // shoot behind
            nextMove = Move.SHOOT;
            nextDirection = Direction.BACK;
            state++;
            break;
        default: // shoot into next room,
            nextMove = Move.SHOOT;
            if(headLeft) nextDirection = Direction.LEFT;
            else         nextDirection = Direction.RIGHT;
            state = 0;
            break;

        }

    }

}