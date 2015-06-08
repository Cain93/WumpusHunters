package Hunters;

import Mechanics.*;

public class ElmerFudd extends Hunter {

    private int state;
    private Direction previousDir;

    public ElmerFudd(){
        name = "ElmerFudd";
    }

    public void newGame() {
        state=0;
        previousDir = Direction.LEFT;
    }

    public void getResponse(boolean[] status){

        nextMove = Move.MOVE;
        switch (previousDir) {
            case LEFT:
                nextDirection = Direction.RIGHT;
                break;
            case RIGHT:
                nextDirection = Direction.LEFT;
                break;
        }   

        if(status[2]&&state==0) {
            state = 1;
            return;
        }

        if(state==1){
            if(status[2]){
                state=2;
            };
            nextDirection = Direction.BACK;
            return;
        }

        if(state==2){
            nextMove = Move.SHOOT;
            nextDirection = Direction.BACK;
            return;
        }

        if(state==3&&status[0]){
            nextMove = Move.SHOOT;
            nextDirection = Direction.BACK;
            return;
        }

        if(state==3) {
            state = 0;
        }

        if(status[0]){
            state=3;
            nextDirection = Direction.BACK;
        }

    }
}