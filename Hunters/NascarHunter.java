package Hunters;

import Mechanics.*;

public class NascarHunter extends Hunter {

    private int state;
    private boolean shootHall;
    private boolean newGame;

    public NascarHunter(){

        name = "NascarHunter";
        state = 0;
        shootHall = true;
        newGame = true;

    }

    public void newGame(){

        state = 0;
        newGame = true;

    }

    public void getResponse(boolean[] status){

        // Wumpus about - stand and deliver
        if( status[0] ){

            nextMove = Move.SHOOT;

            switch( state ){

            case 0: // Must be in either Right or Back
                if(newGame){

                    // try Left if this is the first turn, just in case
                    nextDirection = Direction.LEFT;
                    newGame = false;

                }
                else if(shootHall) nextDirection = Direction.BACK;
                else               nextDirection = Direction.RIGHT;
                shootHall = !shootHall;
                break;
            case 2: // Must be in either Left or Back
                if(newGame){

                    // try Right if this is the first turn, just in case
                    nextDirection = Direction.RIGHT;
                    newGame = false;

                }
                else if(shootHall) nextDirection = Direction.BACK;
                else               nextDirection = Direction.LEFT;
                shootHall = !shootHall;
                break;
            default: // Must be in either Left or Right
                if(newGame){

                    // try Back if this is the first turn, just in case
                    nextDirection = Direction.BACK;
                    newGame = false;

                }
                else if(shootHall) nextDirection = Direction.RIGHT;
                else               nextDirection = Direction.LEFT;
                shootHall = !shootHall;
                break;

            }

        }else{

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
            case 3: // shoot left
                nextMove = Move.SHOOT;
                nextDirection = Direction.LEFT;
                state = 0;
                break;

            }

        }

    }

}