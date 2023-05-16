import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;

/**
 * Write a description of class Notify here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Notify extends Actor
{
    public int duration = -1;
    public boolean inWorld = false;
    public boolean haveAdd = false;
    private GreenfootImage text;
    public static final GreenfootImage mapImage = new GreenfootImage(790,50);
    static
    {
        mapImage.setColor(Color.WHITE);
        mapImage.fill();
    }
    public Notify(){
        GreenfootImage image = new GreenfootImage(mapImage);
        setImage(image);
    }
    public void settext(int i){
        try{
            String[] lines = readText();
            text = new GreenfootImage(lines[i], 30, Color.BLACK, Color.WHITE);
        }catch(IOException ioe){
            text = new GreenfootImage("I couldn't read the story!",30, Color.WHITE, Color.BLACK);
        }
    }
    public void showText(int i){
        GreenfootImage image = new GreenfootImage(mapImage);
        settext(i);
        image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
                        (image.getHeight()-text.getHeight())/2);
        setImage(image);
    }
    public String[] readText() throws IOException
    {
        InputStream is = getClass().getClassLoader().getResourceAsStream("content/Notify.txt");
        BufferedReader r = new BufferedReader(new InputStreamReader(is));

        String[] lines = new String[30];
        
        for (int i = 0; i < 30; i++) {
            lines[i] = r.readLine();
        }
        
        return lines;
    }
    /**
     * Act - do whatever the Notify wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        if(inWorld && !haveAdd){
            duration = 40;
            haveAdd = true;
        }
        if(duration > 0) duration--;
        if(duration == 0){
            getWorld().removeObject(this);
            duration = -1;
            haveAdd = false;
        }
        if(getWorld()!=null) inWorld = true;
        else inWorld = false;
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
