package ca.mcmaster.cas.se2aa4.a3.island.CityGraphs;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileSegment;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;

public class RoadObserver {

    private TileSegment tileSegment; 
    private TileVertex vertex1; 
    private TileVertex vertex2;

    public RoadObserver(TileSegment tileSegment, TileVertex vertex1, TileVertex vertex2){
        this.tileSegment = tileSegment; 
        this.vertex1 = vertex1; 
        this.vertex2 = vertex2;
    }

    public void update(){
        tileSegment.setSegmentRoad();
        vertex1.setVertexRoad();
        vertex2.setVertexRoad();
    }

}
