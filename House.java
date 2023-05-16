import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HHouse here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class House extends Obstacle
{
    public House(int i){
        if(i == 1) setImage("OBSTACLE/F1/house1.png");
        else if(i == 2) setImage("OBSTACLE/F1/house2.png");
        else if(i == 3) setImage("OBSTACLE/F1/house3.png");
        else if(i == 4) setImage("OBSTACLE/F1/house4.png");
        else if(i == 0) setImage("OBSTACLE/F1/house2.oppo.png");
        else if(i == 5) setImage("OBSTACLE/F3/house1.png");
        else if(i == 6) setImage("OBSTACLE/F3/house2.png");
        else if(i == 7) setImage("OBSTACLE/F3/house3.png");
        else if(i == 8) setImage("OBSTACLE/F3/house8.png");
        else if(i == 9) setImage("OBSTACLE/F3/house9.png");
        else if(i == 10) setImage("OBSTACLE/F3/house10.png");
        else if(i == 11) setImage("OBSTACLE/F3/castle.png");
        else if(i == 12) setImage("OBSTACLE/F3/jail.png");
        else if(i == 13) setImage("OBSTACLE/F3/traditional.png");
        else if(i == 14)  setImage("OBSTACLE/F2/House1.png");
        else if(i == 15)  setImage("OBSTACLE/F2/House2.png");
        else if(i == 16)  setImage("OBSTACLE/F2/House3.png");
        else if(i == 17)  setImage("OBSTACLE/F2/House4.png");
        else if(i == 18)  setImage("OBSTACLE/F2/House5.png");
        else if(i == 19)  setImage("OBSTACLE/F2/Store.png");
        else if(i == 20)  setImage("OBSTACLE/F2/Church.png");
        else if(i == 21)  setImage("OBSTACLE/F2/Castle.png");
    }
    /**
     * Act - do whatever the HHouse wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        showRon();
    }
}
