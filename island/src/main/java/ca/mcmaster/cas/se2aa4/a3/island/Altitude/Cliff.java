package ca.mcmaster.cas.se2aa4.a3.island.Altitude;

import ca.mcmaster.cas.se2aa4.a3.island.Modes.Mode;

public class Cliff {

    public AltitudeFunction function= (x,y)->{return x*y/10000;};

    public AltitudeFunction getFunction(){
        return this.function;
    }
}
