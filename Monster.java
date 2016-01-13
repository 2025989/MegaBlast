import greenfoot.*;

public class Monster extends MoveLeft
{
    int monsterHp;
    
    public Monster() {
        monsterHp = 3;
    }
   
    public void act() 
    {
        if(monsterHp > 0) 
        {
            moveLeft();
            if (Chip.hp != 0) 
            {
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
                if(getOneObjectAtOffset(0, 0, Chip.class) != null)
                {
                    Chip.hp --;
                }
            }
        }    
            Actor shot = getOneObjectAtOffset(0, 0, Shot.class);
            if(shot != null) {
                monsterHp--;
                if (!Greenfoot.isKeyDown("/")) {
                    getWorld().removeObject(shot);
                }
                else {monsterHp -= 10;}
                if (monsterHp <= 0) {
                    if(Boss.invulnerable == true) {
                        Shot.killcount++;
                    }
                    getWorld().removeObject(this);
                }
            }
    }
}
