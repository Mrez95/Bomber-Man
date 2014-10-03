import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Center extends Flame
{
    int frameCounter = 0;
    public void act() 
    {
        animate();
        frameCounter++;
    }
    
    public void animate(){
        setRotation(0);
        if (frameCounter==3){
            setImage("Explode-center-1.png");
        }
        if (frameCounter==6){
            setImage("Explode-center-2.png");
        }
        if (frameCounter==9){
            setImage("Explode-center-3.png");
        }
        if (frameCounter==12){
            setImage("Explode-center-4.png");
        }
        if (frameCounter==15){
            setImage("Explode-center-3.png");
        }
        if (frameCounter==18){
            setImage("Explode-center-2.png");
        }
        if (frameCounter==21){
            setImage("Explode-center-1.png");
        }
        
        if (frameCounter>21){
            getWorld().removeObject(this);
        }
    } 
}
