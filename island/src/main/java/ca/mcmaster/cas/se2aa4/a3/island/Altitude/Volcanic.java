package ca.mcmaster.cas.se2aa4.a3.island.Altitude;

import ca.mcmaster.cas.se2aa4.a3.island.Modes.Mode;

import java.util.Random;


public class Volcanic implements LandAltitude{

    Random rand=new Random();

    Double spread;
    Double height;

    public AltitudeFunction function= (x,y)->{return height*Math.exp(-1*((Math.pow((x-Mode.getWidth()/2),2)+Math.pow((y-Mode.getHeight()/2),2))/(spread)));};

    public AltitudeFunction getFunction(){
        spread=rand.nextDouble(10000.0,20000);
        height=rand.nextDouble(4000.0,4500);

        return this.function;
    }

}
