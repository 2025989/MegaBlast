import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class bosshp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BossHealthBar extends DrawImage {
    
    private GreenfootImage image = new GreenfootImage(529, 31);
    public void act() {
        draw();
    } 
    
    public void draw() {
        image.clear();
        //set image background
        if (Boss.bosshp < 0) {
            Boss.bosshp = 0;
        }
        if (Boss.bosshp > 0 ) {
            GreenfootImage background = new GreenfootImage(529, 31);
            GreenfootImage bosshp48 = new GreenfootImage("bosshp48_v3.png");
            background.setColor(new Color(175, 175, 175));
            background.fillRect(15, 4, 500, 15);
            background.setColor(new Color(255, 0, 0));
            background.fillRect(15, 4, Boss.bosshp/2, 15);
            image.drawImage(background, 0, 0);
            image.drawImage(bosshp48, 0, 0);
            setImage(image);
        }
    }

}