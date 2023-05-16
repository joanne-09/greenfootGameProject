import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Starter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Starter extends World
{
    private StartText startText = new StartText();
    private None none;
    private int[] var = new int[9];
    /**
     * Constructor for objects of class Starter.
     * 
     */
    public Starter()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 500, 1);
        prepare();
        //Greenfoot.start();
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {       
        addObject(startText,400,470);
        setarry();
        none = new None(var);
    }
    public void act(){
        if(Greenfoot.isKeyDown("space")) Greenfoot.setWorld(none);
    }
    public void setarry(){
        //0:world 1:coin 2:gun 3: knife 4:sword 5:shield 6:life 7:puzzle 8:diary
        var[0] = 1;
        var[1] = 15;
        var[2] = 0;
        var[3] = 0;
        var[4] = 0;
        var[5] = 0;
        var[6] = 0;
        var[7] = 0;
        // var[8] = int[3];
        var[8] = 0;
    }
}
