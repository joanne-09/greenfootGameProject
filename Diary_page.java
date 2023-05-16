import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Diary_page here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Diary_page extends Actor
{
    public int page = 0; //共有幾頁
    public int pageN = 0; //現在的頁碼
    public int pageS = 0; //應該要到的頁碼
    public GreenfootImage[] img;
    public Diary_page(int d){
        setText(d);
    }
    /**
     * Act - do whatever the Diary_page wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    public void setText(int d){
        if(d == 310){
            img = new GreenfootImage[1];
            img[0] = new GreenfootImage("DIARY/310.p.png");
        }else if(d == 315){
            img = new GreenfootImage[1];
            img[0] = new GreenfootImage("DIARY/315.p.png");
        }else if(d == 317){
            img = new GreenfootImage[1];
            img[0] = new GreenfootImage("DIARY/317.p.png");
        }else if(d == 318){
            img = new GreenfootImage[1];
            img[0] = new GreenfootImage("DIARY/318.p.png");
        }else if(d == 325){
            img = new GreenfootImage[1];
            img[0] = new GreenfootImage("DIARY/325.p.png");
        }else if(d == 326){
            img = new GreenfootImage[1];
            img[0] = new GreenfootImage("DIARY/326.p.png");
        }else if(d == 327){
            img = new GreenfootImage[1];
            img[0] = new GreenfootImage("DIARY/327.p.png");
        }else if(d == 328){
            img = new GreenfootImage[2];
            page = 1;
            img[0] = new GreenfootImage("DIARY/328.p.1.png");
            img[1] = new GreenfootImage("DIARY/328.p.2.png");
        }else if(d == 403){
            img = new GreenfootImage[1];
            img[0] = new GreenfootImage("DIARY/403.p.png");
        }else if(d == 404){
            img = new GreenfootImage[1];
            img[0] = new GreenfootImage("DIARY/404.p.png");
        }else if(d == 601){
            img = new GreenfootImage[1];
            img[0] = new GreenfootImage("DIARY/601.p.png");
        }else if(d == 718){
            img = new GreenfootImage[1];
            img[0] = new GreenfootImage("DIARY/718.p.png");
        }else if(d == 720){
            img = new GreenfootImage[1];
            img[0] = new GreenfootImage("DIARY/720.p.png");
        }else if(d == 729){
            img = new GreenfootImage[1];
            img[0] = new GreenfootImage("DIARY/729.p.png");
        }else if(d == 730){
            img = new GreenfootImage[1];
            img[0] = new GreenfootImage("DIARY/730.p.png");
        }else if(d == 731){
            img = new GreenfootImage[2];
            page = 1;
            img[0] = new GreenfootImage("DIARY/731.p.1.png");
            img[1] = new GreenfootImage("DIARY/731.p.2.png");
        }else if(d == 802){
            img = new GreenfootImage[1];
            img[0] = new GreenfootImage("DIARY/802.p.png");
        }else if(d == 901){
            img = new GreenfootImage[2];
            page = 1;
            img[0] = new GreenfootImage("DIARY/901.p.1.png");
            img[1] = new GreenfootImage("DIARY/901.p.2.png");
        }else if(d == 5303){
            img = new GreenfootImage[1];
            img[0] = new GreenfootImage("DIARY/5.303.p.png");
        }else if(d == 5311){
            img = new GreenfootImage[2];
            page = 1;
            img[0] = new GreenfootImage("DIARY/5.311.p.1.png");
            img[1] = new GreenfootImage("DIARY/5.311.p.2.png");
        }else if(d == 5312){
            img = new GreenfootImage[2];
            page = 1;
            img[0] = new GreenfootImage("DIARY/5.312.p.1.png");
            img[1] = new GreenfootImage("DIARY/5.312.p.2.png");
        }else if(d == 5707){
            img = new GreenfootImage[1];
            img[0] = new GreenfootImage("DIARY/5.707.p.png");
        }else if(d == 12111){
            img = new GreenfootImage[1];
            img[0] = new GreenfootImage("DIARY/5.1211.p.1.png");
        }else if(d == 12112){
            img = new GreenfootImage[1];
            img[0] = new GreenfootImage("DIARY/5.1211.p.2.png");
        }else if(d == 12113){
            img = new GreenfootImage[1];
            img[0] = new GreenfootImage("DIARY/5.1211.p.3.png");
        }else if(d == 12114){
            img = new GreenfootImage[1];
            img[0] = new GreenfootImage("DIARY/5.1211.p.4.png");
        }else if(d == 12115){
            img = new GreenfootImage[1];
            img[0] = new GreenfootImage("DIARY/5.1211.p.5.png");
        }else if(d == 070701){
            img = new GreenfootImage[1];
            img[0] = new GreenfootImage("DIARY/letter1.png");
        }else if(d == 070702){
            img = new GreenfootImage[1];
            img[0] = new GreenfootImage("DIARY/letter2.png");
        }else if(d == 070703){
            img = new GreenfootImage[1];
            img[0] = new GreenfootImage("DIARY/letter3.png");
        }else if(d == 070704){
            img = new GreenfootImage[1];
            img[0] = new GreenfootImage("DIARY/letter4.png");
        }else if(d == 070705){
            img = new GreenfootImage[1];
            img[0] = new GreenfootImage("DIARY/letter5.png");
        }else if(d == 070706){
            img = new GreenfootImage[1];
            img[0] = new GreenfootImage("DIARY/letter6.png");
        }
        else{
            img = new GreenfootImage[1];
            img[0] = new GreenfootImage("DIARY/else.p.png");
        }
        setImage(img[0]);
    }
    /**
     * overrides the 'setLocation(int, int)' method of the Actor class so the counter cannot be relocated
     * (it removes the implementation provided by the Actor class)
     */
    public void setLocation(int x, int y) {}
}
