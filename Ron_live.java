import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ron_live here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ron_live extends Actor
{
    private static int live;
    public static int over = 0;
    /**
     * Act - do whatever the Ron_live wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public Ron_live()
    {
        live = 0;
        setImage("LIFE/lives00.png");
    }
    public void act()
    {
        if(live < 0) setImage("LIFE/lives00.png");
        check();
        if(getWorld().getClass() == BattleField.class)  getOver();
    }
    //public void move(int dist) {}
    /**
     * overrides the 'setLocation(int, int)' method of the Actor class so the counter cannot be relocated
     * (it removes the implementation provided by the Actor class)
     */
    public void setLocation(int x, int y) {}
    public static void minuslife(){
        live++;
    }
    public void minusminuslife(){
        live = live+2;
        if(live>18) live = 18;
    }
    public void addlife(){
        live--;
    }
    public int live(){
        return live;
    }
    public void check(){
        switch(live){
            case 1:
                setImage("LIFE/lives01.png");
                break;
            case 2:
                setImage("LIFE/lives02.png");
                break;
            case 3:
                setImage("LIFE/lives03.png");
                break;
            case 4:
                setImage("LIFE/lives04.png");
                break;
            case 5:
                setImage("LIFE/lives05.png");
                break;
            case 6:
                setImage("LIFE/lives06.png");
                break;
            case 7:
                setImage("LIFE/lives07.png");
                break;
            case 8:
                setImage("LIFE/lives08.png");
                break;
            case 9:
                setImage("LIFE/lives09.png");
                break;
            case 10:
                setImage("LIFE/lives10.png");
                break;
            case 11:
                setImage("LIFE/lives11.png");
                break;
            case 12:
                setImage("LIFE/lives12.png");
                break;
            case 13:
                setImage("LIFE/lives13.png");
                break;
            case 14:
                setImage("LIFE/lives14.png");
                break;
            case 15:
                setImage("LIFE/lives15.png");
                break;
            case 16:
                setImage("LIFE/lives16.png");
                break;
            case 17:
                setImage("LIFE/lives17.png");
                break;
            case 18:
                setImage("LIFE/lives18.png");
                over = 1;
                //遊戲結束
                break;
            
        }
    }
    public static int returnOver(){
        return over;
    }
    public void getOver(){
        over = ((BattleField)getWorld()).backOver();
    }
}
