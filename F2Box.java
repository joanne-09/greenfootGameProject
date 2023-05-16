import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;

/**
 * Write a description of class F2Box here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class F2Box extends Chatbox
{
    //public Notify notify;
    public Notify[] notify = new Notify[3];
    public boolean haveShow = false;
    public boolean inWorld_R = false;
    public int spaceClicked = 0;
    
    private boolean isDown = false;
    
    //for storeOwner, -1:for choose 0:yes 1:no -2:選完一次後，再遇見說的話
    public int choose = -1;
    // StoreOwner:2 FortuneTeller:3 Paul:4 Wilson:5
    private int peo = 0;
    
    private static final Color transparent = new Color(0,0,0,0);
    private GreenfootImage text;
    
    public Choose input;
    public Choose[] ans = new Choose[4];
    public Choose[] alpha = new Choose[26];
    public Riddle[] riddle = new Riddle[3];
    public Riddle file;
    public Riddle secret;
    
    public boolean pause_R = false;
    private boolean rid = false;
    private int times = 0; //看這是第幾次輸入，共會輸入九次
    private int check = 0;
    public boolean openW3 = false;
    public int meet0 = 0;
    public int meet1 = 0;
    // the initial image
    public static final GreenfootImage mapImage = new GreenfootImage(790,50);
    static
    {
        mapImage.setColor(Color.WHITE);
        mapImage.setTransparency(215);
        mapImage.fill();
    }
    /** initializes map array and image*/
    public F2Box(int p)
    {
        GreenfootImage image = new GreenfootImage(mapImage);
        setImage(image);
        
        for(int i=0; i<3; i++)  notify[i] = new Notify();
        
        peo = p;
    }
    public void settext(int p, int i, int c){
        try {
            String [] lines;
            if(p == 2){
                lines = SreadText(c);
            }
            else if(p == 3){
                lines = FreadText(c);
            }
            else if(p == 4){
                lines = PreadText(c);
            } 
            else if(p == 5){
                lines = WreadText(c);
            } 
            else{
                lines = BRreadText(c);
            }
            text = new GreenfootImage(lines[i],30, Color.BLACK, Color.WHITE); 
        }
        catch (IOException ioe) {
            text = new GreenfootImage("I couldn't read the story!",30, Color.WHITE, Color.BLACK);
        }
    }
    private Choose yes = new Choose(0); //choose == 0
    private Choose no = new Choose(1); //choose == 1
    private int goDown = 6;
    public void SshowText(){
        GreenfootImage image = new GreenfootImage(mapImage);
        if(Greenfoot.mouseClicked(yes)){
            if(((Frame2)getWorld()).var[1] >= 8){
                ((Frame2)getWorld()).var[1] -= 8;
                choose = 0;
                spaceClicked = 0;
                goDown = 2;
                getWorld().removeObject(yes);
                getWorld().removeObject(no);
                getWorld().addObject(notify[0], 400, 470);
                notify[0].showText(19);
                getWorld().addObject(notify[1], 400, 420);
                notify[1].showText(15);
                getWorld().addObject(notify[2], 400, 370);
                notify[2].showText(3);
            }
            else{
                getWorld().addObject(notify[0], 400, 470);
                notify[0].showText(22);
                //spaceClicked = 0;
            }
        }
        else if(Greenfoot.mouseClicked(no)){
            choose = 1;
            spaceClicked = 0;
            goDown = 2;
            getWorld().removeObject(yes);
            getWorld().removeObject(no);
            getWorld().addObject(notify[0], 400, 470);
            notify[0].showText(23);
        }
        if(Greenfoot.isKeyDown("space") && !isDown) {
            spaceClicked++;
            isDown = true;   
        }
        if(!Greenfoot.isKeyDown("space") && isDown) {
            isDown=false;
        }
        if(spaceClicked == -1)       ;
        else if(spaceClicked > goDown){
            if(/*spaceClicked == 2*/choose == 0 || choose == 1){
                goDown = 1;
                choose = -2;
            }
            else if(spaceClicked > 6 ){
                goDown = 6;
                choose = -1;
                if(yes.getWorld() != null){
                    getWorld().removeObject(yes);
                    getWorld().removeObject(no);
                }
            }
            pause_R = false;
            spaceClicked=-1;
            image.clear();
            setImage(image);
        }else{                
            pause_R = true;
            settext(peo, spaceClicked, choose);
            image.drawImage(text,30,(image.getHeight()-text.getHeight())/2);
            setImage(image);
            if(spaceClicked == 6){
                getWorld().addObject(yes, 654, 469);
                getWorld().addObject(no, 744, 469);
            }
        }        
    }
    public String[] SreadText(int c) throws IOException
    {
        InputStream is;
        if(c == 0)
            is = getClass().getClassLoader().getResourceAsStream("content/F2/StoreOwnerYes.txt");                       
        else if(c == 1)
            is = getClass().getClassLoader().getResourceAsStream("content/F2/StoreOwnerNo.txt");
        else if(c == -1)
            is = getClass().getClassLoader().getResourceAsStream("content/F2/StoreOwner.txt");
        else
            is = getClass().getClassLoader().getResourceAsStream("content/F2/StoreOwner_else.txt");
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        
        String[] lines = new String[8];
        for (int i = 0; i < 8; i++) {
            lines[i] = r.readLine();
        }
        
        return lines;
    }
    public void FshowText(){
        GreenfootImage image = new GreenfootImage(mapImage);
        riddle(5, 0, 6, 0);
        if(choose == -1)    goDown = 5;
        if(Greenfoot.isKeyDown("space") && !isDown && !rid) {
            spaceClicked++;
            isDown = true;          
        }
        if(!Greenfoot.isKeyDown("space") && isDown) isDown=false;
        if(spaceClicked == -1) ;
        else if(spaceClicked > goDown){
            if(goDown == 5){
                goDown = 0;
            }
            pause_R = false;
            spaceClicked=-1;
            image.clear();
            setImage(image);
        }else{     
            pause_R = true;
            settext(peo, spaceClicked, choose);
            image.drawImage(text, 30, (image.getHeight()-text.getHeight())/2);
            setImage(image);
        }
    }
    public String[] FreadText(int c) throws IOException
    {
        InputStream is;
        if(c == -1){
            is = getClass().getClassLoader().getResourceAsStream("content/F2/FortuneTeller.txt");
        }else{
            is = getClass().getClassLoader().getResourceAsStream("content/F2/FortuneTeller_else.txt");
        }
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        
        String[] lines = new String[6];
        for (int i = 0; i < 6; i++) {
            lines[i] = r.readLine();
        }
        
        return lines;
    }
    public void PshowText(){
        GreenfootImage image = new GreenfootImage(mapImage);
        riddle2(28, 0, 1);
        if(choose == -1) goDown = 28;
        if(Greenfoot.isKeyDown("space") && !isDown && !rid) {
            spaceClicked++;
            isDown = true;          
        }
        if(!Greenfoot.isKeyDown("space") && isDown) isDown=false;
        if(spaceClicked == -1) ;
        else if(spaceClicked > goDown){
            if(choose == 0) {
                choose = -2;
                goDown = 0;
            }
            if(riddle[1].getWorld() != null){
                getWorld().removeObject(riddle[1]);
                getWorld().removeObject(input);
                getWorld().removeObject(secret);
            }
            pause_R = false;
            spaceClicked=-1;
            image.clear();
            setImage(image);
        }else{ 
            pause_R = true;
            settext(peo, spaceClicked, choose);
            image.drawImage(text,30,(image.getHeight()-text.getHeight())/2);
            setImage(image);
        }
    }
    public String[] PreadText(int c) throws IOException
    {
        InputStream is;
        if(c == -1)
            is = getClass().getClassLoader().getResourceAsStream("content/F2/Paul.txt");
        else if(c == 0)
            is = getClass().getClassLoader().getResourceAsStream("content/F2/Paul_open.txt");
        else if(c == 1)
            is = getClass().getClassLoader().getResourceAsStream("content/F2/Paul_fail.txt");
        else
            is = getClass().getClassLoader().getResourceAsStream("content/F2/Paul_else.txt");
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        
        String[] lines = new String[30];
        
        for (int i = 0; i < 29; i++) {
            lines[i] = r.readLine();
        }
        
        return lines;
    }
    public void WshowText(){
        GreenfootImage image = new GreenfootImage(mapImage);
        if(((Frame2)getWorld()).npcState()){
            if(choose == -1)         goDown = 34;
            else if(choose == 0)     choose = 1;
            else if(choose == -2)    goDown = 13;
            if(Greenfoot.isKeyDown("space") && !isDown) {
                spaceClicked++;
                isDown = true;          
            }
            if(!Greenfoot.isKeyDown("space") && isDown) isDown=false;
            if(spaceClicked == -1) ;
            else if(spaceClicked > goDown){
                if(goDown == 34){
                    choose = 0;
                    goDown = 55;
                }
                if(choose == -2 && ((Frame2)getWorld()).chat[3]){
                    getWorld().addObject(notify[0], 400, 420);
                    notify[0].showText(3);
                }
                pause_R = false;
                spaceClicked=-1;
                image.clear();
                setImage(image);
            }else{                
                pause_R = true;
                settext(peo, spaceClicked, choose);
                image.drawImage(text, 30, (image.getHeight()-text.getHeight())/2);
                setImage(image);
            }
        }
        else{
            getWorld().addObject(notify[0], 400, 470);
            notify[0].showText(8);
        }
    }
    public String[] WreadText(int c) throws IOException
    {
        InputStream is;
        if(c == -1){
            is = getClass().getClassLoader().getResourceAsStream("content/F2/Wilson1.txt");
        }else if(c == 1){
            is = getClass().getClassLoader().getResourceAsStream("content/F2/Wilson2.txt");
        }else{
            is = getClass().getClassLoader().getResourceAsStream("content/F2/Wilson3.txt");
        }
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        
        String[] lines = new String[56];
        for (int i = 0; i < 56; i++) {
            lines[i] = r.readLine();
        }
        
        return lines;
    }

    //private int who = -1;//for Branch2 0:soldier1, 1:citizen, 2:Branch02
    public void BRshowText(int c){
        GreenfootImage image = new GreenfootImage(mapImage);
        //for Br0 & Br1
        if(c == 0 || c == 1){
            if(((Frame2)getWorld()).npcState()){
                if(c == 0)            goDown = 16;
                else if(c == 1){
                    goDown = 14;
                    openW3 = true;
                }
                if(Greenfoot.isKeyDown("space") && !isDown) {
                    spaceClicked++;
                    isDown = true;          
                }
                if(!Greenfoot.isKeyDown("space") && isDown) isDown=false;
                if(spaceClicked == -1) ;
                else if(spaceClicked > goDown){
                    if(meet0 == 0 && ((Frame2)getWorld()).chat[4]){
                        getWorld().addObject(notify[0], 400, 420);
                        notify[0].showText(3);
                        meet0++;
                    }
                    else if(meet1 == 0 && ((Frame2)getWorld()).chat[5]){
                        getWorld().addObject(notify[0], 400, 420);
                        notify[0].showText(3);
                        meet1++;
                    }
                    pause_R = false;
                    spaceClicked=-1;
                    image.clear();
                    setImage(image);
                }else{ 
                    pause_R = true;
                    settext(peo, spaceClicked, c);
                    image.drawImage(text,30,(image.getHeight()-text.getHeight())/2);
                    setImage(image);
                }
            }else{
                getWorld().addObject(notify[0], 400, 470);
                notify[0].showText(8);
            }
        }else{// use choose 
            riddle(1, 0, 0, 2);
            if(choose == -1) goDown = 2;
            else             goDown = 0;
            if(Greenfoot.isKeyDown("space") && !isDown &&!rid) {
                    spaceClicked++;
                    isDown = true;          
            }
            if(!Greenfoot.isKeyDown("space") && isDown) isDown=false;
            if(spaceClicked == -1) ;
            else if(spaceClicked > goDown){
                    pause_R = false;
                    spaceClicked=-1;
                    image.clear();
                    setImage(image);
            }else{ 
                    pause_R = true;
                    settext(peo, spaceClicked, choose);
                    image.drawImage(text,30,(image.getHeight()-text.getHeight())/2);
                    setImage(image);
            }
        }
    }
    public String[] BRreadText(int c) throws IOException
    {
        InputStream is;
        if(c == 0){
            is = getClass().getClassLoader().getResourceAsStream("content/F2/Soldier.txt");
        }else if(c == 1){
            is = getClass().getClassLoader().getResourceAsStream("content/F2/Citizen.txt");
        }else if(c == -1){
            is = getClass().getClassLoader().getResourceAsStream("content/F2/Branch02.txt");
        }else{
            is = getClass().getClassLoader().getResourceAsStream("content/F2/Branch02_else.txt");
        }
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        
        String[] lines = new String[17];
        for (int i = 0; i < 17; i++) {
            lines[i] = r.readLine();
        }
        
        return lines;
    }
    //riddles
    {
        for(int i=0; i<3; i++) riddle[i] = new Riddle(2, i);
    }
    //selective choose
    {
        for(int i=0; i<4; i++) ans[i] = new Choose(i+2);
    }
    public void riddle(int t, int a, int s, int r){
        /*
         w : 誰要講話, w = 0:FortuneTeller, w = 1:Paul
         t : spaceclicked 到第幾時候要切換riddle
         a : 答案是abcd哪個
         s : 謎題結束之後要回到哪個數字
         r : 是哪一個謎題 0:FortuneTeller, 2=:Branch02
         */
        boolean wrongKey = false;
        for(int i=0; i<4; i++){
            if(i==a) ;
            else if(Greenfoot.mouseClicked(ans[i])){
                wrongKey = true;
                break;
            }
        }
        if(spaceClicked == t /*&& times==0*/){
            rid = true;
            getWorld().addObject(riddle[r], 400, 250);
            for(int i=0; i<4; i++) getWorld().addObject(ans[i], 366+50*i, 323);
        }
        if(Greenfoot.mouseClicked(ans[a])){
            if(r == 0){
                getWorld().addObject(notify[0], 400, 420);
                notify[0].showText(0);
                getWorld().addObject(notify[1], 400, 370);
                notify[1].showText(15);
                getWorld().addObject(notify[2], 400, 320);
                notify[2].showText(3);
            }
            else if(r == 2){
                ((Frame2)getWorld()).var[1] += 3;
                getWorld().addObject(notify[0], 400, 470);
                notify[0].showText(0);
                getWorld().addObject(notify[1], 400, 420);               
                notify[1].showText(20); 
                goDown = 0;
            }
            rid = false;
            spaceClicked = s;
            choose = -2;
            getWorld().removeObject(riddle[r]);
            for(int i=0; i<4; i++) getWorld().removeObject(ans[i]);
        }else if(wrongKey){
            getWorld().addObject(notify[0], 400, 470);
            notify[0].showText(1);
            rid = false;
            spaceClicked = s ;
            choose = -3;//return to F2
            getWorld().removeObject(riddle[r]);
            for(int i=0; i<4; i++) getWorld().removeObject(ans[i]);
            if(r == 2)  goDown = 0;
        }
    }
    //Keyboard, input window, secret, file
    {
        for(int i=0; i<26; i++) alpha[i] = new Choose(i+6);
        input = new Choose(32);
        file = new Riddle(2, 3);
        secret = new Riddle(2, 4);
    }
    public void riddle2(int t, int w, int r){
        /*
         w : 誰要講話, 0:Paul, 1:Branch02
         t : spaceclicked 到第幾時候要切換riddle
         r : 是哪一個謎題 0:FortuneTeller, 1=:Branch02
         */
        boolean clicked = false;
        for(int i=0; i<26; i++){
            if(Greenfoot.mouseClicked(alpha[i])){
                clicked = true;
                break;
            }
        }
        if(spaceClicked == t /*&& times==0*/){
            rid = true;
            if(w == 0)getWorld().addObject(input, 658, 213);
            getWorld().addObject(riddle[r], 267, 162);
            for(int i=0; i<13; i++){
                getWorld().addObject(alpha[i], 102+50*i, 358);
                getWorld().addObject(alpha[i+13], 102+50*i, 407);
            }
        }
        if(clicked){
            times++;
            if(w == 0){
                if(times == 1){
                    input.setImage("password08.png");
                    if(Greenfoot.mouseClicked(alpha[15]))check++;
                }else if(times == 2){
                    input.setImage("password07.png");
                    if(Greenfoot.mouseClicked(alpha[20]))check++;
                }else if(times == 3){
                    input.setImage("password06.png");
                    if(Greenfoot.mouseClicked(alpha[15]))check++;
                }else if(times == 4){
                    input.setImage("password05.png");
                    if(Greenfoot.mouseClicked(alpha[15]))check++;
                }else if(times == 5){
                    input.setImage("password04.png");
                    if(Greenfoot.mouseClicked(alpha[4])) check++;
                }else if(times == 6){
                    input.setImage("password03.png");
                    if(Greenfoot.mouseClicked(alpha[19]))check++;
                }else if(times == 7){
                    input.setImage("password02.png");
                    if(Greenfoot.mouseClicked(alpha[17]))check++;
                }else if(times == 8){
                    input.setImage("password01.png");
                    if(Greenfoot.mouseClicked(alpha[20]))check++;
                }else if(times == 9){
                    input.setImage("password00.png");
                    if(Greenfoot.mouseClicked(alpha[13]))check++;
                }
            }
            clicked = false;
        }
        if(times == 9){
            if(check == 9){
                choose = 0;
                goDown = 1;
                for(int i=0; i<26; i++) getWorld().removeObject(alpha[i]);
                riddle[r].setImage("RIDDLE/Hint.png");
                input.setImage("RIDDLE/prince.png");
                getWorld().removeObject(riddle[r]);
                getWorld().removeObject(input);
                getWorld().addObject(file, 400, 250);
                getWorld().addObject(notify[0], 400, 470);
                notify[0].showText(15);
                getWorld().addObject(notify[1], 400, 420);
                notify[1].showText(3);
            }
            else{
                choose = 1;
                goDown = 0;
                check = 0;
                input.setImage("password09.png");
            }
            spaceClicked = 0;
            times = 0;
        }
        if(Greenfoot.mouseClicked(file)){
            rid = false;
            spaceClicked = 1;
            getWorld().removeObject(file);
            getWorld().addObject(secret, 310, 230);
            getWorld().addObject(input, 700, 178);
            getWorld().addObject(riddle[r], 700, 330);
        }
    }
    public void addNotify(int n){
        
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
        //inWorld_R = notify.inWorld;
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
