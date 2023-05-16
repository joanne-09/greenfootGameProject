import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ball extends F1Obstacle
{
    public boolean isTouch = false;
    private int dir = 0;
    private Color[] color=new Color[9];
    public Ball(){
        setImage("OBSTACLE/F1/basketball.png");
        color[0]=new Color(35,35,53); //樹
        color[1]=new Color(75,105,45); //樹 噴水池 建築物
        color[2]=new Color(98,70,50); //水池
        color[3]=new Color(187,187,187); //時鐘
        color[4]=new Color(65,85,50); //噴水池
        color[5]=new Color(110,110,120); //路燈
        color[6]=new Color(205,220,250); //雕像
        color[7]=new Color(165,185,200); //雕像
        color[8]=new Color(7,7,7); //地圖邊框
    }
    /**
     * Act - do whatever the Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        if(!isTouch){
            Frame1 w = (Frame1) getWorld();
            if(w.run.turbo) move(5);
            else move(3);
        }
    }
    public void move(int m){
        if(isTouching(Run1.class)){
            int dx=0, dy=0;
            if(Greenfoot.isKeyDown("left")){
                dir=2;
                if(!getColor(dir)) dx-=m;
            }
            if (Greenfoot.isKeyDown("right")){
                dir=0;
                if(!getColor(dir)) dx+=m;                 
            }
            if (Greenfoot.isKeyDown("up")){
                dir=3;
                if(!getColor(dir)) dy-=m;
            }
            if (Greenfoot.isKeyDown("down")){
                dir=1;
                if(!getColor(dir)) dy+=m;
            }  
            setLocation(getX()+dx, getY()+dy);
        }
    }
    public void changeLocation(int X, int Y){
        setLocation(X-40, Y+10);
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
        Actor a = getOneObjectAtOffset(x1,y1,Brick.class);
        Actor b = getOneObjectAtOffset(x2,y2,Brick.class);
        Actor c = getOneObjectAtOffset(x3,y3,Brick.class);
        
        for(int i=0; i<9; i++){
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
}
