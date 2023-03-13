package ca.mcmaster.cas.se2aa4.a3.island.Shape;

import java.util.List;

import ca.mcmaster.cas.se2aa4.a3.island.IslandCommandLineReader;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import java.awt.geom.AffineTransform;

public class Oval extends Shape{
    double maxlength;
    double length;
    double shortLength;
    java.awt.Shape ellipse;
    
    public Oval (double width, double height, List<Tile> tiles){
        super();

        this.maxlength = Double.compare(width, height) < 0? width: height;
        this.tiles = tiles;
        this.length = IslandCommandLineReader.randomGenerator.getNextdouble(maxlength*0.7, maxlength);
        this.shortLength = IslandCommandLineReader.randomGenerator.getNextdouble(length*0.25,length*0.7);
        this.meshCenterX = width /2;
        this.meshCenterY = height /2;
        double xStartingPoint = meshCenterX - shortLength/2;
        double yStartingPoint = meshCenterY - length/2;
        double angleRad = IslandCommandLineReader.randomGenerator.getNextdouble(-Math.PI/2, Math.PI/2);
        
        java.awt.geom.Ellipse2D.Double oval = new java.awt.geom.Ellipse2D.Double(xStartingPoint, yStartingPoint, shortLength, length);
        this.ellipse = AffineTransform.getRotateInstance(angleRad,meshCenterX, meshCenterY).createTransformedShape(oval);
        markTiles();
    }

    public List<Tile> getMarkedTiles(){
        return markedtiles;
    }
    public List<Tile> getUnMarkedTiles(){
        return unMarkedtiles;
    }

    public void markTiles(){
        //Any Tile's centroid that falls within the oval shape is considered as unmarked
        double xRelativePos;
        double yRelativePos;

        for (Tile tile: tiles){
            //xRelativePos = tile.getCentroidX() + shortLength/2;
            //yRelativePos = tile.getCentroidY() + length/2;
            xRelativePos = tile.getCentroidX();
            yRelativePos = tile.getCentroidY();
            
            if(ellipse.contains(xRelativePos, yRelativePos)){
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
