package ca.mcmaster.cas.se2aa4.a3.island.Terrains;

import java.util.List;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.TileTypes;

public class BeachGenerator implements Generator{
    private Boolean createBeaches;
    private List<Tile> underterminedTiles;
    public BeachGenerator(Boolean createBeaches, List<Tile> undeterminedTiles){
        this.createBeaches = createBeaches;
        underterminedTiles = undeterminedTiles;
    }

    public void generate(){
        //Any Tile that are neighbours to a water tile is a beach tile 
        if (createBeaches){  
            for(Tile tile: underterminedTiles){
                for(Tile neighbouringTile: tile.getNeighbouringTile()){
                    if (neighbouringTile.isTileWater()){
                        tile.setTileType(TileTypes.BEACH);
                        break;
                    }
                }
            }
        }
    }
    
}
