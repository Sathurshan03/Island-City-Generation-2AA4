package ca.mcmaster.cas.se2aa4.a3.island.Altitude;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;

import java.util.ArrayList;
import java.util.List;

public class Altitude {

    List<Tile> tiles;

    AltitudeFunction function;

    public Altitude(){
        tiles=new ArrayList<>();
    }

    public void SetElevation(AltitudeType altitude, List<Tile> tiles){
        this.tiles=tiles;
        this.function=altitude.getAltitude(tiles);
        for(Tile tile: tiles){
            for (TileVertex vertex:tile.getTileVertices()){
                vertex.setElevation(function.valueAt(vertex.getX(), vertex.getY()));
            }
        }

    }

}
