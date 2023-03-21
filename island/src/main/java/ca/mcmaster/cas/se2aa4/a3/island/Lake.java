package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;

import java.util.ArrayList;
import java.util.List;

public class Lake {

    private List<Tile> lakeTiles;
    private int lakeSize;

    public Lake(List<Tile> potentialLakeTiles, int maximumSize){
        this.lakeSize = 0;
        this.lakeTiles = new ArrayList<>();
        createLake(potentialLakeTiles, maximumSize);
    }

    private void createLake(List<Tile> potentialLakeTiles, int maxLakeSize){

        lakeSize = IslandCommandLineReader.randomGenerator.getNextint(1,maxLakeSize+1); // lake must be made up of at least 2 tiles

        Tile currentTile = potentialLakeTiles.remove(IslandCommandLineReader.randomGenerator.getNextint(0,potentialLakeTiles.size()));
        lakeTiles.add(currentTile);
        int tilesAdded = 1;
        List<Tile> neighbouringTiles;

        while (tilesAdded < lakeSize) {
            neighbouringTiles = currentTile.getNeighbouringTile();

            for (Tile tile : neighbouringTiles) {
                if (tilesAdded < lakeSize && potentialLakeTiles.contains(tile)) {
                    tilesAdded++;
                    lakeTiles.add(tile);
                    potentialLakeTiles.remove(tile);
                }
            }

            currentTile = neighbouringTiles.get(IslandCommandLineReader.randomGenerator.getNextint(0,currentTile.numNeighbouringTiles()));
        }

    }


    public List<Tile> getLakeTiles() {
        return lakeTiles;
    }
}
