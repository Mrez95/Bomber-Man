import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Bomb extends methods
{
    private int frameCounter = 0;
    boolean start = true;
    private int bombTimer = 0;
    public void act() 
    {
        checkMan();
        frameCounter++;
        timer();
    }
    
    //Checks for the Man originally to overlay the man to be on top of the bomb.
    public void checkMan(){
        Actor check = getOneObjectAtOffset(0,0,Man.class);
        if (check!=null){
            if (frameCounter ==0){
                Actor checkMan = getOneObjectAtOffset(0,0,Man.class);
                if (checkMan!=null){
                    getWorld().removeObject(check);
                    getWorld().addObject(new Man(),getX(),getY());
                }
                frameCounter = 0;
            }
        }
    }
    
    
    public void timer(){
        if (bombTimer>=150){
            Explode();
            getWorld().removeObject(this);
            bombTimer=0;
        }
        else{
            bombTimer++;
        }
    }
    
    public void Explode(){
        Actor checkLeft1 = getOneObjectAtOffset(-1,0,Block.class);
        Actor checkRight1 = getOneObjectAtOffset(1,0,Block.class);
        Actor checkUp1 = getOneObjectAtOffset(0,-1,Block.class);
        Actor checkDown1 = getOneObjectAtOffset(0,1,Block.class);
        Actor checkLeft2 = getOneObjectAtOffset(-1,0,Brick.class);
        Actor checkRight2 = getOneObjectAtOffset(1,0,Brick.class);
        Actor checkUp2 = getOneObjectAtOffset(0,-1,Brick.class);
        Actor checkDown2 = getOneObjectAtOffset(0,1,Brick.class); 
        
        
        getWorld().addObject(new Center(),getX(),getY());
        if ((checkLeft1 == null) && (checkLeft2 == null)){
            getWorld().addObject(new Left(),getX()-1,getY());
        }
        
        if ((checkRight1 == null) && (checkRight2 == null)){
            getWorld().addObject(new Right(),getX()+1,getY());
        }
        
        if ((checkUp1 == null ) && (checkUp2 == null)){
            getWorld().addObject(new Up(),getX(),getY()-1);
        }
        
        if ((checkDown1 == null) && (checkDown2 == null)){
            getWorld().addObject(new Down(),getX(),getY()+1);
        }
        
    }
    
}
