# Path Finder
  - Author: Sathurshan Arulmohan [arulmohs@mcmaster.ca]

## Rationale
To model a non-negative weighted directed graph that contains a set of nodes and edges and to find the shortest path between any two nodes in the graph.

## Explanation
The Path Finder is a Dijkstra's algorithm implementation for a non-negative weighted directed graph. This algorithm is used to find the shortest path between any two nodes in the given graph. For this project, the Path Finder will be used to find the shortest path between any two city nodes. This will be helpful in determining the central city node for the star road network. A city node is considered as a central node in a star network if all its shortest paths to all other nodes are minimal. 

## How to use the library
`Node (String id)` → A node of the graph\
`Edge (Node node1, Node node2, double weight)` → An edge (node1, node2) of the graph\
`Graph(Set<Node> nodes, Set<Edge> edges)` → A graph that contains a set of nodes and edges\
`ShortestPathFinder(Graph graph)` → A generator to find the shortest path in the graph\
`Queue<Edge>: ShortestPathFinder.findPath(Node start, Node target)` → Returns a queue of edges that represents the shortest path from the start node to target node
