import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnterPuzzle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnterPuzzle extends Buttons
{
    private int[] var = new int[9];
    public EnterPuzzle(int var1[]){
        //0:world 1:coin 2:gun 3: knife 4:sword 5:shield 6:life 7:puzzle 8:diary
        var[0] = var1[0];
        var[1] = var1[1];
        var[2] = var1[2];
        var[3] = var1[3];
        var[4] = var1[4];
        var[5] = var1[5];
        var[6] = var1[6];
        var[7] = var1[7];
        //puzzle = var[7];
        var[8] = var1[8];
    }
    /**
     * Act - do whatever the EnterPuzzle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        if(Greenfoot.mouseClicked(this)) Greenfoot.setWorld(new Puzzle(var));
    }
    /**
     * overrides the 'setLocation(int, int)' method of the Actor class so the counter cannot be relocated
     * (it removes the implementation provided by the Actor class)
     */
    public void setLocation(int x, int y) {}
}
