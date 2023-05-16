import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BranchNoWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BranchNoWord extends F1NPC
{
    public GreenfootImage img;
    private GifImage Gif1;
    private int who = -1;
    
    public int countDown = -1;
    public BranchNoWord(int a){
        if(a == 0) img = new GreenfootImage("NPC/door.png");
        else if(a == 1) Gif1 = new GifImage("NPC/craze.gif");
        else if(a == 2) img = new GreenfootImage("NPC/treasure.png");
        else if(a == 3) img = new GreenfootImage("NPC/book.png");
        else if(a == 4 || a == 6) img = new GreenfootImage("RIDDLE/blank.png");
        else if(a == 5) img = new GreenfootImage("NPC/lock.png");
        else if(a == 7) img = new GreenfootImage("NPC/door_pass.png");
        else if(a == 8) img = new GreenfootImage("NPC/puzzle.png");
        who = a;
    }
    /**
     * Act - do whatever the BranchNoWord wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        if(who==1) setImage(Gif1.getCurrentImage());
        else setImage(img);
        if(who==7) openOnNPCs(60);
        else openOnNPCs(42);
        if(countDown>0) countDown--;
        if(countDown==0 && who==5){
            countDown = -1;
            if(getWorld()!=null){
                removeOnit();
                getWorld().removeObject(this);
            }
        }
    }
    public boolean touchRun(){
        return isTouching(Run1.class);
    }
}
