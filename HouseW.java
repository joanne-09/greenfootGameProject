import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Library here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HouseW extends World
{
    private EnterDiary ed = new EnterDiary();
    public Ron_live ron_live = new Ron_live();
    public Back4 back = new Back4();
    public Book[] book = new Book[7];
    public Cursor c = new Cursor();
    
    public Notify notify = new Notify();
    public Riddle riddle1 = new Riddle(1, 4);
    public Riddle riddle2 = new Riddle(1, 5);
    public int choose = -1;
    public int temp = -1;
    
    private int[] var = new int[9];
    private boolean[] chat = new boolean[15];
    private boolean[] check = new boolean[9];
    /**
     * Constructor for objects of class Library.
     * 
     */
    public HouseW(int var1[], boolean chat1[], boolean check1[])
    {    
        // Create a new world with 800x500 cells with a cell size of 1x1 pixels.
        super(800, 500, 1);
        //0:world 1:coin 2:gun 3: knife 4:sword 5:shield 6:life 7:puzzle 8:diary
        for(int i=0; i<9; i++) var[i] = var1[i];
        for(int i=0; i<15; i++) chat[i] = chat1[i];
        for(int i=0; i<9; i++) check[i] = check1[i];
        prepare();
    }
    public void prepare(){       
        addObject(ed, 44, 83);
        addObject(ron_live, 70, 30);        
        for(int i = 0; i < 7; i++){
            book[i] = new Book(i);
        }
        if(!check[0]) addObject(book[0], 198, 78);
        if(!check[1]) addObject(book[1], 658, 140);
        if(!check[2]) addObject(book[2], 503, 253);
        if(!check[3]) addObject(book[3], 116, 196);
        if(!check[4]) addObject(book[4], 607, 195);
        if(!check[5]) addObject(book[5], 698, 251);
        if(!check[6]) addObject(book[6], 687, 251);
        
        addObject(c, 400, 453);
        
        addObject(back, 765, 465);
    }
    public void act(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        diary();
        if(mouse != null) 
            c.followMouse(mouse.getX(), mouse.getY());
        things();
        riddle();
        
        if(Greenfoot.mouseClicked(back)) Greenfoot.setWorld(new Frame1(var, chat, check));
    }
    public void riddle(){
        if(riddle1.getWorld()!=null){
            riddle1.riddle(1);
            choose = riddle1.choose;
        }
        if(riddle2.getWorld()!=null){
            riddle2.riddle(0);
            choose = riddle2.choose;
        }
        if(!notify.inWorld && temp>-1){
            choose=temp;
            temp=-1;
        }
        if(choose!=-1){
            if(choose==0) var[1]+=2;
            else if(choose==2) var[1]++;
            else if(choose==3) var[8]++;
            addObject(notify, 400, 470);
            notify.showText(choose);
            if(choose==0) temp=2;
            else choose = -1;
        }
    }
    public void things(){
        for(int i=0; i<7; i++){
            if(Greenfoot.mouseClicked(book[i])){
                removeObject(book[i]);
                if(i==0 || i==6) choose=3;
                else if(i==2 || i==3 || i==5) choose=2;
                else if(i==4) addObject(riddle1, 400, 250);
                else if(i==1) addObject(riddle2, 400, 250);
                check[i] = true;
            }
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
}
