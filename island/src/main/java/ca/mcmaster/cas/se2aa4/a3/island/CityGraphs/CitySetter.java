package ca.mcmaster.cas.se2aa4.a3.island.CityGraphs;

import java.util.HashMap;

import graphadt.GraphComponents.Edge;
import graphadt.GraphComponents.Node;

public class CitySetter {
    private HashMap<Node, IslandNode> islandNodeMap;
    private HashMap<Edge, IslandEdge> islandEdgesMap;
    public CitySetter(HashMap<Node, IslandNode> islandNodeMap, HashMap<Edge, IslandEdge> islandEdgesMap){
        this.islandNodeMap = islandNodeMap;
        this.islandEdgesMap = islandEdgesMap;
    }

    public void setCentralCity(Node centralNode){
        IslandNode centralIslandNode = islandNodeMap.get(centralNode);
        centralIslandNode.setCentralCity();
    }
}
