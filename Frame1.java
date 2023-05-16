import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Frame1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Frame1 extends World
{
    public ImgScroll scroller;
    public int[] var = new int[9];
    public boolean[] chat = new boolean[15];
    public boolean[] check = new boolean[9]; //for HouseW
    public boolean haveWin = false;
    public Run1 run;
    
    private Brick[] brick = new Brick[60];
    
    private OldMan o;
    private Baker b; //chat[0]
    private Beggar a; //chat[1] //chat[3] = 是否有在Battle贏
    private Villager v; //chat[2]
    private Branch[] br = new Branch[7]; //chat[4~10]
    private BranchNoWord[] br_N = new BranchNoWord[9]; //chat[11~14]
    private Riddle[] riddle = new Riddle[8];
    {
        for(int i = 0; i<8; i++) riddle[i] = new Riddle(1, i+6);
    }
    
    public int temp = -1;
    private F1Box[] BRbox = new F1Box[7];
    private F1Box Obox = new F1Box(2, -1);
    private F1Box Bbox = new F1Box(3, -1);
    private F1Box Abox = new F1Box(4, -1);
    private F1Box Vbox = new F1Box(5, -1);
    
    public Ron_live ron_live;
    
    private boolean pick = false;
    private boolean hasadd = false;
    
    private ArrayList<NPC> npc = new ArrayList<NPC>(10);
    private ArrayList<Police_live> police_live = new ArrayList<Police_live>(10);
    
    private gun1 gun1 = new gun1();
    private shield1 shield1 = new shield1();
    private live1 live1 = new live1();
    
    //要修改ed的hm以記錄有幾份日記可以看
    private EnterDiary ed;
    
    private int checkLife = 0;
    private int getDiary = 0;
    
    public boolean pause = false;
    /**
     * Constructor for objects of class Frame1.
     * 
     */
    public Frame1(int var1[], boolean chat1[], boolean check1[])
    {    
        super(800, 500, 1, false);
        scroller = new ImgScroll(this, new GreenfootImage("map.f1.png"), 2940 ,3614 );
        //0:world 1:coin 2:gun 3: x 4:y 5:shield 6:life 7:puzzle 8:diary
        for(int i=0; i<9; i++) var[i] = var1[i];
        checkLife = var[6];
        for(int i=0; i<15; i++) chat[i] = chat1[i];
        if(chat[2]) Vbox.choose = -2;
        for(int i=0; i<9; i++) check[i] = check1[i];
        
        prepare();
    }
    private void prepare(){       
        /*addObject(shield,862,600);
        addObject(lives,200,523);
        addObject(gun,507,74);
        addObject(reel,42,489);*/
        //物件實體化
        run = new Run1();
        ron_live = new Ron_live();
        o = new OldMan();
        b = new Baker();
        a = new Beggar();
        v = new Villager();
        ed = new EnterDiary();
        addObject(o,1620,1050);
        addObject(b, 1993, 691);
        addObject(a, 74, 1327);
        addObject(v, 428, 466);
        for(int i=0; i<7; i++){
            if(i==5) ;
            else{
                br[i] = new Branch(i);
                BRbox[i] = new F1Box(1, i);
            }
        }
        addObject(br[0], 1990, 2886); //chat[4]
        addObject(br[1], 406, 2279); //chat[5]
        addObject(br[2], 270, 3019); //chat[6]
        addObject(br[3], 2767, 2346); //chat[7]
        addObject(br[4], 1828, 2319); //chat[8]
        // addObject(br[5], 1704, 3397); //chat[9] chat[10]->看是否有贏
        
        for(int i=0; i<60; i++){
            brick[i] = new Brick();
            addObject(brick[i] , 24+i*49, 2490);
        }
        
        for(int i=0; i<9; i++){
            br_N[i] = new BranchNoWord(i);
        }
        addObject(br_N[1], 974, 1406);
        addObject(br_N[2], 972, 2187); //chat[11]
        addObject(br_N[3], 1469, 1762);
        addObject(br_N[4], 2675, 1476); // chat[12]
        addObject(br_N[5], 1501, 2459); //chat[13]
        addObject(br_N[6], 1068, 895); //paper under clock
        addObject(br_N[7], 1188, 2914); //chat[14] door to pass
        
        addObject(run , 1470, 1050);
        //暫時
        // addObject(run , 1300, 2914);
        
        addObstacle();
        
        addObject(ron_live, 70, 30);
        
        addObject(ed, 44, 83);
        
        scroller.scroll(getWidth()/2-run.getX(), getHeight()/2-run.getY());
    }
    public void act(){
        // System.out.print(var[7]);
        // System.out.println(var[8]);
        scroller.scroll(getWidth()/2-run.getX(), getHeight()/2-run.getY());
        forBranch();
        chatBox();
        pause();
        noti();
        diary();
        
        if(chat[1] && chat[3] && !haveWin){
            var[3]+=5;
            var[7]++;
            var[8]++;
            WH = 15;
            haveWin = true;
        }
        if(var[7] >= 3 && chat[14] && br_N[7].touchRun()){
            var[7] = 3;
            var[0] = 2;
            Greenfoot.setWorld(new Menu(var)); 
        }
        //快捷鍵
        if(Greenfoot.isKeyDown("shift") && Greenfoot.isKeyDown("p")){
            var[7] = 3;
            var[0] = 2;
            Greenfoot.setWorld(new Menu(var));
        }
    }
    private Notify notify = new Notify();
    private boolean haveShow = false;
    private int which = -1;
    private int WH = -1;
    private int temp_WH = -1;
    public void pause(){
        if(BRbox[0].pause_R || BRbox[1].pause_R || BRbox[2].pause_R || 
            BRbox[3].pause_R || BRbox[4].pause_R || /*BRbox[5].pause_R ||*/ 
            BRbox[6].pause_R || Obox.pause_R || Bbox.pause_R ||
            Abox.pause_R || Vbox.pause_R || ed.pause_R) pause = true;
        else pause = false;
        if(pause) run.pause = true;
        else run.pause = false; 
    }
    public boolean gotoBattle_R(){
        return(Abox.gotoBattle);
    }
    public void noti(){
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
        if(!notify.inWorld && temp_WH>-1){
            WH=temp_WH;
            temp_WH=-1;
        }
        if(WH!=-1){
            addObject(notify, 400, 470);
            notify.showText(WH);
            if(WH==0 || WH==15) temp_WH = 3;
            else WH = -1;
        }
    }
    private String[] ans_IN1 = {"2", "4", "8", "5"}; //lock
    private String[] ans_IN2 = {"2", "3", "1", "7"}; //treasure box
    private String[] ans_IN3 = {"3", "6", "9", "8"}; //door to pass
    public void forBranch(){
        //villager and puzzle
        if(chat[2] && !check[7]){
            addObject(br_N[8], house3_2.getX()+12, house3_2.getY()+147);
            check[7] = true;
        }
        if(br_N[8].getWorld()!=null && br_N[8].touchRun()){
            removeObject(br_N[8]);
            var[7]++;
            var[8]++;
            WH = 15;
        }
        //priest1
        if(chat[4]) addObject(br[6], 630+br[0].getX(), 297+br[0].getY());
        //storyMan
        if(chat[6] && (Math.abs(run.getX()-br[2].getX())>100 &&  Math.abs(run.getY()-br[2].getY())>100))
            addObject(br_N[0], 59+br[2].getX(), -54+br[2].getY());
        //stroyMan-door
        if(br_N[0].getWorld()!=null && br_N[0].touchRun()) Greenfoot.setWorld(new HouseW(var, chat, check));
        //paper under clock
        if(br_N[6].touchRun()) addObject(riddle[0], 400, 250);
        else if(riddle[0].getWorld()!=null) removeObject(riddle[0]);
        //book under statue
        if(br_N[3].touchRun()) addObject(riddle[7], 400, 250);
        else if(riddle[7].getWorld()!=null) removeObject(riddle[7]);
        //craze
        if(br_N[1].touchRun()) addObject(riddle[2], 400, 250);
        else if(riddle[2].getWorld()!=null) removeObject(riddle[2]);
        //lock-brick
        if(br_N[5].getWorld()!=null && br_N[5].touchRun() && !chat[13]){
            addObject(riddle[1], 400, 250);
            riddle[1].answers = ans_IN1;
            riddle[1].ans_num = 4;
        }else if(riddle[1].getWorld()!=null) riddle[1].removeAll_IN();
        if(riddle[1].ifRight){
            chat[13] = true;
            riddle[1].ifRight = false;
            WH = 13;
        }
        if(brick[0].getWorld()!=null && chat[13]){
            br_N[5].img = new GreenfootImage("NPC/unlock.png");
            br_N[5].countDown = 70;
            removeObjects(getObjects(Brick.class));
        }
        //child ->var[1]
        if(br[3].touchBall()) chat[7] = true;
        if(chat[7] && !check[8]){
            check[8] = true;
            BRbox[3].choose=0;
            ball.isTouch = true;
            ball.changeLocation(br[3].getX(), br[3].getY());
            WH = 2;
            var[1]+=3;
        }
        //lady ->var[8]
        if(BRbox[4].riddle7.getWorld()!=null && run.getSomeone()==0){
            BRbox[4].riddle7.removeAll_IN();
            /*if(BRbox[4].riddle7.key>0)
                for(int i=BRbox[4].riddle7.key; i>0; i--)
                    removeObject(BRbox[4].riddle7.keys[i-1]);
            removeObject(BRbox[4].riddle7);*/
        }
        if(BRbox[4].riddle7.ifRight){
            var[8]++;
            WH = 0;
            chat[8] = true;
            BRbox[4].riddle7.ifRight = false;
        }
        //treasure box ->var[8]
        if(br_N[2].touchRun() && !chat[11]){
            addObject(riddle[3], 400, 250);
            riddle[3].answers = ans_IN2;
            riddle[3].ans_num = 4;
        }else if(riddle[3].getWorld()!=null) riddle[3].removeAll_IN();
        if(br_N[2].touchRun() && chat[11]) addObject(riddle[4], 400, 250);
        else if(riddle[4].getWorld()!=null) removeObject(riddle[4]);
        if(riddle[3].ifRight){
            chat[11] = true;
            riddle[3].ifRight = false;
            var[8]+=1;
            WH = 0;
        }
        //paper on the grass ->var[8]
        if(br_N[4].getWorld()!=null && br_N[4].touchRun() && !chat[12]){
            addObject(riddle[5], 400, 250);
            riddle[5].riddle(3);
            if(riddle[5].choose!=-1){
                WH = riddle[5].choose;
                chat[12] = true;
                if(riddle[5].choose==0) var[8]++;
            }
        }else if(riddle[5].getWorld()!=null) riddle[5].removeAll();
        if(br_N[4].getWorld()!=null && !br_N[4].touchRun() && chat[12]){
            br_N[4].removeOnit();
            removeObject(br_N[4]);
        }
        //door to pass
        if(br_N[7].touchRun() && !chat[14]){
            addObject(riddle[6], 400, 250);
            riddle[6].answers = ans_IN3;
            riddle[6].ans_num = 4;
        }else if(riddle[6].getWorld()!=null) riddle[6].removeAll_IN();
        if(riddle[6].ifRight){
            chat[14] = true;
            riddle[6].ifRight = false;
            WH = 14;
        }
        else if(chat[14] && var[7]<3 && br_N[7].touchRun()) WH = 16;
        //scaverger
        /*if((BRbox[5].spaceClicked==3 && !chat[9]) || (BRbox[5].spaceClicked==1 && chat[9]))
            ron_live.minuslife();*/
    }
    public int start;
    public void chatBox(){
        start = 0;
        if(run.getSomeone() == 1){
            for(temp=0; temp<7; temp++){
                if(temp==5) ;
                else if(br[temp].getWorld()!=null && br[temp].touchRun()){
                    addObject(BRbox[temp], 400, 470);
                    if(temp==1){
                        if(chat[5]) start = 0;
                        else start = -2;
                    }else if(temp==3){
                        if(chat[7]) start = 4;
                        else start = 1;
                    }else if(temp==4){
                        if(chat[8]) start = 8;
                        else start = 6;
                    }else if(temp==5){
                        if(chat[9]) start = 17;
                        else start = 10;
                    }
                    BRbox[temp].BRshowText(start, chat[temp+4]);
                    break;
                }
            }
            riddle(0);
            riddle(1);
            if(temp==2 && !chat[6]) chat[6] = true;
        }else if(run.getSomeone() == 2){
            addObject(Obox, 400, 470);
            Obox.OshowText();
        }else if(run.getSomeone() == 3){
            addObject(Bbox, 400, 470);
            Bbox.BshowText();
            if(Bbox.spaceClicked==6 && !chat[0]){
                var[3]+=5;
                var[8]++;                
                var[7]++;
                chat[0] = true;
                WH = 15;
            }
        }else if(run.getSomeone() == 4){
            addObject(Abox, 400, 470);
            Abox.AshowText();
            if(Abox.spaceClicked==3 && !chat[1]) chat[1] = true;
        }else if(run.getSomeone() == 5){
            addObject(Vbox, 400, 470);
            Vbox.VshowText();
            if(Vbox.spaceClicked==4 && !chat[2]){
                if(Vbox.choose==0){
                    var[1]-=3;
                    var[8]++;
                    var[7]++;
                    check[7] = true;
                    WH = 15;
                }
                chat[2] = true;
            }
        }
        Obox.reset(run.getSomeone());
        Bbox.reset(run.getSomeone());
        Abox.reset(run.getSomeone());
        Vbox.reset(run.getSomeone());
        
        if(temp>-1 && run.getSomeone()<1){
            BRbox[temp].reset(run.getSomeone());
            temp=-1;
        }
    }
    public void riddle(int wh){
        if(temp==wh && !BRbox[temp].inWorld_R && BRbox[temp].choose!=-1 && !chat[temp+4]){
            if(BRbox[temp].choose == 0){
                var[1]+=5;
                var[8]++;
            }
            BRbox[temp].choose=-1;
            chat[temp+4] = true;
        }
    }
    public void diary(){
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
    }
    //把目前的參數傳到run裡面
    public int[] returnvar(){
        return var;
    }
    /*public int returncoins(){
        return var[1];
    }
    public int returnworld(){
        return var[0];
    }
    public int returngun(){
        return var[2];
    }
    public int returnknife(){
        return var[3];
    }
    public int returnsword(){
        return var[4];
    }
    public int returnshield(){
        return var[5];
    }
    public int returnlive(){
        return var[6];
    }
    public int returnpuzzle(){
        return var[7];
    }
    public int returndiary(){
        return var[8];
    }*/

    
    private Ball ball = new Ball();
    //這些就是障礙物
    private Clock clock = new Clock();
    private Fountain fountain = new Fountain(1);
    private Statue statue = new Statue(1);
    private Church church = new Church();
    private Plants plants_1 = new Plants();
    private Plants plants_2 = new Plants();
    private Statue small_statue = new Statue(0);
    private House house1 = new House(1);
    private House house2 = new House(2);
    private House house2_o = new House(0);
    private House house3_1 = new House(3);
    private House house3_2 = new House(3);
    private House house4_1 = new House(4);
    private House house4_2 = new House(4);
    private Tree tree1_1 = new Tree(1);
    private Tree tree1_2 = new Tree(1);
    private Tree tree2 = new Tree(2);   
    private Tree[] tree3 = new Tree[8];   
    private Tree[] tree4 = new Tree[7];
    private Tree[] tree5_1 = new Tree[5];
    private Tree[] tree5_2 = new Tree[5];
    private Tree tree5 = new Tree(5);
    private Tree[] tree6 = new Tree[10];
    public void addObstacle(){
        addObject(ball, 1304, 419);
        
        addObject(clock,1090,826);
        addObject(fountain, 318, 2033);
        addObject(statue, 1476, 1661);
        addObject(church, 2617, 2585);
        addObject(plants_1, 164, 741);
        addObject(plants_2, 165, 890);
        addObject(small_statue, 166, 827);
        addObject(house1, 2149, 290);
        addObject(house2, 1035, 2647);
        addObject(house2_o, 1818, 2647);
        addObject(house3_1, 328, 2663);
        addObject(house3_2, 1152, 3100);
        addObject(house4_1, 318, 182);
        addObject(house4_2, 314, 1180);
        addObject(tree1_1, 1051, 528);
        addObject(tree1_2, 1810, 2168);
        addObject(tree2, 1008, 2186);       
        for(int i = 0; i < 8; i++){
            tree3[i] = new Tree(3);
        }
        addObject(tree3[0], 2011, 1452);
        addObject(tree3[1], 1691, 1211);
        addObject(tree3[2], 2050, 1211);
        addObject(tree3[3], 850, 171);
        addObject(tree3[4], 1250, 411);
        addObject(tree3[5], 810, 531);
        addObject(tree3[6], 850, 1171);
        addObject(tree3[7], 890, 1451);       
        for(int i = 0; i < 7; i++){
            tree4[i] = new Tree(4);
        }
        addObject(tree4[0], 1310, 142);
        addObject(tree4[1], 1270, 1222);
        addObject(tree4[2], 1990, 1942);
        addObject(tree4[3], 1790, 2342);
        addObject(tree4[4], 2470, 1382);
        addObject(tree4[5], 2750, 1142);
        addObject(tree4[6], 2470, 1022);
        for(int i = 0; i < 5; i++){
            tree5_1[i] = new Tree(5);
            addObject(tree5_1[i], 310+160*i, 1708);
        }
        for(int i = 0; i < 5; i++){
            tree5_2[i] = new Tree(5);
            addObject(tree5_2[i], 1990+160*i, 1708);
        }
        addObject(tree5, 1310, 548);
        for(int i = 0; i < 10; i++){
            tree6[i] = new Tree(6);
        }
        addObject(tree6[0], 830, 2342);
        addObject(tree6[1], 1230, 2342);
        addObject(tree6[2], 1310, 2142);
        addObject(tree6[3], 870, 1942);
        addObject(tree6[4], 2470, 2262);
        addObject(tree6[5], 2750, 2022);
        addObject(tree6[6], 2470, 1902);
        addObject(tree6[7], 2070, 2142);
        addObject(tree6[8], 1670, 2142);
        addObject(tree6[9], 2070, 2342);
    }
}