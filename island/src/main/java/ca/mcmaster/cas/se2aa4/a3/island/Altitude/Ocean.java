package ca.mcmaster.cas.se2aa4.a3.island.Altitude;

import ca.mcmaster.cas.se2aa4.a3.island.IslandCommandLineReader;

public class Ocean implements WaterAltitude {

    Double elevation;

    public AltitudeFunction function= (x,y)->{return elevation;};

    public AltitudeFunction getFunction(){
        findElevation();
        return this.function;
    }

    public void findElevation(){
        elevation = IslandCommandLineReader.randomGenerator.getNextdouble();
    }




}
