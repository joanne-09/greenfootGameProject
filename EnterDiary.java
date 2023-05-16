import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnterDiary here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnterDiary extends Buttons
{
    private Diary d = new Diary();
    private Back4 b = new Back4();
    
    public boolean pause_R = false;
    public int pageNow = 1;
    
    public int[] hm = new int[3];    
    /**
     * Act - do whatever the EnterDiary wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        intoDiary();
        if(!d.inWorldr) goBack();
        else toDM();
        d.showInfo();
        update();
    }
    //進入日期選單
    public void intoDiary(){
        if(Greenfoot.mouseClicked(this)){
            getWorld().addObject(d, 380, 260);
            getWorld().addObject(b, 765, 465);
            d.showText(hm[pageNow-1]);
            pause_R = true;
        }
    }
    //離開日期選單
    public void goBack(){
        if(Greenfoot.mouseClicked(b)){
            d.delText(hm[pageNow-1]);
            getWorld().removeObject(d);
            getWorld().removeObject(b);
            pause_R = false;
        }
    }
    //離開日記內容，進到日期選單
    public void toDM(){
        if(Greenfoot.mouseClicked(b)){
            d.inWorldr = false;
            d.delInfo();
        }
    }
    public void update(){
        if(pageNow != d.pageS){
            d.delText(hm[pageNow-1]);
            pageNow = d.pageS;
            d.showText(hm[pageNow-1]);
        }
    }
    /**
     * overrides the 'setLocation(int, int)' method of the Actor class so the counter cannot be relocated
     * (it removes the implementation provided by the Actor class)
     */
    public void setLocation(int x, int y) {}
}
