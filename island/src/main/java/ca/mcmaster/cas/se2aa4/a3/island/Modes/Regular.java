package ca.mcmaster.cas.se2aa4.a3.island.Modes;

import java.io.IOException;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a3.island.Altitude.*;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.Shape;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.ShapeType;
import ca.mcmaster.cas.se2aa4.a3.island.Tiles.TileTypes;

public class Regular extends Mode {
    
    public Regular(String inputMesh, String outputMesh, ShapeType shapeType, AltitudeType altitudeType) throws IOException{
        super(inputMesh, outputMesh, shapeType, altitudeType);

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

        altitude.getAltitude(undecidedTiles);
        AltitudeType.WATER.getAltitude(oceanTiles);



        for (Tile t:undecidedTiles){
            for (TileVertex v: t.getTileVertices()){
                v.setThickness(v.getElevation());
            }

        }

        for (Tile t:oceanTiles){
            for (TileVertex v: t.getTileVertices()){
                v.setThickness(v.getElevation());
            }

        }
        
    }
    
}
