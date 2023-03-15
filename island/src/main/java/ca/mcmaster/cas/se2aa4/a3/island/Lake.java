package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.tools.RandomGenerator;

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

        lakeSize = IslandCommandLineReader.randomGenerator.getNextInteger(1,maxLakeSize+1); // lake must be made up of at least 2 tiles
        System.out.println("Lake Size: " + lakeSize);

        outerLoop: while(true){
            Tile firstTile = potentialLakeTiles.get(IslandCommandLineReader.randomGenerator.getNextInteger(0, potentialLakeTiles.size()));
            List<Tile> neighbourTiles = firstTile.getNeighbouringTile();
            for(Tile tile: neighbourTiles){
                if(tile.isTileWater()){
                    continue outerLoop;
                }
            }

            if(!firstTile.isTileWater()){
                lakeTiles.add(firstTile);
                int tilesAdded = 1;
                addRemainingTiles(firstTile, tilesAdded);
                break;
            }
        }
    }

    private void addRemainingTiles (Tile startTile, int tilesAdded) {
        Tile currentTile = startTile;

        outerLoop:
        while (tilesAdded < lakeSize) {
            List<Tile> neighbouringTiles = currentTile.getNeighbouringTile();
            Tile nextTile = neighbouringTiles.get(IslandCommandLineReader.randomGenerator.getNextInteger(0, neighbouringTiles.size()));
            List<Tile> neighbourTiles = nextTile.getNeighbouringTile();
            for (Tile tile : neighbourTiles) {
                if (tile.isTileWater()) {
                    continue outerLoop;
                }

                if (!nextTile.isTileWater() && !lakeTiles.contains(nextTile)) {
                    lakeTiles.add(nextTile);
                    currentTile = nextTile;
                    tilesAdded++;
                }
            }
        }
    }

    public List<Tile> getLakeTiles() {
        return lakeTiles;
    }
}
