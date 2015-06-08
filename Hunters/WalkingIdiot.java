package Hunters;

import Mechanics.*;

public class WalkingIdiot extends Hunter {
    private boolean wumpusNear = false;

    @Override
    public void newGame() {
        wumpusNear = false;
    }

    public WalkingIdiot(){
        name = "WalkingIdiot";
    }

    @Override
    public void getResponse(boolean[] status){
        boolean wumpusWasNear = wumpusNear;
        wumpusNear = status[0];
        if (status[0]) {
            nextMove = Move.SHOOT;
            if (wumpusWasNear) {
                nextDirection = Direction.LEFT;
            } else {
                nextDirection = Direction.RIGHT;
            }
            return;
        }
        nextMove = Move.MOVE;
        nextDirection = Math.random() < 0.5 ? Direction.LEFT : Direction.RIGHT;
    }
}