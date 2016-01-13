import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Shot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shot extends MoveLeft {
    
    private int speed = 10;
    public static int killcount = 0;
    public static boolean kc = false;
    
    public Shot(boolean isLeft) {
        if (isLeft) {
            getImage().mirrorHorizontally();
            speed = -10;
        }
    }
    
    public void act() {
        moveLeft();
        move(speed);
        checkHit();
    }
    
    public void checkHit() {
        Actor boss = getOneObjectAtOffset(0,0, Boss.class);
        if(boss!=null && Boss.invulnerable == false && Boss.bosshp > 0) {
            Boss.bosshp -= 10;
            getWorld().removeObject(this);  
        }
        else if (getX() > 2000) {getWorld().removeObject(this);}
        if (killcount >= 4) {
            kc = true;
            killcount = 0;
            
        }
    }
    
}