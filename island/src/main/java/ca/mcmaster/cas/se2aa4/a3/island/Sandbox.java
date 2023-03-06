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

        //Outer Circle
        Circle outerCircle = new Circle(width, height, tiles);
        List<Tile> oceanTiles = outerCircle.getMarkedTiles();
        List<Tile> undecidedTiles = outerCircle.getUnMarkedTiles();

        //set oceanTiles to their color
        for(Tile tile: oceanTiles){
            tile.setTileType(TileTypes.Ocean);
        }

        //Inner Circle
        Circle innerCircle = new Circle(outerCircle.getRadius(), outerCircle.getRadius(), outerCircle.getCenterX(), outerCircle.getCenterY(), undecidedTiles);
        List<Tile> landTiles = innerCircle.getMarkedTiles();
        List<Tile> lagoonTiles = innerCircle.getUnMarkedTiles();

        //Set lagoon tiles
        for(Tile tile: lagoonTiles){
            tile.setTileType(TileTypes.LAGOON);
        }

        //Set the unMarked Tiles color
        for(Tile tile: landTiles){
            tile.setTileType(TileTypes.UNDETERMINEDLAND);
        }

    }

}
