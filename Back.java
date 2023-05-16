import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Back here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Back extends Buttons
{
    private int[] var = new int[9];
    /**
     * Act - do whatever the Back wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        getnum();
        if(Greenfoot.mouseClicked(this)) Greenfoot.setWorld(new Menu(var));        
    }
    public void getnum(){
        Store s = (Store)getWorld();
        var[0] = s.var[0];
        var[1] = s.var[1];
        var[2] = s.gunnum;
        var[5] = s.shieldnum;
        var[6] = s.livenum;
        var[7] = s.var[7];
        var[8] = s.var[8];
    }
}
