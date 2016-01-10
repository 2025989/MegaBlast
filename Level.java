import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

/**
 * Write a description of class NewGame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level extends DrawImage {
    
    private GreenfootImage image = new GreenfootImage(1, 1);
    public static int waveCount;
    
    public Level() {
        waveCount = 1;
        drawText();
        waveCount = 0;
        setImage(image);
    }
    
    /**
     * Act - do whatever the NewGame wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        drawText();
    }
    
    public void drawText() {
        //set image dimensions
        image.setFont(new Font("Arial", Font.BOLD, 48));
        String text = "Level "+ waveCount;//(int)(MoveLeft.worldX/10000+1);
        Dimension dim = getTextDimensions(image, text);
        
        //draw string into image
        image.clear();
        image.scale(dim.width+50, dim.height+50);
        image.setColor(new Color(0, 255, 255));
        image.drawString(text, 1, dim.height+1);
        image.setColor(new Color(0, 75, 75));
        image.drawString(text, 0, dim.height);
        setImage(image);
    }
    
}
