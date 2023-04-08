package ca.mcmaster.cas.se2aa4.a3.island.CityCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import graphadt.GraphComponents.Edge;
import graphadt.GraphComponents.Graph;
import graphadt.GraphComponents.Node;
import graphadt.PathCreator.ShortestPathFinder;

public class NearestCityFinder {
    private List<Queue<Edge>> pathList;
    public NearestCityFinder(Graph graph, Set<Node> cityNodes, Node centralNode){
        //find the nearest city of each city except for the central node. This will be used to create the non-star network

        ShortestPathFinder shortestPathFinder;
        Queue<Edge> path = null;
        Queue<Edge> shortestPath = null;
        double shortestPathCost;
        pathList = new ArrayList<>();

        for(Node startNode: cityNodes){
            if (!startNode.equals(centralNode)){
                shortestPathCost = Double.MAX_VALUE;
                shortestPathFinder = new ShortestPathFinder(graph, startNode);
                for (Node targetNode: cityNodes){
                    try{
                        path = shortestPathFinder.findPath(targetNode);

                        if (shortestPathFinder.getPathCost(targetNode) < shortestPathCost && shortestPathFinder.getPathCost(targetNode) > 1){
                            shortestPathCost = shortestPathFinder.getPathCost(targetNode);
                            shortestPath = path;

                        }
                    }
                    catch(Exception e){
                        System.out.println("Invalid island graph was analyzed.");
                        System.exit(0);
                    }
                }
                pathList.add(shortestPath);
            }
        }
    }

    public List<Queue<Edge>> getShortestPaths(){
        return pathList;
    }
    
}
