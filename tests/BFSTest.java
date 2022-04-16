/*
 * File: BFSTest.java
 * Date: Spring 2022
 * Auth: Dustin Cassell
 * Desc:
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;
import java.util.List;


/* TODO: You must do the following steps: 

   (1). Implement the algorithms in GraphAlgorithms.java. Note it is
   best to get these done one at a time, starting with BFS. Once done,
   do the steps below to test your algorithms. 

   (2). For BFS, you must add one new test for directed and one new
   test for undirected graphs. Your tests must contain a significantly
   more complex graph than provided below and must test the search
   from different nodes within the graph. You should spend time
   considering what good tests would be for each case (directed vs
   undirected).

   (3). For shortest paths, you must again add two additional tests,
   one for a directed graph and one for an undirected graph. Your
   graphs must be significantly more complex and require checking much
   longer paths. In addition, each test must check for multiple paths
   in the grahs.

   (4). For connected components, you must create one additional test
   over a considerably more complex graph (with many components). 

   (5). For the bipartite check, you must create one additional test
   over a considerable more complex graph. It should be a positive
   test in the sense that the graph selected is bipartite. 

   (6). You must create a write up that contains: (a) a drawing of
   each graph you tested with (and which test the graph was usef for);
   and (b) an explanation of what tests you did with respect to the
   corresponding graphs. 

   (7). Once you finish the tests, run and plot the performance tests: 
      
        bazel-bin/hw2 > output.dat
        gnuplot -c plot_script.gp

   Include the graph and an explanation of why you think the graph
   came out the way it did in your writeup.

*/



public class BFSTest {

    //--------------------------------------------------------------------
    // Directed Graph Tests
    //--------------------------------------------------------------------

    @Test
    public void basicDirectedAllReachableBFS() throws Exception {
        Graph<Integer> g = new AdjacencyList<>(4, true);
        g.add(0, null, 1);
        g.add(0, null, 2);
        g.add(2, null, 3);
        g.add(1, null, 0);
        // bfs from 0
        Map<Integer,Integer> tree = GraphAlgorithms.bfs(g, 0);
        assertEquals(4, tree.size());
        assertTrue(-1 == tree.get(0));
        assertTrue(0 == tree.get(1));
        assertTrue(0 == tree.get(2));
        assertTrue(2 == tree.get(3));
        // bfs from 1
        tree = GraphAlgorithms.bfs(g, 1);
        assertEquals(4, tree.size());
        assertTrue(-1 == tree.get(1));
        assertTrue(1 == tree.get(0));
        assertTrue(0 == tree.get(2));
        assertTrue(2 == tree.get(3));
    }


    @Test
    public void basicDirectedSomeReachableBFS() {
        Graph<Integer> g = new AdjacencyList<>(5, true);
        g.add(0, null, 1);
        g.add(0, null, 3);
        g.add(1, null, 2);
        g.add(1, null, 4);
        g.add(3, null, 1);
        g.add(3, null, 4);
        g.add(4, null, 2);
        // bfs from 3
        Map<Integer,Integer> tree = GraphAlgorithms.bfs(g, 3);
        assertEquals(4, tree.size());
        assertTrue(-1 == tree.get(3));
        assertTrue(3 == tree.get(1));
        assertTrue(3 == tree.get(4));
        assertTrue(1 == tree.get(2) || 4 == tree.get(2));
    }


    @Test
    public void basicDirectedNoneReachableBFS() {
        Graph<Integer> g = new AdjacencyList<>(5, true);
        g.add(0, null, 1);
        g.add(0, null, 3);
        g.add(1, null, 2);
        g.add(1, null, 4);
        g.add(3, null, 1);
        g.add(3, null, 4);
        g.add(4, null, 2);
        // bfs from 0
        Map<Integer,Integer> tree = GraphAlgorithms.bfs(g, 2);
        assertEquals(1, tree.size());
        assertTrue(-1 == tree.get(2));
    }


    @Test
    public void basicUndirectedAllReachableBFS() {
        Graph<Integer> g1 = new AdjacencyList<>(5, false);
        g1.add(0, null, 1);
        g1.add(0, null, 2);
        g1.add(1, null, 4);
        g1.add(1, null, 3);
        g1.add(3, null, 4);
        // bfs from 0
        Map<Integer,Integer> tree = GraphAlgorithms.bfs(g1, 0);
        assertEquals(5, tree.size());
        assertTrue(-1 == tree.get(0));
        assertTrue(0 == tree.get(2));
        assertTrue(0 == tree.get(1));
        assertTrue(1 == tree.get(3));
        assertTrue(1 == tree.get(4));
        // inverse edges
        Graph<Integer> g2 = new AdjacencyList<>(5, false);
        g2.add(1, null, 0);
        g2.add(2, null, 0);
        g2.add(4, null, 1);
        g2.add(3, null, 1);
        g2.add(4, null, 3);
        // bfs from 0
        tree = GraphAlgorithms.bfs(g2, 0);
        assertEquals(5, tree.size());
        assertTrue(-1 == tree.get(0));
        assertTrue(0 == tree.get(2));
        assertTrue(0 == tree.get(1));
        assertTrue(1 == tree.get(3));
        assertTrue(1 == tree.get(4));
    }


    @Test
    public void basicUndirectedSomeReachableBFS() {
        Graph<Integer> g = new AdjacencyList<>(4, false);
        g.add(0, null, 1);
        g.add(2, null, 3);
        // bfs from 0
        Map<Integer,Integer> tree = GraphAlgorithms.bfs(g, 0);
        assertEquals(2, tree.size());
        assertTrue(-1 == tree.get(0));
        assertTrue(0 == tree.get(1));
        // bfs from 3
        tree = GraphAlgorithms.bfs(g, 3);
        assertEquals(2, tree.size());
        assertTrue(-1 == tree.get(3));
        assertTrue(3 == tree.get(2));
    }


