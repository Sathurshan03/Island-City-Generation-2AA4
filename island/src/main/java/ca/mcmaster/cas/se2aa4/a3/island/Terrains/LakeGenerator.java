package ca.mcmaster.cas.se2aa4.a3.island.Terrains;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a3.island.IslandCommandLineReader;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.TileTypes;

public class LakeGenerator implements Generator{
    private int maxNumLakes;
    private List<Tile> undecidedTiles;
    private List<Tile> potentialLakeTiles;
    private List<Lake> lakes;

    public LakeGenerator(int maxNumLakes, List<Tile> undecidedTiles){
        this.maxNumLakes = maxNumLakes;
        this.undecidedTiles = undecidedTiles;
        this.potentialLakeTiles = new ArrayList<>(undecidedTiles);
        this.lakes = new ArrayList<>();
    }

    public void generate(){
        //Generate the Lake Tiles

        int numLakes = IslandCommandLineReader.randomGenerator.getNextInteger(0, maxNumLakes);
        determineLakeTiles();
        int maxLakeSize = (int) Math.floor(Math.sqrt((double) potentialLakeTiles.size()/(double) numLakes));
        Lake lake;

        for(int i = 0; i < numLakes; i++){
            lake = new Lake(potentialLakeTiles, maxLakeSize);
            lake.setLakeTiles();
            lakes.add(lake);
            undecidedTiles.removeAll(lake.getLakeTiles());
            potentialLakeTiles = lake.getRemainingTiles();
        }
    }

    private void determineLakeTiles(){
        List<Tile> beachTiles = new ArrayList<>();

        for(Tile tile: undecidedTiles){
            for(Tile neighbouringTile: tile.getNeighbouringTile()){
                if (neighbouringTile.isTileWater()){
                    beachTiles.add(tile);
                    break;
                }
            }
        }

        potentialLakeTiles.removeAll(beachTiles);
    }

    public List<Tile> getRemainingTiles(){
        return undecidedTiles;
    }
    
}
