import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class boss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boss extends MoveLeft {
    
    public static int bosshp;
    public static boolean bossdeath;
    public int gTimer;
    public int aTimer;
    public int sTimer;
    public int attackstyle;
    public boolean inAttack = false;
    public int frame = 1;
    public static boolean invulnerable;
    private GreenfootImage boss;
    private GreenfootImage bosskill;
    private GreenfootImage attack1;
    private GreenfootImage attack2;
    private GreenfootImage attack3;
    public Boss() {
        bosshp = 1000;
        boss = new GreenfootImage("boss/boss.png");
        bosskill = new GreenfootImage("boss/boss2.png");
        attack1 = new GreenfootImage("boss/bossattack1.png");
        attack2 = new GreenfootImage("boss/bossattack2.png");
        attack3 = new GreenfootImage("boss/bossattack3.png");
        bossdeath = false;
        invulnerable = false;
        aTimer = 0;
        sTimer = 0;
        attackstyle = 0;
        gTimer = 0;
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
            gTimer = gTimer + 1;   //als de boss dood is telt hij er elkekeer 1 bij op
            setImage(bosskill);
            if(gTimer > 22) {   //na 22 komt er een explosie
                getWorld().addObject(new Explosion(), getX(), getY());
                getWorld().removeObject(this);   
            }
        }
    }
    
    public void attack() {
        if(Shot.kc == true && invulnerable == true) {  //als chip 4 monsters heeft gekilt kan je de boss weer aanvallen
            setImage(boss);
            invulnerable = false;
            inAttack = false;
            Shot.kc = false;
            
        }
        if (bossdeath == false && inAttack == false) { //als hij niet aan het aanvallen is doet hij niks
            setImage(boss);
            invulnerable = false;
        }
        if(Greenfoot.getRandomNumber(250) == 1 && inAttack == false) {  //hij valt random aan
                        setImage(bosskill);
            inAttack = true;  
            invulnerable = true;
            getWorld().addObject(new Monster(), getX()+75, getY());
            getWorld().addObject(new Monster(), getX()+145, getY());
            getWorld().addObject(new Monster(), getX()+200, getY());
            getWorld().addObject(new Monster(), getX()+500, getY()); 
            
        } 
        if(Greenfoot.getRandomNumber(250) == 25 && inAttack == false) {  //hij valt random aan
            attackstyle = 1;
        }
        if (attackstyle == 1) {
             inAttack = true;
        attackEmote();
  
        }

    }
    
    public void attackEmote() {
        inAttack = true;   
        aTimer ++;
        sTimer ++;
        if (aTimer == 5) {
            setImage(attack1);
        }
        if (aTimer == 15) {
            setImage(attack2);
        }
        if (aTimer == 25) {
           setImage(attack3);
        }
        if (aTimer == 28) {
                getWorld().addObject(new Fireball(), getX()+3, getY()+10-Greenfoot.getRandomNumber(30));   
                aTimer = 0;
            }
        if (sTimer == 155) {
            attackstyle = 0;
             inAttack = false;
             sTimer = 0;
        }
    }
    
}