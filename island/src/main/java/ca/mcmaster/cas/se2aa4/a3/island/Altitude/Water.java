package ca.mcmaster.cas.se2aa4.a3.island.Altitude;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;

import java.util.List;

public class Water implements WaterAltitude {

    List<Tile> waterTiles;

    public Water(List<Tile> tiles){
        waterTiles=tiles;
        SetElevation();
    }

    public void SetElevation(){

        for(Tile tile: waterTiles){
            for (TileVertex vertex:tile.getTileVertices()){
                vertex.setElevation(1.0);

            }
        }
    }


}
