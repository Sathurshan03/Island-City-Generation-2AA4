package graphadt;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import graphadt.GraphComponents.Edge;
import graphadt.GraphComponents.Graph;
import graphadt.GraphComponents.Node;
import graphadt.PathCreator.ShortestPathFinder;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShortestPathTest {

    private static Node n1; 
    private static Node n2; 
    private static Node n4;
    private static Edge e2;
    private static Edge e3;
    private static Edge e4;
    private static ShortestPathFinder path;

    @BeforeAll
    public static void setup(){
        //setup for the test case, creates a graph to evaluate the base cases
        n1 = new Node("1");
        n2 = new Node("2");
        Node n3 = new Node("3");
        n4 = new Node("4");

        Edge e1 = new Edge(n1, n2, 10);
        e2 = new Edge(n1, n3, 2);
        e3 = new Edge(n3, n4, 3);
        e4 = new Edge(n4, n2, 2);
        Edge e5 = new Edge(n3, n2, 7);

        Set<Node> nodes = new HashSet<>();
        nodes.add(n1);
        nodes.add(n2);
        nodes.add(n3);
        nodes.add(n4);

        Set<Edge> edges = new HashSet<>();
        edges.add(e1);
        edges.add(e2);
        edges.add(e3);
        edges.add(e4);
        edges.add(e5);

        Graph graph = new Graph(nodes, edges);
        path = new ShortestPathFinder(graph);
    }

    @Test
    public  void shortestPathTest(){
        //Test for a shortest path among 3 different paths from start to end node

        Queue<Edge> myDefinedShortestPath = new LinkedList<>();
        myDefinedShortestPath.add(e2);
        myDefinedShortestPath.add(e3);
        myDefinedShortestPath.add(e4);

        try{
            Queue<Edge> shortestPath = path.findPath(n1, n2);
            while (myDefinedShortestPath.peek() != null){
                assertEquals(myDefinedShortestPath.poll(), shortestPath.poll());
            }
        }
        catch(Exception e){
            assertTrue(false);
        }
    }

    @Test
    public  void startNodePathTest(){
        //Test that the shortest path for start node to itself is nothing
        try{
            Queue<Edge> shortestPath = path.findPath(n1, n1);
            assertEquals(0, shortestPath.size());
        }
        catch(Exception e){
            assertTrue(false);
        }
    }

    @Test
    public void startNodeTest(){
        //Test if the path starts at starting node
        try{
            Queue<Edge> shortestPath = path.findPath(n1, n4);
            Edge firstEdge = shortestPath.poll();
            assertEquals(n1, firstEdge.getNode1());
        }
        catch(Exception e){
            assertTrue(false);
        }
    }

    @Test
    public void endNodeTest(){
        //Test if the path ends at ending node
        try{
            Queue<Edge> shortestPath = path.findPath(n1, n4);

            Edge lastEdge = null;
            for (Edge edge: shortestPath){
                lastEdge = edge;
            }

            assertEquals(n4, lastEdge.getNode2());
        }
        catch(Exception e){
            assertTrue(false);
        }
    }

    @Test 
    public void connectedPathTest(){
        //Checks to see if the path is connected
        try{
            Queue<Edge> shortestPath = path.findPath(n1, n2);
            Node currentNode = shortestPath.poll().getNode2();
            for (Edge edge: shortestPath){
                assertEquals(currentNode, edge.getNode1());
                currentNode = edge.getNode2();
            }
        }
        catch(Exception e){
            assertTrue(false);
        }
    }

    @Test
    public void noDuplicateEdgesTest(){
        //Test to ensure that there are not any duplciate edges in the shortest path
        try{
            Queue<Edge> shortestPath = path.findPath(n1, n2);
            
            Edge currentEdge; 

            while (shortestPath.size() != 0){
                currentEdge = shortestPath.poll();
                for (Edge otherEdges: shortestPath){
                    assertTrue(!currentEdge.equals(otherEdges));
                }
            }
        }
        catch(Exception e){
            assertTrue(false);
        }
    }


}
