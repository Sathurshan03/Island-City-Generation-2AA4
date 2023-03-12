package ca.mcmaster.cas.se2aa4.a3.island.Altitude;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.Modes.Mode;

import java.util.List;
import java.util.Random;

public class Ocean implements WaterAltitude {

    Double elevation;

    public AltitudeFunction function= (x,y)->{return elevation;};

    public AltitudeFunction getFunction(){
        findElevation();
        return this.function;
    }

    public void findElevation(){
        Random rand=new Random();
        elevation= rand.nextDouble();
    }




}
