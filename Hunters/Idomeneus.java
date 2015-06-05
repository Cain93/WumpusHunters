package Hunters;
import Mechanics.Direction;
import Mechanics.Hunter;
import Mechanics.Move;
import java.util.Random;



public class Idomeneus extends Hunter
{
    int direction;
    Random r;
    public Idomeneus()
    {
        super();
        name = "Idomeneus";
        direction = 0;
        r = new Random();
    }

    @Override
    public void getResponse(boolean[] status){
        boolean wumpusNear = status[0];
        boolean hunterNear = status[1];
        boolean corpseNear = status[2];
        boolean arrowNear = status[3];
        boolean hunterHere = status[4];
        direction++;

        if(wumpusNear)
        {
            //ATTACK!!!
            nextMove = Move.SHOOT;
            nextDirection = Direction.values()[direction%3];
        }
        else if(hunterHere || arrowNear)
        {
            //Run away
            nextMove = Move.MOVE;
            nextDirection = Direction.values()[r.nextInt(3)];
        }
        else if(hunterNear)
        {
            //ATTACK!!!
            nextMove = Move.SHOOT;
            nextDirection = Direction.values()[direction%3];
        }
        else if(corpseNear)
        {
            //Stay and wait...
            nextMove = Move.MOVE;
            nextDirection = Direction.HERE;
        }
        else
        {
            //wander around
            nextMove = Move.MOVE;
            nextDirection = Direction.values()[r.nextInt(3)];
        }

    }

    public void newGame(){}



}