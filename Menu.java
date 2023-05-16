import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Menu extends World
{
    private int gun = 0;
    private int knife = 0;
    private int sword = 0;
    private int shield = 0;
    private int live = 0;
    //private int puzzle = 0;
    private int[] var = new int[9];
    // private boolean[] chat = new boolean[15];
    // private boolean[] check = new boolean[9];
    private Continue ccontinue = new Continue();
    private  EndChat Chat = new EndChat(true);
    private  EndChat Chat1 = new EndChat(true);
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Menu(int var1[])
    {    
        //0:world 1:coin 2:gun 3: knife 4:sword 5:shield 6:life 7:puzzle 8:diary
        super(800, 500, 1);       
        for(int i=0; i<9; i++) var[i] = var1[i];
        addObject(ccontinue, 400, 470);        
        change();
    }
    public void change(){
        if(var[0]==1){
            setBackground("peaceLand.big.noPass.png");
            addObject(new EnterFrame(var), 562, 262);
        }else if(var[0]==2){
            setBackground("peaceLand.big.passOne.png");
            addObject(new EnterFrame(var), 582, 92);
        }else if(var[0]==3){
            setBackground("peaceLand.big.passTwo.png");
            addObject(new EnterFrame(var), 322, 232);
        }else if(var[0]==4){
            setBackground("peaceLand.big.allPass.png");
            addObject(new EnterFrame(var), 419, 388);
        }        
        addObject(new EnterStore(var), 765, 36);
        addObject(new EnterPuzzle(var), 765, 104);
    }
    /*public void act(){
        if(Greenfoot.isKeyDown("1")){
            Greenfoot.setWorld(new Frame1(var, chat, check));
        }
    }*/
}
