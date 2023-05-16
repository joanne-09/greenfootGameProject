import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class F2NPC here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class F2NPC extends Actor
{
    private OnNPCs onit_main = new OnNPCs(0);
    private OnNPCs onit = new OnNPCs(1);
    /**
     * Act - do whatever the F2NPC wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    public void openOnNPCs(){
        int answer = 0;
        List<Run2> a = getObjectsInRange(150, Run2.class);
        if(a.size()!=0){
            getWorld().addObject(onit, getX(), getY()-42);
        }else{
            getWorld().removeObject(onit);
        }
    }
    public void openOnNPCs_main(){
        int answer = 0;
        List<Run2> a = getObjectsInRange(150, Run2.class);
        if(a.size()!=0){
            getWorld().addObject(onit_main, getX(), getY()-42);
        }else getWorld().removeObject(onit_main);
    }
    public boolean openOnNPCs_box(){
        int answer = 0;
        List<Run2> a = getObjectsInRange(150, Run2.class);
        if(a.size()!=0){
            getWorld().addObject(onit, getX(), getY()-42);
            return true;
        }else{
            getWorld().removeObject(onit);
        }
        return false;
    }
    /*public void removeOnit(){
        if(onit.getWorld()!=null)
            getWorld().removeObject(onit);
    }*/
}
