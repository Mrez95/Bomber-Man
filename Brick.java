import greenfoot.*;
import java.util.List;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Brick extends methods
{
    int frameCounter=0;
    int speed = 4;
    boolean start = false;
    public void act() 
    {
        hit();
    }
    
    
    public void hit(){
        Actor checkLeft = getOneObjectAtOffset(-1,0,Center.class);
        Actor checkRight = getOneObjectAtOffset(1,0,Center.class);
        Actor checkUp = getOneObjectAtOffset(0,-1,Center.class);
        Actor checkDown = getOneObjectAtOffset(0,1,Center.class);
        if (checkLeft!=null || checkRight!=null || checkUp!=null || checkDown!=null){
            start=true;
        }
        
        if (start==true){
            animate();
            frameCounter++;
        }
    }
    
    
    public void animate(){
        if (frameCounter==speed){
            setImage("Brick-die-1.png");
        }
        if (frameCounter==speed*2){
            setImage("Brick-die-2.png");
        }
        if (frameCounter==speed*3){
            setImage("Brick-die-3.png");
        }
        if (frameCounter==speed*4){
            setImage("Brick-die-4.png");
        }
        if (frameCounter==speed*5){
            setImage("Brick-die-5.png");
        }
        if (frameCounter==speed*6){
            setImage("Brick-die-6.png");
        }
        if (frameCounter>speed*6){
            getWorld().removeObject(this);
        }
    }
}
