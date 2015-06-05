package Hunters;

import Mechanics.*;

public class Badger extends Hunter {

    public Badger(){
        name = "Badger";
    }

    @Override
    public void getResponse(boolean[] status){
        nextMove = Move.SHOOT;
        nextDirection = Direction.values()[((int) (Math.random() * 3))];
    }
}