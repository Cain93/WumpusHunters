package Hunters;

import Mechanics.*;
import java.util.Random;

public class NealHunter extends Hunter {

    private boolean goLeft;

    public NealHunter(){
        name = "NealHunter";
        goLeft = false;
    }

    public void newGame() {
        goLeft = false;
    }

    public void getResponse(boolean[] status){

        Random rand = new Random();

        if(status[0] || status[1]){
            nextMove = Move.SHOOT;

            switch ( rand.nextInt(3) ){
                case 0:
                    nextDirection = Direction.LEFT;
                    break;
                case 1:
                    nextDirection = Direction.BACK;
                    break;
                case 2:
                    nextDirection = Direction.RIGHT;
                    break;
            }
        } else {
            nextMove = Move.MOVE;
            if (goLeft) {
                nextDirection = Direction.LEFT;
            } else {
                nextDirection = Direction.RIGHT;
            }

            goLeft = !goLeft;
        }
    }
}