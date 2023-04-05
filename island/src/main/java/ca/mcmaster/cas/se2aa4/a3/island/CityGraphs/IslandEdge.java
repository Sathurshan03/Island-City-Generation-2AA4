package ca.mcmaster.cas.se2aa4.a3.island.CityGraphs;

import graphadt.GraphComponents.Edge;
import graphadt.GraphComponents.Node;

public class IslandEdge{
    private Observer observer; 
    private Edge edge;

    public IslandEdge (Node node1, Node node2, double weight, Observer observer){
        this.observer = observer; 
        this.edge = new Edge(node1, node2, weight, this.hashCode());
    }

    public Edge getEdge(){
        return edge;
    }

    public void setRoad(){
        observer.update();
    }
    
}
