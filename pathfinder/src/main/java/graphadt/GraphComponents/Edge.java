package graphadt.GraphComponents;

public class Edge {
    private double weight;
    private Node node1;
    private Node node2;

    public Edge(Node node1, Node node2, double weight){
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
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

    public Node getNode1(){
        return node1;
    }

    public Node getNode2(){
        return node2;
    }

    public boolean isNode1 (Node node){
        if (node.equals(node1)){
            return true;
        }
        return false;
    }

    public boolean isNode2 (Node node){
        if (node.equals(node2)){
            return true;
        }
        return false;
    }

}
