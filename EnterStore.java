import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnterStore here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnterStore extends Buttons
{
    private int coins = 0;
    private int world = 0;
    private int[] var = new int[9];
    public EnterStore(int var1[]){
        //coins = c;
        //world = w;
        //0:world 1:coin 2:gun 3: knife 4:sword 5:shield 6:life 7:puzzle 8:diary
        var[0] = var1[0];
        var[1] = var1[1];
        var[2] = var1[2];
        var[3] = var1[3];
        var[4] = var1[4];
        var[5] = var1[5];
        var[6] = var1[6];
        var[7] = var1[7];
        var[8] = var1[8];
    }
    /**
     * Act - do whatever the EnterStore wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        if(Greenfoot.mouseClicked(this)) {
            //setarry();
            Greenfoot.setWorld(new Store(var));
        }
    }
    /*public void setarry(){
        var[0] = 1;
        var[1] = 3;
        var[2] = 0;
        var[3] = 0;
        var[4] = 0;
        var[5] = 0;
        var[6] = 0;
    }*/
    /**
     * overrides the 'setLocation(int, int)' method of the Actor class so the counter cannot be relocated
     * (it removes the implementation provided by the Actor class)
     */
    // public void setLocation(int x, int y) {}
}
