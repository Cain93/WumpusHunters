package Hunters;

import Mechanics.*;

public class Stay extends Hunter {
    private Direction lastShot = Direction.LEFT;
    private Direction corpse = null;
    private boolean hunterNear = false;

    public Stay(){
        name = "Stay";
    }

    @Override
    public void newGame() {
        corpse = null;
        hunterNear = false;
        lastShot = Direction.LEFT;
    }

    @Override
    public void getResponse(boolean[] status){
        nextMove = Move.SHOOT;//always
        boolean hunterWasNear = hunterNear;
        hunterNear = status[1];

        if (hunterWasNear && status[2] && !status[1]) {
            corpse = lastShot;
        }

        if (status[0]) {
            if (corpse != null) {
                nextDirection = corpse;
                return;
            }
        }
        if ((status[1] && !status[4]) || status[0]) {
            switch (lastShot) {
                case LEFT: lastShot = nextDirection = Direction.RIGHT; break;
                case RIGHT: lastShot = nextDirection = Direction.BACK; break;
                case BACK: lastShot = nextDirection = Direction.LEFT; break;
            }
            return;
        }

        //default
        lastShot = nextDirection = Direction.LEFT;
    }
}