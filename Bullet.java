import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends Actor
{
    public Bullet(int r){
        setRotation(r);
    }
    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move(10);
        checkHit();//確認是否被子彈碰到
    }
    public void checkHit(){
        if(isTouching(NPC.class)){
            NPC p = (NPC)getOneIntersectingObject(NPC.class);
            p.hurt();
            getWorld().removeObject(this);
        }
        else if(atWorldEdge() || isTouching(Obstacle.class)){
            getWorld().removeObject(this);
        }
    }   
    public boolean atWorldEdge(){
        return (getWorld().getWidth()-getX()<5 || getWorld().getHeight()-getY()<5 || getX()<5 || getY() < 5);
    }
    public void removeobject(){
        getWorld().removeObject(this);
    }
}
