package ca.mcmaster.cas.se2aa4.a3.island.Shape;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import java.util.List;
import java.util.Random;

public class Circle implements Shape{
    double maxRadius;
    List<Tile> tiles;
    double radius;

    public Circle (double width, double height, List<Tile> tiles){
        this.maxRadius = Double.compare(width, height) < 0? height: width;
        this.tiles = tiles;

        Random random = new Random();
        this.radius = random.nextDouble(maxRadius*0.5,maxRadius);
    }
    public List<Tile> getMarkedTiles(){
        return null;
    }
    public List<Tile> getUnMarkedPolygons(){
        return null;
    }
    public void markTiles(){
        

    }
    
}
