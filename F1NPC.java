import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class F1NPC here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class F1NPC extends Actor
{
    public OnNPCs onit = new OnNPCs(1);
    public OnNPCs onit_main = new OnNPCs(0);
    /**
     * Act - do whatever the F1NPC wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    public void openOnNPCs(int x){
        int answer = 0;
        List<Run1> a = getObjectsInRange(150, Run1.class);
        if(a.size()!=0){
            getWorld().addObject(onit, getX(), getY()-x);
        }else getWorld().removeObject(onit);
    }
    public void openOnNPCs_main(){
        int answer = 0;
        List<Run1> a = getObjectsInRange(150, Run1.class);
        if(a.size()!=0){
            getWorld().addObject(onit_main, getX(), getY()-42);
        }else getWorld().removeObject(onit_main);
    }
    public void removeOnit(){
        if(onit.getWorld()!=null)
            getWorld().removeObject(onit);
    }
}
