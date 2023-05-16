import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Choose here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Choose extends Buttons
{
    public Choose(int c){
        if(c == 0)       setImage("yes.png");
        else if(c == 1)  setImage("no.png");
        else if(c == 2)  setImage("A.png");
        else if(c == 3)  setImage("B.png");
        else if(c == 4)  setImage("C.png");
        else if(c == 5)  setImage("D.png");
        else if(c == 6)  setImage("a24.png");
        else if(c == 7)  setImage("a25.png");
        else if(c == 8)  setImage("a00.png");
        else if(c == 9)  setImage("a01.png");
        else if(c == 10) setImage("a02.png");
        else if(c == 11) setImage("a03.png");
        else if(c == 12) setImage("a04.png");
        else if(c == 13) setImage("a05.png");
        else if(c == 14) setImage("a06.png");
        else if(c == 15) setImage("a07.png");
        else if(c == 16) setImage("a08.png");
        else if(c == 17) setImage("a09.png");
        else if(c == 18) setImage("a10.png");
        else if(c == 19) setImage("a11.png");
        else if(c == 20) setImage("a12.png");
        else if(c == 21) setImage("a13.png");
        else if(c == 22) setImage("a14.png");
        else if(c == 23) setImage("a15.png");
        else if(c == 24) setImage("a16.png");
        else if(c == 25) setImage("a17.png");
        else if(c == 26) setImage("a18.png");
        else if(c == 27) setImage("a19.png");
        else if(c == 28) setImage("a20.png");
        else if(c == 29) setImage("a21.png");
        else if(c == 30) setImage("a22.png");
        else if(c == 31) setImage("a23.png");
        else if(c == 32) setImage("password09.png");
        else if(c == 33) setImage("NUMBER/0.png");
        else if(c == 34) setImage("NUMBER/1.png");
        else if(c == 35) setImage("NUMBER/2.png");
        else if(c == 36) setImage("NUMBER/3.png");
        else if(c == 37) setImage("NUMBER/4.png");
        else if(c == 38) setImage("NUMBER/5.png");
        else if(c == 39) setImage("NUMBER/6.png");
        else if(c == 40) setImage("NUMBER/7.png");
        else if(c == 41) setImage("NUMBER/8.png");
        else if(c == 42) setImage("NUMBER/9.png");
        else if(c == 43) setImage("NUMBER/slash.png");
        else if(c == 44) setImage("NUMBER/3B1.png");
        else if(c == 45) setImage("NUMBER/3B2.png");
        else if(c == 46) setImage("NUMBER/3B3.png");
        else if(c == 47) setImage("NUMBER/3B4.png");
        else if(c == 48) setImage("NUMBER/3B5.png");
        else if(c == 49) setImage("NUMBER/3B6.png");
        else if(c == 50) setImage("NUMBER/3B7.png");
        else if(c == 51) setImage("NUMBER/3B8.png");
        else if(c == 52) setImage("NUMBER/3B9.png");
        else if(c == 53) setImage("NUMBER/3B10.png");

    }
    /**
     * Act - do whatever the Choose wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    /**
     * overrides the 'move(int)' method of the Actor class so the counter cannot be moved
     * (it removes the implementation provided by the Actor class)
     */
    public void move(int dist) {}
    /**
     * overrides the 'setLocation(int, int)' method of the Actor class so the counter cannot be relocated
     * (it removes the implementation provided by the Actor class)
     */
    public void setLocation(int x, int y) {}
}
