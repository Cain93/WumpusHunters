package Hunters;

import Mechanics.*;

public class Pacifist extends Hunter {


    public Pacifist(){
        name = "Pacifist";
    }

    public void getResponse(boolean[] status){
        nextMove = Move.MOVE;
        if(status[0]||status[1]||status[2]||status[3]||status[4]){
            nextDirection = Direction.values()[((int) (Math.random() * 3))];
            return;
        }
        nextDirection = Direction.HERE;
    }
}