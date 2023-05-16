import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Frame1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Frame3 extends World
{
    String reponse;
    public ImgScroll scroller;
    /*
     * public house real = new real();
     * public boolean c = true;
     * 
      */
    public int[] var = new int[9];
    public boolean[] chat = new boolean[15];
    public boolean[] check = new boolean[10];
    public boolean[] chat1 = new boolean[15];
    public boolean chaveWin = false;
    public boolean haveWin = false;
    public boolean isattack = true;//判斷警察是否需要攻擊
    
    private Run3 run;
    
    public Ron_live ron_live;
   
    //上方圓圓的武器
    private gun1 gun1 = new gun1();
    private shield1 shield1 = new shield1();
    private live1 live1 = new live1();
    
    /*private Shield shield = new Shield();
    private Lives lives = new Lives();
    private Gun gun = new Gun();*/
    
    private boolean havefinish = false;
    private boolean hasadd = false;
    public boolean nobleappear = false;
    //日記
    //要修改ed的hm以記錄有幾份日記可以看
    private EnterDiary ed;
    private int getDiary = 0;
    
    //對話框
    private F3Box Cbox = new F3Box(2);
    private F3Box Nbox = new F3Box(3);
    private F3Box Fbox = new F3Box(4);
    private F3Box Obox = new F3Box(5);
    private F3Box Tbox = new F3Box(6);
    private F3Box Gbox = new F3Box(7);
    private F3Box Jbox = new F3Box(8);
    private F3Box Bbox = new F3Box(9);
    
    
    //talking npc
    private int inNpc = 0;
    private OnNPCs onit = new OnNPCs(inNpc);
    private OnNPCs onit1 = new OnNPCs(inNpc);
    private OnNPCs onit2 = new OnNPCs(1);
    private Carriage carriage;
    private Noble noble;//chat[0] //if(win) chat[1] = true
    private Farmer farmer;//chat[2]
    private The_one to;
    private The_second ts;
    private Talker_A ta;
    private Talker_B tb;
    private Catherine c;//chat[3]
    private Johnson j;//chat[11]
    private Branch3[] br = new Branch3[7]; //chat[4~9]
    
    //not talking npc
    private Real_Carriage rcarriage;
    private Caravan caravan;
    private Black_Ball bball;//check[3]
    private White_Ball wball;//check[1]
    private Green_Ball gball;//check[0]
    private Red_Ball rball;//check[2]
    
    //girl
    private Door_front df;
    private Door_back db;
    private Door_right dr;
    private Door_left dl;
    private Notify notify = new Notify();
    private int x = 0;
    private int y = 0;
    public int ballCount = 0;
    public boolean isOpen = false;
    
    //farmer
    private boolean isfirst = true;
    
    private boolean pick = false;
    private int checkLife = 0;
    public boolean npc_ismove = true;
    
    public boolean pause = false;
    
    //Obstacle
    private Block block = new Block();
    
    //riddle
    private Riddle[] riddle = new Riddle[7];
    {
        for(int i = 0; i<7; i++) riddle[i] = new Riddle(3, i+2);
    }
    
    private ArrayList<NPC> npc = new ArrayList<NPC>(10);
    private ArrayList<Police_live> police_live = new ArrayList<Police_live>(10);
    /**
     * Constructor for objects of class Frame1.
     * 
     */
    public Frame3(int var1[],boolean chat1[], boolean check1[])
    {    
        super(800, 500, 1, false);
        scroller = new ImgScroll(this, new GreenfootImage("map.f3.png"),2512, 3097);
        //0:world 1:coin 2:gun 3: x 4: y  5:shield 6:life 7:puzzle 8:diary
        for(int i=0; i<9; i++) var[i] = var1[i];
        var[0] = 3;
        checkLife = var[6];
        //讓coin有10個
        var[1]+=10;
        for(int i=0; i<12; i++) chat[i] = chat1[i];
        if(chat[1]) nobleappear = true;
        /*
         * branch chat[4]以後都還沒加入判斷
         */
        for(int i=0; i<7; i++) check[i] = check1[i];
        
        for(int i = 0;i<4;i++){
            if(check[i]) ballCount++;
        }
        
        prepare();
        addObject(notify, 400, 470);
        notify.showText(24);
        ron_live.over = -1;
    }
    public void prepare(){
       //obstacle
       //addOnmap();
       addObstacle();
       /*addObject(jail,1230, 1440);
       jail.getImage().setTransparency(0);*/
       //物件實體化
       run = new Run3();
       ron_live = new Ron_live();
       ed = new EnterDiary();
       carriage = new Carriage();
       noble = new Noble();
       farmer = new Farmer();
       to = new The_one();
       ts = new The_second();
       ta = new Talker_A();
       tb = new Talker_B();
       c = new Catherine();
       j = new Johnson();
       
       rcarriage = new Real_Carriage();
       caravan = new Caravan();
       bball = new Black_Ball();
       wball = new White_Ball();
       gball = new Green_Ball();
       rball = new Red_Ball();
       
       df = new Door_front();
       db = new Door_back();
       dr = new Door_right();
       dl = new Door_left();
       //npc
       addObject(noble,1000, 2530); 
       addObject(carriage,800, 2550);
       addObject(rcarriage,960, 2530);
       addObject(farmer,300, 1100);
       addObject(caravan,1500, 2130);
        addObject(to,1600, 2100);
       addObject(ts,1650, 2100);
       addObject(ta, 1360, 1600);
       addObject(tb, 1420, 1600);
       
       //branch
       for(int i=0; i<7; i++){
            br[i] = new Branch3(i);
            //BRbox[i] = new F3Box(1, i);
        }
       addObject(br[0], 1870, 2850); //br[0] 數學詩 chat[4]
       addObject(br[1], 730, 2770); //br[1] 數學題目 chat[5]
       addObject(br[2], 430, 1950); //br[2] 寶晉齋 chat[6]
       addObject(br[3], 1700,550); //美女的側顏 chat[7]
       addObject(br[4], 1700,1200); //一封信 chat[8]
       addObject(br[5], 720,200); //外人的感嘆 chat[9]
     
       addObject(block ,2260, 790);
       block.getImage().setTransparency(0);
       
       if(!check[3]) addObject(bball,1250, 1630);
       if(!check[1]) addObject(wball,1250, 1580);
       if(!check[0]) addObject(gball,1200, 1580);
       if(!check[2]) addObject(rball,1200, 1630);
       addObject(c, 1250, 1450);
       c.getImage().setTransparency(0);
       
       addObject(df,1250, 90);
       addObject(db,1250, 3000);
       addObject(dr,2417, 1467);
       addObject(dl,80, 1468);
    
       
       for (int i=0; i<10; i++){
            npc.add(new NPC(i));
            police_live.add(new Police_live());
            int x = Greenfoot.getRandomNumber(2000);
            int y = Greenfoot.getRandomNumber(2000);
            addObject(npc.get(i), x, y);
            while(npc.get(i).isTouchingObject()){
                x = Greenfoot.getRandomNumber(2000);
                y = Greenfoot.getRandomNumber(2000);
                npc.get(i).setLocation(x, y);
            }
            addObject(police_live.get(i), npc.get(i).getX(), npc.get(i).getY()-45);
        }
        
        //correct location
        addObject(run,1700, 2100);
          
        //addObject(run,1900, 2100);
        //else addObject(run, var[3] , var[4]);
        //noble location
        //addObject(run,1000, 2530);
        //farmer location
        //addObject(run,300, 1100);
        //ball location
        //addObject(run,1100, 1580);
        //johnson
        //addObject(run ,2260, 790);
        //addObject(run,1290, 1630);
        
        //放入商店買的道具
        for(int i = 2;i<7;i++){
            if(var[i] > 0){
                if(i == 2) addObject(gun1, 200, 40);
                else if(i == 5) addObject(shield1, 300, 40);
                else if(i == 6) addObject(live1, 400, 40);
            }
        }
        addObject(ron_live, 70, 30);
        //提示的部分，等到有空再做
        //addObject(hints, 44, 462);
        addObject(ed, 44, 83);
        scroller.scroll(getWidth()/2-run.getX(), getHeight()/2-run.getY());
    }

    public void act(){
        //System.out.print(var[7]);
        scroller.scroll(getWidth()/2-run.getX(), getHeight()/2-run.getY());
        for(int i = 0 ; i< 10 ; i++) 
        {
            if(police_live.get(i).live()<4)
                police_live.get(i).setNewLocation(npc.get(i).getX(), npc.get(i).getY());
        }
        add();
        removelive();
        openOnit();
        chatBox();
        forBranch();
        //battle
        pause();
        if(chat[1] && !chaveWin){
            chaveWin = true;
            var[1]+=5;
            var[7]++;
            var[8]++;
            //System.out.print(var[7]);
        }
       
        //diary
        if(var[8]<10) ed.hm[0] = var[8];
        else if(var[8]>=10 && var[8]<20){
            ed.hm[0] = 10;
            ed.hm[1] = var[8]-10; 
        }else if(var[8]>=20){
            ed.hm[0] = 10;
            ed.hm[1] = 10;
            ed.hm[2] = var[8]-20;
        }
        
        //girl
        opendoor();
        if(var[7] == 9 /*||(chat[1] && chat[2] && chat[3])*/){
            //System.out.print(var[7]);
            //System.out.print(ballCount);
        //0:world 1:coin 2:gun 3: knife 4:sword 5:shield 6:life 7:puzzle 8:diary
            havefinish = true;//完成所有的劇情
        }
        //System.out.print(var[7]);
        if(havefinish){
            addObject(j ,block.getX(), block.getY());
        }
        //快捷鍵
        if(Greenfoot.isKeyDown("shift") && Greenfoot.isKeyDown("p")){
            //通關
            var[7] = 9;
            var[0] = 4;
            Greenfoot.setWorld(new End(0,var,true));
        }
        
        //die
        if(ron_live.over == 1){
            var[7] = 6;
            var[8] = 0;
            var[0] = 3;
            Greenfoot.setWorld(new Menu(var));
        }else if(var[1] <= 0){
            var[1] = 0;
            var[7] = 6;
            var[8] = 0;
            var[0] = 3;
            Greenfoot.setWorld(new Menu(var));
        }
    }
    private String[] ans_IN1 = {"7"}; //math question
    private String[] ans_IN2 = {"1", "0", "9"}; //math question
    private int[] ans_IN3 = {7, 9, 10}; //math question
    private int spaceClicked = 0;
    private int riddlenum = 2;
    public boolean ri = false; //finish riddle or not
    private boolean finishriddle = false;
    public boolean isDown = false;
    public void forBranch(){
        //math poem
        if(br[0].touchRun() && !chat1[4]){
            isattack = false;
            addObject(riddle[0], 400, 220);
            riddle[0].answers = ans_IN1;
            riddle[0].ans_num = 1;
        }else if(riddle[0].getWorld()!=null){
            //removeObject(riddle[0]);
            riddle[0].removeAll_IN();
        }
        if(riddle[0].ifRight && riddle[0].getWorld()!=null){
            addObject(notify, 400, 470);
            notify.showText(17);
            riddle[0].ifRight = false;
            if(chat[4] == false){
                var[1]+=4;
                var[8]++;
            }
            chat[4] = true;
            chat1[4] = true;
            isattack = false;
        }
        //math question
        if(br[1].touchRun() && !chat1[5]){
            isattack = false;
            addObject(riddle[1], 400, 220);
            riddle[1].answers = ans_IN2;
            riddle[1].ans_num = 3;
        }else if(riddle[1].getWorld()!=null){
            //removeObject(riddle[1]);
            riddle[1].removeAll_IN();
        }
        if(riddle[1].ifRight && riddle[1].getWorld()!=null){
            addObject(notify, 400, 470);
            notify.showText(17);
            riddle[1].ifRight = false;
            if(chat[5] == false) var[1]+=4;
            chat[5] = true;
            chat1[5] = true;
            isattack = false;
        }
        //寶晉齋
        if(br[2].getWorld()!=null && br[2].touchRun()  && !chat[6]){
            isattack = false;
            addObject(riddle[2], 400, 260);
            if(Greenfoot.mouseClicked(riddle[2]) && !isDown) {
                spaceClicked++;
                isDown = true;          
            }
            if(spaceClicked == -1) ;
            else if(spaceClicked > 4 && ri){
                spaceClicked = -1;
                removeObject(riddle[2]);
                if(!chat[6]) var[8]++;
                chat[6] = true;
                isattack = true;
            }else{
                if(spaceClicked == 3 && !ri){
                    riddle[2].answer = ans_IN3;
                    riddle[2].riddleMutiple();
                    //isDown = false;
                }else if(isDown){
                    riddle[2].setImage(riddlenum);
                    riddlenum++;
                    isDown = false;
                }
            }
        }else if(riddle[2].getWorld()!=null){
            removeObject(riddle[2]);
            for(int i=0; i<10; i++) removeObject(riddle[2].ans3[i]);
        }
        //美女的側顏
        if(br[3].touchRun()){
            isattack = false;
            addObject(riddle[3], 400, 240);
            if(!chat[7]) var[8]++;
            chat[7] = true;
        }else if(riddle[3].getWorld()!=null){
            removeObject(riddle[3]);
            isattack = true;
        }
        //letter
        if(br[4].touchRun()){
            isattack = false;
            addObject(riddle[4], 400, 240);
            chat[8] = true;
        }else if(riddle[4].getWorld()!=null) {
            removeObject(riddle[4]);
            isattack = true;
        }

        if(!br[0].touchRun()){
            chat1[4] = false;
            riddle[0].repeat = -1;
        }
        if(!br[1].touchRun()){
            chat1[5] = false;
            riddle[1].repeat = -1;
        }
    }
    //battle
    public void pause(){
        if(Cbox.pause_R || Nbox.pause_R ||
            Fbox.pause_R || Obox.pause_R || Tbox.pause_R || Gbox.pause_R || Jbox.pause_R || ed.pause_R) pause = true;
        else pause = false;
        if(pause) run.pause = true;
        else run.pause = false;
    }
    public boolean JgotoBattle_R(){
        return(Jbox.gotoBattle);
    }
    public boolean NgotoBattle_R(){
        return(Nbox.ngotoBattle);
    }
    //對話框
    public void openOnit(){
        isattack = false;
        if(run.openOnNPCs() == 2){
            addObject(onit, carriage.getX(), carriage.getY()-42);
            onit.setNewLocation(carriage.getX(), carriage.getY());           
        }else if(run.openOnNPCs() == 3){
            if(nobleappear){
                addObject(onit, noble.getX(), noble.getY()-42);
                onit.setNewLocation(noble.getX(), noble.getY());    
            }    
        }else if(run.openOnNPCs() == 4){
            addObject(onit, farmer.getX(), farmer.getY()-42);
            onit.setNewLocation(farmer.getX(), farmer.getY());           
        }else if(run.openOnNPCs() == 5){
            addObject(onit, to.getX(), to.getY()-42);
            addObject(onit1, ts.getX(), ts.getY()-42);
            onit.setNewLocation(to.getX(), to.getY());
            onit1.setNewLocation(ts.getX(), ts.getY());
        }else if(run.openOnNPCs() == 6){
            addObject(onit, ta.getX(), ta.getY()-42);
            addObject(onit1, tb.getX(), tb.getY()-42);
            onit.setNewLocation(ta.getX(), ta.getY());
            onit1.setNewLocation(tb.getX(), tb.getY());
        }else if(run.openOnNPCs() == 7){
            addObject(onit, c.getX(), c.getY()-42);
            onit.setNewLocation(c.getX(), c.getY());
        }else if(run.openOnNPCs() == 8){
            addObject(onit, j.getX(), j.getY()-42);
            onit.setNewLocation(j.getX(), j.getY());
        }else if(run.openOnNPCs() == 9){
            addObject(onit2, br[5].getX(), br[5].getY()-42);
            onit2.setNewLocation(br[5].getX(), br[5].getY());
        }
        else{
            removeObject(onit);
            removeObject(onit1);
            isattack = true;
        }
    }
    
    public void chatBox(){
        //可以控制日記的數量
        if(run.getSomeone() == 2){
            addObject(Cbox, 400, 470);
            Cbox.CshowText();
        }else if(run.getSomeone() == 3){
            if(nobleappear){
                addObject(Nbox, 400, 470);
                Nbox.NshowText();
            }
        }else if(run.getSomeone() == 4){
            addObject(Fbox, 400, 470);
            Fbox.FshowText();
            riddle();
        }else if(run.getSomeone() == 5){
            addObject(Obox, 400, 470);
            Obox.OshowText();
        }else if(run.getSomeone() == 6){
            addObject(Tbox, 400, 470);
            Tbox.TshowText();
        }else if(run.getSomeone() == 7){
            addObject(Gbox, 400, 470);
            Gbox.GshowText();
            riddle();
        }else if(run.getSomeone() == 8){
            addObject(Jbox, 400, 470);
            Jbox.JshowText();
        }else if(run.getSomeone() == 9){
            if(br[5].btouchRun()){
                isattack = false;
                addObject(Bbox, 400, 470);
                Bbox.BshowText();
            }
        }
        
        Cbox.reset(run.getSomeone());
        Nbox.reset(run.getSomeone());
        Fbox.reset(run.getSomeone());
        Obox.reset(run.getSomeone());
        Tbox.reset(run.getSomeone());
        Gbox.reset(run.getSomeone());
        Jbox.reset(run.getSomeone());
        Bbox.reset(run.getSomeone());
    }
    //riddle
    public void riddle(){
        if(!Gbox.inWorld_R && Gbox.choose!=-1 && /*Gbox.times==0 &&*/ !chat[3]){
            if(Gbox.choose == 4){
                var[1]+=5;
            }else if(Gbox.choose == 5){
                var[1]-=5;
            }
            var[7]++;
            var[8]++;
            addObject(notify, 400, 370);
            notify.showText(15);
            Gbox.times++;
            Gbox.choose = -1;
        }
       
        if(!Fbox.inWorld_R && Fbox.choose!=-1 && isfirst && !chat[2]){
            if(Fbox.choose == 6){
                var[1]+=5;//金幣+5
                //isfirst = false;
            }else if(Fbox.choose == 7){
                var[1]-=5;
            }
            isfirst = false;
            chat[2] = true;
            var[7]++; //拿到拼圖
            addObject(notify, 400, 370);
            notify.showText(15);
            var[8]++;//拿到日記
            Fbox.choose = -1;
        }else if(Fbox.choose == 6) Fbox.choose = -1;
        else if(Fbox.choose == 7) {
            var[1]-=5;
            Fbox.choose = -1;
        }
    }
    //把目前的參數傳到run裡面
    public int[] returnvar(){
        return var;
    }
    //remove icon
    public void removeicon(int i){
        if(i == 2) removeObject(gun1);
        if(i == 5) removeObject(shield1);
    }
    public void removelive(){
        if(checkLife <= 0) removeObject(live1);
    }

    public void add(){
        if(checkLife > 0 && ron_live.live() > 0 && hasadd == false) {
            ron_live.addlife();
            hasadd = true;
        }
    }
    //槍射到
    public void decrease_RonLife(){
        hasadd = false;
        if(checkLife > 0){
           checkLife--;
           if(checkLife < 0){
               checkLife = 0;
           }
        }
        else if(checkLife == 0)  ron_live.minuslife();
        
    }   
    //被警察碰到
    public void touch_decrease_Ronlive(){
        if(isattack){
            hasadd = false;
            if(checkLife > 0){
               checkLife--;
               if(checkLife < 0){
                   checkLife = 0;
               }
            }
            else if(checkLife == 0)
            ron_live.minusminuslife();
        }
    }
    public void decrease_Life(int number){
        if(police_live.get(number).live() < 4){       
            police_live.get(number).minuslife();
            if(police_live.get(number).live() >= 4){
                removeObject(police_live.get(number));
                if(npc.get(number)!=null){
                    removeObject(npc.get(number));
                    var[8]++;
                    addObject(notify, 400, 270);
                    notify.showText(2);
                }
            }
        }
    }
   //關卡內容
    //catherine從門出來
    public void opendoor(){
        if(ballCount == 4 && isOpen == false){
            isOpen = true;           
            //addObject(c, 500, 250);
            c.setLocation(c.getX(), c.getY() + 100);
            c.getImage().setTransparency(250);
            if(!chat[3]){
                addObject(notify, 400, 300);
                notify.showText(9);
            }
        }
    }
    //障礙物
    private House house1 = new House(5);
    private House house2 = new House(6);
    private House house3 = new House(7);
    private House house8 = new House(8);
    private House house9 = new House(9);
    private House house10 = new House(10);
    private House castle = new House(11);
    private House jail = new House(12);
    private House traditional = new House(13);
    private Tree apple = new Tree(7);
    private Tree yellow = new Tree(9);
    private Tree green = new Tree(10);
    private Tree old = new Tree(11);
    private Tree[] orange = new Tree[6];
    private Board board = new Board();
    
    public void addObstacle(){
        addObject(apple, 339, 715);
        addObject(yellow ,944, 700);
        for(int i = 0; i < 6; i++){
            orange[i] = new Tree(8);
        }
        for(int i = 0;i<3;i++){
             addObject(orange[i] ,320+i*256, 1420);
        }
        for(int i = 3;i<6;i++){
             addObject(orange[i] ,1664+(i-3)*256, 1420);
        }
        addObject(jail ,1217, 1258);
        addObject(house9,930,256);
        addObject(house10,1582, 246);
        addObject(house8,860,800);
        addObject(traditional ,332,1678);
        addObject(green,1092,1812);
        addObject(board,1636, 1987);
        addObject(house3,2188,1626);
        addObject(old,1729, 2252);
        addObject(castle,923, 2184);
        addObject(house1,418, 2561);
        addObject(house2,1716, 2622);
    }
}