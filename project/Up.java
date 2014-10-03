import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Up extends Flame
{
    int frameCounter = 0;
    public void act() 
    {
        animate();
        frameCounter++;
    }
    
    public void animate(){
        setRotation(360);
        if (frameCounter==3){
            setImage("Explode-end-1.png");
        }
        if (frameCounter==6){
            setImage("Explode-end-2.png");
        }
        if (frameCounter==9){
            setImage("Explode-end-3.png");
        }
        if (frameCounter==12){
            setImage("Explode-end-4.png");
        }
        if (frameCounter==15){
            setImage("Explode-end-3.png");
        }
        if (frameCounter==18){
            setImage("Explode-end-2.png");
        }
        if (frameCounter==21){
            setImage("Explode-end-1.png");
        }
        
        if (frameCounter>21){
            getWorld().removeObject(this);
        }
    }  
}
