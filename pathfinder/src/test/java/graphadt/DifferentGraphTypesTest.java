package graphadt;

import org.junit.jupiter.api.Test;
import graphadt.GraphComponents.Edge;
import graphadt.GraphComponents.Graph;
import graphadt.GraphComponents.Node;
import graphadt.PathCreator.ShortestPathFinder;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class DifferentGraphTypesTest {

    @Test
    public void linearGraphTest(){
        //Test on a linear graph, the path should follow the linear path
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);

        Edge e1 = new Edge(n1, n2, 10);
        Edge e2 = new Edge(n2, n3, 2);
        Edge e3 = new Edge(n3, n4, 3);
        Edge e4 = new Edge(n4, n5, 2);

        Set<Node> nodes = new HashSet<>();
        nodes.add(n1);
        nodes.add(n2);
        nodes.add(n3);
        nodes.add(n4);
        nodes.add(n5);

        Set<Edge> edges = new HashSet<>();
        edges.add(e1);
        edges.add(e2);
        edges.add(e3);
        edges.add(e4);

        Graph graph = new Graph(nodes, edges);
        ShortestPathFinder path = new ShortestPathFinder(graph, n1);

        Queue<Edge> myDefinedShortestPath;
    
        try{
            Queue<Edge> shortestPath;
            for (Node node: nodes){
                myDefinedShortestPath = new LinkedList<>();
                myDefinedShortestPath.add(e1);
                myDefinedShortestPath.add(e2);
                myDefinedShortestPath.add(e3);
                myDefinedShortestPath.add(e4);

                shortestPath = path.findPath(node);

                while (shortestPath.peek() != null){
                    assertEquals(myDefinedShortestPath.poll(), shortestPath.poll());
                }
            }
            
        }
        catch(Exception e){
            assertTrue(false);
        }
    }

    @Test
    public  void cyclicGraphTest(){
        //Test in a cyclic graph, the shortest path would cycle around without closing the cicle
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);

        Edge e1 = new Edge(n1, n2, 10);
        Edge e2 = new Edge(n2, n3, 2);
        Edge e3 = new Edge(n3, n4, 3);
        Edge e4 = new Edge(n4, n5, 2);
        Edge e5 = new Edge(n5, n1, 7);

        Set<Node> nodes = new HashSet<>();
        nodes.add(n1);
        nodes.add(n2);
        nodes.add(n3);
        nodes.add(n4);
        nodes.add(n5);

        Set<Edge> edges = new HashSet<>();
        edges.add(e1);
        edges.add(e2);
        edges.add(e3);
        edges.add(e4);
        edges.add(e5);

        Graph graph = new Graph(nodes, edges);
        ShortestPathFinder path = new ShortestPathFinder(graph, n1);

        Queue<Edge> myDefinedShortestPath = new LinkedList<>();
        myDefinedShortestPath.add(e1);
        myDefinedShortestPath.add(e2);
        myDefinedShortestPath.add(e3);
        myDefinedShortestPath.add(e4);

        try{
            Queue<Edge> shortestPath = path.findPath(n5);
            while (myDefinedShortestPath.peek() != null){
                assertEquals(myDefinedShortestPath.poll(), shortestPath.poll());
            }
        }
        catch(Exception e){
            assertTrue(false);
        }
    }

    @Test
    public  void midCyclicGraphTest(){
        //Test in a cyclic graph where the starting node is not part of the cycle and the end node is part of the cycle
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);

        Edge e1 = new Edge(n1, n2, 10);
        Edge e2 = new Edge(n2, n3, 2);
        Edge e3 = new Edge(n3, n4, 10);
        Edge e4 = new Edge(n5, n4, 2);
        Edge e5 = new Edge(n3, n5, 7);
        Edge e6 = new Edge(n4, n5, 1);
        Edge e7 = new Edge(n5, n3, 1);

        Set<Node> nodes = new HashSet<>();
        nodes.add(n1);
        nodes.add(n2);
        nodes.add(n3);
        nodes.add(n4);
        nodes.add(n5);

        Set<Edge> edges = new HashSet<>();
        edges.add(e1);
        edges.add(e2);
        edges.add(e3);
        edges.add(e4);
        edges.add(e5);
        edges.add(e6);
        edges.add(e7);

        Graph graph = new Graph(nodes, edges);
        ShortestPathFinder path = new ShortestPathFinder(graph, n1);

        Queue<Edge> myDefinedShortestPath = new LinkedList<>();
        myDefinedShortestPath.add(e1);
        myDefinedShortestPath.add(e2);
        myDefinedShortestPath.add(e5);
        myDefinedShortestPath.add(e4);

        try{
            Queue<Edge> shortestPath = path.findPath(n4);
            while (myDefinedShortestPath.peek() != null){
                assertEquals(myDefinedShortestPath.poll(), shortestPath.poll());
            }
        }
        catch(Exception e){
            assertTrue(false);
        }
    }

    @Test
    public  void biDirectionalGraphTest(){
        //Test if we can get the same resutls ad ShortestPathTest using a Bidirectional graph
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);

        Edge e1 = new Edge(n1, n2, 10);
        Edge e2 = new Edge(n1, n3, 2);
        Edge e3 = new Edge(n3, n4, 3);
        Edge e4 = new Edge(n4, n2, 2);
        Edge e5 = new Edge(n3, n2, 7);
        Edge e1r = new Edge(n2, n1, 10);
        Edge e2r = new Edge(n3, n1, 2);
        Edge e3r = new Edge(n4, n3, 3);
        Edge e4r = new Edge(n2, n4, 2);
        Edge e5r = new Edge(n2, n3, 7);

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
        edges.add(e1r);
        edges.add(e2r);
        edges.add(e3r);
        edges.add(e4r);
        edges.add(e5r);

        Graph graph = new Graph(nodes, edges);
        ShortestPathFinder path = new ShortestPathFinder(graph, n1);

        Queue<Edge> myDefinedShortestPath = new LinkedList<>();
        myDefinedShortestPath.add(e2);
        myDefinedShortestPath.add(e3);
        myDefinedShortestPath.add(e4);

        try{
            Queue<Edge> shortestPath = path.findPath(n2);
            while (myDefinedShortestPath.peek() != null){
                assertEquals(myDefinedShortestPath.poll(), shortestPath.poll());
            }
        }
        catch(Exception e){
            assertTrue(false);
        }
    }

    @Test
    public  void treeGrapTest(){
        //Test pn a tree graph
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);


        Edge e1 = new Edge(n1, n2, 10);
        Edge e2 = new Edge(n1, n3, 2);
        Edge e3 = new Edge(n3, n4, 3);
        Edge e4 = new Edge(n4, n5, 2);
        Edge e5 = new Edge(n4, n6, 1);
        Edge e6 = new Edge(n5, n7, 1);
        Edge e7 = new Edge(n6, n7, 1);

        Set<Node> nodes = new HashSet<>();
        nodes.add(n1);
        nodes.add(n2);
        nodes.add(n3);
        nodes.add(n4);
        nodes.add(n5);
        nodes.add(n6);
        nodes.add(n7);

        Set<Edge> edges = new HashSet<>();
        edges.add(e1);
        edges.add(e2);
        edges.add(e3);
        edges.add(e4);
        edges.add(e5);
        edges.add(e6);
        edges.add(e7);

        Graph graph = new Graph(nodes, edges);
        ShortestPathFinder path = new ShortestPathFinder(graph, n1);

        Queue<Edge> myDefinedShortestPath = new LinkedList<>();
        myDefinedShortestPath.add(e2);
        myDefinedShortestPath.add(e3);
        myDefinedShortestPath.add(e5);
        myDefinedShortestPath.add(e7);


        try{
            Queue<Edge> shortestPath = path.findPath(n7);
            while (myDefinedShortestPath.peek() != null){
                assertEquals(myDefinedShortestPath.poll(), shortestPath.poll());
            }
        }
        catch(Exception e){
            assertTrue(false);
        }
    }

    @Test
    public void unConnectedGraphTest(){
        //Test on an unconnected graph and test to see that a path will never be created 
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);

        Edge e1 = new Edge(n1, n2, 10);
        Edge e2 = new Edge(n1, n3, 2);
        Edge e3 = new Edge(n3, n2, 3);
        Edge e4 = new Edge(n4, n5, 2);
        Edge e5 = new Edge(n5, n6, 7);
        Edge e6 = new Edge(n6, n4, 7);

        Set<Node> nodes = new HashSet<>();
        nodes.add(n1);
        nodes.add(n2);
        nodes.add(n3);
        nodes.add(n4);
        nodes.add(n5);

        Set<Edge> edges = new HashSet<>();
        edges.add(e1);
        edges.add(e2);
        edges.add(e3);
        edges.add(e4);
        edges.add(e5);
        edges.add(e6);

        Graph graph = new Graph(nodes, edges);
        ShortestPathFinder path = new ShortestPathFinder(graph, n1);
    
        try{
            Queue<Edge> shortestPath = path.findPath(n6); 
            assertTrue(false);
        }
        catch(Exception e){
            assertTrue(true);
        }
    }

    @Test 
    public void emptyGraphTest(){
        //Test on a graph that is empty 
        
        Set<Node> nodes = new HashSet<>();
        Set<Edge> edges = new HashSet<>();

        Node n1 = new Node(1);
        Node n6 = new Node(6);

        Graph graph = new Graph(nodes, edges);
        ShortestPathFinder path = new ShortestPathFinder(graph, n1);

        try{
            Queue<Edge> shortestPath = path.findPath(n6); 
            assertTrue(false);
        }
        catch(Exception e){
            assertTrue(true);
        }
    }

        @Test 
    public void oneNodeGraphTest(){
        //Test on a graph that only has one node
        Node n1 = new Node(1);
        Set<Node> nodes = new HashSet<>();
        nodes.add(n1);

        Set<Edge> edges = new HashSet<>();

        Graph graph = new Graph(nodes, edges);
        ShortestPathFinder path = new ShortestPathFinder(graph, n1);

        try{
            Queue<Edge> shortestPath = path.findPath(n1); 
            assertEquals(shortestPath.size(), 0);;
        }
        catch(Exception e){
            assertTrue(false);
        }
    }

}
