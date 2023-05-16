import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Riddle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Riddle extends Actor
{
    private Notify notify = new Notify();
    
    public boolean ifRight = false;
    public int which = -1;
    public int choose = -1;
    public int repeat =  -1;//play second time or not
    public int world = 0;
    public Choose[] ans = new Choose[4];
    {
        for(int i=0; i<4; i++) ans[i] = new Choose(i+2);
    }
    public Choose[] ans3 = new Choose[10];
    {
        for(int i=0; i<10; i++) ans3[i] = new Choose(i+44);
    }
    public Riddle(int w, int wh){
        if(w == 1){
            if(wh == 0) setImage("RIDDLE/1Branch0.png");
            else if(wh == 1) setImage("RIDDLE/1Branch1.png");
            else if(wh == 2) setImage("RIDDLE/1Branch1_1.png");
            else if(wh == 4) setImage("RIDDLE/1Branch4.png");
            else if(wh == 5) setImage("RIDDLE/1Branch5.png");
            else if(wh == 6) setImage("RIDDLE/1Branch6_0.png");
            else if(wh == 7) setImage("RIDDLE/1BranchIN.png");
            else if(wh == 8) setImage("RIDDLE/1Branch8.png");
            else if(wh == 9) setImage("RIDDLE/1Branch9_0.png");
            else if(wh == 10) setImage("RIDDLE/1Branch9_1.png");
            else if(wh == 13) setImage("RIDDLE/1motto10.png");
            else if(wh == 11) setImage("RIDDLE/1Branch11.png");
            else if(wh == 12) setImage("RIDDLE/1Branch12.png");
            which = wh;
        }else if(w == 2){
            if(wh == 0)      setImage("RIDDLE/Riddle_of_f.jpg");
            else if(wh == 1) setImage("RIDDLE/Paul_message.png");
            else if(wh == 2) setImage("RIDDLE/Br2_0.jpg");
            else if(wh == 3) setImage("RIDDLE/File2.png");
            else if(wh == 4) setImage("RIDDLE/secret.png");
            else if(wh == 5) setImage("RIDDLE/PassBox.png");
        }else if(w == 3){
            if(wh == 0) setImage("RIDDLE/farmer.png");
            else if(wh == 1) setImage("RIDDLE/girl1.png");
            else if(wh == 2) setImage("RIDDLE/3Branch.3.png");
            else if(wh == 3) setImage("RIDDLE/3Branch.2.png");
            else if(wh == 4) setImage("RIDDLE/3Branch.11.png");
            else if(wh == 5) setImage("RIDDLE/3Branch.6.png");
            else if(wh == 6) setImage("RIDDLE/3Branch.7.png");
            which = wh;
        }
        world = w;
    }
    /**
     * Act - do whatever the Riddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        if(world == 1){
            if(/*getWorld()!=null && */(which==7 || which==9 || which==12)) riddleInput();
        }else if(world == 3){
            if(/*getWorld()!=null && */(which == 2 || which == 3)) riddleInput();
        }
        
    }
    public String[] nums = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "/", "enter", "left", "up", "down"};
    private int temp = -1; //暫存按鍵的編號
    public int now = 0; //現在按到第幾個鍵
    public int right = 0; //對的按鍵有幾個
    private boolean rightKey; //若按的建案數字或斜線，設為true
    private boolean isDown = false;
    public String[] answers; //正確答案
    public int ans_num = 0;
    public Choose[] keys = new Choose[10]; //目前按過的鍵的編號
    public int key = 0; //將目前按過的鍵數存進去
    public void riddleInput(){
        //System.out.print("is in riddle");
        rightKey = false;
        if(Greenfoot.getKey()!=null && now<ans_num && !isDown){
            isDown = true;
            for(int i=0; i<15; i++){
                if(Greenfoot.isKeyDown(nums[i])){
                    temp = i;
                    if(temp<11) rightKey = true;
                    break;
                }
            }
            /*if(temp==-1){
                getWorld().addObject(notify, 400, 470);
                notify.showText(10);
            }*/
        }
        if(rightKey){
            if(world == 1){
                now++;
                if(answers[now-1].equals(nums[temp])) right++;
                keys[now-1] = new Choose(temp+33);
                key++;
                if(which==7) getWorld().addObject(keys[now-1], 473+(now-1)*32, 260);
                else if(which==9 || which==12) getWorld().addObject(keys[now-1], 533+(now-1)*32, 448);
                temp = -1;
            }
            else if(world == 3){
                now++;
                if(answers[now-1].equals(nums[temp])) right++;
                keys[now-1] = new Choose(temp+33);
                key++;
                if(which==2) getWorld().addObject(keys[now-1], 700, 470);
                else if(which==3) {
                    if(now == 3) getWorld().addObject(keys[now-1], 700, 465);
                    else getWorld().addObject(keys[now-1], 550+(now-1)*32, 465);
                }
                temp = -1;
            }
        }
        if(Greenfoot.isKeyDown("enter")){
            if(right==ans_num){
                // ifRight = true;
                choose=0;
                right = 0;
                //0607 凡 新增(因為f3可以重複答題)
                repeat = 0;
                for(int i=key; i>0; i--) getWorld().removeObject(keys[i-1]);
                getWorld().removeObject(this);
                ifRight = true;
                if(world == 3 /*chat[4] chat[5] chat[6]*/){
                    now=0;
                    right=0;
                    key = 0;
                }
            }else if(choose!=0 || repeat != 0){
                getWorld().addObject(notify, 400, 470);
                notify.showText(1);
                for(int i=key; i>0; i--) getWorld().removeObject(keys[i-1]);
                now=0;
                right=0;
                key = 0;
                if(world == 3){
                    Frame3 w = (Frame3)getWorld();
                    w.isattack = false;
                }
            }
        }
        if(Greenfoot.getKey()==null && isDown) isDown=false;
    }
    public void removeAll_IN(){
        if(key>0)
            for(int i=key; i>0; i--) getWorld().removeObject(keys[i-1]);
        getWorld().removeObject(this);
        now = 0;
        right = 0;
        key = 0;
    }
    private boolean wrongKey = false;
    public void riddle(int answer){
        for(int i=0; i<4; i++){
            if(Greenfoot.mouseClicked(ans[i])){
                if(i==answer) ;
                else wrongKey = true;
                break;
            }
        }
        for(int i=0; i<4; i++) getWorld().addObject(ans[i], 160+160*i, 483);
        if(Greenfoot.mouseClicked(ans[answer])){
            choose = 0;
        }else if(wrongKey){
            choose = 1;
        }
        if(ans[0].getWorld()!=null && choose!=-1){
            for(int i=0; i<4; i++) getWorld().removeObject(ans[i]);
            getWorld().removeObject(this);
        }
    }
    public int[] answer;
    public boolean[] answercheck = {false, false, false};
    private int wchoose = 0;
    private boolean wrong = true;
    public void riddleMutiple(){
        wrongKey = false;
        wrong = true;
        /*for(int i = 0;i<3;i++){
             answercheck[i] = false;
        }*/
        for(int i=0; i<10; i++){
            if(Greenfoot.mouseClicked(ans3[i])){
                for(int j = 0;j<3;j++){
                    if(i+1 == answer[j]);
                    else wrongKey = true;
                    break;
                }
            }
        }
        for(int i=0; i<5; i++) getWorld().addObject(ans3[i], 100+160*i, 180);
        for(int i=5; i<10; i++) getWorld().addObject(ans3[i], 100+160*(i-5), 280);
        for(int j = 0;j<3;j++){
            if(Greenfoot.mouseClicked(ans3[answer[j] - 1]) && !answercheck[j]){
                //getWorld().addObject(notify, 400, 470);
                //notify.showText(0);
                if(!answercheck[j]) choose ++;
                answercheck[j] = true;
                wrong = false;
            }
        }
        if(wrongKey && wrong){
                wchoose++;
                //getWorld().addObject(notify, 400, 470);
                //notify.showText(1);
                Frame3 w = (Frame3)getWorld();
                w.var[1] -= 1;
        }
    
        if(ans3[0].getWorld()!=null && choose == 2 && wchoose == 0){
            getWorld().addObject(notify, 400, 470);
            notify.showText(0);
            Frame3 w = (Frame3)getWorld();
            w.ri = true;
            for(int i=0; i<10; i++) getWorld().removeObject(ans3[i]);
            //getWorld().removeObject(this);
            w.isDown = false;
        }
        if(wchoose == 3){
            getWorld().addObject(notify, 400, 470);
            notify.showText(18);
            choose = -1;
            wchoose = 0;
            for(int i = 0;i<3;i++){
                 answercheck[i] = false;
            }
        }
    }
    public void setImage(int wh){
        if(wh == 2) setImage("RIDDLE/3Branch.12.png");
        else if(wh == 3) setImage("RIDDLE/3Branch.13.png");
        else if(wh == 4) setImage("RIDDLE/3Branch.14.png");
    }
    public void removeAll(){
        if(ans[0].getWorld()!=null){
            for(int i=0; i<4; i++) getWorld().removeObject(ans[i]);
            getWorld().removeObject(this);
        }
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
