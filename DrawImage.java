import greenfoot.GreenfootImage;  
import greenfoot.Actor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Dimension;

/**
 * Deze klasse is een hulpklasse om al te ingewikkelde code te voorkomen.
 * Er is een methode die de hoogte en de lengte van een string berekent. 
 * Vooraf moet het juiste font voor de image gezet zijn zodat de juiste maten van de string
 * berekend kunnen worden.
 * 
 * @author Ton van Beuningen 
 * @version 19-10-2012
 */
public abstract class DrawImage extends Actor {

    /**
     * De methode getTextDimensions berekent de breedte en de hoogte van dee string in 
     * pixels.
     * 
     * @param image De Greenfootimage waarin de string wordt geplaatst.
     * @param tekst de String waarvan de breedte en de hoogte wordt berekend.
     * @return Dimension met daarin de breedte en de hoogte van de string
     */
    public Dimension getTextDimensions(GreenfootImage image, String text) {
        FontMetrics metrics = image.getAwtImage().getGraphics().getFontMetrics(image.getFont()); 
        int width = metrics.stringWidth(text);
        int height = metrics.getLeading() + metrics.getAscent() + metrics.getDescent();
        return new Dimension(width, height);
    }
    
}