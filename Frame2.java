import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Frame2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
/**528 Joanne在act有改東西喔(你們看完就可以刪掉了~)*/
public class Frame2 extends World
{
    public ImgScroll scroller;
    public int[] var = new int[9];
    public boolean[] chat = new boolean[7];//0:storeOwner, 1:FortuneTeller, 2:Paul, 3:Wilson, 4:soldier, 5:citizen, 6:PassBox
    //private OnNPCs onit = new OnNPCs(1);
    
    private Run2 run;
    private StoreOwner s;
    private FortuneTeller f;
    private Paul p;
    private Wilson w;
    private Branch2[] Br = new Branch2[3];
    public Ron_live ron_live;
    //要修改ed的hm以記錄有幾份日記可以看
    private EnterDiary ed;
    private Notify notify;
    private PassBox box;
    private Puzzle_f2[] pf = new Puzzle_f2[2];
    
    
    private F2Box Sbox = new F2Box(2);
    private F2Box Fbox = new F2Box(3);
    private F2Box Pbox = new F2Box(4);
    private F2Box Wbox = new F2Box(5);
    private F2Box BRbox = new F2Box(6);
    private F2Box Mbox = new F2Box(7);
     
    //private Shield shield = new Shield();
    //private Lives lives = new Lives();
    //private Gun gun = new Gun();
    //private boolean pick = false;
    //private boolean hasadd = false;
    //private int checkLife = 0;
    private int getDiary = 0;
    
