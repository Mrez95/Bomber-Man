import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
public class Man extends methods
{
    private int frameCounter = 0;
    private int bombFrameCount = 0;
    private int deathCounter = 0;
    private int speed = 12;
    boolean start = false;
    boolean allowMove = true;
    public void act() 
    {
        keyPressed();
        frameCounter++;
        bombFrameCount++;
        die();
    } 
    //Checks for keys pressed.
    public void keyPressed(){
        boolean w = false;
        boolean s = false;
        boolean a = false;
        boolean d = false;
        boolean space = false;
        if (allowMove==true){
            if (Greenfoot.isKeyDown("w") == true){
                w = true;
            }
            if (Greenfoot.isKeyDown("w") == false){
                w = false;
            }
            if (Greenfoot.isKeyDown("s") == true){
                s = true;
            }
            if (Greenfoot.isKeyDown("s") == false){
                s = false;
            }
            if (Greenfoot.isKeyDown("a") == true){
                a = true;
            }
            if (Greenfoot.isKeyDown("a") == false){
                a = false;
            }
            if (Greenfoot.isKeyDown("d") == true){
                d = true;
            }
            if (Greenfoot.isKeyDown("d") == false){
                d = false;
            }
            if (Greenfoot.isKeyDown(" ") == true){
                space = true;
            }
            if (Greenfoot.isKeyDown(" ") == false){
                space = false;
            }
            move(w,s,a,d,space);
        }
    }
    //Actually moves the User.
    public void move(boolean w, boolean s, boolean a, boolean d, boolean space){
        if (frameCounter >= 5)
        {
            Actor checkLeft = getOneObjectAtOffset(-1,0,Block.class);
            Actor checkRight = getOneObjectAtOffset(1,0,Block.class);
            Actor checkUp = getOneObjectAtOffset(0,-1,Block.class);
            Actor checkDown = getOneObjectAtOffset(0,1,Block.class);
            Actor checkLeft2 = getOneObjectAtOffset(-1,0,Brick.class);
            Actor checkRight2 = getOneObjectAtOffset(1,0,Brick.class);
            Actor checkUp2 = getOneObjectAtOffset(0,-1,Brick.class);
            Actor checkDown2 = getOneObjectAtOffset(0,1,Brick.class);
            Actor checkLeft3 = getOneObjectAtOffset(-1,0,Bomb.class);
            Actor checkRight3 = getOneObjectAtOffset(1,0,Bomb.class);
            Actor checkUp3 = getOneObjectAtOffset(0,-1,Bomb.class);
            Actor checkDown3 = getOneObjectAtOffset(0,1,Bomb.class);
            if (((w==true) && (d!=true) && (a!=true) && (checkUp==null)) && (checkUp2==null) && (checkUp3==null)){
                    setLocation(getX(), getY() - 1);
            }
            if ((s==true) && (d!=true) && (a!=true) && (checkDown==null) && (checkDown2==null) && (checkDown3==null)){
                setLocation(getX(), getY() + 1);
                //timer(50);
            }
            if ((a==true) && (checkLeft==null) && (checkLeft2==null) && (checkLeft3==null)){
                setLocation(getX() - 1, getY());
                //timer(50);
            }
            if ((d==true) && (checkRight==null) && (checkRight2==null) && (checkRight3==null)){
                setLocation(getX() + 1, getY());
                //timer(50);
            }
            frameCounter = 0;
        }
        List objects = getWorld().getObjects(Bomb.class);
        if (objects.size()==0){
            if (space==true){
                getWorld().addObject(new Bomb(), getX(), getY());
            }
        }
    }
    
    //Checks to see if an enemy or explosion hits the User.
    public void die(){
        Actor check1 = getOneObjectAtOffset(0,0,Center.class);
        Actor check2 = getOneObjectAtOffset(0,0,Left.class);
        Actor check3 = getOneObjectAtOffset(0,0,Right.class);
        Actor check4 = getOneObjectAtOffset(0,0,Up.class);
        Actor check5 = getOneObjectAtOffset(0,0,Down.class);
        Actor check6 = getOneObjectAtOffset(0,0,Enemy.class);
        
        if (check1!=null || check2!=null || check3!=null || check4!=null || check5!=null || check6!=null){
            start = true;
            allowMove=false;
        }
        
        if (start==true){
            animate();
            deathCounter++;
        }
    }
    
    //The animation that happens when the user dies.
    public void animate(){
        if ((deathCounter>0) && (deathCounter<=speed)){
            setImage("Man-die-1.png");
        }
        if (deathCounter==speed*2){
            setImage("Man-die-2.png");
        }
        if (deathCounter==speed*3){
            setImage("Man-die-3.png");
        }
        if (deathCounter==speed*4){
            setImage("Man-die-4.png");
        }
        if (deathCounter==speed*5){
            setImage("Man-die-5.png");
        }
        if (deathCounter==speed*6){
            setImage("Man-die-6.png");
        }
        if (deathCounter>speed*6){
            getWorld().removeObject(this);
            BomberWorld world = new BomberWorld();
            Greenfoot.setWorld(world);
        }       
    }
}