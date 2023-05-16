import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    private int enemy_live = 10;
    public static int dir = 0;
    //private Counter enemyCounter;
    private boolean stop = false;
    private int stop2 = 0;
    private boolean ran = false;
    private int freeze = 0;
    private int countDown = 20;
    
    private int pause = 0;
    private double dis = 0;
    private static boolean xDir = false;
    private static boolean yDir = false;
    private boolean up = false;
    
    private int whichRun = 0;
    /**
     * Act - do whatever the enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){   
        if(((BattleField)getWorld()).whichRun() == 1){
            if(stop2 == 0)  chase1();
        }
        else if(((BattleField)getWorld()).whichRun() == 3){
            if(stop2 == 0)  chase3();
        }
        hurt();
        removeWeapon();
        if(freeze>0)        freeze--;
        else if(freeze<=0)  stop = false;
        if(pause>0) pause--;
        if(stop2>0) stop2--;
    }
    public int getRandomNumber(int start,int end){
       int normal = Greenfoot.getRandomNumber(end-start+1);
       if(normal+start<2)    ran = true;
       else                  ran = false;
       if(normal+start>2)   return -normal+start;
       else                 return normal+start;
    }
    private void hurt(){
        if(isTouching(Detect.class)) {
            ((BattleField)getWorld()).hurtEnemy();
            stop = true;
            freeze = 20;
        }
    }
    private void touchStop(){
        if(isTouching(Run1.class) || isTouching(Run3.class)){
            stop2 = 20;
        }
    }
    private void chase1(){
        whichRun = 1;
        Actor player = (Actor)getWorld().getObjects(Run1.class).get(0);
        getRandomNumber(1, 4);
        if(player.getX() > getX()){
            if(up){
                if(getY() < 5){
                    setLocation(getX()+1, getY()+7);
                    up = false;
                }
                else    setLocation(getX()+1, getY()-2);
            }
            if(!up) {
                if(getY() > 517){
                    setLocation(getX()+1, getY()-7);
                    up = true;
                }
                else    setLocation(getX()+1, getY()+2);
            }
        }
        else if(player.getX() < getX()){
            if(up){  
                if(getY() < 5){
                    setLocation(getX()-1, getY()+7);
                    up = false;
                }
                else    setLocation(getX()-1, getY()-2);
            }
            if(!up){
                if(getY() > 517){
                    setLocation(getX()-1, getY()-7);
                    up = true;
                }
                else    setLocation(getX()-1, getY()+2);
            }
        }
        else{
            if(ran){
                if(up){
                    if(getY() < 5){
                        setLocation(getX()+1, getY()+10);
                        up = false;
                    }
                    else    setLocation(getX()+1, getY()-3);
                }
                if(!up) {
                    if(getY() > 517){
                        setLocation(getX()+1, getY()-10);
                        up = true;
                    }
                    else    setLocation(getX()+1, getY()+3);
                }
            }
            else{
                if(up){  
                    if(getY() < 5){
                        setLocation(getX()-1, getY()+7);
                        up = false;
                    }
                    else    setLocation(getX()-1, getY()-2);
                }
                if(!up){
                    if(getY() > 517){
                        setLocation(getX()-1, getY()-7);
                        up = true;
                    }
                    else    setLocation(getX()-1, getY()+2);
                }
            }
        }
    }
    private void chase3(){
        whichRun = 3;
        Actor player = (Actor)getWorld().getObjects(Run3.class).get(0);
        getRandomNumber(1, 4);
        if(player.getX() > getX()){
            if(up){
                if(getY() < 5){
                    setLocation(getX()+1, getY()+10);
                    up = false;
                }
                else    setLocation(getX()+1, getY()-3);
            }
            if(!up) {
                if(getY() > 517){
                    setLocation(getX()+1, getY()-10);
                    up = true;
                }
                else    setLocation(getX()+1, getY()+3);
            }
        }
        else if(player.getX() < getX()){
            if(up){  
                if(getY() < 5){
                    setLocation(getX()-1, getY()+10);
                    up = false;
                }
                else    setLocation(getX()-1, getY()-3);
            }
            if(!up){
                if(getY() > 517){
                    setLocation(getX()-1, getY()-10);
                    up = true;
                }
                else    setLocation(getX()-1, getY()+3);
            }
        }
        else{
            if(ran){
                if(up){
                    if(getY() < 5){
                        setLocation(getX()+1, getY()+10);
                        up = false;
                    }
                    else    setLocation(getX()+1, getY()-3);
                }
                if(!up) {
                    if(getY() > 517){
                        setLocation(getX()+1, getY()-10);
                        up = true;
                    }
                    else    setLocation(getX()+1, getY()+3);
                }
            }
            else{
                if(up){  
                    if(getY() < 5){
                        setLocation(getX()-1, getY()+10);
                        up = false;
                    }
                    else    setLocation(getX()-1, getY()-3);
                }
                if(!up){
                    if(getY() > 517){
                        setLocation(getX()-1, getY()-10);
                        up = true;
                    }
                    else    setLocation(getX()-1, getY()+3);
                }
            }
        }
    }
    private void checkDis1(){
        Actor player = (Actor)getWorld().getObjects(Run1.class).get(0);
        int x = player.getX() - getX();
        int y = player.getY() - getY();
        int x1_ = Math.abs(x);
        int y1_ = Math.abs(y);
        double tmp = x1_*x1_ + y1_*y1_;
        dis = Math.sqrt(tmp);
        if(dis<=20) stop =  true;
        else        stop =  false;
    }
    private void checkDis3(){
        Actor player = (Actor)getWorld().getObjects(Run3.class).get(0);
        int x = player.getX() - getX();
        int y = player.getY() - getY();
        int x1_ = Math.abs(x);
        int y1_ = Math.abs(y);
        double tmp = x1_*x1_ + y1_*y1_;
        dis = Math.sqrt(tmp);
        if(dis<=20) stop =  true;
        else        stop =  false;
    }
    public void attackRun1(){
        Weapon weapon = new Weapon();
        Actor player = (Actor)getWorld().getObjects(Run1.class).get(0);
        int x = player.getX() - getX();
        int y = player.getY() - getY();
        if(x > 0)   xDir = true;
        else        xDir = false;
        if(y < 0)   yDir = true;
        else        yDir = false;
        if(pause==0){
            stop = true;
            if(xDir && yDir)        getWorld().addObject(weapon, getX()+10, getY()-10);
            else if(!xDir && yDir)  getWorld().addObject(weapon, getX()-10, getY()-10);
            else if(xDir && !yDir)  getWorld().addObject(weapon, getX()+10, getY()+10);
            else if(!xDir && !yDir) getWorld().addObject(weapon, getX()-10, getY()+10);
            pause = 100;
        }
    }
    public void attackRun3(){
        Weapon weapon = new Weapon();
        Actor player = (Actor)getWorld().getObjects(Run3.class).get(0);
        int x = player.getX() - getX();
        int y = player.getY() - getY();
        if(x > 0)   xDir = true;
        else        xDir = false;
        if(y < 0)   yDir = true;
        else        yDir = false;
        if(pause==0){
            stop = true;
            if(xDir && yDir)        getWorld().addObject(weapon, getX()+3, getY()-3);
            else if(!xDir && yDir)  getWorld().addObject(weapon, getX()-3, getY()-3);
            if(xDir && !yDir)       getWorld().addObject(weapon, getX()+3, getY()+3);
            else if(!xDir && !yDir) getWorld().addObject(weapon, getX()-3, getY()+3);
            pause = 100;
        }
    }
    public void removeWeapon(){
        if(countDown>0)countDown--;
        if(countDown==0){
            getWorld().removeObjects(getWorld().getObjects(Weapon.class));
            countDown = 20;
        }
    }
    public static int returnDir(){
        if(xDir && yDir)        return 0;   //the enemy is at the left, down  
        else if(!xDir && yDir)  return 1;   //the enemy is at the right, down 
        else if(xDir && !yDir)  return 2;   //the enemy is at the left, up 
        else                    return 3;   //the enemy is at the right, up 
    }
}

