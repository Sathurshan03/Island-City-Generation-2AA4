package ca.mcmaster.cas.se2aa4.a3.island.Shape;

import ca.mcmaster.cas.se2aa4.a3.island.IslandCommandLineReader;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import java.util.List;

public class Circle extends Shape{
    double maxRadius;
    double radius;
    double meshCenterX;
    double meshCenterY;
    List<Tile> tiles;
    
    public Circle (double width, double height, List<Tile> tiles){
        super();

        this.maxRadius = Double.compare(width, height) < 0? width/2: height/2;
        this.tiles = tiles;
        this.radius = IslandCommandLineReader.randomGenerator.getNextdouble(maxRadius*0.5,maxRadius);
        this.meshCenterX = width /2;
        this.meshCenterY = height /2;
        markTiles();
    }

    public Circle (double width, double height, double centerX, double centerY, List<Tile> tiles){
        super();
        this.maxRadius = Double.compare(width, height) < 0? width/2: height/2;
        this.tiles = tiles;

        this.radius = IslandCommandLineReader.randomGenerator.getNextdouble(maxRadius*0.5,maxRadius);
        this.meshCenterX = centerX;
        this.meshCenterY = centerY;
        markTiles();
    }


    public List<Tile> getMarkedTiles(){
        return markedtiles;
    }
    public List<Tile> getUnMarkedTiles(){
        return unMarkedtiles;
    }
    public double getRadius(){
        return radius;
    }
    public double getCenterX(){
        return meshCenterX;
    }

    public double getCenterY(){
        return meshCenterY;
    }

    public void markTiles(){
        //Any Tile's centroid that falls within the radius is considered as unmarked
        double xDifference;
        double yDifference;
        double distance;

        for (Tile tile: tiles){
            xDifference =tile.getCentroidX() - meshCenterX;
            yDifference =tile.getCentroidY() - meshCenterY;
            distance = Math.sqrt(Math.pow(xDifference,2) + Math.pow(yDifference, 2));

            if(distance < radius){
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
