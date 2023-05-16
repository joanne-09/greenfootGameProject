import greenfoot.*;
import java.io.*;
/**
 * CLASS: Map (subclass of Actor)<br>
 * AUTHOR: danpost (greenfoot.org username)<br>
 * VERSION DATE: April 15, 2015<br>
 * <br>
 * DESCRIPTION: a mini-map of the world within this project; the elements in the map array are
 * a sum of values (1 and 2) that indicate whether a connection is made to the next element in
 * that direction (right or down)
 */
public class F3Box extends Chatbox
{
    String reponse;
    public boolean inWorld_R = false;
    //private Noble n = new Noble();
    private int spaceClicked=-1;
    public boolean pause_R = false;
    private boolean isDown = false;
    private static final Color transparent = new Color(0,0,0,0);
    
    private int peo = 0;
     //-1:for choose 0:yes 1:no -2:選完一次後，再遇見說的話
    public int choose = -1;
    
    private boolean isclick = false;
    
    //girl
    private int girl_max = 17;
    //farmer
    private int farmer_max = 19;
    
    private GreenfootImage text;
    
    //battle
    public boolean gotoBattle = false;
    public boolean ngotoBattle = false;
    //riddle
    public Notify notify = new Notify(); //系統提示
    private int wh = -1;//誰要講話(支線人物才要用)
    public int times = 0;
    public boolean first = true;
    public boolean rid = false;
    //Frame3 w = (Frame3)getWorld();
    public Choose[] ans = new Choose[4];
    {
        for(int i=0; i<4; i++) ans[i] = new Choose(i+2);
        //choose 裡面是A B C D的圖片
    }
    public Riddle[] riddle = new Riddle[2];
    {
        for(int i=0; i<2; i++) riddle[i] = new Riddle(3, i);
    }
    //Riddle裡面是放要出現哪個謎題
    
