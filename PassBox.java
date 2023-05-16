import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PassBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PassBox extends F2NPC
{
    /**
     * Act - do whatever the PassBox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Notify notify = new Notify();
    public Riddle riddle = new Riddle(2, 5);
    public Choose[] ans = new Choose[4];
    {
        for(int i=0; i<4; i++)  ans[i] = new Choose(i+2);
    }
    public PassBox(){
        setImage("mailbox_1.png");
    }
    public void act()
    {
        openOnNPCs_box();
        if(touchRun2())     useRiddle();
        if(!touchRun2())    removeRiddle();
    }
    public boolean touchRun2(){
        return isTouching(Run2.class);
    }
    public void useRiddle(){
        boolean wrongKey = false;
        for(int i=0; i<4; i++){
            if(i == 3) ;
            else if(Greenfoot.mouseClicked(ans[i])){
                wrongKey = true;
                break;
            }
        }
        if(!((Frame2)getWorld()).chat[6] && /*((Frame2)getWorld()).npcState()*/ ((Frame2)getWorld()).var[7] >= 6){
            getWorld().addObject(riddle, 400, 250);
            for(int i=0; i<4; i++){
                getWorld().addObject(ans[i], 300+50*i, 300);
            }
            if(Greenfoot.mouseClicked(ans[3])){
                getWorld().addObject(notify, 400, 470);
                notify.showText(0);
                ((Frame2)getWorld()).chat[6] = true;
                getWorld().removeObject(riddle);
                for(int i=0; i<4; i++){
                    getWorld().removeObject(ans[i]);
                }
            }else if(wrongKey){
                getWorld().addObject(notify, 400, 470);
                notify.showText(1);
            }
        }else{
            getWorld().addObject(notify, 400, 470);
            notify.showText(21);
        }
    }
    public void removeRiddle(){
        if(riddle.getWorld() != null){
            getWorld().removeObject(riddle);
            for(int i=0; i<4; i++){
                getWorld().removeObject(ans[i]);
            }
        }
    }
}
