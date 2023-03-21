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

        lakeSize = IslandCommandLineReader.randomGenerator.getNextint(1,maxLakeSize+1); // lake must be made up of at least 2 tiles
        System.out.println("Lake Size: " + lakeSize);

        Tile firstTile;

        while(true) {
            firstTile = potentialLakeTiles.get(IslandCommandLineReader.randomGenerator.getNextint(0, potentialLakeTiles.size() - 1));
            List<Tile> neighbourTiles = firstTile.getNeighbouringTile();
            boolean neighbourTileIsWater = false;
            for (Tile tile : neighbourTiles) {
                if (tile.isTileWater()) {
                    neighbourTileIsWater = true;
                    break;
                }
            }

            if(neighbourTileIsWater || firstTile.isTileWater()) {
                continue;
            }
            break;
        }

        lakeTiles.add(firstTile);
        int tilesAdded = 1;
        addRemainingTiles(firstTile, tilesAdded);
    }

    private void addRemainingTiles (Tile startTile, int tilesAdded) {
        Tile currentTile = startTile;

        while (tilesAdded < lakeSize) {
            List<Tile> neighbouringTiles = currentTile.getNeighbouringTile();
            Tile nextTile = neighbouringTiles.get(IslandCommandLineReader.randomGenerator.getNextint(0, neighbouringTiles.size()-1));
            List<Tile> neighbourTiles = nextTile.getNeighbouringTile();

            boolean nextTileNeighbourIsWater = false;
            for (Tile tile : neighbourTiles) {
                if (tile.isTileWater()) {
                    nextTileNeighbourIsWater = true;
                    break;
                }
            }

            if(nextTileNeighbourIsWater || nextTile.isTileWater() || lakeTiles.contains(nextTile)) {
                continue;
            }

            lakeTiles.add(nextTile);
            currentTile = nextTile;
            tilesAdded++;
        }
    }

    public List<Tile> getLakeTiles() {
        return lakeTiles;
    }
}
