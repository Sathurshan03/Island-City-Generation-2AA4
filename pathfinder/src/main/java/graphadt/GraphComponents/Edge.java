package graphadt.GraphComponents;

public class Edge {
    private double weight;
    private Node node1;
    private Node node2;

    public Edge(double weight, Node node1, Node node2){
        this.weight = weight;
        this.node1 = node1;
        this.node2 = node2;
    }

    public double getWeight(){
        return weight;
    }

    public boolean nodeInEdge(Node node){
        if (node.equals(node1) || node.equals(node2)){
            return true;
        }
        return false;
    }
}
