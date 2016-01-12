import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fireball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fireball extends MoveLeft
{
       GreenfootImage fire1 = new GreenfootImage("boss/fireball1.png");
       GreenfootImage fire2 = new GreenfootImage("boss/fireball2.png");
       
       public int frame = 1;
       public int animspeed = 0;
    public void act() 
    {
        move(5);
         animspeed ++;
         
         if(animspeed == 6) {
        anim();
        animspeed = 0;
        checkHit();
    } 
    }   
    
    public void anim() {
                 if (frame == 1 ) {
           setImage(fire1);
           frame = 2;
       }
       else if (frame == 2 ) {
           setImage(fire2);
           frame = 1;
        }
    }
    public void checkHit() {
       Actor chip = getOneObjectAtOffset(0,0, Chip.class); 
       
       if(chip != null) {
           Chip.hp -= 5;
           getWorld().removeObject(this);
    }
    }
   
}
