package Hunters;

import Mechanics.*;

public class MonsterHunter extends Hunter 
{
    private Direction lastDirection=Direction.HERE;
    private boolean[] lastStatus=new boolean[5];
    private int   shooted=0;
    private boolean   walkMode=true;
    private int         turnStayed=0;

    public MonsterHunter(){
        super();
        name = "MonsterHunter";
    }

    @Override
    public void getResponse(boolean[] status)
    {
        if(status[0])
        {
            if(!lastStatus[0]||shooted==0)
            {
                nextDirection=(walkMode)?Direction.RIGHT:Direction.LEFT;;
                nextMove=Move.SHOOT;
            }
            else if(lastStatus[0]&&shooted==1)
            {
                nextDirection=Direction.BACK;
                nextMove=Move.MOVE;
            }
            else
            {
                nextDirection=Direction.BACK;
                nextMove=Move.SHOOT;
            }
        }

        else if(status[2])
        {
            nextMove=Move.MOVE;
            if(Math.random()*6<turnStayed)
            {
                nextDirection=Direction.HERE;
                turnStayed++;
            }
            else
                nextDirection=(walkMode)?Direction.RIGHT:Direction.LEFT;
        }
        else
        {
            nextMove=(!status[1]&&Math.random()<0.5)?Move.MOVE:Move.SHOOT;
            nextDirection=(walkMode)?Direction.RIGHT:Direction.LEFT;
        }

        if(nextMove==Move.MOVE)
        {
            if(shooted>0)
                walkMode=walkMode^(shooted>0);
            if(lastStatus[0]&&shooted==1)
                shooted++;
            else
                shooted=0;
            lastDirection=nextDirection;
        }
        else
            shooted++;
        for(int i=0;i<status.length;i++)
            lastStatus[i]=status[i];
    }
}