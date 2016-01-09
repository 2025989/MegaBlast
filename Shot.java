import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Shot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shot extends MoveLeft {
    
    private int speed = 10;
    public static int killcount;
    public static boolean kc;
    
    public Shot(boolean isLeft) {
        killcount = 0;
        kc = false;
        if (isLeft) {
            speed = -10;
        }
    }
    
    public void act() {
        moveLeft();
        move(speed);
        checkHit();
    }
    
    public void checkHit() {
        if (Boss.bossdeath == false) {
            Actor monster = getOneObjectAtOffset(0,0, Monster.class);
            Actor boss = getOneObjectAtOffset(0,0, Boss.class);
            if(boss!=null && Boss.invulnerable == false && Boss.bosshp > 0) {
                Boss.bosshp -= 10;
                getWorld().removeObject(this);  
            }
            else if(Boss.invulnerable == true && monster != null) {
                killcount++;
                getWorld().removeObject(monster);  
                getWorld().removeObject(this);
            }
            else if(Boss.invulnerable == false && monster != null) {
                getWorld().removeObject(monster);  
                getWorld().removeObject(this);
            }
            else if (getX() > 2000) {getWorld().removeObject(this);}
            if (killcount >= 4) {
                kc = true;
                killcount = 0;
            }
        }
    }
    
}