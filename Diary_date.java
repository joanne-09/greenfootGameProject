import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Diary_date here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Diary_date extends Buttons
{
    public int date;
    private Diary_page p;
    private Arrow aR = new Arrow(0);
    private Arrow aL = new Arrow(1);
    public Diary_date(int d){
        setText(d);
        date = d;
        p = new Diary_page(date);
    }
    /**
     * Act - do whatever the Diary_date wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        update();
        switchPage();
    }
    public void showDiary(){
        getWorld().addObject(p, 380, 260);
        if(p.pageN+1<p.page+1) getWorld().addObject(aR, 707, 266);
        if(p.pageN-1>-1) getWorld().addObject(aL, 55, 266);
    }
    public void delDiary(){
        getWorld().removeObject(p);
        if(p.pageN-1>-1) getWorld().removeObject(aL);
        if(p.pageN+1<p.page+1) getWorld().removeObject(aR);
    }
    //換頁
    public void switchPage(){
        if(Greenfoot.mouseClicked(aL)) p.pageS--;
        if(Greenfoot.mouseClicked(aR)) p.pageS++;
    }
    public void update(){
        if(p.pageS!=p.pageN){
            if(p.pageN-1>-1) getWorld().removeObject(aL);
            if(p.pageN+1<p.page+1) getWorld().removeObject(aR);
            p.pageN = p.pageS;
            p.setImage(p.img[p.pageN]);
            if(p.pageN+1<p.page+1) getWorld().addObject(aR, 707, 266);
            if(p.pageN-1>-1) getWorld().addObject(aL, 55, 266);
        }
    }
    public void setText(int d){
        if(d == 310)      setImage("DIARY/310.png");
        else if(d == 315) setImage("DIARY/315.png");
        else if(d == 317) setImage("DIARY/317.png");
        else if(d == 318) setImage("DIARY/318.png");
        else if(d == 325) setImage("DIARY/325.png");
        else if(d == 326) setImage("DIARY/326.png");
        else if(d == 327) setImage("DIARY/327.png");
        else if(d == 328) setImage("DIARY/328.png");
        else if(d == 403) setImage("DIARY/403.png");
        else if(d == 404) setImage("DIARY/404.png");
        else if(d == 601) setImage("DIARY/601.png");
        else if(d == 718) setImage("DIARY/718.png");
        else if(d == 720) setImage("DIARY/720.png");
        else if(d == 729) setImage("DIARY/729.png");
        else if(d == 730) setImage("DIARY/730.png");
        else if(d == 731) setImage("DIARY/731.png");
        else if(d == 802) setImage("DIARY/802.png");
        else if(d == 901) setImage("DIARY/901.png");
        else if(d == 5303) setImage("DIARY/5.303.png");
        else if(d == 5311) setImage("DIARY/5.311.png");
        else if(d == 5312) setImage("DIARY/5.312.png");
        else if(d == 5707) setImage("DIARY/5.707.png");
        else if(d == 12111) setImage("DIARY/5.1211-1.png");
        else if(d == 12112) setImage("DIARY/5.1211-2.png");
        else if(d == 12113) setImage("DIARY/5.1211-3.png");
        else if(d == 12114) setImage("DIARY/5.1211-4.png");
        else if(d == 12115) setImage("DIARY/5.1211-5.png");
        else if(d == 070701) setImage("DIARY/page1.png");
        else if(d == 070702) setImage("DIARY/page2.png");
        else if(d == 070703) setImage("DIARY/page3.png");
        else if(d == 070704) setImage("DIARY/page4.png");
        else if(d == 070705) setImage("DIARY/page5.png");
        else if(d == 070706) setImage("DIARY/page6.png");
        else setImage("DIARY/else.png");
    }
    /**
     * overrides the 'setLocation(int, int)' method of the Actor class so the counter cannot be relocated
     * (it removes the implementation provided by the Actor class)
     */
    public void setLocation(int x, int y) {}
}
