import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class bosshp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealthBar extends DrawImage {
    
    private GreenfootImage image = new GreenfootImage(129, 22);
    public void act() {
        drawGameOverWindow();
    }
    
    public void drawGameOverWindow() {
        image.clear();
        //set image background
        if (Chip.hp < 0) {
            Chip.hp = 0;
        }
        if (Chip.hp > 0 ) {
            GreenfootImage background = new GreenfootImage(129, 22);
            GreenfootImage hp = new GreenfootImage("hp.png");
            background.setColor(new Color(175, 175, 175));
            background.fillRect(15, 2, 100, 8);
            background.setColor(new Color(255, 0, 0));
            background.fillRect(15, 2, Chip.hp, 8);
            image.drawImage(background, 0, 0);
            image.drawImage(hp, 0, 0);
            setImage(image);
        }
    }

}