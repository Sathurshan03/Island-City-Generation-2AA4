package ca.mcmaster.cas.se2aa4.a3.island.Terrains;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;

import java.util.ArrayList;
import java.util.List;

public class Aquifer extends BodiesWater{
    private Tile aquiferTile;
    public Aquifer(Tile aquiferTile){
        this.aquiferTile = aquiferTile;
        // add humidity level
    }
    public Double getHumidityLevel() {
        return this.humidity_level;
    }
    public List<TileVertex> getMidPoints(){
        List<TileVertex> mid=new ArrayList<>();
        mid.add(aquiferTile.getCentroid());
        return mid;
    }
}
