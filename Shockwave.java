import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Shockwave here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shockwave extends Actor
{
       GreenfootImage explosion1 = new GreenfootImage("boss/shockwave1.png");
       GreenfootImage explosion2 = new GreenfootImage("boss/shockwave2.png");
       GreenfootImage explosion3 = new GreenfootImage("boss/shockwave3.png");
       GreenfootImage explosion4 = new GreenfootImage("boss/shockwave4.png");
       GreenfootImage explosion5 = new GreenfootImage("boss/shockwave5.png");
       GreenfootImage explosion6 = new GreenfootImage("boss/shockwave6.png");
       GreenfootImage explosion7 = new GreenfootImage("boss/shockwave7.png");
       GreenfootImage explosion8 = new GreenfootImage("boss/shockwave8.png");
       public int frame = 1;
       public int animspeed = 0;
    public void act() 
    {
                 animspeed ++;
                 if(animspeed == 6) {
                animate();
                animspeed = 0;
            }
                move(5);
                checkHit();
    }    
    
     public void animate() {
    if (frame == 1 ) {
           setImage(explosion1);
           frame = 2;
    }
    else if (frame == 2 ) {
           setImage(explosion2);
           frame = 3;
    }
    else if (frame == 3 ) {
           setImage(explosion3);
           frame = 4;
    }
    else if (frame == 4 ) {
           setImage(explosion4);
           frame = 5;
    }
    else if (frame == 5 ) {
           setImage(explosion5);
           frame = 6;
    }
    else if (frame == 6 ) {
           setImage(explosion6);
           frame = 7;
    }
    else if (frame == 7 ) {
           setImage(explosion7);
           frame = 8;
    }
        else if (frame == 8 ) {
           setImage(explosion8);
    }
}
    public void checkHit() {
       Actor chip = getOneObjectAtOffset(0,0, Chip.class); 
       
       if(chip != null) {
           Chip.hp -= 1;
    }
    }
}
