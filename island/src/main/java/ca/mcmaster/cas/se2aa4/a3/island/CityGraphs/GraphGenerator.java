package ca.mcmaster.cas.se2aa4.a3.island.CityGraphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileSegment;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.Generator;
import graphadt.GraphComponents.Edge;
import graphadt.GraphComponents.Graph;
import graphadt.GraphComponents.Node;

public class GraphGenerator implements Generator{
    private List<TileVertex> tileVerticies;
    private List<TileSegment> tileSegments;
    private HashMap<TileVertex, Node> nodeMap;
    private HashMap<Integer, IslandEdge> islandEdges;
    private Set<Edge> edges;
    private Graph graph;

    public GraphGenerator(List<TileVertex> tileVertices, List<TileSegment> tileSegments){
        this.tileSegments = tileSegments;
        this.tileVerticies = tileVertices;
        nodeMap = new HashMap<>();
        islandEdges = new HashMap<>();
        edges = new HashSet<>();
    }


    public void generate(){
        //generates a graph to on the nodes and eddges from the island
        createNodes();
        createEdges();
        Set<Node> nodes = new HashSet<Node>(nodeMap.values());
        graph = new Graph(nodes, edges);
    }

    private void createNodes(){
        //create nodes for the graph
        int id;
        for (TileVertex vertex: tileVerticies){
            id = vertex.hashCode();
            nodeMap.put(vertex, new Node(id));
        }
    }

    private void createEdges(){
        //create edges for the graph
        Observer observer;
        Node node1; 
        Node node2;
        TileVertex vertex1;
        TileVertex vertex2;
        IslandEdge islandEdge1;
        IslandEdge islandEdge2;
        double weight;

        for (TileSegment segment: tileSegments){
            //create observer
            vertex1 = segment.getTileVertex1();
            vertex2 = segment.getTileVertex2();
            observer = new Observer(segment, vertex1, vertex2);

            //Create 2 IslandEdge to represent bidirectional edge
            node1 = nodeMap.get(vertex1);
            node2 = nodeMap.get(vertex2);
            weight = calculateWeight(vertex1.getX(), vertex1.getY(), vertex2.getX(), vertex2.getY());
            islandEdge1 = new IslandEdge(node1, node2, weight, observer);
            islandEdge2 = new IslandEdge(node2, node1, weight, observer);

            islandEdges.put(islandEdge1.hashCode(), islandEdge1);
            islandEdges.put(islandEdge2.hashCode(), islandEdge2);
            edges.add(islandEdge1.getEdge());
            edges.add(islandEdge2.getEdge());
        }
    }

    private double calculateWeight(double v1x, double v1y, double v2x, double v2y){
        //calculate the weight of an edge based on distance 
        double distance = Math.sqrt(Math.pow((v1x - v2x),2) + Math.pow((v1y - v2y),2));
        return distance; 
    }

    public Graph getGraph(){
        return this.graph;
    }

    public Node returnNode(TileVertex vertex){
        return nodeMap.get(vertex);
    }

    public void islandEdgeSetRoad(int id){
        IslandEdge islandEdge = islandEdges.get(id);
        islandEdge.setRoad();
    }
}
