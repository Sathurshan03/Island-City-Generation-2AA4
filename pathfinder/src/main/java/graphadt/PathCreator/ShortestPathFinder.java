package graphadt.PathCreator;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.lang.Exception;
import graphadt.GraphComponents.Edge;
import graphadt.GraphComponents.Graph;
import graphadt.GraphComponents.Node;

public class ShortestPathFinder implements NodeDistance{
    private Set<Node> nodes;
    private Set<Edge> edges;
    private HashMap<Node,Optional<Node>> path;
    private HashMap<Node,Double> cost;
    private Stack<Node> shortestPathNodes;
    private Queue<Edge> shortestPath;

    public ShortestPathFinder(Graph graph){
        this.nodes = graph.getNodes();
        this.edges = graph.getEdges();
    }

    public Queue<Edge> findPath(Node start, Node target) throws Exception{
        //Dijkstra algorithm implementation 
        
        //Create path and cost set
        Optional<Node> holder = Optional.empty();
        path = new HashMap<>();
        cost = new HashMap<>();

        for (Node node: nodes){
            path.put(node, holder);
            cost.put(node, Double.POSITIVE_INFINITY);
        }

        //initialize the starting node path and cost
        path.put(start, Optional.ofNullable(start));
        cost.put(start, 0.0);

        //put node s into the priority queue
        PriorityQueue<Node> queue = new PriorityQueue<>(new nodeCostComparator());
        queue.add(start);

        Node startNode;
        Node endNode;
        while (queue.size() != 0){
            startNode = queue.poll();
            for (Edge edge: edges){
                if (startNode.equals(edge.getNode1())){
                    //(startNode, endNode)
                    endNode = edge.getNode2();
                    if (cost.get(startNode) + edge.getWeight() < cost.get(endNode)){
                        path.put(endNode, Optional.ofNullable(startNode));
                        cost.put(endNode, cost.get(startNode) + edge.getWeight());

                        //update the Queue
                        queue.add(endNode);
                    }
                }
            }
        }

        getNodeStack(start, target);
        getEdgeStack();

        return shortestPath;
    }

    private void getNodeStack(Node start, Node target) throws Exception{
        //Get the node representation of the shortest path
        shortestPathNodes = new Stack<>();
        shortestPathNodes.push(target);
        Node currentNode = target;

        //iterate through the path in order from target to start
        Optional<Node> val;
        while (!currentNode.equals(start)){
            val = path.get(currentNode);

            if(val.isPresent()){
                currentNode = val.get();
            }
            else{
                System.out.println("tes");
                throw new Exception("Shortest Path is not a complete path");
            }
            shortestPathNodes.add(currentNode);
        }
    }

    private void getEdgeStack(){
        //get the edge representation of the stack
        shortestPath = new LinkedList<>();
        Node node1;
        Node node2;

        int upperBound = shortestPathNodes.size() - 1;
        for (int i = 0; i < upperBound; i ++){
            node1 = shortestPathNodes.pop();
            node2 = shortestPathNodes.peek();

            for (Edge edge: edges){
                if (edge.isNode1(node1) && edge.isNode2(node2)){
                    shortestPath.add(edge);
                    break;
                }
            } 
        }
    }

    private class nodeCostComparator implements Comparator<Node>{
        @Override
        public int compare(Node node1, Node node2) {
            return Double.compare(cost.get(node1), cost.get(node2));
        }
    }
}


