package ca.mcmaster.cas.se2aa4.a3.island.Altitude;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;

import java.util.ArrayList;
import java.util.List;

public class Altitude {

    List<Tile> tiles;

    Double min_elevation;

    AltitudeFunction function;

    public Altitude(){
        tiles=new ArrayList<>();
        min_elevation=Double.MAX_VALUE;
    }

    public Double getMinElevation(){
        return this.min_elevation;
    }

    public void SetElevation(AltitudeType altitude, List<Tile> tiles){
        this.tiles=tiles;
        this.function=altitude.getAltitude(tiles);
        for(Tile tile: tiles){
            for (TileVertex vertex:tile.getTileVertices()){
                vertex.setElevation(function.valueAt(vertex.getX(), vertex.getY()));
                if (vertex.getElevation()<min_elevation){
                    min_elevation=vertex.getElevation();
                }
            }
        }

    }

}
