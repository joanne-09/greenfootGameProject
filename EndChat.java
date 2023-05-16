import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndChat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndChat extends Chatbox
{
    /**
     * Act - do whatever the EndChat wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private boolean isDown = false;
    public boolean pause_R = false;
    private GreenfootImage text;
    private int spaceClicked = 0;
    private boolean win = true;
    private static final Color transparent = new Color(0,0,0,0);
    public static final GreenfootImage mapImage = new GreenfootImage(790,250);
    static
    {
        mapImage.setColor(Color.BLACK);
        mapImage.setTransparency(100);
        mapImage.fill();
    }     
    GreenfootImage image = new GreenfootImage(mapImage);
    public EndChat(boolean w){
        win = w;
        setImage(image);
    }
    public void act()
    {
        // Add your action code here.
        
    }
    public void showtext(){
        //GreenfootImage image = new GreenfootImage(mapImage);
        if(Greenfoot.isKeyDown("space") && !isDown) {
            spaceClicked++;
            isDown = true;          
        }
        if(!Greenfoot.isKeyDown("space") && isDown) isDown=false;
        if(spaceClicked == -1) ;
        else if(spaceClicked > 12){
            pause_R = false;
            spaceClicked=-1;
            image.clear();
            ((End)getWorld()).johnson.setRotation(90);
            End e = (End)getWorld();
            e.finish = true;
        }else{                
            //if(spaceClicked > 0) w.pause = true;
            pause_R = true;
            setimage(win, spaceClicked+1);
            //setImage(image);
        }        
    }
    public void lshowtext(){
        if(Greenfoot.isKeyDown("space") && !isDown) {
            spaceClicked++;
            isDown = true;          
        }
        if(!Greenfoot.isKeyDown("space") && isDown) isDown=false;
        if(spaceClicked == -1) ;
        else if(spaceClicked > 1){
            pause_R = false;
            spaceClicked=-1;
            image.clear();
            End e = (End)getWorld();
            e.finish = true;
            //setImage(image);
        }else{                
            //if(spaceClicked > 0) w.pause = true;
            pause_R = true;
            setimage(false ,spaceClicked+1);
        }        
    }
    public void setimage(boolean w, int i){
        if(w){
            if(i == 1) setImage("NONE/john1.png");
            else if(i == 2) setImage("NONE/john2.png");
            else if(i == 3) setImage("NONE/john3.png");
            else if(i == 4) setImage("NONE/john4.png");
            else if(i == 5) setImage("NONE/john5.png");
            else if(i == 6) setImage("NONE/john6.png");
            else if(i == 7) setImage("NONE/john7.png");
            else if(i == 8) setImage("NONE/john8.png");
            else if(i == 9) setImage("NONE/john9.png");
            else if(i == 10) setImage("NONE/john10.png");
            else if(i == 11) setImage("NONE/john11.png");
            else if(i == 12) setImage("NONE/john13.png");
            else if(i == 13) setImage("NONE/john14.png");
            else if(i == 14) setImage("NONE/john15.png");
        }else if(!w){
            if(i == 1) setImage("NONE/john16.png");
            else if(i == 2) setImage("NONE/john17.png");
        }
        
    }
}
