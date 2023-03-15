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
import ca.mcmaster.cas.se2aa4.a3.tools.RandomGenerator;

public class Regular extends Mode {
    
    public Regular(String inputMesh, String outputMesh, ShapeType shapeType, AltitudeType altitudeType, String maxNumLakes) throws IOException{
        super(inputMesh, outputMesh, shapeType, altitudeType, maxNumLakes);

        //extract all the info from the input mesh
        extractInformation();
    }

    public void generate(){
        //generate the map based on the user inputs
        Shape islandShape = shape.getShape(width, height, tiles);
        List<Tile> oceanTiles = islandShape.getMarkedTiles();
        List<Tile> undecidedTiles = islandShape.getUnMarkedTiles();

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
        System.out.println(potentialLakeTiles.size());
        System.out.println(numLakes);
        System.out.println((double) potentialLakeTiles.size()/(double) numLakes);
        System.out.println(Math.sqrt((double) potentialLakeTiles.size()/(double) numLakes));
        System.out.println(maxLakeSize);

        for(int i = 0; i < numLakes; i++){
            Lake lake = new Lake(potentialLakeTiles, maxLakeSize);
            System.out.println("Lake Tiles: " + lake.getLakeTiles());
            for(Tile tile: lake.getLakeTiles()){
                tile.setTileType(TileTypes.LAKE);
            }
        }

        new Altitude(AltitudeType.OCEAN, oceanTiles);
        new Altitude(altitude, undecidedTiles);

    }
    private List<Tile> determineLakeTiles(List<Tile> undecidedTiles){
        List<Tile> potentialLakeTiles = new ArrayList<>(undecidedTiles); // Create deep copy of undecided tiles, so we can remove beach tiles.
                                                                         // Lakes can only be formed on tiles which are not beach or ocean tiles.
        List<Tile> beachTiles = new ArrayList<>();

        for(Tile tile: undecidedTiles){
            for(Tile neighbouringTile: tile.getNeighbouringTile()){
                if (neighbouringTile.isTileWater()){
                    beachTiles.add(neighbouringTile);
                    break;
                }
            }
        }
        potentialLakeTiles.removeAll(beachTiles);
        return potentialLakeTiles;
    }
}
