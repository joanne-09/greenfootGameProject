import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tree here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tree extends Obstacle
{
    public Tree(int i){
        if(i == 1) setImage("OBSTACLE/F1/tree1.png");
        else if(i == 2) setImage("OBSTACLE/F1/tree2.png");
        else if(i == 3) setImage("OBSTACLE/F1/tree3.png");
        else if(i == 4) setImage("OBSTACLE/F1/tree4.png");
        else if(i == 5) setImage("OBSTACLE/F1/tree5.png");
        else if(i == 6) setImage("OBSTACLE/F1/tree6.png");
        else if(i == 7) setImage("OBSTACLE/F3/apple.png");
        else if(i == 8) setImage("OBSTACLE/F3/orange_tree.png");
        else if(i == 9) setImage("OBSTACLE/F3/yellow_tree.png");
        else if(i == 10) setImage("OBSTACLE/F3/tree.png");
        else if(i == 11) setImage("OBSTACLE/F3/old_tree.png");
        else if(i == 12)  setImage("OBSTACLE/F2/Tree1.png");
        else if(i == 13)  setImage("OBSTACLE/F2/Tree2.png");
    }
    /**
     * Act - do whatever the Tree wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        showRon();
    }
}
