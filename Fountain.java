import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fountain here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fountain extends Obstacle
{
    public Fountain(int i){
        if(i == 1) setImage("OBSTACLE/F1/fountain.png");
        else if(i == 2) setImage("OBSTACLE/F2/Fountain2.png");
    }
    /**
     * Act - do whatever the Fountain wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        showRon();
    }
}
