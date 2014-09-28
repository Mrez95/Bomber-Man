import greenfoot.*;
import java.util.ArrayList;
import java.util.List;

public class BomberWorld extends World
{   //X and Y positions for the blocks and bricks
    ArrayList<Integer> blockX = new ArrayList<Integer>();
    ArrayList<Integer> blockY = new ArrayList<Integer>();
    ArrayList<Integer> brickX = new ArrayList<Integer>();
    ArrayList<Integer> brickY = new ArrayList<Integer>();
    boolean record = true;
    int doorX;
    int doorY;
    int size;
    int brickCount = 40;
    public BomberWorld()
    {    
        super(27, 13, 50);
        size = 50;
        Greenfoot.setSpeed(50);
        makeBorder();
        generateBlocks();
        randomBricks();
        spawnDoor();
        addBricks();
        createMan();
    }
        
    void makeBorder(){
        boolean row1 = false;
        boolean cols = false;
        boolean done = false;
        int worldX = getWidth();
        int worldY = getHeight();
        int countX = 0;
        int countY = 0;
        //Creating the first row of blocks
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
        //Makes the left and right sides of the border
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
        //Makes the bottom row of blocks
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
    
    //Makes the grid of blocks.
    public void generateBlocks(){
        int worldX = getWidth();
        int worldY = getHeight();
        int countX = 2;
        int countY = 2;
        boolean done = false;
        while (done == false){
            for (int i=2;i<worldX-2 ;i+=2){
                addObject(new Block(),i,countY);
                blockX.add(i);
                blockY.add(countY);
            }
            if (countY>=worldY-4){
                done=true;
            }
            else{
                countY+=2;
            }
        }
    }
    
    //Randomly generates the X and Y values for the bricks in the world where blocks are not.
    public void randomBricks(){
        int worldX = getWidth();
        int worldY = getHeight();
        int countX = 1;
        int countY = 1;
        for (int i=0;i<brickCount;i++){
            int randomX = (Greenfoot.getRandomNumber(worldX-2)) + 1;
            int randomY = (Greenfoot.getRandomNumber(worldY-2)) + 1;
            //calls checkXY to check if the selected cell is clear.
            if (checkXY(randomX, randomY) == true){
                brickX.add(randomX);
                brickY.add(randomY);
            }
            else{
                i-=1;
            }
        }
    }
    
    //Adds the bricks to the world from the generated X and Y positions.
    public void addBricks(){
        for (int i=0;i<brickX.size()-1;i++){
            addObject(new Brick(), brickX.get(i),brickY.get(i));
        }
        List check = getObjectsAt(doorX,doorY,Brick.class);
        if (check.size()!=0){
            addObject(new Brick(), brickX.get(brickX.size()-1),brickY.get(brickY.size()-1));
        }
        else{
            addObject(new Brick(), doorX, doorY);
        }
    }
    
    //Checks to see if a certain cell is clear of blocks and bricks
    public boolean checkXY(int x, int y){
        boolean yes = true;
        for (int i=0;i<blockX.size();i++){
            if ((x == blockX.get(i)) && (y == blockY.get(i))){
                yes = false;
            }
        }
        if (brickX.size()>1){
            for (int i=0;i<brickX.size();i++){
                if ((x == brickX.get(i)) && (y == brickY.get(i))){
                    yes = false;
                }
            }
        }
        if (yes==true){
               return true;
        }
        else{
            return false;
        }
    }
    
    //Creates the User into the world.
    public void createMan(){
        boolean cont = false;
        boolean yes;
        int worldX = getWidth();
        int worldY = getHeight();
        int x=0;
        int y=0;
        while (cont == false){
            yes = true;
            x = (Greenfoot.getRandomNumber(worldX-2)) + 1;
            y = (Greenfoot.getRandomNumber(worldY-2)) + 1;
            for (int i=0;i<blockX.size();i++){
                if ((x == blockX.get(i)) && (y == blockY.get(i))){
                    yes = false;
                }
            }
            
            for (int i=0;i<brickX.size();i++){
                if ((x == brickX.get(i)) && (y == brickY.get(i))){
                    yes = false;
                }
            }
            
            if ((x == doorX) && (y == doorY)){
                yes = false;
            }
            
            List check1 = getObjectsAt(x+1,y,null);
            List check2 = getObjectsAt(x-1,y,null);
            List check3 = getObjectsAt(x,y+1,null);
            List check4 = getObjectsAt(x,y-1,null);
            
            if (check1.size() + check2.size() + check3.size() + check4.size() > 3){
                yes = false;
            }
            
            
            if (yes==true){
                addObject(new Man(), x,y);
                //System.out.println(x);
                //System.out.println(y);
                cont = true;
            }
        }
        spawnEnemys(5,x,y);
    }
    
    //Spawns a door into the world. Happens before the Bricks are made.
    public void spawnDoor(){
        int num1 = Greenfoot.getRandomNumber(brickX.size()-1);
        int num2 = Greenfoot.getRandomNumber(brickY.size()-1);
        doorX = brickX.get(num1);
        doorY = brickY.get(num2);
        addObject(new Door(), doorX, doorY);
        //System.out.println(doorX + ", " + doorY);
    }
    
    //Creates the Enemies. Takes the # of enemies to spawn, and the User x,y position as parameters.
    public void spawnEnemys(int amount, int manX, int manY){
        boolean cont;
        int worldX = getWidth();
        int worldY = getHeight();
        ArrayList<Integer> enemyPosX = new ArrayList<Integer>();
        ArrayList<Integer> enemyPosY = new ArrayList<Integer>();
        for (int count=0;count<amount;count++){
            cont = true;
            int enemyX = (Greenfoot.getRandomNumber(worldX-2)) + 1;
            int enemyY = (Greenfoot.getRandomNumber(worldY-2)) + 1;
            if ((enemyX!=manX) && (enemyY != manY)){
                //checks for duplicates 
                for (int x=0;x<enemyPosX.size();x++){
                    if ((enemyX == enemyPosX.get(x)) && (enemyY == enemyPosY.get(x))){
                        cont = false;
                    }
                }
                //checks for blocks 
                for (int i=0;i<blockX.size();i++){
                    if ((enemyX == blockX.get(i)) && (enemyY == blockY.get(i))){
                        cont = false;
                    }
                }
                //checks for bricks
                for (int i=0;i<brickX.size();i++){
                    if ((enemyX == brickX.get(i)) && (enemyY == brickY.get(i))){
                        cont = false;
                    }
                }
                //happens if nothing is found at that x,y.
                if (cont==true){
                    addObject(new Enemy(manX,manY), enemyX, enemyY);
                    enemyPosX.add(enemyX);
                    enemyPosY.add(enemyY);
                }
                //happens if something is found at that x,y.
                else{
                    count-=1;
                }
            }
            else{
                count-=1;
            }
        }
    }
}
