package ca.mcmaster.cas.se2aa4.a3.island.CityCreator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.IslandEdge;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.IslandNode;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileSegment;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Observers.NodeObserver;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Observers.RoadObserver;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.Generator;
import graphadt.GraphComponents.Edge;
import graphadt.GraphComponents.Graph;
import graphadt.GraphComponents.Node;

public class GraphGenerator implements Generator{
    private List<TileVertex> tileVerticies;
    private List<TileSegment> tileSegments;
    private HashMap<Node, IslandNode> islandNodeMap;
    private HashMap<Edge, IslandEdge> islandEdgesMap;
    private Set<Node> nodes;
    private Set<Edge> edges;
    private Graph graph;

    public GraphGenerator(List<TileVertex> tileVertices, List<TileSegment> tileSegments){
        this.tileSegments = tileSegments;
        this.tileVerticies = tileVertices;
        islandNodeMap = new HashMap<>();
        islandEdgesMap = new HashMap<>();
        nodes = new HashSet<>();
        edges = new HashSet<>();
    }


    public void generate(){
        //generates a graph to on the nodes and eddges from the island
        createNodes();
        createEdges();
        graph = new Graph(nodes, edges);
    }

    private void createNodes(){
        //create nodes for the graph
        IslandNode islandNode;
        NodeObserver nodeObserver;

        for (TileVertex vertex: tileVerticies){
            nodeObserver = new NodeObserver(vertex);
            islandNode = new IslandNode(nodeObserver);
            vertex.associateIslandNode(islandNode);
            islandNodeMap.put(islandNode.getNode(), islandNode);
            nodes.add(islandNode.getNode());
        }
    }

    private void createEdges(){
        //create edges for the graph
        RoadObserver observer;
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
            observer = new RoadObserver(segment, vertex1, vertex2);

            //Create 2 IslandEdge to represent bidirectional edge
            node1 = vertex1.getNodeRepresentation();
            node2 = vertex2.getNodeRepresentation();
            weight = calculateWeight(vertex1.getX(), vertex1.getY(), vertex1.getElevation(), vertex1.isVertexWater() ,vertex2.getX(), vertex2.getY(), vertex2.getElevation(), vertex2.isVertexWater());
            islandEdge1 = new IslandEdge(node1, node2, weight, observer);
            islandEdge2 = new IslandEdge(node2, node1, weight, observer);

            islandEdgesMap.put(islandEdge1.getEdge(), islandEdge1);
            islandEdgesMap.put(islandEdge2.getEdge(), islandEdge2);
            edges.add(islandEdge1.getEdge());
            edges.add(islandEdge2.getEdge());
        }
    }

    private double calculateWeight(double v1x, double v1y, double v1z, boolean v1isWater, double v2x, double v2y, double v2z, boolean v2isWater){
        //calculate the weight of an edge based on distance 
        double distance = Math.sqrt(Math.pow((v1x - v2x),2) + Math.pow((v1y - v2y),2) + Math.pow((v1z - v2z),2));

        if (v1isWater || v2isWater){
            distance *= 100;
        }

        return distance; 
    }

    public Graph getGraph(){
        return this.graph;
    }

    public HashMap<Node, IslandNode> getIslandNodeMap(){
        return islandNodeMap;
    }

    public HashMap<Edge, IslandEdge> getIslandEdgesMap(){
        return islandEdgesMap;
    }
}
