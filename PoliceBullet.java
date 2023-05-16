import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PoliceBullet extends Actor
{
    public boolean isFlying = false;
    private int fly = 0;
    public PoliceBullet(int w){
        setRotation(w);
    }
    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {               
        move(10);
        checkHit();//確認是否被子彈碰到       
        if(fly > 0) fly--;
    }
    public void checkHit(){
        if(isFlying){
           if(isTouching(Run3.class)){
                Run3 r = (Run3)getOneIntersectingObject(Run3.class);
                r.hurt();
                getWorld().removeObject(this);
                isFlying = false;
            }else if(atWorldEdge() || isTouching(Obstacle.class) || fly==0){
                getWorld().removeObject(this);
                isFlying = false;
            }
        }else{
            if(getWorld() != null){
                isFlying = true;
                fly = 20;
            }
        }
    }
    public boolean atWorldEdge(){
        return (getWorld().getWidth()-getX()<5 || getWorld().getHeight()-getY()<5 || getX()<5 || getY() < 5);
    }
    public void removeobject(){
        getWorld().removeObject(this);
    }
}