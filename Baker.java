import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Baker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Baker extends F1NPC
{
    private GifImage Gif1 = new GifImage("baker.gif");
    /**
     * Act - do whatever the Baker wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        setImage(Gif1.getCurrentImage());
        openOnNPCs_main();
    }
}