    public boolean pause = false;
    public boolean haveShow = false;
    private boolean getP0 = false;
    private boolean getP1 = false;
    //暫時
    public int down = 0;
    public Frame2(int var1[])
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 500, 1, false);
        scroller = new ImgScroll(this, new GreenfootImage("map.f2.png"),2357 ,2930 );
        //0:world 1:coin 2:gun 3: knife 4:sword 5:shield 6:life 7:puzzle 8:diary
        for(int i=0; i<9; i++) var[i] = var1[i];
        for(int i=0; i<7; i++) chat[i] = false;
        prepare();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */

    private void prepare()
    {
        //物件實體化
        s = new StoreOwner();
        f = new FortuneTeller();
        p = new Paul();
        w = new Wilson();
        for(int i=0; i<3; i++)  Br[i] = new Branch2(i);
        ed = new EnterDiary();
        ron_live = new Ron_live();
        run = new Run2();
        box = new PassBox();
        for(int i=0; i<2; i++)  pf[i] = new Puzzle_f2();
        
        addObject(pf[0], 784, 937);
        addObject(pf[1], 1200, 2700);
        addObstacle();
        addObject(f, 661, 2250);
        addObject(p, 258, 2784);
        addObject(s, 1920, 1466);
        addObject(w, 542, 1356);
        
        addObject(Br[0], 1823, 2800);
        addObject(Br[1], 1056, 455);
        addObject(Br[2], 175, 2166);
        addObject(box, 2067, 420);
        
        addObject(ed, 44, 83);
        addObject(ron_live, 70, 30);
        addObject(run, 1200, 1200);
        scroller.scroll(getWidth()/2-run.getX(), getHeight()/2-run.getY());
    }
    public void act(){
        scroller.scroll(getWidth()/2-run.getX(), getHeight()/2-run.getY());
        chatBox();
        BrBox();
        pause();
        goFindPuzzle();
        //528 Joanne有改
        if(var[8]<10) ed.hm[0] = var[8];
        else if(var[8]>=10 && var[8]<20){
            ed.hm[0] = 10;
            ed.hm[1] = var[8]-10; 
        }else if(var[8]>=20){
            ed.hm[0] = 10;
            ed.hm[1] = 10;
            ed.hm[2] = var[8]-20;
        }
        //作弊用的快捷鍵:P
        if(Greenfoot.isKeyDown("shift") && Greenfoot.isKeyDown("p") ){
            var[7] = 6;
            var[0] = 3;
            Greenfoot.setWorld(new Menu(var)); 
        }
        //Passing F2
        if(var[7] >= 6 && box.touchRun2() && chat[6]){
            var[7] = 6;
            var[0] = 3;
            Greenfoot.setWorld(new Menu(var)); 
        }
    }
    public void pause(){
        notify = new Notify();
        if(Sbox.pause_R || Fbox.pause_R || Pbox.pause_R || Wbox.pause_R || BRbox.pause_R || ed.pause_R) pause = true;
        else pause = false;
        if(pause)      run.pause = true;
        else           run.pause = false;
        if(pause && !haveShow){
            addObject(notify, 400, 470);
            notify.showText(11);
            haveShow = true;
        }
        if(!pause && haveShow){
            addObject(notify, 400, 470);
            notify.showText(12);
            haveShow = false; 
        } 
    }
    public void chatBox(){
        if(run.getSomeone() == 2){
            addObject(Sbox, 400, 470);
            Sbox.SshowText();
            if(Sbox.choose == 0 && !chat[0]){
                var[7]++;
                var[8]++;
                chat[0] = true;
            }
            else if(Sbox.choose == 1 && !chat[0])   chat[0] = true;
        }else if(run.getSomeone() == 3){
            addObject(Fbox, 400, 470);
            Fbox.FshowText();
            if(Fbox.choose == -2 && !chat[1]){
                var[7]++;
                var[8]++;
                chat[1] = true;
            }
            else if(Fbox.choose == -3 && !chat[1])   chat[1] = true;
        }else if(run.getSomeone() == 4){
            addObject(Pbox, 400, 470);
            Pbox.PshowText();
            if(Pbox.choose == 0 && !chat[2]){
                var[7]++;
                var[8] = var[8] + 2;
                chat[2] = true;
            }
        }else if(run.getSomeone() == 5){
            addObject(Wbox, 400, 470);
            Wbox.WshowText();
            if(Wbox.choose == 0)                    w.setLocation(w.getX()-270, w.getY()-870);
            if(BRbox.openW3 && Wbox.choose == 1)    Wbox.choose = -2;
            if(Wbox.choose == -2 && !chat[3]){
                //addObject(notify, 400, 420);
                //notify.showText(3);
                var[8]++;
                chat[3] = true;
            }            
        }
        Sbox.reset(run.getSomeone());
        Fbox.reset(run.getSomeone());
        Pbox.reset(run.getSomeone());
        Wbox.reset(run.getSomeone());
    }
    public void BrBox(){
        if(run.getSomeone() == 6){
            if(Br[0].touchRun2()){
                addObject(BRbox, 400, 470);
                BRbox.BRshowText(0);
                if(BRbox.spaceClicked >= 16 && !chat[4]){
                    var[8]++;
                    chat[4] = true;
                }
            }
            else if(Br[1].touchRun2()){
                addObject(BRbox, 400, 470);
                BRbox.BRshowText(1);
                if(BRbox.spaceClicked >= 14 && !chat[5]){
                    var[8]++;
                    chat[5] = true;
                }
            }
            else{
                addObject(BRbox, 400, 470);
                BRbox.BRshowText(2);
            }
        }
        BRbox.reset(run.getSomeone());
    }
    //Pass F2
    //active puzzle
    public void goFindPuzzle(){
        if(Sbox.choose == 1){
            pf[0].getImage().setTransparency(255);
            getP0 = true;
        }
        if(pf[0].getWorld() != null && getP0)   pf[0].getPuzzle();
        if(Fbox.choose == -3){
            pf[1].getImage().setTransparency(255);
            getP1 = true;
        }
        if(pf[1].getWorld() != null && getP1)   pf[1].getPuzzle();
    }
    //return the state of NPCs
    public boolean npcState(){
        if(chat[0] && chat[1] && chat[2])    return true;
        return false;
    }
    //把目前的參數傳到run裡面
    public int[] returnvar(){
        return var;
    }
    //remove icon
    /*public void removeicon(int i){
        if(i == 2) removeObject(gun1);
        if(i == 3) removeObject(knife1);
        if(i == 4) removeObject(sword1);
        if(i == 5) removeObject(shield1);
    }*/
    //Obstacles
    private House[] H1 = new House[4];
    private House H2 = new House(15);
    private House[] H3 = new House[2];
    private House H4 = new House(17);
    private House H5 = new House(18);
    private House H6 = new House(19); //Store
    private House H7 = new House(20); //Church
    private House H8 = new House(21); //Castle
    private Clock clock = new Clock();
    private Statue[] S = new Statue[3];
    private Tree[] T1 = new Tree[8];
    private Tree[] T2 = new Tree[6];
    private Fountain F = new Fountain(2);
    public void addObstacle(){
        for(int i=0; i<8; i++)  T1[i] = new Tree(12);
        for(int i=0; i<4; i++)  H1[i] = new House(14);
        for(int i=0; i<2; i++)  H3[i] = new House(16);
        for(int i=0; i<3; i++)  S[i] = new Statue(i+2);
        for(int i=0; i<6; i++)  T2[i] = new Tree(13);
        //Trees
        addObject(T1[0], 1327, 1714);
        addObject(T1[1], 1514, 1824);
        addObject(T1[2], 695, 1504);
        addObject(T1[3], 678, 293);
        addObject(T1[4], 702, 845);
        addObject(T1[6], 1022, 848);
        addObject(T1[5], 867, 906);
        addObject(T1[7], 359, 2067);
        addObject(T2[0], 657, 2144);
        addObject(T2[1], 981, 2198);
        addObject(T2[2], 803, 2166);
        addObject(T2[3], 661, 2341);
        addObject(T2[4], 877, 2281);
        addObject(T2[5], 1069, 2348);
        //Houses
        addObject(H1[0], 2089, 1636);
        addObject(H1[1], 1814, 1636);
        addObject(H1[2], 827, 1631);
        addObject(H1[3], 261, 1638);
        addObject(H3[0], 262, 200);
        addObject(H3[1], 940, 189);
        addObject(F, 729, 1081);
        addObject(clock, 871, 625);
        addObject(S[0], 1178, 1297);
        addObject(H6, 1831, 1180);
        addObject(H7, 2089, 610);
        addObject(H2, 1699, 156);
        addObject(S[1], 1651, 534);
        addObject(S[2], 1746, 534);
        addObject(H4, 264, 949);
        addObject(H8, 1753, 2278);
        addObject(H5, 265, 2546);
    }
}
