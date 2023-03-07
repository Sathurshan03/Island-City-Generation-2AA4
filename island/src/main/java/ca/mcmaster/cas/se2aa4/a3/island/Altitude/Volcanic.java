package ca.mcmaster.cas.se2aa4.a3.island.Altitude;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;

import java.util.List;

public class Volcanic implements LandAltitude{

    public AltitudeFunction function= (x,y)->{return x*y;};

    List<Tile> landTiles;

    public Volcanic(List<Tile> landTiles){
        this.landTiles=landTiles;
        SetElevation();
    }


    public void SetElevation(){

        for(Tile tile: landTiles){
            for (TileVertex vertex:tile.getTileVertices()){
                vertex.setElevation(function.valueAt(vertex.getX(), vertex.getY()));

            }
        }
    }

}
