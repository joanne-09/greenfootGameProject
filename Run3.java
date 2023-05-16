import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
/**528 Joanne 有改getColor()喔*/
public class Run3 extends Actor
{
    private int atFrame=3;
    //image
    private boolean notreturn  = true;
    private boolean haschangegun = false;
    private boolean haschangeshield = false;
    
    private boolean is_touch = true;
    
    private boolean cool=true; //加速的冷卻時間   
    private boolean timer=false; //加速的使用時間   
    private boolean turbo=false; //用一個布林值偵測是否為加速狀態
    private long time1;
    private long time2;
    private long time3;
    private int dx=0, dy=0;
    
    private long shieldCountdown = 600; //護盾的使用時間
    private int takeItem = 0; //只用一個變數紀錄：0沒東西 1撿護盾 2撿武器
    private int attackTimes = 10;
    
    private GifImage Gif1;
    private GifImage Gif2;
    private GifImage Gif3;
    private GifImage Gif4;
    
    private Shield shield = new Shield();   
    private int cooldown = 0; //發射子彈的冷卻時間
    
    Circle circle = new Circle();
    public static int dir = 0; //紀錄Ron的方向  
    public static int who = 0; //if Run3 entered BattleField, then set who = 3
    private Color[] color = new Color[4]; //宣告顏色
    private int[] var = new int[9];
    
    private int coolpause = 0;
    
    public boolean pause = false;
    
    //private Menu menu = new Menu();
    
