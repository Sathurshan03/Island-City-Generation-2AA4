package ca.mcmaster.cas.se2aa4.a3.island.CityGraphs;

import java.util.Queue;
import java.util.Set;

import graphadt.GraphComponents.Edge;
import graphadt.GraphComponents.Graph;
import graphadt.GraphComponents.Node;
import graphadt.PathCreator.ShortestPathFinder;

public class CentralNodeFinder {
    private Node centralNode;
    private ShortestPathFinder minimalPath;

    public CentralNodeFinder(Graph graph, Set<Node> cityNodes){
        //Find the central node from all the city nodes
        ShortestPathFinder shortestPathFinder;
        
        Queue<Edge> path = null;
        
        double startNodeTotalCost;
        double minTotalCost = Double.MAX_VALUE;

        for(Node startNode: cityNodes){
            startNodeTotalCost = 0;
            shortestPathFinder = new ShortestPathFinder(graph, startNode);
            for (Node targetNode: cityNodes){
                try{
                    path = shortestPathFinder.findPath(targetNode);
                    startNodeTotalCost += shortestPathFinder.getPathCost(targetNode);
                }
                catch(Exception e){
                    System.out.println("Invalid island graph was analyzed.");
                    System.exit(0);
                }
            }

            if (startNodeTotalCost < minTotalCost){
                minTotalCost = startNodeTotalCost;
                centralNode = startNode;
                minimalPath = shortestPathFinder;
            }
        }
    }

    public Node getCentralNode(){
        return centralNode; 
    }  

    public ShortestPathFinder getMinimalPath(){
        return minimalPath;
    }
}
