import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PuzzleClippings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PuzzleClippings extends Actor
{
    public boolean change = false;
    public boolean init = false;
    public int num = -1;
    private GreenfootImage img1;
    private GreenfootImage img2;
    private int nx;
    private int ny;
    public PuzzleClippings(int n, int x, int y){
        if(n == 0){
            img1 = new GreenfootImage("PUZZLE/puzzle1.s.png");
            img2 = new GreenfootImage("PUZZLE/puzzle1.png");
        }else if(n == 1){
            img1 = new GreenfootImage("PUZZLE/puzzle2.s.png");
            img2 = new GreenfootImage("PUZZLE/puzzle2.png");
        }else if(n == 2){
            img1 = new GreenfootImage("PUZZLE/puzzle3.s.png");
            img2 = new GreenfootImage("PUZZLE/puzzle3.png");
        }else if(n == 3){
            img1 = new GreenfootImage("PUZZLE/puzzle4.s.png");
            img2 = new GreenfootImage("PUZZLE/puzzle4.png");
        }else if(n == 4){
            img1 = new GreenfootImage("PUZZLE/puzzle5.s.png");
            img2 = new GreenfootImage("PUZZLE/puzzle5.png");
        }else if(n == 5){
            img1 = new GreenfootImage("PUZZLE/puzzle6.s.png");
            img2 = new GreenfootImage("PUZZLE/puzzle6.png");
        }else if(n == 6){
            img1 = new GreenfootImage("PUZZLE/puzzle7.s.png");
            img2 = new GreenfootImage("PUZZLE/puzzle7.png");
        }else if(n == 7){
            img1 = new GreenfootImage("PUZZLE/puzzle8.s.png");
            img2 = new GreenfootImage("PUZZLE/puzzle8.png");
        }else if(n == 8){
            img1 = new GreenfootImage("PUZZLE/puzzle9.s.png");
            img2 = new GreenfootImage("PUZZLE/puzzle9.png");
        }
        num = n;
        nx = x;
        ny = y;
    }
    /**
     * Act - do whatever the PuzzleClippings wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        change();
    }
    //還沒放進拼圖時用的
    public void change(){
        
        if(!init){
            if(Greenfoot.mouseDragged(this)) change = true;
            else if(Greenfoot.mouseDragEnded(this)) change = false;
            if(change){
                setImage(img2);
                followMouse();
            }else{
               setImage(img1);
               setLocation(nx, ny);
            }
        }        
    }
    public void followMouse(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null)
            setLocation(mouse.getX(), mouse.getY());
    }
    public boolean check(int t, int X, int Y){
        if(Math.abs(getX()-X)<100 
            && Math.abs(getY()-Y)<80){
            intoPuzzle(t, X, Y);
            return true;
        }else return false;
    }
    //放入拼圖中
    public void intoPuzzle(int t, int X, int Y){
        if(Greenfoot.mouseDragEnded(this) && t==num){
            init = true;
            change = false;
            setImage(img2);
            setLocation(X, Y);
        }
    }
}
