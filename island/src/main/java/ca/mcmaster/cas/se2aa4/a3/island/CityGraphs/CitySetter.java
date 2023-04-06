package ca.mcmaster.cas.se2aa4.a3.island.CityGraphs;

import java.util.HashMap;
import java.util.Queue;
import java.util.Set;

import graphadt.GraphComponents.Edge;
import graphadt.GraphComponents.Node;
import graphadt.PathCreator.ShortestPathFinder;

public class CitySetter {
    private HashMap<Node, IslandNode> islandNodeMap;
    private HashMap<Edge, IslandEdge> islandEdgesMap;
    public CitySetter(HashMap<Node, IslandNode> islandNodeMap, HashMap<Edge, IslandEdge> islandEdgesMap){
        this.islandNodeMap = islandNodeMap;
        this.islandEdgesMap = islandEdgesMap;
    }

    public void setCentralCity(Node centralNode){
        //Visualizes the central city with a red node 
        IslandNode centralIslandNode = islandNodeMap.get(centralNode);
        centralIslandNode.setCentralCity();
    }

    public void setRoads(ShortestPathFinder shortestPaths, Set<Node> cityNodes){
        //Set the paths as roads on the island 
        Queue<Edge> path;
        Edge road;
        IslandEdge islandRoad;

        for (Node targetNode: cityNodes){
            try{
                path = shortestPaths.findPath(targetNode);
                while (!path.isEmpty()){
                    road = path.poll();
                    islandRoad = islandEdgesMap.get(road);
                    islandRoad.setRoad();
                }
            }
            catch(Exception e){
                System.out.println("Invalid island graph was analyzed.");
                System.exit(0);
            }
        }

    }
}
