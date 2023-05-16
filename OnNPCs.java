import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class OnNPCs here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OnNPCs extends Chatbox
{
    private GifImage Gif1;
    public OnNPCs(int i){
        if(i==0) Gif1 = new GifImage("onNPCs_main.gif");
        else Gif1 = new GifImage("onNPCs.gif");
    }
    /**
     * Act - do whatever the OnSomeone wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setImage(Gif1.getCurrentImage());
        // Add your action code here.
    }
    public void setNewLocation(int X, int Y){
        setLocation(X, Y-42);
    }
}