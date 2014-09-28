import greenfoot.*;
import java.util.ArrayList;
import java.util.List;

public class Win extends World
{   ArrayList<Integer> blockX = new ArrayList<Integer>();
    ArrayList<Integer> blockY = new ArrayList<Integer>();
    ArrayList<Integer> brickX = new ArrayList<Integer>();
    ArrayList<Integer> brickY = new ArrayList<Integer>();
    boolean record = true;
    int doorX;
    int doorY;
    int size;
    int brickCount = 40;
    public Win()
    {    
        super(27, 13, 50);
        size = 50;
        Greenfoot.setSpeed(50);
        makeBorder();
        spawnDoor();
    }
        
    void makeBorder(){
        boolean row1 = false;
        boolean cols = false;
        boolean done = false;
        int worldX = getWidth();
        int worldY = getHeight();
        int countX = 0;
        int countY = 0;
        while (row1 == false){
            addObject(new Block(),countX,countY);
            if ((countX==worldX-1)&&(countY<1)){
                countX=0;
                countY++;
                row1 = true;
            }
            else{
                countX++;
            }
        }
        countX = 0;
        countY = 1;
        while (cols == false){
            addObject(new Block(),countX,countY);
            if (countX>=worldX){
                countY++;
                countX=0;
            }
            else if (countY >= worldY-1){
                cols=true;
            }
            
            else{
                countX+=(worldX-1);
            }
        }
        countX = 0;
        countY = worldY;
        while (done==false){
            addObject(new Block(),countX,countY);
            if (countX<worldX-1){
                countX++;
            }
            else{
                done= true;
            }
        }
    }
    
    public void spawnDoor(){
        addObject(new Door(),1,1);
        createMan();
    }
    
    public void createMan(){
        addObject(new Man(), 2, 1);
    }
}
