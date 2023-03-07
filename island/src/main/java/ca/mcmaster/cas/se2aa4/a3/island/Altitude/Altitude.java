package ca.mcmaster.cas.se2aa4.a3.island.Altitude;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class Altitude {

    public AltitudeFunction function;

    List<Tile> landTiles;
    List<Tile> waterTiles;

    public Altitude(LandAltitude land_type, WaterAltitude water_type){
        LandAltitude land=land_type;
        WaterAltitude water=water_type;
    }



}
