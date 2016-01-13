import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Chip here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Chip extends Actor {
    
    private GreenfootImage sword = new GreenfootImage("chipswordright.png");
    private GreenfootImage swordLeft = new GreenfootImage("chipswordleft.png");
    private GreenfootImage left = new GreenfootImage("shootleft.png");
    private GreenfootImage right = new GreenfootImage("shootright.png");
    private GreenfootImage duckLeft = new GreenfootImage("duckleft.png");
    private GreenfootImage duckRight = new GreenfootImage("duckright.png");
    private GreenfootImage jumpLeft = new GreenfootImage("jumpleft.png");
    private GreenfootImage jumpRight = new GreenfootImage("jumpright.png");
    GifImage teleport = new GifImage("teleport.gif");
    GifImage runright = new GifImage("runright.gif");
    GifImage runleft = new GifImage("runleft.gif");
    GreenfootImage noImg = new GreenfootImage(1, 1);
    private int swordTimer = 0;
    private int shotTimer = 0;
    private int jumpTimer = 0;
    private int jumpCounter = 0;
    private int vspeed = 0;
    private int hspeed = 0;
    private int acceleration = 1;
    private int cooldown = 20;
    boolean cd = false;
    boolean spawned = false;
    int spawnTimer = 0;
    
    public static int speed;
    public boolean isGameOver = false;
    public boolean isDead = false;
    public boolean isLeft = false;
    public static boolean bosswave;
    public static int hp;
    public static int chipX;
    public Chip() {
        speed = 5;
        bosswave = false;
        hp = 100;
        getImage().clear();
        noImg.clear();
        MoveLeft.worldX = 0;
    }
    
    /**
     * Act - do whatever the Chip wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if (spawned == false) {
            if (!onGround()) {fall();}
            spawnTimer++;
            setImage(teleport.getCurrentImage());
            if (spawnTimer > 20) {spawned = true;}
        }
        else {
            chipX = getX();
            if (Greenfoot.isKeyDown("/")) {
                cooldown = 1;
                this.hp = 100;
            }
            if (Greenfoot.isKeyDown("Q")) {speed = 100;}
            if (!isGameOver) {
                if (hp < 0) {
                    hp = 0;
                    isDead = true;
                    isGameOver = true;
                }
                die();
                if (hp > 0) {
                    movement();
                    shoot();
                    slash();
                }
            }
        }
    }
    /* movement */
    /**************************************************************/
    protected void movement() {
        if (Greenfoot.isKeyDown("left")) {
            if (onGround()) {
                setImage(left);
            }
            setRotation(180);
        }
        if (Greenfoot.isKeyDown("right")) {
            if (onGround()) {
                setImage(right);
            }
            setRotation(0);
        }
        
        //duck
        if (Greenfoot.isKeyDown("down") && !(Greenfoot.isKeyDown("up"))) {duck();}
        
        //jump
        if (Greenfoot.isKeyDown("up") && !(Greenfoot.isKeyDown("down"))) {
            if (jumpTimer%25 == 0) {
                jump();
                jumpCounter++;
            }
            jumpTimer++;
        }
        else if (jumpCounter < 2) {jumpTimer = 0;}
        checkFall();
        
        //walk
        if (!(Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("down"))) {walk();}
        
        if(!(Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("down")
        || Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("right"))
        && onGround()
        && swordTimer == 0) {
            if (isLeft) {setImage(left);}
            else {setImage(right);}
        }
    }
    //die
    protected void die() {
        if (hp == 0) {
            getImage().clear();
        }
    }
    //duck
    protected void duck() {
        if (Greenfoot.isKeyDown("left") || getImage() == left) {setImage(duckLeft);}
        if (Greenfoot.isKeyDown("right") || getImage() == right) {setImage(duckRight);}
    }
    
    //walk
    protected void walk() {
        if (Greenfoot.isKeyDown("left")) {setImage(left);}
        if (Greenfoot.isKeyDown("right")) {setImage(right);}
        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("right")) {
            moveHorizontally();
        }
    }
    
    /* jump */
    /*******************************/
    protected void jump() {
        if (Greenfoot.isKeyDown("left") || getImage() == left) {setImage(jumpLeft);}
        if (Greenfoot.isKeyDown("right") || getImage() == right) {setImage(jumpRight);}
        vspeed = -10;
        fall();
    }
    public void checkFall() {
        if(onGround()) {
            vspeed = 0;
            jumpCounter = 0;
            while (getOneObjectAtOffset(0, getImage().getHeight()/2-2, Ground.class) != null) {
                setLocation(getX(), getY()-1);
            }
        }
        else {
            fall();
            if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("right")) {
                moveHorizontally();
            }
        }
    }
    public void fall() {
        setLocation (getX(), getY() + vspeed);
        vspeed += acceleration;
    }
    //*****************************//
    
    protected void moveHorizontally() {
        if (!(Greenfoot.isKeyDown("left") && Greenfoot.isKeyDown("right"))) {
            move(speed);
            //hspeed += acceleration;
            if (Greenfoot.isKeyDown("left")) {
                isLeft = true;
                if (onGround()) {setImage(runleft.getCurrentImage());}
            }
            if (Greenfoot.isKeyDown("right")) {
                isLeft = false;
                if (onGround()) {setImage(runright.getCurrentImage());}
            }
            if ((getX() > 700 && bosswave == false)
            || getX() > getWorld().getWidth()-getImage().getWidth()/2) {setLocation(getX()-speed, getY());}
            if ((getX() < 200 && MoveLeft.worldX > 0 && bosswave == false)
            || getX() < getImage().getWidth()/2) {setLocation(getX()+speed, getY());}
        }
        speed = 5;
    }
    //************************************************************//
    
    /* collision */
    protected boolean onGround() {
        return getOneIntersectingObject(Ground.class) != null;
    }
    
    public void shoot() {
        if (Greenfoot.isKeyDown("F")) {
            if (shotTimer%cooldown == 0) {
                if (isLeft) {getWorld().addObject(new Shot(isLeft), getX()-20, getY()-3);}
                else {getWorld().addObject(new Shot(isLeft), getX()+20, getY()-3);}
            }
            shotTimer++;
        }
        else {shotTimer = 0;}
        cooldown = 20;
    }
    public void slash() {
        if (Greenfoot.isKeyDown("R")) {
            if (swordTimer%cooldown == 0) {
                if (isLeft) {setImage(swordLeft);}
                else {setImage(sword);}
                Monster monster = getOneIntersectingObject(Monster.class);
                if (monster != null) {
                    if (Boss.invulnerable == true) {
                        Shot.killcount++;
                    }
                    getWorld().removeObject(monster);
                }
            }
            swordTimer++;
        }
        else {
            swordTimer = 0;
        }
        cooldown = 20;

    }
}