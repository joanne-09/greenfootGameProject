import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class Coinsnum here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Coinsnum extends InStore
{
    private int num = 0;
    private static final Color transparent = new Color(0,0,0,0);
    private String prefix;
    private int value;
    private GreenfootImage background;
    public Coinsnum(){
        this(new String());
    }

    public Coinsnum(String prefix)
    {
        background = getImage();
        //background.setTransparency(0);
        value = 0;
        this.prefix = prefix;
        updateImage();
    }
    public void act()
    {
        setValue();
        //setTransparency(0);
    }
    public void setValue()
    {
        Store s = (Store)getWorld();
        value = s.var[1];
        updateImage();
    }
    /*public void setPrefix(String prefix)
    {
        this.prefix = prefix;
        updateImage();
    }*/
    private void updateImage()
    {
        GreenfootImage image = new GreenfootImage(background);
        GreenfootImage text = new GreenfootImage(prefix + value, 30, Color.BLACK, transparent);
        
        if (text.getWidth() > image.getWidth() - 20)
        {
            image.scale(text.getWidth() + 20, image.getHeight());
        }
        
        image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
                        (image.getHeight()-text.getHeight())/2);
        setImage(image);
    }
}
