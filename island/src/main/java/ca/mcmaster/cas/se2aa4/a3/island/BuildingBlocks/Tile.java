package ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks;

import ca.mcmaster.cas.se2aa4.a2.visualizer.ExtractPolygonInfo;
import ca.mcmaster.cas.se2aa4.a3.island.Tiles.TileElement;
import ca.mcmaster.cas.se2aa4.a3.island.Tiles.TileTypes;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import java.util.ArrayList;
import java.awt.Color;
import java.util.List;

public class Tile extends ExtractPolygonInfo{
    TileVertex centroid;
    public TileTypes tileType;
    List<TileSegment> tileSegmentList;
    List<TileSegment> neighbouringTileSegmentList;
    List<TileVertex> tileVerticesList;
    List<Tile> neighbouringTileList;
    Color color;
    public Tile(Polygon polygon, List<Segment> meshSegments, List<Vertex> meshVertices, int numPolygons){
        super(polygon, meshSegments, meshVertices, numPolygons);
        tileSegmentList = new ArrayList<>();
        neighbouringTileSegmentList = new ArrayList<>();
        tileVerticesList = new ArrayList<>();
        neighbouringTileList = new ArrayList<>();
    }
    public void setCentroid(TileVertex vertex){
        centroid = vertex;
    }
    public void setTileType(TileTypes tileType){
        this.tileType = tileType;
    }
    public Boolean isTileWater(){
        if (tileType.getElememt().equals(TileElement.WATER)){
            return true;
        }
        return false;
    }
    
    public TileVertex getCentroid(){
        return centroid;
    }
    public double getCentroidX(){
        return centroid.getX();
    }
    public double getCentroidY(){
        return centroid.getY();
    }
    public List<TileSegment> getNeighbouringTileSegments(){
        return neighbouringTileSegmentList;
    }
    public List<Tile> getNeighbouringTile(){
        return neighbouringTileList;
    }

    public Boolean isTileVerticesListContains(TileVertex tileVertex){
        if (tileVerticesList.contains(tileVertex)){
            return false;
        }
        return true;
    }

    public Polygon getPolygon(){
        //create the Struct Polygon of Tiles
        color = tileType.getColor();
        setColor();
        String colorCode = color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "," + color.getAlpha();
        Property colourProperty=Property.newBuilder().setKey("background_color").setValue(colorCode).build();
        return Polygon.newBuilder().addAllSegmentIdxs(this.segmentIDs).addAllNeighborIdxs(this.neighbouringSegmentsID).addProperties(0,colourProperty).build();
    }

    public void addTileSegment(TileSegment tileSegment){
        tileSegmentList.add(tileSegment);
    }
    public void addNeighbouringTileSegment(TileSegment tileSegment){
        neighbouringTileSegmentList.add(tileSegment);
    }
    public void addTileVertex(TileVertex tileVertex){
        tileVerticesList.add(tileVertex);
    }
    public void addNeighbouringTile(Tile tile){
        neighbouringTileList.add(tile);
    }
    private void setColor(){
        //set the color of segments and vertices 
        for (TileSegment ts: tileSegmentList){
            ts.setColor(color);
        }
    }
}
