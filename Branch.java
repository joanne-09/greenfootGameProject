import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Murder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Branch extends F1NPC
{
    private GreenfootImage img;
    private GifImage Gif1;
    private boolean isTouch = false;
    private int who = -1;
    public Branch(int a){
        if(a == 0) Gif1 = new GifImage("NPC/priest.gif");
        if(a == 6) Gif1 = new GifImage("NPC/priest1.gif");
        if(a == 1) img = new GreenfootImage("RIDDLE/blank.png");
        if(a == 2) Gif1 = new GifImage("NPC/storyMan.gif");
        if(a == 3) Gif1 = new GifImage("NPC/child.gif");
        if(a == 4) Gif1 = new GifImage("NPC/lady.gif");
        if(a == 5) Gif1 = new GifImage("NPC/scavenger.gif");
        who = a;
    }
    /**
     * Act - do whatever the Murder wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        if(who == 1) setImage(img);
        else setImage(Gif1.getCurrentImage());
        openOnNPCs(42);
    }
    public boolean touchRun(){
        return isTouching(Run1.class);
    }
    public boolean touchBall(){
        if(who==3 && isTouching(Ball.class)){
            return true;
        }else return false;
    }
}
