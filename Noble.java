import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Noble here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Noble extends F3NPC
{
    /**
     * Act - do whatever the Noble wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private  int is = 0;
    public void act()
    {
        // Add your action code here.
    }
    public void walk(){
        //turn(90);
        //move(70);
        setLocation(getX(), getY() + 70);
        //is = true;
        //pause = (++pause)%10; // change '2' higher to slow down animation
        //if (imageCounter == 0) roboImageSwap();
        //while(pause > 0) pause--;
        //is = 1;
    }
}

