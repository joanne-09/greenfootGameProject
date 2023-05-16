import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Police_live here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Police_live extends Actor
{
    private int live;
    
    public Police_live(){
        live = 0;
        setImage("l0_sprite_1.png");
    }
    /**
     * Act - do whatever the Police_live wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        check();
    }
    public void check(){
        switch(live){
            case 1:
                setImage("l0_sprite_2.png");
                break;
            case 2:
                setImage("l0_sprite_3.png");
                break;
            case 3:
                setImage("l0_sprite_4.png");
                break;
            case 4:
                setImage("l0_sprite_5.png");
                getWorld().removeObject(this);
                break;
        }
    }
    public void setNewLocation(int X, int Y){
        setLocation(X, Y-45);
    }
    public void minuslife(){
        live++;
    }
    //新增的
    public int live(){
        return live;
    }
}
