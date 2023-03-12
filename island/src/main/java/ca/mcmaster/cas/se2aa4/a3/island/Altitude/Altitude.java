package ca.mcmaster.cas.se2aa4.a3.island.Altitude;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;

import java.util.List;

public class Altitude {

    List<Tile> tiles;

    AltitudeFunction function;


    public Altitude(AltitudeType altitude, List<Tile> tiles){
        this.tiles=tiles;
        this.function=altitude.getAltitude(tiles);
        SetElevation(this.function);

    }

    public void SetElevation(AltitudeFunction function){
        for(Tile tile: tiles){
            for (TileVertex vertex:tile.getTileVertices()){
                vertex.setElevation(function.valueAt(vertex.getX(), vertex.getY()));
            }
        }

    }

}
