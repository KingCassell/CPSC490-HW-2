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
        queue = new LinkedList<>();
        discoveredNodes = new HashMap<>();
        int uVal;

        queue.add(src);
        while (!queue.isEmpty()) {
            uVal = queue.poll();
            if (!discoveredNodes.containsKey(uVal)){
                discoveredNodes.put(uVal, 1);
                System.out.println(uVal);
                queue.addAll(g.adjacent(uVal));
            }

        }
        return null;
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
