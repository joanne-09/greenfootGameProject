import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Run1 extends Actor
{
    private int atFrame=1;
    private boolean is_touch = true;
    
    //image
    private boolean notreturn  = true;
    private boolean haschangegun = false;
    private boolean haschangeshield = false;
    
    private boolean cool=true; //加速的冷卻時間   
    private boolean timer=false; //加速的使用時間   
    public boolean turbo=false; //用一個布林值偵測是否為加速狀態
    private long time1;
    private long time2;
    private long time3;
    private int dx=0, dy=0;
    
    private GifImage Gif1;
    private GifImage Gif2;
    private GifImage Gif3;
    private GifImage Gif4;
    
    private Shield shield = new Shield();   
    private int cooldown = 0; //發射子彈的冷卻時間
    
    public Circle circle = new Circle();
    public static int dir = 0; //紀錄Ron的方向
    public static int who = 0; //if Run1 entered BattleField, then set who = 1
    private Color[] color = new Color[11]; //宣告五個顏色
    private int[] var = new int[9];
           
    private int puzzles = 0;   
      
    private boolean appear = false;
    private boolean isDown = false;
    private int coolpause = 0;
    private int playGif = 0;
    
    public boolean pause = false; // 當有打開什麼東西的時候，其他東西暫停，阿但是還不知道怎麼寫
    public Run1(){
        color[0]=new Color(35,35,53); //樹
        color[1]=new Color(75,105,45); //樹 噴水池 建築物
        color[2]=new Color(98,70,50); //水池
        color[3]=new Color(187,187,187); //時鐘
        color[4]=new Color(65,85,50); //噴水池
        color[5]=new Color(110,110,120); //路燈
        color[6]=new Color(205,220,250); //雕像
        color[7]=new Color(165,185,200); //雕像
        color[8]=new Color(7,7,7); //地圖邊框
        color[9]=new Color(7,7,7); //地圖邊框
        color[10]=new Color(34,32,52); //battle邊框
        //moving gif
        Gif1 = new GifImage("left_run(slow).gif");
        Gif2 = new GifImage("right_run(slow).gif");
        Gif3 = new GifImage("back_run(slow).gif");
        Gif4 = new GifImage("front_run(slow).gif");
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
        //傳入武器、生命值等數量
        if(!pause){
            if(notreturn && getWorld().getClass() == Frame1.class) returnnum();
            if(getWorld().getClass() == BattleField.class){
                who = 1;
                operation();
                battleAttack();
                battleMinus();
            }else{
                operation();
            }
        
            //switchFrame();
            change();
            //getLives();
            //coolDown(); 會影響到gun的發射
            if(cooldown>0) cooldown--;
        
            //moving in the BattleField, need a variable to check if Run is in the BattleField or not
            //move();
            if(getWorld().getClass() == Frame1.class){
                Frame1 f = (Frame1) getWorld();
                if((f.gotoBattle_R() && !f.chat[3] && f.chat[1] && !f.haveWin))
                    Greenfoot.setWorld(new BattleField(f.var, f.chat,f.check));
            }      
            if(coolpause>0) coolpause--;
        
            circle.setLocation(getX(), getY());
        }
    }
    //移動
    public void operation(){
        //after pressing "f" accelerate time
        if(turbo && timer){
            subope(5);            
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
                subope(5);
            }else{
                //normal mode
                subope(3);                
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
    //Run's action in Battlefield
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
    public static int whichRun1(){
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
        Actor a = getOneIntersectingObject(Branch.class);
        Actor b = getOneIntersectingObject(OldMan.class);
        Actor c = getOneIntersectingObject(Baker.class);
        Actor d = getOneIntersectingObject(Beggar.class);
        Actor e = getOneIntersectingObject(Villager.class);
        Actor f = getOneIntersectingObject(BranchNoWord.class);
        if(a != null) return 1;
        else if(b != null) return 2;
        else if(c != null) return 3;
        else if(d != null) return 4;
        else if(e != null) return 5;
        else if(f != null) return 6;
        else return 0;
    }
    //得到frame傳來的icon
    public void returnnum(){
        Frame1 w = (Frame1)getWorld();
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
    //撿生命值
    /*public void getLives(){
        if(var[6] > 0){
            Frame1 w1 = (Frame1)getWorld();
            w1.add_RonLife();
            var[6] --;
            if(var[6] < 0) var[6] = 0;
        }
    }*/
    
    //應該用不到，不過我不確定
    /*public boolean touchingFile(){
        return (isTouching(SecretFile.class));
    }*/
    //讓Menu知道應該在哪個世界並換相對應的圖片
    /*public void switchFrame(){
        if(puzzles == 3){
            var[0] = 2;
            Greenfoot.setWorld(new Menu(var));
        }       
    }*/
    //碰撞機制
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
        Actor a = getOneObjectAtOffset(x1,y1,Brick.class);
        Actor b = getOneObjectAtOffset(x2,y2,Brick.class);
        Actor c = getOneObjectAtOffset(x3,y3,Brick.class);
        
        for(int i=0; i<11; i++){
            if(getWorld().getColorAt(getX()+x1, getY()+y1).equals(color[i])||
            getWorld().getColorAt(getX()+x2, getY()+y2).equals(color[i])||
            getWorld().getColorAt(getX()+x3, getY()+y3).equals(color[i])||
            getWorld().getColorAt(getX()+x1_1, getY()+y1_1).equals(color[i])||
            getWorld().getColorAt(getX()+x2_1, getY()+y2_1).equals(color[i])||
            getWorld().getColorAt(getX()+x3_1, getY()+y3_1).equals(color[i])||
            a!=null||b!=null||c!=null){
                return true;
            }
        }
        return false;
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
}