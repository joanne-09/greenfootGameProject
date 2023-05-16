import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Statue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Statue extends Obstacle
{
    public Statue(int i){
        if(i == 0) setImage("OBSTACLE/F1/small_statue.png");
        else if(i == 1) setImage("OBSTACLE/F1/statue.png");
        else if(i == 2)  setImage("OBSTACLE/F2/Statue.png");     
        else if(i == 3)  setImage("OBSTACLE/F2/Statue2_1.png");
        else if(i == 4)  setImage("OBSTACLE/F2/Statue2_2.png");
    }
    /**
     * Act - do whatever the Statue wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        showRon();
    }
}
