package Hunters;

import Mechanics.*;

public class NascarHunter extends Hunter {

    private int state;

    public NascarHunter(){

        name = "NascarHunter";
        state = 0;

    }

    public void getResponse(boolean[] status){

        // disregard state, shove out and light 'em up!
        switch( state ){

        case 0: // move out
            nextMove = Move.MOVE;
            nextDirection = Direction.LEFT;
            state++;
            break;
        case 1: // shoot right
            nextMove = Move.SHOOT;
            nextDirection = Direction.RIGHT;
            state++;
            break;
        case 2: // shoot behind
            nextMove = Move.SHOOT;
            nextDirection = Direction.BACK;
            state++;
            break;
        default: // shoot left
            nextMove = Move.SHOOT;
            nextDirection = Direction.LEFT;
            state = 0;
            break;

        }

    }

}