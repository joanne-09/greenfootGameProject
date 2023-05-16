import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PuzzleB here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PuzzleB extends Actor
{
    public GreenfootImage image;
    public static final GreenfootImage puz = new GreenfootImage(200,160);
    static
    {
        puz.setColor(Color.BLACK);
        puz.setTransparency(0);
        puz.fill();
    }
    public PuzzleB(){
        image = new GreenfootImage(puz);
        setImage(image);
    }
    /**
     * Act - do whatever the PuzzleB wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        if(!isTouching(PuzzleClippings.class))
            image.setTransparency(0);       
        setImage(image);
    }
}
