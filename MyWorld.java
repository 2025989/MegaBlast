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
        if ((Level.waveCount == 0)
        || (MoveLeft.worldX > 10000 && Level.waveCount == 1)
        || (MoveLeft.worldX > 20000 && Level.waveCount == 2)
        || (MoveLeft.worldX > 30000 && Level.waveCount == 3)
        || (MoveLeft.worldX > 40000 && Level.waveCount == 4)
        || (MoveLeft.worldX > 50000 && Level.waveCount == 5)
        || (MoveLeft.worldX > 60000 && Level.waveCount == 6)
        || (MoveLeft.worldX > 70000 && Level.waveCount == 7)
        || (MoveLeft.worldX > 80000 && Level.waveCount == 8)) {
            Level.waveCount++;
            for (int i = 0; i < 4*Level.waveCount; i++) {
                addObject(new Monster(), getWidth()+250+Greenfoot.getRandomNumber(1000), getHeight()-120);
            }
        }
        if (MoveLeft.worldX > 90000 && Level.waveCount == 9) {
            Level.waveCount++;
            addObject(new Boss(), 50, getHeight()-130);
            addObject(new BossHealthBar(), getWidth()/2, 25);
            Chip.bosswave = true;
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
        
        for (int i = 0; i < 50; i++) {
            addObject(new Background(), 1158+2316*i, getHeight()/2);
            addObject(new Ground(), 639+1277*i, getHeight()-32);
        }
        
        addObject(new Chip(), getWidth()/2, getHeight()-112);
        
        setPaintOrder(
            NewGame.class, Level.class, HealthBar.class, BossHealthBar.class,
            Explosion.class, Chip.class, Monster.class, Boss.class,
            Ground.class, Shot.class,
            Background.class
        );
    }
    
}