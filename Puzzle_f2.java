import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Puzzle_f2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Puzzle_f2 extends F2NPC
{
    /**
     * Act - do whatever the Puzzle_f2 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Notify notify;
    public Puzzle_f2(){
        getImage().setTransparency(0); //透明ㄉ
        notify = new Notify();
    }
    public void act()
    {
        // Add your action code here.
    }
    public void getPuzzle(){
        if(isTouching(Run2.class)){
            ((Frame2)getWorld()).var[7]++;
            getWorld().addObject(notify, 400, 470);
            notify.showText(15);
            getWorld().removeObject(this);
        }
    }
}
