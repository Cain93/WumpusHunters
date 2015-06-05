package Hunters;

import Mechanics.Direction;
import Mechanics.Hunter;
import Mechanics.Move;

public class PacingHunter extends Hunter {

    int state = 0;//Pacing
    int turn = 0;

    public PacingHunter() {
        name = "Pacer";
    }

    public void newGame() {
        turn =  0;
        state = 0;
    }

    public void getResponse(boolean[] status){
        turn += 1;
        if(state == 0 && status[0] && turn == 1){
            nextMove = Move.SHOOT;
            nextDirection = Direction.BACK;
            return;
        }
        if(state == 0 &&(status[0] || status[1])){
            nextMove = Move.SHOOT;
            nextDirection = Direction.LEFT;
            state = 1;
            return;
        }
        if(state == 1 && (status[0] || status[1])){
            nextMove = Move.SHOOT;
            nextDirection = Direction.RIGHT;
            state = 0;
            return;
        }
        if(status[1] && state == 0){
            nextMove = Move.SHOOT;
            nextDirection = Direction.BACK;
            state = 0;
            return;

    }
    nextMove = Move.MOVE;
    nextDirection = Direction.BACK;
}
}