import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Block extends methods
{
    int frameCounter = 0;
    public void act() 
    {
        if (frameCounter<10){
            fix();
            frameCounter++;
        }
    }    
    public void fix(){
        Actor block = getOneObjectAtOffset(0,0,Block.class);
        if (block != null){
            getWorld().removeObject(this);
        }
    }
    
}