import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Branch2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Branch2 extends F2NPC
{
    //private GreenfootImage img;
    /**
     * Act - do whatever the Branch2 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Branch2(int a){
        if(a == 0)      setImage("soldier.png");
        else if(a == 1) setImage("citizen.png");
        else if(a == 2) setImage("Branch02.png");
    }
    public void act()
    {
        openOnNPCs();
    }
    public boolean touchRun2(){
        return isTouching(Run2.class);
    }
}
