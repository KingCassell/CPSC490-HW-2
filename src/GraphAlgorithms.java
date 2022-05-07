/*
 * File: GraphAlgorithms.java
 * Date: Spring 2022
 * Auth: Dustin Cassell
 * Desc: Implements Breadth First Search, the graph query algorithm. This
 *       Algorithm provides the shortest path from one node to another,
 *       and provides a hash set of connected component nodes in a graph.
 *       The final feature is the determination of if a graph is Bipartite.
 */

import java.util.*;


public class GraphAlgorithms {
    
    private static Queue<Integer> queue;
    private static Map<Integer, Integer> discoveredNodes;

    // singleton
    private GraphAlgorithms() {}


    /**
     * Purpose: Performs a breadth-first traversal of the given graph starting at
     *          the given source node.
     * @param g the directed or undirected graph to search
     * @param src the source node to search from
     * @return the search tree resulting from the breadth-first search
     *         or null if src is an invalid index
     */
    public static Map<Integer,Integer> bfs(Graph g, int src) {
        List<Integer> toVisit = new ArrayList<>(g.nodeCount());
        queue = new LinkedList<Integer>();
        discoveredNodes = new HashMap<Integer, Integer>();
        int uVal;
        int index = 0;
        discoveredNodes.put(src, -1);
        queue.add(src);
        while (!queue.isEmpty()) {
            // get the next node to check and clear the list of adjacent nodes
            uVal = queue.poll();
            toVisit.clear();
            // Get all the nodes adjacent to the uVal
            if (g.directed()) {
                toVisit.addAll(g.outNodes(uVal));
            } else {
                toVisit.addAll(g.adjacent(uVal));
            }
            for (int vertex: toVisit) {
                // check the list of nodes that can be visited to see if they have already been discovered.
                // if not discovered and the edge exists, add it to the queue of possible nodes to be checked.
                if (!discoveredNodes.containsKey(vertex)) {
                    queue.add(vertex);
                    discoveredNodes.put(vertex, uVal);
                }
            }
        }
        return discoveredNodes;
    }


    /**
     * Purpose: Finds the shortest (unweighted) path from src to dst using
     *          (modified) bfs.
     * @param g the directed or undirected graph to search
     * @param src the source node to search from
     * @param dst the destination node of the path
     * @return the shortest path as a list from src to dst or null if
     *         there is no path, src is invalid, or dst is invalid.
     */
    public static List<Integer> shortestPath(Graph g, int src, int dst) {
        List<Integer> shortestPath = new ArrayList<>(g.nodeCount());
        Stack<Integer> stack = new Stack<>();
        // add the list of discovered nodes after performing a Breadth first search.
        discoveredNodes = bfs(g, src);
        int parent = 0;
        int child = dst;
        int index = 0;
        // traverse from the destination backwards to the src until the src is either found or no more nodes exist.
        // child is pointed to by parent for discoveredNodes.
        stack.push(child);
        while (discoveredNodes.get(child) != null) {
            // as the discoverNodes map is traversed and each node found is added to a stack
            // the stack will be used to reverse the order into the shortestPath list once src is found.
            parent = discoveredNodes.get(child);
            stack.push(parent);

            if (parent == src) {
                // load the list from the stack.
                while (!stack.isEmpty()) {
                    shortestPath.add(stack.pop());
                }
                return shortestPath;
            }
            // the child becomes the new parent value to increment the traversal.
            child = parent;
        }
        // if no path exists return null.
        return null;
    }


    /**
     * Purpose: Finds the connected components of the given graph. Treats the
     *          graph as undirected, regardless of whether the graph is directed
     *          or not (i.e., for directed graph, finds its weakly connected
     *          components).
     * @param g the given graph
     * @reutn the node component map (node to component number)
     */
    public static Map<Integer,Integer> connectedComponents(Graph g) {
        return null;
    }


    /**
     * Purpose: Determines if the given graph is bipartite by finding a
     *          2-coloring. Treats the graph as undirected, regardless of whether
     *          the graph is directed or not.
     * @param g the graph to check
     * @return true if the graph is bipartite and false otherwise
     */
    public static boolean bipartite(Graph g) {
        return false;
    }


}
