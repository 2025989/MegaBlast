import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MoveLeft here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MoveLeft extends Actor {
    
    public static int worldX;
    
    public MoveLeft() {worldX = 0;}
    
    /**
     * Act - do whatever the MoveLeft wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void moveLeft() {
        if (!Chip.bosswave) {
            if(Greenfoot.isKeyDown("right")
            && getWorld().getObjects(Chip.class).get(0).getX() >= 700) {
                worldX += Chip.speed/5;
                move(-Chip.speed);
            }
            if(Greenfoot.isKeyDown("left")
            && getWorld().getObjects(Chip.class).get(0).getX() <= 200
            && worldX > 0) {
                worldX -= Chip.speed/5;
                move(Chip.speed);
            }
        }
    }
    
}