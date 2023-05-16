import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class End here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class End extends World
{
    private Run3 run;
    public Johnson johnson;
    private int[] var = new int[9];
    private int lasttime = 0;
    private boolean win = true;
    public boolean finish = false;
    /**
     * Constructor for objects of class End.
     * 
     */
    public End(int l/*判斷是不是最後一次*/, int var1[], boolean winner)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 500, 1);
        lasttime = l;
        var[0] = var1[0];
        var[7] = var1[7];
        win = winner;
        run = new Run3();
        johnson = new Johnson();
        addObject(johnson, 388, 328);
        if(l==1) johnson.setRotation(90);
        addObject(run , 662, 379);
        addObject(Chat1 , 399, 145);
        addObject(Chat , 399, 183);
    }
    private  EndChat Chat = new EndChat(win);
    private  EndChat Chat1 = new EndChat(win);
    
    public void act(){
        //winshowtext();
        if(lasttime == 1){
            //johnson.setRotation(90);
            Chat.setimage(true,14);
            if(Greenfoot.isKeyDown("space")){
                removeObject(Chat);
                removeObject(Chat1);
                removeObject(run);
                removeObject(johnson);
                setBackground("Finish.png");
                Greenfoot.stop();
            }
        }else{
            if(win){
                if(!finish) Chat.showtext();
            }else{
                if(!finish) Chat.lshowtext();
            }
            if(finish){
                removeObject(Chat1);
                removeObject(Chat);
                if(win){
                    addObject(new EnterPuzzle(var), 388, 258);
                }
                else{
                    removeObject(run);
                    removeObject(johnson);
                    setBackground("Finish.png");
                }
            }
        }
        
    }
}
