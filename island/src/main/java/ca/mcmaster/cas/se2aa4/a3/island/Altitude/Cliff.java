package ca.mcmaster.cas.se2aa4.a3.island.Altitude;

import ca.mcmaster.cas.se2aa4.a3.island.Modes.Mode;

import java.util.Random;

public class Cliff {

    Random rand=new Random();

    public AltitudeFunction function= (x,y)->{return x*y/100;};
    public AltitudeFunction function2= (x,y)->{return ((Mode.getWidth()-x)*(Mode.getHeight())/100);};


    public AltitudeFunction getFunction(){
        int choice=rand.nextInt(0,2);
        if (choice==0){
            return this.function;
        }else if (choice==1){
            return this.function2;
        }else{
            return this.function;
        }

    }
}
