import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

/**
 * Write a description of class NewGame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NewGame extends DrawImage {
    
    private GreenfootImage image = new GreenfootImage(1, 1);
    private int alpha = 255;
    
    public NewGame() {
        setImage(image);
    }
    
    /**
     * Act - do whatever the NewGame wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        alpha -= 2;
        if (alpha >= 0) {
            drawText(alpha);
        }
    }
    
    public void drawText(int alpha) {
        //set image dimensions
        image.setFont(new Font("Arial", Font.BOLD, 48));
        String text = "NEW GAME";
        Dimension dim = getTextDimensions(image, text);
        
        //draw string into image
        image.clear();
        image.scale(dim.width+50, dim.height+50);
        image.setColor(new Color(0, 150, 255, alpha));
        image.drawString(text, 1, dim.height+1);
        image.setColor(new Color(0, 0, 0, alpha));
        image.drawString(text, 0, dim.height);
        setImage(image);
    }
    
}