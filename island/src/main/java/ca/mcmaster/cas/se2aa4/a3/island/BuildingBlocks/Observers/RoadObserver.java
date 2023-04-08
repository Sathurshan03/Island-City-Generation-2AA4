package ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Observers;

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

    public void callRoad(){
        tileSegment.setSegmentRoad();
        vertex1.setVertexRoad();
        vertex2.setVertexRoad();
    }

    public void callDirtRoad(){
        tileSegment.setSegmentDirtRoad();
        vertex1.setVertexDirtRoad();
        vertex2.setVertexDirtRoad();
    }
}
