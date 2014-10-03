import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Enemy extends methods
{
    boolean left = false;
    boolean right = false;
    boolean up = false;
    boolean down = false;
    int pX;
    int pY;
    int manX;
    int manY;
    private int frameCounter = 0;
    private int deathCounter = 0;
    boolean start = false;
    boolean stopMove = false;
    int speed = 10;
    int moveSpeed = 30;
    //Takes the User's x,y position as parameters.
    public Enemy(int xpos, int ypos){
        manX = xpos;
        manY = ypos;
    }
    
    public void act() 
    {
        AI();
        die();
        frameCounter++;
    }
    
    //the calling of parts of the AI.
    public void AI(){
        if (frameCounter>=moveSpeed){
            if (stopMove==false){
                move();
                frameCounter=0;
            }
        }
    }
    
    //Decides which movement to use.
    public void move(){
        int neighbors = getNeighbors();
        //System.out.println(neighbors);
        int x = getX();
        int y = getY();
        if (followPath(x,y,pX,pY) == false){
            if (neighbors==3){
                if(left != true){
                    pX = x;
                    setLocation(x-1,y);
                }
                if(right != true){
                    pX = x;
                    setLocation(x+1,y);
                }
                if(up != true){
                    pY = y;
                    setLocation(x,y-1);
                }
                if(down != true){
                    pY = y;
                    setLocation(x,y+1);
                }
            }
            else{
                pickDirection(x,y,pX,pY);
            }
        }
    }
    
    //checks for certain surrounding objects.
    public int getNeighbors(){
        Actor checkLeft1 = getOneObjectAtOffset(-1,0,Block.class);
        Actor checkRight1 = getOneObjectAtOffset(1,0,Block.class);
        Actor checkUp1 = getOneObjectAtOffset(0,-1,Block.class);
        Actor checkDown1 = getOneObjectAtOffset(0,1,Block.class);
        Actor checkLeft2 = getOneObjectAtOffset(-1,0,Brick.class);
        Actor checkRight2 = getOneObjectAtOffset(1,0,Brick.class);
        Actor checkUp2 = getOneObjectAtOffset(0,-1,Brick.class);
        Actor checkDown2 = getOneObjectAtOffset(0,1,Brick.class);
        Actor checkLeft3 = getOneObjectAtOffset(-1,0,Bomb.class);
        Actor checkRight3 = getOneObjectAtOffset(1,0,Bomb.class);
        Actor checkUp3 = getOneObjectAtOffset(0,-1,Bomb.class);
        Actor checkDown3 = getOneObjectAtOffset(0,1,Bomb.class);
        int num = 0;
        if (checkLeft1 != null || checkLeft2 != null || checkLeft3 != null){
            left = true;
            num++;
        }
        else{
            left=false;
        }
        if (checkRight1 != null || checkRight2 != null || checkRight3 != null){
            right = true;
            num++;
        }
        else{
            right=false;
        }
        if (checkUp1 != null || checkUp2 != null || checkUp3 != null){
            up = true;
            num++;
        }
        else{
            up=false;
        }
        if (checkDown1 != null || checkDown2 != null || checkDown3 != null){
            down = true;
            num++;
        }
        else{
            down=false;
        }
        return num;
    }
    
    //Picks a direction to go.
    public void pickDirection(int x, int y, int pX, int pY){
        boolean done = false;
        Actor checkLeft1 = getOneObjectAtOffset(-1,0,Block.class);
        Actor checkRight1 = getOneObjectAtOffset(1,0,Block.class);
        Actor checkUp1 = getOneObjectAtOffset(0,-1,Block.class);
        Actor checkDown1 = getOneObjectAtOffset(0,1,Block.class);
        Actor checkLeft2 = getOneObjectAtOffset(-1,0,Brick.class);
        Actor checkRight2 = getOneObjectAtOffset(1,0,Brick.class);
        Actor checkUp2 = getOneObjectAtOffset(0,-1,Brick.class);
        Actor checkDown2 = getOneObjectAtOffset(0,1,Brick.class);
        
        while (done==false){
            if ((checkLeft1 == null) && (checkLeft2 == null) && (manX<x)){
                pX = x;
                pY = y;
                setLocation(x-1,y);
                done = true;
                //System.out.println("1");
                break;
            }
            
            else if ((checkRight1 == null) && (checkRight2 == null) && (manX>x)){
                pX = x;
                pY = y;
                setLocation(x+1,y);
                done = true;
                //System.out.println("2");
                break;
            }
            
            else if ((checkUp1 == null) && (checkUp2 == null) && (manY<y)){
                pX = x;
                pY = y;
                setLocation(x,y-1);
                done = true;
                //System.out.println("3");
                break;
            }
            
            else if ((checkDown1 == null) && (checkDown2 == null) && (manY>y)){
                pX = x;
                pY = y;
                setLocation(x,y+1);
                done = true;
                //System.out.println("4");
                break;
            }
            
            else{
                done=true;
            }
        }
    }
    
    //This happens until the Enemy hits a wall or object.
    public boolean followPath(int x, int y, int pX, int pY){
        Actor checkLeft1 = getOneObjectAtOffset(-1,0,Block.class);
        Actor checkRight1 = getOneObjectAtOffset(1,0,Block.class);
        Actor checkUp1 = getOneObjectAtOffset(0,-1,Block.class);
        Actor checkDown1 = getOneObjectAtOffset(0,1,Block.class);
        Actor checkLeft2 = getOneObjectAtOffset(-1,0,Brick.class);
        Actor checkRight2 = getOneObjectAtOffset(1,0,Brick.class);
        Actor checkUp2 = getOneObjectAtOffset(0,-1,Brick.class);
        Actor checkDown2 = getOneObjectAtOffset(0,1,Brick.class);
        if (pX==x-1){
            if ((checkRight1==null) &&  (checkRight2==null)){
                setLocation(x+1,y);
                pX = x;
                pY = y;
                return true;
            }
            else{
                return false;
            }
        }
        
        else if (pX == x+1){
            if ((checkLeft1==null) &&  (checkLeft2==null)){
                setLocation(x-1,y);
                pX = x;
                pY = y;
                return true;
            }
            else{
                return false;
            }
        }
        
        else if (pY == y-1){
            if ((checkDown1==null) &&  (checkDown2==null)){
                setLocation(x,y+1);
                pX = x;
                pY = y;
                return true;
            }
            else{
                return false;
            }
        }
        
        else if (pY==y+1){
            if ((checkUp1==null) &&  (checkUp2==null)){
                setLocation(x,y-1);
                pX = x;
                pY = y;
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
    
    //This checks to see if the Enemy is hit by a bomb.
    public void die(){
        Actor checkLeft = getOneObjectAtOffset(-1,0,Center.class);
        Actor checkRight = getOneObjectAtOffset(1,0,Center.class);
        Actor checkUp = getOneObjectAtOffset(0,-1,Center.class);
        Actor checkDown = getOneObjectAtOffset(0,1,Center.class);
        if (checkLeft!=null || checkRight!=null || checkUp!=null || checkDown!=null){
            start=true;
        }
        
        if (start==true){
            animate();
            deathCounter++;
        }
    }
    
    //This is the animation when an Enemy dies.
    public void animate(){
        stopMove = true;
        if ((deathCounter>0) && (deathCounter<=speed)){
            setImage("Enemy-die-1.png");
        }
        if (deathCounter==speed*2){
            setImage("Enemy-die-2.png");
        }
        if (deathCounter==speed*3){
            setImage("Enemy-die-3.png");
        }
        if (deathCounter==speed*4){
            setImage("Enemy-die-4.png");
        }
        if (deathCounter==speed*5){
            setImage("Enemy-die-5.png");
        }
        if (deathCounter>speed*6){
            getWorld().removeObject(this);
        }
    }
}
