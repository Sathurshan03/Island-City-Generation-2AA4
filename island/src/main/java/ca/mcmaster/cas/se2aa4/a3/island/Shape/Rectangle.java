package ca.mcmaster.cas.se2aa4.a3.island.Shape;

import ca.mcmaster.cas.se2aa4.a3.island.IslandCommandLineReader;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import java.awt.geom.AffineTransform;
import java.util.List;


public class Rectangle extends Shape{
    double maxsize;
    double rectangleWidth;
    double rectangleHeight;
    java.awt.Shape rectangle;
    public Rectangle (double width, double height, List<Tile> tiles){
        super();

        this.maxsize = Double.compare(width, height) < 0? width: height;
        this.tiles = tiles;
        this.rectangleWidth = IslandCommandLineReader.randomGenerator.getNextdouble(maxsize*0.5,maxsize*0.85);
        this.rectangleHeight = IslandCommandLineReader.randomGenerator.getNextdouble(maxsize*0.5,maxsize*0.85);
        this.meshCenterX = width /2;
        this.meshCenterY = height /2;
        int xStartingPoint = (int)(meshCenterX - rectangleWidth / 2);
        int yStartingPoint = (int)(meshCenterY - rectangleHeight / 2);
        double angleRad = IslandCommandLineReader.randomGenerator.getNextdouble(0, Math.PI/2);

        java.awt.Rectangle basicRectangle = new java.awt.Rectangle(xStartingPoint, yStartingPoint, (int)rectangleWidth, (int)rectangleHeight);
        this.rectangle  = AffineTransform.getRotateInstance(angleRad,meshCenterX, meshCenterY).createTransformedShape(basicRectangle);
        markTiles();
    }
    public List<Tile> getMarkedTiles(){
        return markedtiles;
    }
    public List<Tile> getUnMarkedTiles(){
        return unMarkedtiles;
    }
    public void markTiles(){
        //Any Tile's centroid that falls within the rectangle is considered as unmarked

        for (Tile tile: tiles){
            if(rectangle.contains(tile.getCentroidX(), tile.getCentroidY())){
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
