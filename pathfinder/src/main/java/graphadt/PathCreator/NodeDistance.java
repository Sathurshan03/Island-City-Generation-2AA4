package graphadt.PathCreator;

import java.util.Queue;

import graphadt.GraphComponents.Edge;
import graphadt.GraphComponents.Node;

public interface NodeDistance {
    
    public Queue<Edge> findPath(Node target) throws Exception;
}