    public Run3(){
        color[0]=new Color(73,65,72); //地圖邊框+house9
        color[1]=new Color(34,32,52); //battle
        color[2]=new Color(147,83,62); //lake
        color[3]=new Color(0 ,0 ,0 );//carriage
        // color[2]=new Color(121,111,101); //house
        //color[4]=new Color(136,122,138); //brown house
        //color[5]=new Color(96,198,70); //brown house
       // color[6]=new Color(20,9,6); //bray
        //color[7]=new Color(106,102,108); //lake
        //color[8]=new Color(73,65,71); //lake
       // color[9]=new Color(129,129,86); //housel shadow
        //color[10]=new Color(129,127,83); //house2 shadow
       // color[11]=new Color(39,30,30); //dark brown house2
        //color[12]=new Color(36,29,53);//樹基底
        //color[13]=new Color(33,30,58);//樹基底
        //color[14]=new Color(99,61,33);//某個邊框?
        //color[15]=new Color(105,106,106);//battle邊框外
        //moving gif
        Gif1 = new GifImage("left_run(slow).gif");
        Gif2 = new GifImage("right_run(slow).gif");
        Gif3 = new GifImage("back_run(slow).gif");
        Gif4 = new GifImage("front_run(slow).gif");
    }
    //新增一個circle
    public class Circle extends Actor{
        public Circle(){
            GreenfootImage image = new GreenfootImage(400, 400);
            image.fillOval(0, 0,400, 400);
            setImage(image);
            //透明的所以看不見
            getImage().setTransparency(0);
        }
    }
    protected void addedToWorld(World w)
    {
        w.addObject(circle, getX(), getY());
    }
    public boolean canSee(Class clss) {
        return getOneObjectAtOffset(0, 0, clss) != null;
    }
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(!pause){
            if(notreturn && getWorld().getClass() == Frame3.class) returnnum();
            operation();
            change();
            //getLives();
            getShields();
            getGuns();
            if(cooldown>0) cooldown--;
            //in BattleField
            if(coolpause>0)coolpause--;
            if(getWorld().getClass() == Frame3.class){
                Frame3 f = (Frame3) getWorld();
                if(f.JgotoBattle_R() && f.chat[3] && f.chat[2] && f.chat[1] && !f.haveWin){
                    f.var[3] = getX();
                    f.var[4] = getY();
                    Greenfoot.setWorld(new BattleField(f.var, f.chat, f.check));
                }
                else if(f.NgotoBattle_R() && !f.chaveWin){
                    f.var[3] = getX();
                    f.var[4] = getY();
                    Greenfoot.setWorld(new BattleField(f.var, f.chat, f.check));
                }
            }  
            if(getWorld().getClass() == BattleField.class){
                who = 3;
                operation();
                battleAttack();
                battleMinus();
            }else {
                operation();
            }
            circle.setLocation(getX(), getY());
            getTouch();
        }
        //快捷鍵
        if(Greenfoot.isKeyDown("shift") && Greenfoot.isKeyDown("s")){
            setLocation(400,250);
        }
    }
    public void operation(){
        int dx=0, dy=0;
        //after pressing "f" accelerate time
        if(turbo && timer){
            if(Greenfoot.isKeyDown("left")){
                dir=2;
                if(!getColor(dir)) dx-=4;
            }
            if (Greenfoot.isKeyDown("right")){
                dir=0;
                if(!getColor(dir)) dx+=4;                   
            }
            if (Greenfoot.isKeyDown("up")){
                dir=3;
                if(!getColor(dir)) dy-=4;
            }
            if (Greenfoot.isKeyDown("down")){
                dir=1;
                if(!getColor(dir)) dy+=4;
            }            
            //time of accelerate
            time2=System.currentTimeMillis();            
            if(time2-time1>=1000){
                turbo=false;
                timer=false;
                cool=false;
            }
        }else if(!turbo){
            //after cooldown and press "f"
            if(Greenfoot.isKeyDown("f") && cool){
                time1=System.currentTimeMillis();
                turbo=true;
                timer=true;
                if(Greenfoot.isKeyDown("left")){
                    dir=2;
                    if(!getColor(dir)) dx-=4;
                }
                if (Greenfoot.isKeyDown("right")){
                    dir=0;
                    if(!getColor(dir)) dx+=4;
                }
                if (Greenfoot.isKeyDown("up")){
                    dir=3;
                    if(!getColor(dir)) dy-=4;
                }
                if (Greenfoot.isKeyDown("down")){
                    dir=1;
                    if(!getColor(dir)) dy+=4;
                }
            }else{
                //normal mode
                if(Greenfoot.isKeyDown("left")){
                    dir=2;
                    if(!getColor(dir) ) dx-=2;
                }
                if (Greenfoot.isKeyDown("right")){
                    dir=0;
                    if(!getColor(dir)) dx+=2;
                }
                if (Greenfoot.isKeyDown("up")){
                    dir=3;
                    if(!getColor(dir)) dy-=2;
                }
                if (Greenfoot.isKeyDown("down")){
                    dir=1;
                    if(!getColor(dir)) dy+=2;
                }                
                //detect cool time
                time3=System.currentTimeMillis();
                if(time3-time1>=2000){
                    cool=true;
                }
            }
        }
        //if moving
        if(dx!=0 || dy!=0){
            //keep player in the central of the window
            if(getX()+dx<getImage().getWidth()/2 || getX()+dx>getWorld().getWidth()-getImage().getWidth()/2) dx = 0;
            if (getY()+dy < getImage().getHeight()/2 || getY()+dy > getWorld().getHeight()-getImage().getHeight()/2) dy = 0;            
            setLocation(getX()+dx, getY()+dy);
        }
        shoot(dir*90);
    }
    public void subope(int m){
        int x=0, y=0;
        if(Greenfoot.isKeyDown("left")){
            dir=2;
            if(!getColor(dir)) x-=m;
        }
        if (Greenfoot.isKeyDown("right")){
            dir=0;
            if(!getColor(dir)) x+=m;
        }
        if (Greenfoot.isKeyDown("up")){
            dir=3;
            if(!getColor(dir)) y-=m;
        }
        if (Greenfoot.isKeyDown("down")){
            dir=1;
            if(!getColor(dir)) y+=m;
        }        
        dx = x;
        dy = y;
    }
    
    //battle

    public void battleAttack(){
        LeftDetect ldc = new LeftDetect();
        RightDetect rdc = new RightDetect();
        if(Greenfoot.isKeyDown("d") && coolpause==0) {
            if(dir == 0){    
                getWorld().addObject(rdc, getX()+30, getY());
            }else if(dir == 2){     
                getWorld().addObject(ldc, getX()-30, getY());
            }else if(dir == 3){
                getWorld().addObject(rdc, getX(), getY()-30);
            }else if(dir == 1){
                getWorld().addObject(ldc, getX(), getY()+30);
            }
            //appear = true;
            //isDown = true;
            coolpause = 30;
            //playGif = 10;
        }
        if(coolpause==28){  
            getWorld().removeObjects(getWorld().getObjects(Detect.class));
            //appear = false;
            //isDown = false;
        }
    }

    public void battleMinus(){
        Actor actor = getOneIntersectingObject(Weapon.class);
        Actor actor2 = getOneIntersectingObject(Enemy.class);
        int x = getX();
        if(actor!=null || actor2!=null){
            if(((BattleField)getWorld()).buffer2 == 0)
            ((BattleField)getWorld()).hurtRun();
        }
    }
    public static int Keydown(){
        return dir;
    }
    public static int whichRun3(){
        return who;
    }
    
    //移動時切換圖片
    public void change(){
        if(Greenfoot.isKeyDown("left"))setImage(Gif1.getCurrentImage());
        if(Greenfoot.isKeyDown("right"))setImage(Gif2.getCurrentImage());
        if(Greenfoot.isKeyDown("up"))setImage(Gif3.getCurrentImage());
        if(Greenfoot.isKeyDown("down"))setImage(Gif4.getCurrentImage());
    }
    
    //對話框
    public int getSomeone(){
        Actor c = getOneIntersectingObject(Carriage.class);
        Actor n = getOneIntersectingObject(Noble.class);
        Actor f = getOneIntersectingObject(Farmer.class);
        Actor o = getOneIntersectingObject(The_one.class);
        Actor t = getOneIntersectingObject(Talker_A.class);
        Actor g = getOneIntersectingObject(Catherine.class);
        Actor j = getOneIntersectingObject(Johnson.class);
        Actor b = getOneIntersectingObject(Branch3.class);
        //Actor e = getOneIntersectingObject(Villager.class);
        if(c != null) return 2;
        else if(n != null) return 3;
        else if(f != null) return 4;
        else if(o != null) return 5;
        else if(t != null) return 6;
        else if(g != null) return 7;
        else if(j != null) return 8;
        else if(b != null) return 9;
        //else if(e != null) return 5;
        else return 0;
    }
    public int openOnNPCs(){
        int answer = 0;
        List<F3NPC> a = getObjectsInRange(70,F3NPC.class);
        if(a.size()!=0){
            if(a.get(0).getClass() == Carriage.class) answer = 2;
            else if(a.get(0).getClass() == Noble.class) answer = 3;
            else if(a.get(0).getClass() == Farmer.class) answer = 4;
            else if(a.get(0).getClass() == The_one.class) answer = 5;
            else if(a.get(0).getClass() == Talker_A.class) answer = 6;
            else if(a.get(0).getClass() == Catherine.class) answer = 7;
            else if(a.get(0).getClass() == Johnson.class) answer = 8;
            else if(a.get(0).getClass() == Johnson.class) answer = 9;
            //else if(a.get(0).getClass() == Villager.class) answer = 5;
        }else answer = 0;
        return answer;
    }
    //得到frame傳來的icon
    public void returnnum(){
        Frame3 w = (Frame3)getWorld();
        var = w.returnvar();
        /*var[0] = w.returnworld();
        var[1] = w.returncoins();
        var[2] = w.returngun();
        var[3] = w.returnknife();
        var[4] = w.returnsword();
        var[5] = w.returnshield();
        var[6] = w.returnlive();
        var[7] = w.returnpuzzle();
        var[8] = w.returndiary();*/
        notreturn = false;
    }
  
    //撿護盾 
    public void getShields(){
        //Actor actor2 = getOneIntersectingObject(Shield.class);
        //if(actor2!=null && takeItem==0) takeItem=1;
        if(Greenfoot.isKeyDown("w") && takeItem==0 && var[5] != 0) {
            takeItem = 1;
            var[5]--;
            if(var[5]==0){
                Frame3 f = (Frame3)getWorld();
                f.removeicon(5);   
            }
        }   
        if(takeItem==1 && haschangeshield == false){
                Gif1=new GifImage("left_shield(run_slow).gif");
                Gif2=new GifImage("right_shield(run_slow).gif");
                Gif3=new GifImage("back_shield(run_slow).gif");
                Gif4=new GifImage("front_shield(run_slow).gif");
                setImage(Gif1.getCurrentImage());
                setImage(Gif2.getCurrentImage());
                setImage(Gif3.getCurrentImage());
                setImage(Gif4.getCurrentImage());
                haschangeshield = true;
        }
        if(takeItem==1) coolDown();
    }
    //撿武器
    public void getGuns(){
        if(Greenfoot.isKeyDown("q") && takeItem==0 && var[2] != 0) {
            takeItem = 2;
            attackTimes = 10;
            var[2]--;
            if(var[2] == 0){
                Frame3 f = (Frame3)getWorld();
                f.removeicon(2);   
            }
        }
        if(takeItem==2 && haschangegun == false){
            Gif1=new GifImage("left_gun(run_slow).gif");
            Gif2=new GifImage("right_gun(run_slow).gif");
            Gif3=new GifImage("back_gun(run_slow).gif");
            Gif4=new GifImage("front_gun(run_slow).gif");
            setImage(Gif1.getCurrentImage());
            setImage(Gif2.getCurrentImage());
            setImage(Gif3.getCurrentImage());
            setImage(Gif4.getCurrentImage());
            haschangegun = true;
        }
        attackLimit();
    }
    //護盾冷卻時間
    public void coolDown(){
        if(shieldCountdown>0) shieldCountdown--;
        if(shieldCountdown<=0){
            Gif1=new GifImage("left_run(slow).gif");
            Gif2=new GifImage("right_run(slow).gif");
            Gif3=new GifImage("back_run(slow).gif");
            Gif4=new GifImage("front_run(slow).gif");
            setImage(Gif1.getCurrentImage());
            setImage(Gif2.getCurrentImage());
            setImage(Gif3.getCurrentImage());
            setImage(Gif4.getCurrentImage());
            shieldCountdown = 500;
            takeItem = 0;
            haschangeshield = false;
        }
    }
    //attack times limit
    public void attackLimit(){
        if(takeItem==2){
            if(attackTimes<=0){
                Gif1 = new GifImage("left_run(slow).gif");
                Gif2 = new GifImage("right_run(slow).gif");
                Gif3 = new GifImage("back_run(slow).gif");
                Gif4 = new GifImage("front_run(slow).gif");
                setImage(Gif1.getCurrentImage());
                setImage(Gif2.getCurrentImage());
                setImage(Gif3.getCurrentImage());
                setImage(Gif4.getCurrentImage());
                takeItem = 0;
                haschangegun = false;
            }
        }
    }
    //shoot定位發射子彈
    public void shoot(int direction){
        if(Greenfoot.isKeyDown("g") && cooldown<=0 && takeItem==2){
            int r = getRotation();
            getWorld().addObject(new Bullet(0+direction/*r+36*i*/),getX(),getY());
            cooldown = 100;
            attackTimes--;
        } 
    }
    //Ron got hurt by bullet
    public void hurt(){
        if(takeItem ==1){
        }else{
            Frame3 w = (Frame3)getWorld();
            w.decrease_RonLife();
        }
    }
    //Ron got touched by police
    public void getTouch(){
        if(is_touch && isTouching(NPC.class)){
            if(takeItem ==1){
            }else{
                Frame3 w = (Frame3)getWorld();
                w.touch_decrease_Ronlive();
                is_touch = false;
            }
        }
        if(!is_touch && !isTouching(NPC.class)){
            is_touch = true;
        }
    }
   
    public boolean getColor(int z){
        int x1=0, y1=0, x2=0, y2=0, x3=0, y3=0;
        int x1_1=0, y1_1=0, x2_1=0, y2_1=0, x3_1=0, y3_1=0;
        //up
        if(z==3){
            x1=0;
            y1=0;
            x2=-18;
            y2=0;
            x3=18;
            y3=0;
            x1_1=0;
            y1_1=-1;
            x2_1=-18;
            y2_1=-1;
            x3_1=18;
            y3_1=-1;
        }
        //down
        if(z==1){
            x1=0;
            y1=24;
            x2=-18;
            y2=24;
            x3=18;
            y3=24;
            x1_1=0;
            y1_1=25;
            x2_1=-18;
            y2_1=25;
            x3_1=18;
            y3_1=25;
        }
        //right
        if(z==0){
            x1=18;
            y1=0;
            x2=18;
            y2=12;
            x3=18;
            y3=24;
            x1_1=19;
            y1_1=0;
            x2_1=19;
            y2_1=12;
            x3_1=19;
            y3_1=24;
        }
        //left
        if(z==2){
            x1=-18;
            y1=0;
            x2=-18;
            y2=12;
            x3=-18;
            y3=24;
            x1_1=-19;
            y1_1=0;
            x2_1=-19;
            y2_1=12;
            x3_1=-19;
            y3_1=24;
        }
        
        for(int i=0; i<4; i++){
            if(getWorld().getColorAt(getX()+x1, getY()+y1).equals(color[i])||
            getWorld().getColorAt(getX()+x2, getY()+y2).equals(color[i])||
            getWorld().getColorAt(getX()+x3, getY()+y3).equals(color[i])||
            getWorld().getColorAt(getX()+x1_1, getY()+y1_1).equals(color[i])||
            getWorld().getColorAt(getX()+x2_1, getY()+y2_1).equals(color[i])||
            getWorld().getColorAt(getX()+x3_1, getY()+y3_1).equals(color[i])){
                return true;
            }
        }
        return false;
    }  
}