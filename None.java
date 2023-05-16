import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class None here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class None extends World
{
    private GreenfootImage[] img = new GreenfootImage[9];
    private int now = -1;
    private int should = 0;
    private int countDown = -1;
    
    private Menu menu;
    private int[] var = new int[9];
    /**
     * Constructor for objects of class None.
     * 
     */
    public None(int var1[])
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 500, 1);
        
        for(int i=0; i<9; i++) var[i] = var1[i];
        menu = new Menu(var);
        
        prepare();
        setBackground(new GreenfootImage("NONE/None.png"));
    }
    public void act(){
        if(countDown>0) countDown--;
        if(countDown==0){
            should++;
            countDown--;
        }
        if(should==9) Greenfoot.setWorld(menu);
        changeImg();
        if(Greenfoot.isKeyDown("shift") && Greenfoot.isKeyDown("p"))    Greenfoot.setWorld(menu);
    }
    public void changeImg(){
        if(now != should && should<9){
            now++;
            setBackground(img[now]);
            //countDown = 3;
            countDown = 200;
        }
    }
    public void prepare(){
        img[0] = new GreenfootImage("NONE/None1.png");
        img[1] = new GreenfootImage("NONE/None2.png");
        img[2] = new GreenfootImage("NONE/None3.png");
        img[3] = new GreenfootImage("NONE/None4.png");
        img[4] = new GreenfootImage("NONE/None5.png");
        img[5] = new GreenfootImage("NONE/None6.png");
        img[6] = new GreenfootImage("NONE/None7.png");
        img[7] = new GreenfootImage("NONE/None8.png");
        img[8] = new GreenfootImage("NONE/None9.png");
    }
}
