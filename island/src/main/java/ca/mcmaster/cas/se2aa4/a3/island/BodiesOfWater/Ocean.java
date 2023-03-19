package ca.mcmaster.cas.se2aa4.a3.island.BodiesOfWater;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;

import java.util.List;

public class Ocean extends BodiesWater {
    private Tile oceanTile;
    private List<TileVertex> mid;

    public Ocean(Tile tile){
        this.oceanTile=tile;
        this.humidity_level=8.0;
    }


    public Double getHumidityLevel() {
        return this.humidity_level;
    }

    public List<TileVertex> getMidPoints(){
        this.mid.add(oceanTile.getCentroid());
        return mid;
    }


}
