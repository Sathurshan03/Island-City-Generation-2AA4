package ca.mcmaster.cas.se2aa4.a3.island.Terrains;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.IslandCommandLineReader;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.TileTypes;

import java.util.ArrayList;
import java.util.List;

public class Lake extends BodiesWater {

    private List<Tile> lakeTiles;
    private int lakeSize;
    private List<Tile> undecidedTiles;


    public Lake(List<Tile> potentialLakeTiles, int maximumSize){
        this.humidity_level=12.0;
        this.lakeSize = 0;
        this.lakeTiles = new ArrayList<>();
        this.undecidedTiles = potentialLakeTiles;
        createLake(undecidedTiles, maximumSize);
    }

    private void createLake(List<Tile> undecidedTiles, int maxLakeSize){

        lakeSize = IslandCommandLineReader.randomGenerator.getNextInteger(1,maxLakeSize+1); // lake must be made up of at least 2 tiles

        Tile currentTile = undecidedTiles.remove(IslandCommandLineReader.randomGenerator.getNextInteger(0,undecidedTiles.size()));
        lakeTiles.add(currentTile);
        int tilesAdded = 1;
        List<Tile> neighbouringTiles;

        while (tilesAdded < lakeSize) {
            neighbouringTiles = currentTile.getNeighbouringTile();

            for (Tile tile : neighbouringTiles) {
                if (tilesAdded < lakeSize && undecidedTiles.contains(tile)) {
                    tilesAdded++;
                    lakeTiles.add(tile);
                    undecidedTiles.remove(tile);
                }
            }

            currentTile = neighbouringTiles.get(IslandCommandLineReader.randomGenerator.getNextInteger(0,currentTile.numNeighbouringTiles()));
        }
    }

    public void setLakeTiles(){
        //set all the tiles in the Lake into Lake tiles
        for(Tile tile: lakeTiles){
            tile.setTileType(TileTypes.LAKE);
        }
    }

    public List<Tile> getRemainingTiles(){
        return undecidedTiles;
    }

    public List<Tile> getLakeTiles() {
        return lakeTiles;
    }

    public Double getHumidityLevel() {
        return this.humidity_level;
    }

    public List<TileVertex> getMidPoints() {
        List<TileVertex> all_centroid=new ArrayList<>();

        for (Tile t:lakeTiles){
            all_centroid.add(t.getCentroid());

        }
        return all_centroid;
    }
}
