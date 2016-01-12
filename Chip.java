import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Chip here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Chip extends Actor {
    
    private GreenfootImage left = new GreenfootImage("ChipLeft.png");
    private GreenfootImage right = new GreenfootImage("ChipRight.png");
    private GreenfootImage duckLeft = new GreenfootImage("DuckLeft.png");
    private GreenfootImage duckRight = new GreenfootImage("DuckRight.png");
    private GreenfootImage jumpLeft = new GreenfootImage("JumpLeft.png");
    private GreenfootImage jumpRight = new GreenfootImage("JumpRight.png");
    GreenfootImage noImg = new GreenfootImage(1, 1);
    private int shotTimer = 0;
    private int jumpTimer = 0;
    private int jumpCounter = 0;
    private int vspeed = 0;
    private int hspeed = 0;
    private int acceleration = 1;
    private int cooldown = 20;
    
    public static int speed;
    public boolean isGameOver = false;
    public boolean isDead = false;
    public boolean isLeft = false;
    public static boolean bosswave;
    public static int hp;
    public Chip() {
        speed = 5;
        bosswave = false;
        hp = 100;
        setImage(right);
        noImg.clear();
        MoveLeft.worldX = 0;
    }
    
    /**
     * Act - do whatever the Chip wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if (Greenfoot.isKeyDown("/")) {cooldown = 1;}
        if (Greenfoot.isKeyDown("Q")) {speed = 100;}
        if (!isGameOver) {
            if (hp < 0) {
                hp = 0;
                isDead = true;
                isGameOver = true;
            }
            die();
            if (hp > 0) {movement();}
            //checkHealth();
            attack();
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
        
        //move(hspeed);
        /*if (!(Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("right"))
        && hspeed > 0) {

            hspeed -= acceleration;
        }*/
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
            }
            if (Greenfoot.isKeyDown("right")) {
                isLeft = false;
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
    /**************************************************************/
    protected boolean onGround() {
        return getOneIntersectingObject(Ground.class) != null;
    }
    //public boolean atWorldEdge() {
    //    return ( /*left*/   getX()-getImage().getWidth()/2 <= 0
    //        ||   /*right*/  getX()+getImage().getWidth()/2 >= getWorld().getWidth()
    //        ||   /*top*/    getY()-getImage().getHeight()/2 <= 0
    //        ||   /*bottom*/ getY()+getImage().getHeight()/2 >= getWorld().getHeight());
    //}
    //************************************************************//
    
    public void attack() {
        if (Greenfoot.isKeyDown("F")) {
            if (shotTimer%cooldown == 0) {
                getWorld().addObject(new Shot(isLeft), getX(), getY());
            }
            shotTimer++;
        }
        else {shotTimer = 0;}
        cooldown = 20;
    }

}