package Hunters;

import Mechanics.*;

public class ScaredyCat extends Hunter {

    public ScaredyCat(){
        name = "ScaredyCat";
    }

    @Override
    public void getResponse(boolean[] status){

        for(int i=0; i<status.length; i++)
            if(status[i])
            {
                nextMove = Move.MOVE;
                nextDirection = Direction.values()[((int) (Math.random() * 3))];
                return;
            }

        nextMove = Move.SHOOT;
        nextDirection = Direction.values()[((int) (Math.random() * 3))];
    }
}