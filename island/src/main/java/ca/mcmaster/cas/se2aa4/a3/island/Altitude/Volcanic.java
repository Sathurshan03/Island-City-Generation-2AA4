package ca.mcmaster.cas.se2aa4.a3.island.Altitude;

import ca.mcmaster.cas.se2aa4.a3.island.Modes.Mode;


public class Volcanic implements LandAltitude{

    public AltitudeFunction function= (x,y)->{return (Mode.getWidth()/10)/((Mode.getWidth()/500000)*Math.pow((x-Mode.getWidth()/2),2)+(Mode.getWidth()/500000)*Math.pow(y-Mode.getHeight()/2,2)+1);};

    public AltitudeFunction getFunction(){
        return this.function;
    }

}
