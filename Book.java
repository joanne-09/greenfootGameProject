import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Book here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Book extends Buttons
{
    public Book(int i){
        if(i == 0 || i == 1) setImage("book1.png");
        else if(i == 2) setImage("book2.png");
        else if(i == 3) setImage("book3.png");
        else if(i == 4) setImage("book4.png");
        else if(i == 5) setImage("book5.png");
        else if(i == 6) setImage("book6.png");
    }
    /**
     * Act - do whatever the Book wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        if(Greenfoot.mouseClicked(this))
            getWorld().removeObject(this);    
    }
}
