import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World {
    
    //private GreenfootSound music = new GreenfootSound(null);
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld() {
        super(1000, 500, 1, false);
        populateTheWorld();
    }
    
    public void act() {
        if (Level.waveCount == 0
        || (MoveLeft.worldX > 20000*Level.waveCount
        && (Level.waveCount == 1
        || Level.waveCount == 2
        || Level.waveCount == 3
        || Level.waveCount == 4
        || Level.waveCount == 5
        || Level.waveCount == 6
        || Level.waveCount == 7
        || Level.waveCount == 8))) {
            Level.waveCount++;
            for (int i = 0; i < 4*Level.waveCount; i++) {
                addObject(new Monster(), getWidth()+250+Greenfoot.getRandomNumber(1000), getHeight()-120);
            }
        }
        if (MoveLeft.worldX > 90000 && Level.waveCount == 9) {
            Level.waveCount++;
            addObject(new Boss(), 50, getHeight()-130);
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
        //addObject(new TEST(), 150, 100);
        addObject(new Boss(), 50, getHeight()-130);
        for (int i = -3; i < 50; i++) {
            addObject(new Background(), 1158+2316*i, getHeight()/2);
            addObject(new Ground(), 639+1277*i, getHeight()-32);
        }
        
        addObject(new Chip(), getWidth()/2, getHeight()-112);
        
        setPaintOrder(
            NewGame.class, Level.class, HealthBar.class, BossHealthBar.class, TEST.class,
            Explosion.class, Chip.class, Monster.class, Boss.class,
            Ground.class, Shot.class, Fireball.class, Shockwave.class,
            Background.class
        );
    }
    
}