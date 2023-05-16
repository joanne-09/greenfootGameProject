import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BattleField here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
/**529 Joanne有改一點(看完就可以刪掉了)*/
public class BattleField extends World
{
    Run1 run1 = new Run1();
    Run3 run3 = new Run3();
    public boolean runIsHere = false;
    
    public int buffer2 = 0;
    private int countDown = 0;
    private boolean hurt = true;
    private boolean hurt2 = true;
    
    public int dir;
    //public static int dir;
    private int checkLife = 0;
    public int where;
    public int over;
    private boolean winner = false;
    
    private static boolean isRun1 = false;
    private static boolean isRun3 = false;
    
    private int[] var = new int[9];
    private boolean[] chat = new boolean[15];
    private boolean[] check = new boolean[9];
    /**
     * Constructor for objects of class BattleField.
     * 
     */
    public BattleField(int var1[], boolean chat1[])
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 552, 1); 
        //0:world 1:coin 2:gun 3: knife 4:sword 5:shield 6:life 7:puzzle 8:diary
        for(int i=0; i<9; i++) var[i] = var1[i];
        for(int i=0; i<4; i++) chat[i] = chat1[i];
        prepare();
    }
    //For Frame1
    public BattleField(int var1[], boolean chat1[], boolean check1[]){
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 552, 1); 
        //0:world 1:coin 2:gun 3: knife 4:sword 5:shield 6:life 7:puzzle 8:diary
        for(int i=0; i<9; i++) var[i] = var1[i];
        for(int i=0; i<15; i++) chat[i] = chat1[i];
        for(int i=0; i<9; i++) check[i] = check1[i];
        prepare();
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Enemy enemy = new Enemy();
        addObject(enemy,809,449);
        Ron_live ron_live = new Ron_live();
        addObject(ron_live,72,24);
        Enemy_live enemy_live = new Enemy_live();
        addObject(enemy_live,799,24);
        if(var[0] == 1){
            addObject(run1, 83, 442);
            runIsHere = true;
        }
        else if(var[0] == 3){
            addObject(run3, 83, 442);
            runIsHere = true;
        }
        //run1Circle.setLocation(133,444)
    }

    public void act(){
        if(buffer2 > 0) buffer2--;
        whichRun();
    }
    public void hurtEnemy(){
        if(checkLife > 0){
           checkLife--;
           if(checkLife < 0) checkLife = 0;
        }
        else if(checkLife == 0) Enemy_live.minuslife();
    }
    public void hurtRun(){
        if(checkLife > 0){
           checkLife--;
           if(checkLife < 0) checkLife = 0;
        }
        else if(checkLife == 0)
        Ron_live.minuslife();
        buffer2 = 30;
    }
    /*private void coolDown(){
        if(!hurt)       buffer--;
        if(buffer<=0)   hurt = true;
        if(!hurt2)      buffer2--;
        if(buffer2<=0)  hurt2 = true;
    }*/
    public static int KeyisDown(){
        return Run1.Keydown();
    }
    public int whichRun(){
        if(var[0] == 1){
            return 1;
        }
        else if(Run3.whichRun3() == 3 || var[0] == 3){
            return 3;
        }
        else return 0;
    }
    public boolean RunIsHere(){
        return runIsHere;
    }
    public int backWhere(){
        where = Enemy_live.returnWhere();
    
        if(where == 1 && !winner && whichRun() == 1){
            chat[3] = true;
            Greenfoot.setWorld(new Frame1(var, chat, check));
            //where=0;
            return 0;
        }else if(Greenfoot.isKeyDown("shift") && Greenfoot.isKeyDown("q")){
            if(whichRun() == 1){
                chat[3] = true;
                Greenfoot.setWorld(new Frame1(var, chat, check));
            }else if(whichRun() == 3 && !chat[11] && chat[1]){
                chat[11] = true;
                var[0] = 4;
                Greenfoot.setWorld(new End(0, var, chat[11]));
            }
            else if(whichRun() == 3){
                chat[1] = true;
                Greenfoot.setWorld(new Frame3(var, chat, check));
            }
            return 0;
        }
        else if(where == 1 && !winner && whichRun() == 3){
            if(!chat[11] && chat[1]){
                chat[11] = true;
                var[0] = 4;
                Greenfoot.setWorld(new End(0, var, chat[11]));
            }
            else{
                chat[1] = true;
                Greenfoot.setWorld(new Frame3(var, chat, check));
            }
            return 0;
        }
        else return -1;
    }
    public void youWin(){
        where = Enemy_live.returnWhere();
        if(where == 1) {
            if(isRun1)      {
                removeObject(run1);
                isRun1 = false;
            }
            else if(isRun3) {
                removeObject(run3);
                isRun3 = false;
            }
            runIsHere = false;
        }
    }
    public int backOver(){
        over = Ron_live.returnOver();
        if(over == 1){
            if(whichRun() == 3){
                if(!chat[11] && chat[1]){
                    Greenfoot.setWorld(new End(0, var, chat[11]));
                    return 0;
                }
            }
            
            var[7]=0;
            var[8]=0;
            if(isRun1)      {
                removeObject(run1);
                isRun1 = false;
            }
            else if(isRun3) {
                removeObject(run3);
                isRun3 = false;
            }
            runIsHere = false;
            Greenfoot.setWorld(new Menu(var));
            return 0;
        }
        else return -1;
    }
}