    @Test
    public void basicDirectedShortestPath() {
        Graph<Integer> g = new AdjacencyList(5, true);
        g.add(0, null, 1);
        g.add(0, null, 3);
        g.add(1, null, 2);
        g.add(1, null, 4);
        g.add(3, null, 1);
        g.add(3, null, 4);
        g.add(4, null, 1);
        // check for path 0 -> 1
        List<Integer> path = GraphAlgorithms.shortestPath(g, 0, 1);
        assertEquals(2, path.size());
        assertTrue(0 == path.get(0));
        assertTrue(1 == path.get(1));
        // check path 0 -> 2
        path = GraphAlgorithms.shortestPath(g, 0, 2);
        assertEquals(3, path.size());
        assertTrue(0 == path.get(0));
        assertTrue(1 == path.get(1));
        assertTrue(2 == path.get(2));
        // check path 1 -> 2
        path = GraphAlgorithms.shortestPath(g, 1, 2);
        assertEquals(2, path.size());
        assertTrue(1 == path.get(0));
        assertTrue(2 == path.get(1));
        // check path 3 -> 4
        path = GraphAlgorithms.shortestPath(g, 3, 4);
        assertEquals(2, path.size());
        assertTrue(3 == path.get(0));
        assertTrue(4 == path.get(1));
        // check path 4 -> 0 (should be null)
        path = GraphAlgorithms.shortestPath(g, 4, 0);
        assertEquals(null, path);
    }


    @Test
    public void basicUndirectedShortestPath() {
        Graph<Integer> g = new AdjacencyList<>(7, false);
        g.add(0, null, 1);
        g.add(0, null, 3);
        g.add(1, null, 2);
        g.add(1, null, 3);
        g.add(2, null, 3);
        g.add(2, null, 4);
        g.add(3, null, 4);
        g.add(5, null, 6);
        // check path 0 -> 4
        List<Integer> path = GraphAlgorithms.shortestPath(g, 0, 4);
        assertEquals(3, path.size());
        assertTrue(0 == path.get(0));
        assertTrue(3 == path.get(1));
        assertTrue(4 == path.get(2));
        // check path 4 -> 0
        path = GraphAlgorithms.shortestPath(g, 4, 0);
        assertEquals(3, path.size());
        assertTrue(4 == path.get(0));
        assertTrue(3 == path.get(1));
        assertTrue(0 == path.get(2));
        // check path 4 -> 1
        path = GraphAlgorithms.shortestPath(g, 4, 1);
        assertEquals(3, path.size());
        assertTrue(4 == path.get(0));
        assertTrue(2 == path.get(1) || 3 == path.get(1));
        assertTrue(1 == path.get(2));
        // check path 0 -> 6 (should be null)
        path = GraphAlgorithms.shortestPath(g, 0, 6);
        assertEquals(null, path);
    }


    @Test
    public void basicBFSConnectedComponents() {
        Graph<Integer> g = new AdjacencyList<>(6, false);
        g.add(0, null, 1);
        g.add(1, null, 2);
        g.add(2, null, 0);
        g.add(3, null, 4);
        g.add(5, null, 3);
        Map<Integer,Integer> components = GraphAlgorithms.connectedComponents(g);
        assertEquals(6, components.size());
        int c1 = components.get(0);
        assertTrue(c1 == components.get(1));
        assertTrue(c1 == components.get(2));
        int c2 = components.get(3);
        assertTrue(c2 == components.get(4));
        assertTrue(c2 == components.get(5));
        assertFalse(c1 == c2);
    }


    @Test
    public void basicBipartiteGraph() {
        // two node graph (undirected)
        Graph<Integer> g1 = new AdjacencyList<>(2, false);
        g1.add(0, null, 1);
        assertTrue(GraphAlgorithms.bipartite(g1));
        // 3-node graph w/ 2-coloring (undirected)
        Graph<Integer> g2 = new AdjacencyList(3, false);
        g2.add(1, null, 0);
        g2.add(0, null, 2);
        assertTrue(GraphAlgorithms.bipartite(g2));
        // 3-node graph w/out 2-coloring (directed)
        Graph<Integer> g3 = new AdjacencyList<>(3, true);
        g3.add(0, null, 1);
        g3.add(2, null, 2);
        g3.add(2, null, 0);
        assertFalse(GraphAlgorithms.bipartite(g3));
        // 4-node graph w/ 2-coloring (undirected)
        Graph<Integer> g4 = new AdjacencyList(4, false);
        g4.add(0, null, 1);
        g4.add(1, null, 3);
        g4.add(3, null, 2);
        g4.add(2, null, 0);
        assertTrue(GraphAlgorithms.bipartite(g4));
        // 4-node graph w/ 2-coloring (directed)
        Graph<Integer> g5 = new AdjacencyList(4, true);
        g5.add(1, null, 0);
        g5.add(1, null, 3);
        g5.add(2, null, 0);
        g5.add(2, null, 3);
        assertTrue(GraphAlgorithms.bipartite(g5));
    }


    @Test
    public void disconnectedBipartiteGraph() {
        // two component graph
        Graph<Integer> g = new AdjacencyList(7, false);
        g.add(0, null, 1);
        g.add(1, null, 3);
        g.add(3, null, 2);
        g.add(2, null, 0);
        g.add(4, null, 5);
        g.add(4, null, 6);
        assertTrue(GraphAlgorithms.bipartite(g));
    }

}
