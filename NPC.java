import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InGameNPC here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NPC extends SmoothMover
{
    private int cooldown = 0;
    private int number = 0;
    private int pause = 30;
    private int rand = 0;
    
    private boolean is_move = true;

    GifImage Gif1 = new GifImage("2gunR_walk.gif");
    GifImage Gif2 = new GifImage("2gunF_walk.gif");
    GifImage Gif3 = new GifImage("2gunL_walk.gif");
    GifImage Gif4 = new GifImage("2gunB_walk.gif");
    public NPC(int n){
        number = n;
    }
    /**
     * Act - do whatever the NPC wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {  
        random();
        shoot();
        if(cooldown>0) cooldown--;
        if(pause>0) pause--;
    }
    public int getRandomNumber(int start,int end)
    {
       int normal = Greenfoot.getRandomNumber(end-start+1);
       return normal+start;
    }
    public void ran(){
        rand = getRandomNumber(1,50);

        if(rand == 2 && is_move == false){
            is_move();
        }
    }
    public void is_move(){
        is_move = true;
    }
    public void is_pause(){
        is_move = false;
    }
    public void random(){
        double x = getExactX();
        double y = getExactY();
        double nx = 0;
        double ny = 0;
        if(pause<=0){
            rand = getRandomNumber(1,4);
            pause = getRandomNumber(33,35);
        }
        //3->left 1->right 2->down 4->up
        if(rand==3){
            if(!isTouching(Obstacle.class)){
                setImage(Gif3.getCurrentImage());           
                nx = getX()-3;
                ny = getY();
            }else{
                setImage(Gif1.getCurrentImage());
                nx = getX()+4;
                ny = getY();
                pause = 0;
            }
        }else if(rand==1){
            if(!isTouching(Obstacle.class)){
                setImage(Gif1.getCurrentImage());
                nx = getX()+3;
                ny = getY();
            }else{
                setImage(Gif3.getCurrentImage());
                nx = getX()-4;
                ny = getY();
                pause = 0;
            }
        }else if(rand==4){
            if(!isTouching(Obstacle.class)){
                setImage(Gif4.getCurrentImage());                
                nx = getX();
                ny = getY()-3;
            }else{
                setImage(Gif2.getCurrentImage());                
                nx = getX();
                ny = getY()+4;
                pause = 0;
            }
        }else if(rand==2){
            if(!isTouching(Obstacle.class)){
                setImage(Gif2.getCurrentImage());
                nx = getX();
                ny = getY()+3;
            }else{
                setImage(Gif4.getCurrentImage());
                nx = getX();
                ny = getY()-4;
                pause = 0;
            }
        }
        setLocation(nx, ny);
    }
    public void hurt(){
        Frame3 w = (Frame3)getWorld();
        w.decrease_Life(number);
    }
    public boolean isTouchingObject(){
        return(isTouching(Obstacle.class) || isTouching(Run1.class) || isTouching(Run3.class));
    }
    public void shoot(){
        int rand = greenfoot.Greenfoot.getRandomNumber(500);
        Frame3 w = (Frame3)getWorld();
        if(circletouching() && cooldown<=0 && w.isattack){
            getWorld().addObject(new PoliceBullet((rand-1)*90),getX(),getY());           
            cooldown = 100;           
        }
    }   
    public boolean circletouching(){
        return(isTouching(Run3.Circle.class));
    }
   
}
