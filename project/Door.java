import greenfoot.*;
import java.util.List;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Door extends methods
{
    public void act() 
    {
        if (checkEnemies()==0){
            keyPressed();
        }
    }
    
    //Checks to see if all of the enemies are dead.
    public int checkEnemies(){
        List check = getWorld().getObjects(Enemy.class);
        int size = check.size();
        return size;
    }
    
    //Checks for "w" to be pressed to enter the door.
    public void keyPressed(){
        Actor check = getOneObjectAtOffset(0,0,Man.class);
        if (check!=null){ 
            if (Greenfoot.isKeyDown("w")){
                if (getWorld() instanceof BomberWorld){
                    Win winner = new Win();
                    Greenfoot.setWorld(winner);
                }
                
                if (getWorld() instanceof Win){
                    BomberWorld world = new BomberWorld();
                    Greenfoot.setWorld(world);
                }
            }
        }
    }
}
