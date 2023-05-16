import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Puzzle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Puzzle extends World
{
    private EndChat e = new EndChat(true);
    private PuzzleClippings[] puz = new PuzzleClippings[9];
    private PuzzleB[] puzB = new PuzzleB[9];
    private Back4 b = new Back4();
    private int[] var = new int[9];
    /**
     * Constructor for objects of class Puzzle.
     * 
     */
    public Puzzle(int var1[])
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 500, 1);
        //0:world 1:coin 2:gun 3: knife 4:sword 5:shield 6:life 7:puzzle 8:diary
        var[0] = var1[0];
        var[1] = var1[1];
        var[2] = var1[2];
        var[3] = var1[3];
        var[4] = var1[4];
        var[5] = var1[5];
        var[6] = var1[6];
        var[7] = var1[7];
        var[8] = var1[8];
        
        //我也不知道為什麼從Menu傳進來的var[7]都會是0，所以才用了這個東西
        //阿之後如果知道發生什麼事再說吧...
        if(var[0] == 1) var[7] = 0;
        else if(var[0] == 2) var[7] = 3;
        else if(var[0] == 3) var[7] = 6;
        else var[7] = 9;
        
        prepare();
    }
    public void prepare(){
        addObject(b, 40, 460);
        for(int i=0; i<9; i++){
            puzB[i] = new PuzzleB();
            if(i>-1 && i<3) addObject(puzB[i], 289+i*200, 90);
            else if(i>=3 && i<6) addObject(puzB[i], 289+(i-3)*200, 250);
            else addObject(puzB[i], 289+(i-6)*200, 410);
        }
        if(var[7] != 0){
            for(int i=0; i<var[7]; i++){
                int x = getRandomNumber(62, 130);
                int y = getRandomNumber(52, 380);                                    
                if(i!=0){
                    for(int j=0; j<i; j++){
                        while(x==puz[j].getX() && y==puz[j].getY()){
                            x = getRandomNumber(62, 130);
                            y = getRandomNumber(52, 380);
                        }
                    }
                }
                puz[i] = new PuzzleClippings(i, x, y);
                addObject(puz[i], x, y);
            }
        }
        if(var[7] != 9){
            for(int i=8; i>=var[7]; i--){
                puz[i] = new PuzzleClippings(i, 0, 0);
            }
        }
    }
    public int getRandomNumber(int start,int end)
    {
        int normal = Greenfoot.getRandomNumber(end-start+1);
        return normal+start;
    }
    public void act(){
        if(var[7] != 0) puzzle();
        back();
    }
    public void puzzle(){
        for(int i=0; i<var[7]; i++){
            if(puz[i].change && !puz[i].init){
                for(int j=0; j<9; j++){
                    if(puz[j].init);
                    else if(puz[i].check(j, puzB[j].getX(), puzB[j].getY()))
                        puzB[j].getImage().setTransparency(150);
                    else puzB[j].getImage().setTransparency(0);
                }
            }else if(!puz[i].change && puz[i].init){
                puzB[i].getImage().setTransparency(0);                    
            }
        }
    }
    public void back(){
        if(Greenfoot.mouseClicked(b)) {
            Greenfoot.setWorld(new Menu(var));
        }
    }
}
