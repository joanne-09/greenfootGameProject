import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
/**528 Joanne 有改getColor()喔*/
public class Run2 extends Actor
{
    private int atFrame = 1;
    //private boolean is_touch = true;
    
    //image
    private boolean notreturn  = true;
    private boolean haschangegun = false;
    private boolean haschangeshield = false;
    
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
    
    //private Shield shield = new Shield();   
    private int cooldown = 0; //發射子彈的冷卻時間
    
    public Circle circle = new Circle();
    public static int dir = 0; //紀錄Ron的方向  
    private Color[] color = new Color[4]; //宣告五個顏色
    private int[] var = new int[9];
           
    private int puzzles = 0;   
      
    private boolean appear = false;
    private boolean isDown = false;
    //private int coolpause = 0; 
    
    public boolean pause = false; // 當有打開什麼東西的時候，其他東西暫停，阿但是還不知道怎麼寫
    
    //private Frame2 w = (Frame2)getWorld();
    public Run2(){
        /*color[0]=new Color(35,35,53); //�? ??�籬*/
        color[0]=new Color(75,105,45); //Buildings
        color[1]=new Color(154,153,149); //Road
        color[2]=new Color(144,144,141); //Boundary
        color[3]=new Color(78,105,51); //Buildings2
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
        //?��?��武器?��?�命?��?�數???
        if(notreturn && getWorld().getClass() == Frame2.class) returnnum();
        if(!pause){
            operation();
            change();
        }
        switchFrame();
        if(cooldown>0)                                         cooldown--;
        circle.setLocation(getX(), getY());
    }
    //移�??
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
        shoot(dir*90);
        if(Greenfoot.isKeyDown("shift") && Greenfoot.isKeyDown("x")){
            if(dir == 0) setLocation(getX()-3, getY());
            else if(dir == 1) setLocation(getX(), getY()-3);
            else if(dir == 2) setLocation(getX()+3, getY());
            else if(dir == 3) setLocation(getX(), getY()+3);
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
    /*public static int Keydown(){
        return dir;
    }*/
    //移�?��?��?��?��?��??
    public void change(){
        if(Greenfoot.isKeyDown("left"))setImage(Gif1.getCurrentImage());
        if(Greenfoot.isKeyDown("right"))setImage(Gif2.getCurrentImage());
        if(Greenfoot.isKeyDown("up"))setImage(Gif3.getCurrentImage());
        if(Greenfoot.isKeyDown("down"))setImage(Gif4.getCurrentImage());
    }
    //對話�?
    public int getSomeone(){
        Actor b = getOneIntersectingObject(StoreOwner.class);
        Actor c = getOneIntersectingObject(FortuneTeller.class);
        Actor d = getOneIntersectingObject(Paul.class);
        Actor e = getOneIntersectingObject(Wilson.class);
        Actor f = getOneIntersectingObject(Branch2.class);
        if(b != null)      return 2;
        else if(c != null) return 3;
        else if(d != null) return 4;
        else if(e != null) return 5;
        else if(f != null) return 6;
        else               return 0;
    }
    public int openOnNPCs(){
        //int answer = 0;
        List<F2NPC> a = getObjectsInRange(150, F2NPC.class);
        if(a.size()!=0){
            if(a.get(0).getClass() == StoreOwner.class) return 2;
            else if(a.get(0).getClass() == FortuneTeller.class) return 3;
            else if(a.get(0).getClass() == Paul.class) return 4;
            else if(a.get(0).getClass() == Wilson.class) return 5;
        }
        return 0;
        //return answer;
    }
    //得到frame?��來�?�icon
    public void returnnum(){
        Frame2 w = (Frame2)getWorld();
        /*var[0] = w.returnworld();
        var[1] = w.returncoins();
        var[2] = w.returngun();
        var[3] = w.returnknife();
        var[4] = w.returnsword();
        var[5] = w.returnshield();
        var[6] = w.returnlive();*/
        notreturn = false;
    }
    //?��??�命??
    /*public void getLives(){
        if(var[6] > 0){
            Frame1 w1 = (Frame1)getWorld();
            w1.add_RonLife();
            var[6] --;
            if(var[6] < 0) var[6] = 0;
        }
    }*/
    //?��得護?�� 
    public void getShields(){
        //Actor actor2 = getOneIntersectingObject(Shield.class);
        //if(actor2!=null && takeItem==0) takeItem=1;
        if(Greenfoot.isKeyDown("4") && takeItem==0 && var[5] != 0) {
            takeItem = 1;
            var[5]--;
            if(var[5]==0){
                Frame2 f = (Frame2)getWorld();
                //f.removeicon(5);   
            }
        }   
        if(takeItem==1&& haschangeshield == false){
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
    //?��得武?��, not used in F2
    /*public void getGuns(){
        if(Greenfoot.isKeyDown("1") && takeItem==0 && var[2] != 0) {
            takeItem = 2;
            attackTimes = 10;
            var[2]--;
            if(var[2] == 0){
                Frame2 f = (Frame2)getWorld();
                //f.removeicon(2);   
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
    }*/
    //護盾?��?��??��??, not used in F2
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
    //notice not write
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
    //shoot定�?�發射�?��??, not used in F2
    public void shoot(int direction){
        if(Greenfoot.isKeyDown("g") && cooldown<=0 && takeItem==2){
            int r = getRotation();
            //getWorld().addObject(new Bullet(0+direction/r+36*i),getX(),getY());
            cooldown = 100;
            attackTimes--;
        }
    }
    //Ron got hurt by bullet, not used in F2
    //Ron got touched by police, not used in F2
    //讓Menu?��??��?�該?��?��?��?��?�並??�相對�?��?��?��??
    public void switchFrame(){
        if(puzzles == 3){
            var[0] = 2;
            Greenfoot.setWorld(new Menu(var));
        }       
    }
    //碰�?��?�制
    public boolean getColor(int z){
        int x1=0, y1=0, x2=0, y2=0, x3=0, y3=0, x4=0, y4=0, x5=0, y5=0;
        int x1_1=0, y1_1=0, x2_1=0, y2_1=0, x3_1=0, y3_1=0, x4_1=0, y4_1=0, x5_1=0, y5_1=0;
        //up
        if(z==3){
            x1=0;
            y1=0;
            x2=-18;
            y2=0;
            x3=18;
            y3=0;
            x4=9;
            y4=-9;
            x1_1=0;
            y1_1=-1;
            x2_1=-18;
            y2_1=-1;
            x3_1=18;
            y3_1=-1;
            x4_1=18;
            y4_1=-18;
        }
        //down
        if(z==1){
            x1=0;
            y1=24;
            x2=-18;
            y2=24;
            x3=18;
            y3=24;
            x4=6;
            y4=9;
            x1_1=0;
            y1_1=25;
            x2_1=-18;
            y2_1=25;
            x3_1=18;
            y3_1=25;
            x4_1=12;
            y4_1=18;
        }
        //right
        if(z==0){           
            x1=18;
            y1=0;
            x2=18;
            y2=12;
            x3=18;
            y3=24;
            x4=9;
            y4=6;
            x1_1=19;
            y1_1=0;
            x2_1=19;
            y2_1=12;
            x3_1=19;
            y3_1=24;
            x4_1=18;
            y4_1=12;
        }
        //left
        if(z==2){
            x1=-18;
            y1=0;
            x2=-18;
            y2=12;
            x3=-18;
            y3=24;
            x4=-9;
            y4=6;
            x1_1=-19;
            y1_1=0;
            x2_1=-19;
            y2_1=12;
            x3_1=-19;
            y3_1=24;
            x4_1=-18;
            y4_1=12;
        }
        
        for(int i=0; i<4; i++){
            if(getWorld().getColorAt(getX()+x1, getY()+y1).equals(color[i])||
            getWorld().getColorAt(getX()+x2, getY()+y2).equals(color[i])||
            getWorld().getColorAt(getX()+x3, getY()+y3).equals(color[i])||
            getWorld().getColorAt(getX()+x4, getY()+y4).equals(color[i])||
            getWorld().getColorAt(getX()+x1_1, getY()+y1_1).equals(color[i])||
            getWorld().getColorAt(getX()+x2_1, getY()+y2_1).equals(color[i])||
            getWorld().getColorAt(getX()+x3_1, getY()+y3_1).equals(color[i])||
            getWorld().getColorAt(getX()+x4_1, getY()+y4_1).equals(color[i])){
                return true;
            }
        }
        return false;
    }
    //?��增�??�circle
    public class Circle extends Actor{
        public Circle(){
            GreenfootImage image = new GreenfootImage(400, 400);
            image.fillOval(0, 0,400, 400);
            setImage(image);
            //?��?��?��?以�?��?��??
            getImage().setTransparency(0);
        }
    }
    protected void addedToWorld(World w)
    {
        w.addObject(circle, getX(), getY());
    }
}
