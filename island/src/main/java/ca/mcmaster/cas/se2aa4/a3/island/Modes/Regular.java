package ca.mcmaster.cas.se2aa4.a3.island.Modes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a3.island.Altitude.*;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.IslandCommandLineReader;
import ca.mcmaster.cas.se2aa4.a3.island.Lake;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.Shape;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.ShapeType;
import ca.mcmaster.cas.se2aa4.a3.island.Tiles.TileTypes;

public class Regular extends Mode {
    
    public Regular(String inputMesh, String outputMesh, ShapeType shapeType, AltitudeType altitudeType, String maxNumLakes) throws IOException{
        super(inputMesh, outputMesh, shapeType, altitudeType, maxNumLakes);

        //extract all the info from the input mesh
        extractInformation();
    }

    public void generate(){
        //generate the map based on the user inputs
        Shape islandShape = shape.getShape(width, height, tiles);
        List<Tile> oceanTiles = islandShape.getOutOfRangeTiles();
        List<Tile> undecidedTiles = islandShape.getInRangeTiles();

        //set oceanTiles to their color
        for(Tile tile: oceanTiles){
            tile.setTileType(TileTypes.Ocean);
        }

        //Set the unMarked Tiles color
        for(Tile tile: undecidedTiles){
            tile.setTileType(TileTypes.GRASSLAND);
        }

        int numLakes = IslandCommandLineReader.randomGenerator.getNextint(0,Integer.parseInt(maxLakes)+1);
        List<Tile> potentialLakeTiles = determineLakeTiles(undecidedTiles);
        int maxLakeSize = (int) Math.floor(Math.sqrt((double) potentialLakeTiles.size()/(double) numLakes));


        for(int i = 0; i < numLakes; i++){
            Lake lake = new Lake(potentialLakeTiles, maxLakeSize);
            for(Tile tile: lake.getLakeTiles()){
                tile.setTileType(TileTypes.LAKE);
            }
        }

        altitude_gen.SetElevation(altitude, undecidedTiles);
        altitude_gen.SetElevation(AltitudeType.OCEAN, oceanTiles);

    }
    private List<Tile> determineLakeTiles(List<Tile> undecidedTiles){
        List<Tile> potentialLakeTiles = undecidedTiles;
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
        return potentialLakeTiles;
    }
}
