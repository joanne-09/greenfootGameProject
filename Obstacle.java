import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Map here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Obstacle extends Actor
{
    /**
     * Act - do whatever the Map wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    public void showRon(){
        Actor a = getOneIntersectingObject(Run1.class);
        Actor a2 = getOneIntersectingObject(Run2.class);
        Actor a3 = getOneIntersectingObject(Run3.class);
        GreenfootImage b = getImage();
        if(a!=null || a2!=null ||  a3!=null) b.setTransparency(150);
        else b.setTransparency(255);
    }
}
