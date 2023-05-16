import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy_live here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy_live extends Actor
{
    private static int live;
    public static int where = 0;
    /**
     * Act - do whatever the Enemy_live wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public Enemy_live()
    {
        live = 0;
        setImage("LIFE/eLive00.png");
    }
    public void act()
    {
        if(live < 0) setImage("LIFE/eLive00.png");
        check();
        getWhere();
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
    public static void minusminuslife(){
        live = live+2;
        if(live>4) live = 4;
    }
    public void addlife(){
        live--;
    }
    public int live(){
        return live;
    }
    public void check(){
        if(((BattleField)getWorld()).whichRun() == 1){
            switch(live){
                case 1:
                    setImage("LIFE/eLive04.png");
                    break;
                case 2:
                    setImage("LIFE/eLive06.png");
                    break;
                case 3:
                    setImage("LIFE/eLive08.png");
                    break;
                case 4:
                    setImage("LIFE/eLive10.png");
                    break;
                case 5:
                    setImage("LIFE/eLive12.png");
                    break;
                case 6:
                    setImage("LIFE/eLive14.png");
                    break;
                case 7:
                    setImage("LIFE/eLive16.png");
                    break;
                case 8:
                    setImage("LIFE/eLive18.png");
                    where = 1;
                    //遊戲結束
                    break;
            }
        }
        else if(((BattleField)getWorld()).whichRun() == 3){
            switch(live){
                case 1:
                    setImage("LIFE/eLive01.png");
                    break;
                case 2:
                    setImage("LIFE/eLive02.png");
                    break;
                case 3:
                    setImage("LIFE/eLive03.png");
                    break;
                case 4:
                    setImage("LIFE/eLive04.png");
                    break;
                case 5:
                    setImage("LIFE/eLive05.png");
                    break;
                case 6:
                    setImage("LIFE/eLive06.png");
                    break;
                case 7:
                    setImage("LIFE/eLive07.png");
                    break;
                case 8:
                    setImage("LIFE/eLive08.png");
                    break;
                case 9:
                    setImage("LIFE/eLive09.png");
                    break;
                case 10:
                    setImage("LIFE/eLive10.png");
                    break;
                case 11:
                    setImage("LIFE/eLive11.png");
                    break;
                case 12:
                    setImage("LIFE/eLive12.png");
                    break;
                case 13:
                    setImage("LIFE/eLive13.png");
                    break;
                case 14:
                    setImage("LIFE/eLive14.png");
                    break;
                case 15:
                    setImage("LIFE/eLive15.png");
                    break;
                case 16:
                    setImage("LIFE/eLive16.png");
                    break;
                case 17:
                    setImage("LIFE/eLive17.png");
                    break;
                case 18:
                    setImage("LIFE/eLive18.png");
                    where = 1;
                    //遊戲結束
                    break;
            }
        }
    }
    public static int returnWhere(){
        return where;
    }
    public void getWhere(){
        where = ((BattleField)getWorld()).backWhere();
    }
}