    // the initial image
    public static final GreenfootImage mapImage = new GreenfootImage(790,50);
    static
    {
        mapImage.setColor(Color.WHITE);
        mapImage.setTransparency(215);
        mapImage.fill();
    }        
    /** initializes map array and image */
    public F3Box(int z)
    {
        GreenfootImage image = new GreenfootImage(mapImage);
        setImage(image);
        peo = z;
    }
    public void settext(int z, int i, int c){
        try {
            String [] lines;
            if(z == 2){
                lines = CreadText(c);
                text = new GreenfootImage(lines[i],30, Color.BLACK, Color.WHITE); 
            }else if(z == 3){
                lines = NreadText(c);
                text = new GreenfootImage(lines[i],30, Color.BLACK, Color.WHITE); 
            }else if(z == 4){
                lines = FreadText(c);
                text = new GreenfootImage(lines[i],30, Color.BLACK, Color.WHITE); 
            }else if(z == 5){
                lines = OreadText(c);
                text = new GreenfootImage(lines[i],30, Color.BLACK, Color.WHITE); 
            }else if(z == 6){
                lines = TreadText(c);
                text = new GreenfootImage(lines[i],30, Color.BLACK, Color.WHITE); 
            }else if(z == 7){
                lines = GreadText(c);
                text = new GreenfootImage(lines[i],30, Color.BLACK, Color.WHITE); 
            }else if(z == 8){
                lines = JreadText(c);
                text = new GreenfootImage(lines[i],30, Color.BLACK, Color.WHITE); 
            }else if(z == 9){
                lines = BreadText(c);
                text = new GreenfootImage(lines[i],30, Color.BLACK, Color.WHITE); 
            }/*else if(z == 5){
                lines = BreadText(c);
                text = new GreenfootImage(lines[i],30, Color.BLACK, Color.WHITE); 
            }*/
        }
        catch (IOException ioe) {
            text = new GreenfootImage("I couldn't read the story!",30, Color.WHITE, Color.BLACK);
        }
    }
    //馬車 Carriage
    public void CshowText(){
        GreenfootImage image = new GreenfootImage(mapImage);
        if(Greenfoot.isKeyDown("space") && !isDown) {
            spaceClicked++;
            isDown = true;          
        }
        if(!Greenfoot.isKeyDown("space") && isDown) isDown=false;
        if(spaceClicked == -1) ;
        else if(spaceClicked > 2){
            pause_R = false;
            spaceClicked=-1;
            //w.pause = false;
            image.clear();
            setImage(image);
        }else{                
            //if(spaceClicked > 0) w.pause = true;
            pause_R = true;
            settext(peo, spaceClicked, choose);
            image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
                            (image.getHeight()-text.getHeight())/2);
            setImage(image);
            if(spaceClicked == 2 && isclick == false){
                for (Object obj : getWorld().getObjects(Noble.class))
                { 
                    Noble n = (Noble) obj;
                    n.walk();
                    Frame3 w = (Frame3)getWorld();
                    w.nobleappear = true;
                }
                isclick = true;
            }
        }        
    }
    public String[] CreadText(int c) throws IOException
    {
        InputStream is = getClass().getClassLoader().getResourceAsStream("content/F3/Carriage.txt");
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        
        String[] lines = new String[3];
        
        for (int i = 0; i < 3; i++) {
            lines[i] = r.readLine();
        }
        
        return lines;
    }
    //貴族 noble
    public void NshowText(){
        GreenfootImage image = new GreenfootImage(mapImage);
        if(Greenfoot.isKeyDown("space") && !isDown) {
            spaceClicked++;
            isDown = true;          
        }
        if(!Greenfoot.isKeyDown("space") && isDown) isDown=false;
        if(spaceClicked == -1) ;
        else if(spaceClicked > 3){
            pause_R = false;
            spaceClicked=-1;
            //w.pause = false;
            image.clear();
            setImage(image);
        }else{                
            //if(spaceClicked > 0) w.pause = true;
            pause_R = true;
            settext(peo, spaceClicked, choose);
            image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
                            (image.getHeight()-text.getHeight())/2);
            setImage(image);
            if(spaceClicked == 3){
                ngotoBattle = true;
            }
        }        
    }
    public String[] NreadText(int n) throws IOException
    {
        InputStream is = getClass().getClassLoader().getResourceAsStream("content/F3/Noble.txt");
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        
        String[] lines = new String[4];
        
        for (int i = 0; i < 4; i++) {
            lines[i] = r.readLine();
        }
        
        return lines;
    }
    
    //農人
    public void FshowText(){
        GreenfootImage image = new GreenfootImage(mapImage);
        if(Greenfoot.isKeyDown("space") && !isDown) {
            spaceClicked++;
            isDown = true;          
        }
        if(!Greenfoot.isKeyDown("space") && isDown) isDown=false;
        if(spaceClicked == -1) ;
        else if(spaceClicked > farmer_max){
            pause_R = false;
            spaceClicked=-1;
            //w.pause = false;
            image.clear();
            setImage(image);
        }else{                
            //if(spaceClicked > 0) w.pause = true;
            pause_R = true;
            settext(peo, spaceClicked, choose);
            image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
                            (image.getHeight()-text.getHeight())/2);
            setImage(image);
            riddle(10, 2, 11, 0);
            if(choose!=-1){
                getWorld().addObject(notify, 400, 470);
                notify.showText(choose);
            }else if(spaceClicked == -1);
            if(choose == 7){
                farmer_max = 11;
            }else if(choose == 6){
                farmer_max = 19;
            }
        }        
    }
    public String[] FreadText(int f) throws IOException
    {
        InputStream is = getClass().getClassLoader().getResourceAsStream("content/F3/Farmer.txt");
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        
        String[] lines = new String[20];
        
        for (int i = 0; i < 20; i++) {
            lines[i] = r.readLine();
        }
        
        return lines;
    }
    
    //一開始的場景 the one / the second
    public void OshowText(){
        GreenfootImage image = new GreenfootImage(mapImage);
        if(Greenfoot.isKeyDown("space") && !isDown) {
            spaceClicked++;
            isDown = true;          
        }
        if(!Greenfoot.isKeyDown("space") && isDown) isDown=false;
        if(spaceClicked == -1) ;
        else if(spaceClicked > 2){
            pause_R = false;
            spaceClicked = -1;
            //w.pause = false;
            image.clear();
            setImage(image);
        }else{                
            //if(spaceClicked > 0) w.pause = true;
            pause_R = true;
            settext(peo, spaceClicked, choose);
            image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
                            (image.getHeight()-text.getHeight())/2);
            setImage(image);
        }        
    }
    public String[] OreadText(int o) throws IOException
    {
        InputStream is = getClass().getClassLoader().getResourceAsStream("content/F3/The_one.txt");
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        
        String[] lines = new String[3];
        
        for (int i = 0; i < 3; i++) {
            lines[i] = r.readLine();
        }
        
        return lines;
    }
    //Talker_A
     public void TshowText(){
        GreenfootImage image = new GreenfootImage(mapImage);
        if(Greenfoot.isKeyDown("space") && !isDown) {
            spaceClicked++;
            isDown = true;          
        }
        if(!Greenfoot.isKeyDown("space") && isDown) isDown=false;
        if(spaceClicked == -1) ;
        else if(spaceClicked > 7){
            pause_R = false;
            spaceClicked=-1;
            //w.pause = false;
            image.clear();
            setImage(image);
        }else{                
            //if(spaceClicked > 0) w.pause = true;
            pause_R = true;
            settext(peo, spaceClicked, choose);
            image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
                            (image.getHeight()-text.getHeight())/2);
            setImage(image);
        }        
    }
    public String[] TreadText(int t) throws IOException
    {
        InputStream is = getClass().getClassLoader().getResourceAsStream("content/F3/Talker_A.txt");
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        
        String[] lines = new String[8];
        
        for (int i = 0; i < 8; i++) {
            lines[i] = r.readLine();
        }
        
        return lines;
    }
    //Catherine
     public void GshowText(){
        GreenfootImage image = new GreenfootImage(mapImage);
        if(Greenfoot.isKeyDown("space") && !isDown) {
            spaceClicked++;
            isDown = true;          
        }
        if(!Greenfoot.isKeyDown("space") && isDown) isDown=false;
        if(spaceClicked == -1) ;
        else if(spaceClicked > girl_max){
            pause_R = false;
            spaceClicked=-1;
            //w.pause = false;
            image.clear();
            setImage(image);
            first = false;
            Frame3 w = (Frame3)getWorld();
            w.chat[3] = true;
        }else{                
            //if(spaceClicked > 0) w.pause = true;
            pause_R = true;
            settext(peo, spaceClicked, choose);
            image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
                            (image.getHeight()-text.getHeight())/2);
            setImage(image);
            Frame3 w = (Frame3)getWorld();
            if(w.chat[3] == false) riddle(13, 3, 14, 1);
            if(choose!=-1 /*&& first*/ && !w.chat[3]){
                getWorld().addObject(notify, 400, 470);
                notify.showText(choose);
            }else if(spaceClicked == -1);
            if(choose == 4){
                girl_max = 15;
            }
            if(w.chat[3]/*!first*/ && girl_max != 15 && spaceClicked == 13){
                spaceClicked += 3;
            }
        }        
    }
    public String[] GreadText(int c) throws IOException
    {
        InputStream is = getClass().getClassLoader().getResourceAsStream("content/F3/Catherine.txt");
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        
        String[] lines = new String[18];
        
        for (int i = 0; i < 18; i++) {
            lines[i] = r.readLine();
        }
        
        return lines;
    }
    //強森
    public void JshowText(){
        GreenfootImage image = new GreenfootImage(mapImage);
        if(Greenfoot.isKeyDown("space") && !isDown) {
            spaceClicked++;
            isDown = true;          
        }
        if(!Greenfoot.isKeyDown("space") && isDown) isDown=false;
        if(spaceClicked == -1) ;
        else if(spaceClicked > 15){
            pause_R = false;
            spaceClicked=-1;
            //w.pause = false;
            image.clear();
            setImage(image);
        }else{                
            //if(spaceClicked > 0) w.pause = true;
            pause_R = true;
            settext(peo, spaceClicked, choose);
            image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
                            (image.getHeight()-text.getHeight())/2);
            setImage(image);
            if(spaceClicked == 15){
                gotoBattle = true;
            }
        }        
    }
    public String[] JreadText(int c) throws IOException
    {
        InputStream is = getClass().getClassLoader().getResourceAsStream("content/F3/Johnson.txt");
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        
        String[] lines = new String[16];
        
        for (int i = 0; i < 16; i++) {
            lines[i] = r.readLine();
        }
        
        return lines;
    }
    //Someone
    public void BshowText(){
        GreenfootImage image = new GreenfootImage(mapImage);
        if(Greenfoot.isKeyDown("space") && !isDown) {
            spaceClicked++;
            isDown = true;          
        }
        if(!Greenfoot.isKeyDown("space") && isDown) isDown=false;
        if(spaceClicked == -1) ;
        else if(spaceClicked > 3){
            pause_R = false;
            spaceClicked=-1;
            //w.pause = false;
            image.clear();
            setImage(image);
            Frame3 w = (Frame3)getWorld();
            w.isattack = true;
        }else{                
            //if(spaceClicked > 0) w.pause = true;
            pause_R = true;
            settext(peo, spaceClicked, choose);
            image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
                            (image.getHeight()-text.getHeight())/2);
            setImage(image);
        }        
    }
     public String[] BreadText(int c) throws IOException
    {
        InputStream is = getClass().getClassLoader().getResourceAsStream("content/F3/Someone.txt");
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        
        String[] lines = new String[4];
        
        for (int i = 0; i < 4; i++) {
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
            //Frame3 w = (Frame3)getWorld();
            //w.npc_ismove();
        }      
    }
    
    public void riddle(int t, int a, int s, int r){
        /*
         w : 誰要講話(暫不用)
         t : spaceclicked 到第幾時候要切換riddle
         a : 答案是abcd哪個
         s : 謎題結束之後要回到哪個數字
         r : 是哪一個謎題
         */
        boolean wrongKey = false;
        Frame3 w = (Frame3)getWorld();
        w.isattack = false;
        for(int i=0; i<4; i++){
            if(i==a) ;
            else if(Greenfoot.mouseClicked(ans[i])){
                wrongKey = true;
                break;
            }
        }
        if(spaceClicked == t /*times==0*/){
            rid = true;
            getWorld().addObject(riddle[r], 400, 250);
            //System.out.print(w.chat[3]);
            // System.out.print(times);
            //加入abcd
            for(int i=0; i<4; i++) getWorld().addObject(ans[i], 90+160*i, 470);
        }
        if(Greenfoot.mouseClicked(ans[a])){
            if(r == 1){
                choose = 4;
                rid = false;
                spaceClicked = s; 
            }
            else if(r == 0){
                choose = 6;
                rid = false;
                spaceClicked = s + 1; 
            }
        }else if(wrongKey){
            if(r == 1){
                choose = 5;
                rid = false;
                spaceClicked = s + 2;
            }
            else if(r == 0){
                choose = 7;
                rid = false;
                spaceClicked = s;
            }
        }
        if(!rid && ans[0].getWorld() != null){
            //spaceClicked = s;
            getWorld().removeObject(riddle[r]);
            for(int i=0; i<4; i++) getWorld().removeObject(ans[i]);
        }
    }
    public void act(){
        inWorld_R = notify.inWorld;
    }
    
    public void move(int dist) {}
    /**
     * overrides the 'setLocation(int, int)' method of the Actor class so the counter cannot be relocated
     * (it removes the implementation provided by the Actor class)
     */
    public void setLocation(int x, int y) {}
}
