import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class boss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boss extends MoveLeft {
    
    public static int bosshp;
    public static GreenfootImage boss;
    GreenfootImage bosskill = new GreenfootImage("boss/boss2.png");
    public static boolean bossdeath;
    public int gTimer = 0;
    public int aTimer = 0;
    public boolean inAttack = false;
    public static boolean invulnerable;
    
    public Boss() {
        bosshp = 1000;
        boss = new GreenfootImage("boss/boss.png");
        bossdeath = false;
        invulnerable = false;
    }
    
    public void act() {
        if (Chip.bosswave == false) {getWorld().addObject(new BossHealthBar(), getWorld().getWidth()/2, 25);}
        Chip.bosswave = true;
        moveLeft();
        attack();
        death();
    }
    
    public void death() {
        if(bosshp == 0) {
            bossdeath = true;
            gTimer = gTimer + 1; 
            setImage(bosskill);
            if(gTimer > 22) {
                getWorld().addObject(new Explosion(), getX(), getY());
                getWorld().removeObject(this);   
            }
        }
    }
    
    public void attack() {
        if(Shot.kc == true) {
            setImage(boss);
            invulnerable = false;
            inAttack = false;
            Shot.kc = false;
        }
        if (bossdeath == false && inAttack == false) {
            setImage(boss);
            invulnerable = false;
        }
        if(Greenfoot.getRandomNumber(250) == 1 && inAttack == false) {
            setImage(bosskill);
            inAttack = true;  
            invulnerable = true;
            getWorld().addObject(new Monster(), getX()+75, getY());
            getWorld().addObject(new Monster(), getX()+145, getY());
            getWorld().addObject(new Monster(), getX()+200, getY());
            getWorld().addObject(new Monster(), getX()+500, getY()); 
        }   
    }
    
}