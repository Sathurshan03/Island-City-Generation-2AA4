package ca.mcmaster.cas.se2aa4.a3.island;
import java.io.IOException;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.Circle;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.ShapeType;
import ca.mcmaster.cas.se2aa4.a3.island.Tiles.TileTypes;

import java.util.List;

public class Sandbox extends Mode{
    
    public Sandbox(String inputMesh, String outputMesh) throws IOException{
        super(inputMesh, outputMesh, ShapeType.CIRCLE);

         //extract all the info from the input mesh
         extractInformation();
    }

    public void generate(){
        //generate a map following the sandbox requirements
        Circle circle = new Circle(width, height, tiles);
        List<Tile> oceanTiles = circle.getMarkedTiles();
        List<Tile> undecidedTiles = circle.getUnMarkedTiles();

        //set oceanTiles to their color
        for(Tile tile: oceanTiles){
            tile.setTileType(TileTypes.Ocean);
        }

        //Set the unMarked Tiles color
        for(Tile tile: undecidedTiles){
            tile.setTileType(TileTypes.EMPTY);
        }

    }

}
