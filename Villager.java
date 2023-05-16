import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Villager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Villager extends F1NPC
{
    private GifImage Gif1 = new GifImage("villager.gif");
    /**
     * Act - do whatever the Villager wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        setImage(Gif1.getCurrentImage());
        openOnNPCs_main();
    }
}
