import greenfoot.*;
/**
 * Write a description of class Enter_frame1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnterFrame extends Buttons
{
    private int[] var = new int[9];
    private boolean[] chat = new boolean[15];
    private boolean[] check = new boolean[9];
    private Frame1 f1;
    private Frame2 f2;
    private Frame3 f3;
    private End end;
    /**
     * Constructor for objects of class Enter_frame1
     */
    public EnterFrame(int var1[])
    {
        //Add something here
        //0:world 1:coin 2:gun 3: knife 4:sword 5:shield 6:life 7:puzzle 8:diary
        for(int i=0; i<9; i++) var[i] = var1[i];
        for(int i=0; i<15; i++) chat[i]=false;
        for(int i=0; i<9; i++) check[i]=false;
        
        f1 = new Frame1(var, chat, check);
        f2 = new Frame2(var);
        f3 = new Frame3(var, chat, check);
        end = new End(1, var, true);
        
        if(var[0]==1) setImage("button2.png");  
        else if(var[0]==2) setImage("button1.png");
        else if(var[0]==3) setImage("button1.png");
        else if(var[0]==4) setImage("button2.png");
    }
    public void act()
    {
        enterFrame();
    }
    //let this know which Frame to enter
    private void enterFrame(){
        if(Greenfoot.mouseClicked(this)){
            if(var[0]==1) Greenfoot.setWorld(f1);  
            else if(var[0]==2) Greenfoot.setWorld(f2);
            else if(var[0]==3) Greenfoot.setWorld(f3);
            else if(var[0]==4) Greenfoot.setWorld(end);
        }
    }
}
