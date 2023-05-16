import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Store here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Store extends World
{
    private Gun_in_store gun = new Gun_in_store();
    private Shield_in_store shield = new Shield_in_store();
    private Live_in_store live = new Live_in_store();
    
    private Coinsnum coin = new Coinsnum();
    //private Coin coin = new Coin();
    private Back back;
    
    public int gunnum = 0;
    public int shieldnum = 0;
    public int livenum = 0;
    
    public int[] var = new int[9];
    /**
     * Constructor for objects of class Store.
     * 
     */
    public Store(int var1[])
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 500, 1);
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
        back = new Back();
        prepare();
    }
    public void prepare(){
        addObject(gun, 175, 150);
        addObject(shield, 175, 375);
        addObject(live, 575, 375);
        //addObject(coin, 715, 50);
        //740 457
        addObject(back, 740, 457);
        addObject(coin, 740, 60);
    }
    public void act(){
        buynum();
    }
    public void buynum(){
        if(Greenfoot.mouseClicked(gun) && var[1] >= 3){
            gunnum++;
            var[1]-=3;
        }else if(Greenfoot.mouseClicked(shield)&& var[1] >= 3){
            shieldnum++;
            var[1]-=3;
        }else if(Greenfoot.mouseClicked(live)&& var[1] >= 4){
            livenum++;
            var[1]-=4;
        }
        else{
            //no coins image
        }
    }
}
