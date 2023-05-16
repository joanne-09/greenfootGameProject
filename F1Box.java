import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;

/**
 * Write a description of class Box here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class F1Box extends Chatbox
{
    public Notify notify = new Notify();
    public boolean inWorld_R = false;
    public int leng = 0;
    public int spaceClicked = 0;
    public int times = 0;
    public boolean pause_R = false;
    private boolean isDown = false;
    //for villager
    //-1:for choose 0:yes 1:no -2:選完一次後，再遇見說的話
    public int choose = -1;
    // Old:2 Baker:3 Beggar(A):4 Villager:5
    private int peo = 0;
    //for Branch
    //0:支線1 1:支線2
    private int wh = -1;
    
    public String[] ans_IN = {"0", "7", "/", "2", "2"};
    
    public boolean rid = false;
    public Choose[] ans = new Choose[4];
    {
        for(int i=0; i<4; i++) ans[i] = new Choose(i+2);
    }
    public Riddle[] riddle = new Riddle[2];
    public Riddle emblem = new Riddle(1, 2);
    {
        for(int i=0; i<2; i++) riddle[i] = new Riddle(1, i);
    }
    public Riddle riddle7 = new Riddle(1, 7);
    
    public boolean gotoBattle = false;
    
    private GreenfootImage text;
    // the initial image
    public static final GreenfootImage mapImage = new GreenfootImage(790,50);
    static
    {
        mapImage.setColor(Color.WHITE);
        mapImage.setTransparency(215);
        mapImage.fill();
    }
    /** initializes map array and image */
    public F1Box(int z, int w)
    {
        GreenfootImage image = new GreenfootImage(mapImage);
        setImage(image);
        
        peo = z;
        wh = w;
    }
    public void settext(int z, int i, int c){
        try {
            String [] lines;
            if(z == 1) lines = BRreadText(c);
            else if(z == 2) lines = OreadText(c);
            else if(z == 3) lines = BreadText(c);
            else if(z == 4) lines = AreadText(c);
            else lines = VreadText(c);
            text = new GreenfootImage(lines[i],30, Color.BLACK, Color.WHITE); 
        }
        catch (IOException ioe) {
            text = new GreenfootImage("I couldn't read the story!",30, Color.WHITE, Color.BLACK);
        }
    }
    public void riddle(int who, int showRid, int answer, int rear, int r, boolean ch){
        if(wh == who){
            if(spaceClicked == showRid && !ch) rid = true;
            if(rid){
                getWorld().addObject(riddle[r], 400, 250);
                riddle[r].riddle(answer);
                choose = riddle[r].choose;
            }
            if(riddle[r].getWorld()==null && choose!=-1){
                rid = false;
                spaceClicked = rear;
            }
        }
    }
    public void riddle_IN(int showRid, int rear, boolean ch){
        if(wh == 4){
            if(spaceClicked == showRid && !ch){
                getWorld().addObject(riddle7, 400, 250);
                riddle7.key = 0;
                riddle7.now = 0;
                riddle7.right = 0;
                riddle7.answers = ans_IN;
                riddle7.ans_num = 5;
            }
        }
    }
    public void BRshowText(int sp, boolean ch){
        GreenfootImage image = new GreenfootImage(mapImage);
        //riddle(who, showRid, answer, leaveRid, whichRiddle, chat)
        riddle(0, 27, 1, 16, 0, ch);
        riddle(1, 0, 2, 0, 1, ch);
        riddle_IN(1, 2, ch);
        if(choose!=-1 && choose<20){
            getWorld().addObject(notify, 400, 470);
            notify.showText(choose);
            if(wh==3) choose=20;
        }else if(spaceClicked == -1) ;
        else if(spaceClicked > leng && spaceClicked!=26 && spaceClicked!=27){
            if(wh==1) getWorld().removeObject(emblem);
            pause_R = false;
            spaceClicked=-1;
            image.clear();
            setImage(image);
        }else{
            pause_R = true;
            if(wh == 1 && spaceClicked+sp == 0) getWorld().addObject(emblem, 400, 254);
            if(wh == 1 && spaceClicked+sp == -2) ;
            else{
                if(wh == 0 && spaceClicked == 15 && !ch) spaceClicked = 26;
                // if(wh == 5 && spaceClicked == 3) gotoBattle = true;
                settext(peo, spaceClicked+sp, wh);
                image.drawImage(text, 30, 
                                (image.getHeight()-text.getHeight())/2);
                setImage(image);
            }
        }
        if(Greenfoot.isKeyDown("space") && !isDown && !inWorld_R) {
            spaceClicked++;
            isDown = true;          
        }
        if(!Greenfoot.isKeyDown("space") && isDown) isDown=false;
    }
    public String[] BRreadText(int c) throws IOException
    {
        InputStream is;
        if(c == 0){
            is = getClass().getClassLoader().getResourceAsStream("content/F1/Branch0.txt");
            leng = 24;
        }else if(c == 6){
            is = getClass().getClassLoader().getResourceAsStream("content/F1/Branch1.txt");
            leng = 15;
        }else if(c == 1){
            is = getClass().getClassLoader().getResourceAsStream("content/F1/Branch2.txt");
            leng = 0;
        }else if(c == 2){
            is = getClass().getClassLoader().getResourceAsStream("content/F1/Branch3.txt");
            leng = 16;
        }else{
            is = getClass().getClassLoader().getResourceAsStream("content/F1/Branch.txt");
            if(c == 3){
                if(choose == -1) leng = 1; //start with 1 chat==true:4
                else leng = 0;
            }else if(c == 4) leng = 0; //start with 6 chat==true:8
            else if(c == 5){
                if(choose == -1) leng = 2; //start with 10
                else leng = 0; //win:14 lose:15 chat==true:17
            }
        }
        BufferedReader r = new BufferedReader(new InputStreamReader(is));

        String[] lines = new String[30];
        
        for (int i = 0; i < 30; i++) {
            lines[i] = r.readLine();
        }
        
        return lines;
    }
    public void OshowText(){
        GreenfootImage image = new GreenfootImage(mapImage);
        if(Greenfoot.isKeyDown("space") && !isDown) {
            spaceClicked++;
            isDown = true;          
        }
        if(!Greenfoot.isKeyDown("space") && isDown) isDown=false;
        if(spaceClicked == -1) ;
        else if(spaceClicked > 10){
            pause_R = false;
            spaceClicked=-1;
            image.clear();
            setImage(image);
        }else{
            pause_R = true;
            settext(peo, spaceClicked, choose);
            image.drawImage(text, 30, 
                            (image.getHeight()-text.getHeight())/2);
            setImage(image);
        }        
    }
    public String[] OreadText(int c) throws IOException
    {
        InputStream is = getClass().getClassLoader().getResourceAsStream("content/F1/Old.txt");
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        
        String[] lines = new String[11];
        
        for (int i = 0; i < 11; i++) {
            lines[i] = r.readLine();
        }
        
        return lines;
    }
    public void BshowText(){
        GreenfootImage image = new GreenfootImage(mapImage);
        if(Greenfoot.isKeyDown("space") && !isDown) {
            spaceClicked++;
            isDown = true;          
        }
        if(!Greenfoot.isKeyDown("space") && isDown) isDown=false;
        if(spaceClicked == -1) ;
        else if(spaceClicked > 6){
            pause_R = false;
            spaceClicked=-1;
            image.clear();
            setImage(image);
        }else{ 
            pause_R = true;
            settext(peo, spaceClicked, choose);
            image.drawImage(text, 30, 
                            (image.getHeight()-text.getHeight())/2);
            setImage(image);
        }
    }
    public String[] BreadText(int c) throws IOException
    {
        InputStream is = getClass().getClassLoader().getResourceAsStream("content/F1/Baker.txt");
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        
        String[] lines = new String[10];
        
        for (int i = 0; i < 10; i++) {
            lines[i] = r.readLine();
        }
        
        return lines;
    }
    public void AshowText(){
        GreenfootImage image = new GreenfootImage(mapImage);
        if(Greenfoot.isKeyDown("space") && !isDown) {
            spaceClicked++;
            isDown = true;          
        }
        if(!Greenfoot.isKeyDown("space") && isDown) isDown=false;
        if(spaceClicked == -1) ;
        else if(spaceClicked > 4){
            pause_R = false;
            spaceClicked=-1;
            image.clear();
            setImage(image);
        }else{
            pause_R = true;
            settext(peo, spaceClicked, choose);
            image.drawImage(text, 30, 
                            (image.getHeight()-text.getHeight())/2);
            setImage(image);
            if(spaceClicked == 4) gotoBattle = true;
        }
    }
    public String[] AreadText(int c) throws IOException
    {
        InputStream is = getClass().getClassLoader().getResourceAsStream("content/F1/Assassin.txt");
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        
        String[] lines = new String[10];
        
        for (int i = 0; i < 10; i++) {
            lines[i] = r.readLine();
        }
        
        return lines;
    }
    private Choose yes = new Choose(0); //choose == 0
    private Choose no = new Choose(1); //choose == 1
    public void VshowText(){
        GreenfootImage image = new GreenfootImage(mapImage);
        if(Greenfoot.mouseClicked(yes)){
            choose = 0;
            getWorld().removeObject(yes);
            getWorld().removeObject(no);
        }else if(Greenfoot.mouseClicked(no)){
            choose = 1;
            getWorld().removeObject(yes);
            getWorld().removeObject(no);          
        }
        if(Greenfoot.isKeyDown("space") && !isDown) {
            spaceClicked++;
            isDown = true;          
        }
        if(spaceClicked == -1) ;
        else if(spaceClicked > leng){
            pause_R = false;
            if(choose==0 || choose==1) choose = -2;
            if(yes.getWorld() != null && choose==-1){
                getWorld().removeObject(yes);
                getWorld().removeObject(no);
            }
            spaceClicked=-1;
            image.clear();
            setImage(image);
        }else{
            pause_R = true;
            settext(peo, spaceClicked, choose);
            image.drawImage(text, 30, 
                            (image.getHeight()-text.getHeight())/2);
            setImage(image);
            if(spaceClicked == 0 && choose==-1){
                getWorld().addObject(yes, 654, 469);
                getWorld().addObject(no, 744, 469);
            }
        }               
        if(!Greenfoot.isKeyDown("space") && isDown) isDown=false;       
    }
    public String[] VreadText(int w) throws IOException
    {
        InputStream is;
        if(w == 0){
            is = getClass().getClassLoader().getResourceAsStream("content/F1/Villager_Yes.txt");                       
            leng = 4;
        }else if(w == 1){
            is = getClass().getClassLoader().getResourceAsStream("content/F1/Villager_No.txt");
            leng = 4;
        }else if(w == -1){
            is = getClass().getClassLoader().getResourceAsStream("content/F1/Villager.txt");
            leng = 0;
        }else{
            is = getClass().getClassLoader().getResourceAsStream("content/F1/Villager_Else.txt");
            leng = 3;
        }
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        
        String[] lines = new String[10];
        
        for (int i = 0; i < 10; i++) {
            lines[i] = r.readLine();
        } 
        return lines;
    }
    public void reset(int i){
        if(i < 1){
            GreenfootImage image = new GreenfootImage(mapImage);
            setImage(image);
            spaceClicked = 0;
            if(getWorld() != null) getWorld().removeObject(this);
        }      
    }
    public void act(){
        inWorld_R = notify.inWorld;
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
