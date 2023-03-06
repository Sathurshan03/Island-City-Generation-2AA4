package ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks;

import ca.mcmaster.cas.se2aa4.a2.visualizer.ExtractPolygonInfo;
import ca.mcmaster.cas.se2aa4.a3.island.Tiles.TileTypes;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;

import java.awt.Color;
import java.util.List;

public class Tile extends ExtractPolygonInfo{
    TileVertex centroid;
    TileTypes tileType;
    public Tile(Polygon polygon, List<Segment> meshSegments, List<Vertex> meshVertices, int numPolygons){
        super(polygon, meshSegments, meshVertices, numPolygons);
    }
    public void setCentroid(TileVertex vertex){
        centroid = vertex;
    }
    public void setTileType(TileTypes tileType){
        this.tileType = tileType;
    }
    public double getCentroidX(){
        return centroid.getX();
    }
    public double getCentroidY(){
        return centroid.getY();
    }
    public Polygon getPolygon(){
        //create the Struct Polygon of Tiles
        Color color = tileType.getColor();
        String colorCode = color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "," + color.getAlpha();
        Property colourProperty=Property.newBuilder().setKey("background_color").setValue(colorCode).build();
        return Polygon.newBuilder().addAllSegmentIdxs(this.segmentIDs).addAllNeighborIdxs(this.neighbouringSegmentsID).addProperties(0,colourProperty).build();
    }
}
