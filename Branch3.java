import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Murder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Branch3 extends F3NPC
{
    private GreenfootImage img;
    private GifImage Gif1;
    private boolean isTouch = false;
    private int who = -1;
    public Branch3(int a){
        if(a == 0) img = new GreenfootImage("NPC/Girl.png");
        if(a == 1) img = new GreenfootImage("NPC/Bucket.png");
        if(a == 2) img = new GreenfootImage("NPC/Box.png");
        if(a == 3) img = new GreenfootImage("NPC/Picture.png");
        if(a == 4) img = new GreenfootImage("NPC/Letter.png");
        if(a == 5) img = new GreenfootImage("NPC/Someone.png");
        who = a;
    }
    /**
     * Act - do whatever the Murder wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        setImage(img);
        //setImage(Gif1.getCurrentImage());
        //openOnNPCs(42);
        /*
         * 不知道這個是甚麼?
         * openOnNPCs(42);
         */
        openOnNPCs(42);
    }
    public boolean touchRun(){
        return isTouching(Run3.class);
    }
    public boolean btouchRun(){
        if(who == 5 && isTouching(Run3.class)){
            return true;
        }else return false;
    }
}
