import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Black_Ball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class White_Ball extends F3Obstacle
{
    /**
     * Act - do whatever the Black_Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private boolean is_down = false;
    private int dir = 0;
    private boolean isTouch = false;
    private Color[] color=new Color[2];
    public White_Ball(){
         color[0]=new Color(73,65,72); //地圖邊框
         color[1]=new Color(147,83,62); //lake
    }
    public void act()
    {
        // Add your action code here.
        if(isTouch == false){
            move();
            isTouch_door();   
        }
    }
    public void move(){
        if(isTouching(Run3.class)){
        int dx=0, dy=0;
        if(Greenfoot.isKeyDown("left")){
                dir=2;
                //dx-=4;
                if(!getColor(dir)) dx-=4;
            }
            if (Greenfoot.isKeyDown("right")){
                dir=0;
                //dx+=4;  
                if(!getColor(dir)) dx+=4;                  
            }
            if (Greenfoot.isKeyDown("up")){
                dir=3;
                //dy-=4;
                if(!getColor(dir)) dy-=4;
            }
            if (Greenfoot.isKeyDown("down")){
                dir=1;
                //dy+=4;
                if(!getColor(dir)) dy+=4;
            }  
            setLocation(getX()+dx, getY()+dy);
        }
        
    }   
    public void isTouch_door(){
        if(isTouching(Door_left.class) && isTouch == false){
            Frame3 w = (Frame3)getWorld();
            w.ballCount++;
            isTouch = true;
            w.check[1] = true;
            getWorld().removeObject(this);
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
        
        for(int i=0; i<2; i++){
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
