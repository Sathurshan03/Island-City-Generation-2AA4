package ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Observers.NodeObserver;
import graphadt.GraphComponents.Node;

public class IslandNode {
    private int id;
    private Node node;
    private NodeObserver nodeObserver;

    public IslandNode (NodeObserver nodeObserver){
        this.id = this.hashCode();
        this.node = new Node(id);
        this.nodeObserver = nodeObserver;
    }

    public Node getNode(){
        return this.node;
    }

    public void setCity(){
        nodeObserver.setCity();
    }

    public void setCentralCity(){
        nodeObserver.setCentralCity();
    }
    
}
