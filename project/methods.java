import greenfoot.*;
import java.util.Timer;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class methods extends Actor
{
    boolean startTimer = true;
    public void act() 
    {
            timer2();
    }
    
    public void timer(int time){
        long start = System.currentTimeMillis();
        boolean stop = false;
        long end;
        while (stop==false){
            end = System.currentTimeMillis();
            if (end-start>=time){
                stop=true;
            }
        }
    }
    
    public boolean timer2(){
        int time = 100;
        boolean rewind = false;
        long start = setTime();
        long end = System.currentTimeMillis()+1;
        long result = (end-start);
        end = System.currentTimeMillis();
        result = (end-start);
        //int swag = getWorld().setTime();
        //System.out.println("start is " + start);
        //System.out.println("end is " + end);
        //System.out.println("result is " + result);
        if ((result%time>0) && (result%time<=time)){
            //System.out.println("True");
            //System.out.println(result);
            return true;
        }
        else{
            //System.out.println("False");
            //System.out.println(result);
            return false;
        }
    }
    
    public long setTime(){
        long time = System.currentTimeMillis();
        return time;
    }
}
