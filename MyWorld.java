import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World {
    
    //private GreenfootSound music = new GreenfootSound(null);
    public static int waveCount = 0;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld() {
        super(1000, 500, 1, false);
        populateTheWorld();
    }
    
    public void act() {
        if ((waveCount == 0)
        || (MoveLeft.worldX > 10000 && waveCount == 1)
        || (MoveLeft.worldX > 20000 && waveCount == 2)
        || (MoveLeft.worldX > 30000 && waveCount == 3)
        || (MoveLeft.worldX > 40000 && waveCount == 4)
        || (MoveLeft.worldX > 50000 && waveCount == 5)
        || (MoveLeft.worldX > 60000 && waveCount == 6)
        || (MoveLeft.worldX > 70000 && waveCount == 7)
        || (MoveLeft.worldX > 80000 && waveCount == 8)) {
            waveCount++;
            for (int i = 0; i < 4*waveCount; i++) {
                addObject(new Monster(), getWidth()+250+Greenfoot.getRandomNumber(1000), getHeight()-120);
            }
        }
    }
    
    public void started() {
        //music.playLoop();
    }
    public void stopped() {
        //music.pause();
    }
    
    protected void populateTheWorld() {
        addObject(new NewGame(), getWidth()/2, getHeight()/2);
        addObject(new Level(), 150, 50);
        addObject(new HealthBar(), 150, getHeight()-35);
        //addObject(new BossHealthBar(), getWidth()/2, 25);
        
        for (int i = 0; i < 25; i++) {
            addObject(new Background(), 1158+2316*i, getHeight()/2);
            addObject(new Ground(), 639+1277*i, getHeight()-32);
        }
        
        addObject(new Chip(), getWidth()/2, getHeight()-112);
        
        //addObject(new Boss(), 50, getHeight()-130);
        
        setPaintOrder(
            NewGame.class, Level.class, HealthBar.class, BossHealthBar.class,
            Explosion.class, Chip.class, Monster.class, Boss.class,
            Ground.class, Shot.class,
            Background.class
        );
    }
    
}