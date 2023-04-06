package ca.mcmaster.cas.se2aa4.a3.island.CityGraphs;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;

public class NodeObserver {
    private TileVertex tileVertex;

    public NodeObserver(TileVertex vertex){
        this.tileVertex = vertex;
    }

    public void setCity(){
        tileVertex.setVertexCity();
    }

    public void setCentralCity(){
        tileVertex.setCentralVertexCity();
    }
    
}
