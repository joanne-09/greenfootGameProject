import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Frame3NPC here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class F3NPC extends Actor
{
    public OnNPCs onit = new OnNPCs(1);
    /**
     * Act - do whatever the Frame3NPC wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    public void openOnNPCs(int x){
        int answer = 0;
        List<Run3> a = getObjectsInRange(150, Run3.class);
        if(a.size()!=0){
            getWorld().addObject(onit, getX(), getY()-x);
        }else getWorld().removeObject(onit);
    }
    public void removeOnit(){
        if(onit.getWorld()!=null)
            getWorld().removeObject(onit);
    }
}
