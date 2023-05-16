import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;

/**
 * Write a description of class Diary here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Diary extends Actor
{
    private Diary_date[][] text = new Diary_date[3][16];
    private Arrow aR = new Arrow(0);
    private Arrow aL = new Arrow(1);
    
    //看日記內容有沒有在世界上
    public boolean inWorldr = false;
    
    private Diary_page p;
    private int wh = -1; //就只是記錄是哪個日記出現，以方便刪掉而已
    public int page = 1; //第幾頁，一個世界的日記一頁
    public int pageS = 1;
    public Diary(){
        setText();
    }
    /**
     * Act - do whatever the Diary wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        switchPage();
    }
    //日期出現在頁面上
    public void showText(int h){
        if(page+1<4) getWorld().addObject(aR, 707, 266);
        if(page-1>0) getWorld().addObject(aL, 55, 266);
        if(h<=5 && h>0){
            for(int i = 0; i < h; i++)
                getWorld().addObject(text[page-1][i], 220, 105 + 80*i);          
        }else if(h>5 && h<=10){
            for(int i = 0; i < 5; i++)
                getWorld().addObject(text[page-1][i], 220, 105 + 80*i);
            for(int i = 5; i < h; i++)
                getWorld().addObject(text[page-1][i], 542, 105 + 80*(i-5));        
        }
    }
    //刪掉日期們
    public void delText(int h){
        if(page-1>0) getWorld().removeObject(aL);
        if(page+1<4) getWorld().removeObject(aR);
        for(int i = 0; i < 5; i++){
            getWorld().removeObject(text[page-1][i]);
        }
        for(int i = 5; i < 10; i++){
            getWorld().removeObject(text[page-1][i]);
        }
        if(page!=pageS) page = pageS;
    }
    //出現日記內容
    public void showInfo(){
        for(int i = 0; i < 10; i++){
            if(Greenfoot.mouseClicked(text[page-1][i])){
                inWorldr = true;
                text[page-1][i].showDiary();
                wh = i;
            }
        }
        // text[page-1][wh].update();
    }
    //刪掉日記內容
    public void delInfo(){
        text[page-1][wh].delDiary();
        wh = -1;
    }
    //換頁
    public void switchPage(){
        if(Greenfoot.mouseClicked(aL)) pageS--;
        if(Greenfoot.mouseClicked(aR)) pageS++;
    }
    public void setText(){
        text[0][0] = new Diary_date(310);
        text[0][1] = new Diary_date(315);
        text[0][2] = new Diary_date(317);
        text[0][3] = new Diary_date(318);
        text[0][4] = new Diary_date(325);
        text[0][5] = new Diary_date(326);
        text[0][6] = new Diary_date(327);
        text[0][7] = new Diary_date(328);
        text[0][8] = new Diary_date(403);
        text[0][9] = new Diary_date(404);
        
        text[1][0] = new Diary_date(601);
        text[1][1] = new Diary_date(718);
        text[1][2] = new Diary_date(720);
        text[1][3] = new Diary_date(729);
        text[1][4] = new Diary_date(730);
        text[1][5] = new Diary_date(731);
        text[1][6] = new Diary_date(802);
        text[1][7] = new Diary_date(901);
        text[1][8] = new Diary_date(5303);
        text[1][9] = new Diary_date(5311);
        
        text[2][0] = new Diary_date(5312);
        text[2][1] = new Diary_date(5707);
        text[2][2] = new Diary_date(12111);
        text[2][3] = new Diary_date(12112);
        text[2][4] = new Diary_date(12113);
        text[2][5] = new Diary_date(12114);
        text[2][6] = new Diary_date(12115);
        
        text[2][7] = new Diary_date(0);
        text[2][8] = new Diary_date(0);
        text[2][9] = new Diary_date(0);
        
        text[2][10] = new Diary_date(070701);
        text[2][11] = new Diary_date(070702);
        text[2][12] = new Diary_date(070703);
        text[2][13] = new Diary_date(070704);
        text[2][14] = new Diary_date(070705);
        text[2][15] = new Diary_date(070706);
    }
    /**
     * overrides the 'setLocation(int, int)' method of the Actor class so the counter cannot be relocated
     * (it removes the implementation provided by the Actor class)
     */
    public void setLocation(int x, int y) {}
}
