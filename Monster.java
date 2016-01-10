import greenfoot.*;

public class Monster extends MoveLeft
{
    public void act() 
    {
        moveLeft();
        if (Chip.hp != 0) {
        if(getX() >= getWorld().getObjects(Chip.class).get(0).getX())
        {
            move(-2);
            setImage("monsterRight.png");
        }
        else
        {
            move(2);
            setImage("monsterLeft.png");
        }
    
        if(getOneObjectAtOffset(0, 0, Chip.class) != null
        && !(Greenfoot.isKeyDown("/")))
        {
            Chip.hp --;
        }
    }
    }    
}
