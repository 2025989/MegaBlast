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
    private GreenfootImage boss,bosskill,attack1,attack2,attack3,angry1,angry2,angry3,kick1,kick2,kick3;    
    private int vspeed = 0;
    private int acceleration = 1;
    public Boss() {
        bosshp = 1000;
        boss = new GreenfootImage("boss/boss.png");
        bosskill = new GreenfootImage("boss/boss2.png");
        attack1 = new GreenfootImage("boss/bossattack1.png");
        attack2 = new GreenfootImage("boss/bossattack2.png");
        attack3 = new GreenfootImage("boss/bossattack3.png");
        angry1 = new GreenfootImage("boss/bossangry1.png");
        angry2 = new GreenfootImage("boss/bossangry2.png");
        angry3 = new GreenfootImage("boss/bossangry3.png");
        kick1 = new GreenfootImage("boss/bosskick1.png");
        kick2 = new GreenfootImage("boss/bosskick2.png");
        kick3 = new GreenfootImage("boss/bosskick3.png");
        bossdeath = false;
        invulnerable = false;
        aTimer = 0;
        sTimer = 0;
        attackstyle = 0;
        gTimer = 0;
    }
    
    public void act() {
        if (Chip.bosswave == false) {
            getWorld().addObject(new BossHealthBar(), getWorld().getWidth()/2, 25);
        }
        Chip.bosswave = true;
        moveLeft();
        attack();
        checkFall();
        death();
    }
    
    public void death() {
        if(bosshp == 0) {  
            bossdeath = true;
            gTimer = gTimer + 1;   //als de boss dood is telt hij er elkekeer 1 bij op
            setImage(bosskill);
        if(gTimer > 22) {   //na 22 komt er een explosie
                Chip.bosswave = false;
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
            getWorld().addObject(new Monster(), Chip.chipX-100-Greenfoot.getRandomNumber(300), getWorld().getHeight()-120);
            getWorld().addObject(new Monster(), Chip.chipX-100-Greenfoot.getRandomNumber(300), getWorld().getHeight()-120);
            getWorld().addObject(new Monster(), Chip.chipX+100+Greenfoot.getRandomNumber(300), getWorld().getHeight()-120);
            getWorld().addObject(new Monster(), Chip.chipX+100+Greenfoot.getRandomNumber(300), getWorld().getHeight()-120);
            
        } 
        if(Greenfoot.getRandomNumber(250) == 25 && inAttack == false) {  //hij valt random aan
            attackstyle = 1;
        }
        if(Greenfoot.getRandomNumber(250) == 38 && inAttack == false) {  //hij valt random aan
        attackstyle = 2;
        } 
        if(Greenfoot.getRandomNumber(250) == 50 && inAttack == false) {  //hij valt random aan
        attackstyle = 3;
        }
        if (attackstyle == 1) {
        attackEmote();
        }
        else if (attackstyle == 2) {
        angryEmote();
        }
        else if (attackstyle == 3) {
        kickEmote();
        }

    }
    
    /* jump */
    /*******************************/
    protected void jump() {
        vspeed = -10;
        fall();
    }
    public void checkFall() {
        if(onGround()) {
            vspeed = 0;
            while (getOneObjectAtOffset(0, getImage().getHeight()/2-2, Ground.class) != null) {
                setLocation(getX(), getY()-1);
            }
        }
        else {
            fall();
        }
    }
    public void fall() {
        setLocation (getX(), getY() + vspeed);
        vspeed += acceleration;
    }
    //*****************************//
    protected boolean onGround() {
        return getOneIntersectingObject(Ground.class) != null;
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
     public void angryEmote() {
        inAttack = true;   
        aTimer ++;
        sTimer ++;
        if (aTimer == 1) {
            setImage(angry1);
            jump();
        }
        if (aTimer == 15) {
            setImage(angry2);
        }
        if (aTimer == 50) {
           setImage(angry3);
        }
        if (aTimer == 60) {
             attackstyle = 0;
             inAttack = false;
             sTimer = 0;
             aTimer = 0;
            }
    }
    public void kickEmote() {
        inAttack = true;   
        aTimer ++;
        sTimer ++;
        if (aTimer == 1) {
            setImage(kick1);
        }
        if (aTimer == 15) {
            setImage(kick2);
        }
        if (aTimer == 30) {
           setImage(kick3);
           getWorld().addObject(new Shockwave(), getX()+3, getY()+35);   
        }
        if (aTimer == 40) {
             attackstyle = 0;
             inAttack = false;
             sTimer = 0;
             aTimer = 0;
            }
    }
}