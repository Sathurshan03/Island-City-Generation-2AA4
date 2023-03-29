package graphadt.PathCreator;

import java.util.Stack;
import graphadt.GraphComponents.Edge;
import graphadt.GraphComponents.Node;

public interface NodeDistance {
    public Stack<Edge> findPath(Node start, Node target);
}
