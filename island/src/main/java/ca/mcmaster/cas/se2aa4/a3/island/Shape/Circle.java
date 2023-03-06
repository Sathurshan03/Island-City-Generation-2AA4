package ca.mcmaster.cas.se2aa4.a3.island.Shape;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import java.util.List;
import java.util.Random;

public class Circle extends Shape{
    double maxRadius;
    double radius;
    double meshCenterX;
    double meshCenterY;
    List<Tile> tiles;
    
    public Circle (double width, double height, List<Tile> tiles){
        super();
        
        this.maxRadius = Double.compare(width, height) < 0? height: width;
        this.tiles = tiles;

        Random random = new Random();
        this.radius = random.nextDouble(maxRadius*0.5,maxRadius);
        this.meshCenterX = width /2;
        this.meshCenterY = height /2;
        markTiles();
    }
    public List<Tile> getMarkedTiles(){
        return markedtiles;
    }
    public List<Tile> getUnMarkedPolygons(){
        return unMarkedtiles;
    }
    public void markTiles(){
        //Any Tile's centroid that falls within the radius is considered as unmarked
        double lowerBoundX = meshCenterX - radius;
        double upperBoundX = meshCenterX + radius;
        double lowerBoundY = meshCenterY - radius;
        double upperBoundY = meshCenterY + radius;

        for (Tile tile: tiles){
            if(tile.getCentroidX() >= lowerBoundX && tile.getCentroidX() <= upperBoundX && tile.getCentroidY() >= lowerBoundY && tile.getCentroidY() <= upperBoundY){
                //in range
                unMarkedtiles.add(tile);
            }
            else{
                //out of range
                markedtiles.add(tile);
            }
        }

    }
    
}
