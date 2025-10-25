

import algorithms.DSU;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


import java.util.*;
import graph.Edge;
import graph.Graph;
import metrics.Metrics;
import algorithms.PrimAlgorithm;
import algorithms.KruskalAlgorithm;

public class AlgorithmsTest {

    @Test
    public void testTotalCostIsSame() {
        List<String> vertices = Arrays.asList("A", "B", "C", "D");
        List<Edge> edges = Arrays.asList(
                new Edge("A", "B", 1),
                new Edge("B", "C", 2),
                new Edge("C", "D", 3),
                new Edge("A", "D", 10)
        );

        Graph graph = new Graph(vertices, edges);
        Metrics primMetrics = new Metrics();
        Metrics kruskalMetrics = new Metrics();

        var primResult = PrimAlgorithm.run(graph, primMetrics);
        var kruskalResult = KruskalAlgorithm.run(graph, kruskalMetrics);

        assertEquals(primMetrics.getTotalCost(), kruskalMetrics.getTotalCost());
    }
    @Test
    public void testNumberOfEdges() {
        List<String> vertices = Arrays.asList("A", "B", "C", "D");
        List<Edge> edges = Arrays.asList(
                new Edge("A", "B", 1),
                new Edge("B", "C", 2),
                new Edge("C", "D", 3),
                new Edge("A", "D", 10)
        );

        Graph graph = new Graph(vertices, edges);
        Metrics primMetrics = new Metrics();
        Metrics kruskalMetrics = new Metrics();

        var primResult = PrimAlgorithm.run(graph, primMetrics);
        var kruskalResult = KruskalAlgorithm.run(graph, kruskalMetrics);

        assertEquals(primResult.size(),kruskalResult.size());
    }
    @Test
    public void testNoCycles() {
        List<String> vertices = Arrays.asList("A", "B", "C", "D");
        List<Edge> edges = Arrays.asList(
                new Edge("A", "B", 1),
                new Edge("B", "C", 2),
                new Edge("C", "D", 3),
                new Edge("A", "D", 10)
        );

        Graph graph = new Graph(vertices, edges);
        Metrics primMetrics = new Metrics();
        Metrics kruskalMetrics = new Metrics();

        var primResult = PrimAlgorithm.run(graph, primMetrics);
        var kruskalResult = KruskalAlgorithm.run(graph, kruskalMetrics);

        DSU primDSU = new DSU(vertices);
        for(Edge edge: primResult){
            String rootX= primDSU.find(edge.getFrom());
            String rootY=primDSU.find(edge.getTo());
            assertFalse("Prim has a cycle",rootX.equals(rootY));
            primDSU.union(edge.getFrom(),edge.getTo());
        }

        DSU kruskalDSU = new DSU(vertices);
        for(Edge edge: kruskalResult){
            String rootX= kruskalDSU.find(edge.getFrom());
            String rootY=kruskalDSU.find(edge.getTo());
            assertFalse("Kruskal has a cycle",rootX.equals(rootY));
            kruskalDSU.union(edge.getFrom(),edge.getTo());
        }
    }
    @Test
    public void testConnectivity() {
        List<String> vertices = Arrays.asList("A", "B", "C", "D");
        List<Edge> edges = Arrays.asList(
                new Edge("A", "B", 1),
                new Edge("B", "C", 2),
                new Edge("C", "D", 3),
                new Edge("A", "D", 10)
        );

        Graph graph = new Graph(vertices, edges);
        Metrics primMetrics = new Metrics();
        Metrics kruskalMetrics = new Metrics();

        var primResult = PrimAlgorithm.run(graph, primMetrics);
        var kruskalResult = KruskalAlgorithm.run(graph, kruskalMetrics);

        DSU kruskalDSU = new DSU(vertices);
        for(Edge edge: kruskalResult){
            kruskalDSU.union(edge.getFrom(),edge.getTo());
        }
        DSU primDSU = new DSU(vertices);
        for(Edge edge: primResult){
            primDSU.union(edge.getFrom(),edge.getTo());
        }

        String rootPrim= primDSU.find(vertices.get(0));
        String rootKruskal= kruskalDSU.find(vertices.get(0));
        for (String vertex : vertices) {
            assertEquals(rootPrim, primDSU.find(vertex));
            assertEquals(rootKruskal, kruskalDSU.find(vertex));
        }
    }
    @Test
    public void testDisconnectedGraphHandledGracefuley() {
        List<String> vertices = Arrays.asList("A", "B", "C", "D");
        List<Edge> edges = Arrays.asList(
                new Edge("A", "B", 1),
                new Edge("B", "C", 2)
        );

        Graph graph = new Graph(vertices, edges);
        Metrics primMetrics = new Metrics();
        Metrics kruskalMetrics = new Metrics();

        var primResult = PrimAlgorithm.run(graph, primMetrics);
        var kruskalResult = KruskalAlgorithm.run(graph, kruskalMetrics);

        assertEquals(primResult,null);
        assertEquals(kruskalResult,null);
    }
    @Test
    public void testExecutionIsPositiveAndInMS() {
        List<String> vertices = Arrays.asList("A", "B", "C", "D");
        List<Edge> edges = Arrays.asList(
                new Edge("A", "B", 1),
                new Edge("B", "C", 2),
                new Edge("C", "D", 3),
                new Edge("A", "D", 10)
        );

        Graph graph = new Graph(vertices, edges);
        Metrics primMetrics = new Metrics();
        Metrics kruskalMetrics = new Metrics();

        var primResult = PrimAlgorithm.run(graph, primMetrics);
        var kruskalResult = KruskalAlgorithm.run(graph, kruskalMetrics);

        assertFalse("Prim execution time is negative",primMetrics.getExecutionTimeMs()<=0);
        assertFalse("Kruskal execution time is negative",primMetrics.getExecutionTimeMs()<=0);
        assertFalse("Prim execution time looks too large", primMetrics.getExecutionTimeMs() > 100);
        assertFalse("Kruskal execution time looks too large", kruskalMetrics.getExecutionTimeMs() > 100);
    }
    @Test
    public void testConsistency() {
        List<String> vertices = Arrays.asList("A", "B", "C", "D");
        List<Edge> edges = Arrays.asList(
                new Edge("A", "B", 1),
                new Edge("B", "C", 2),
                new Edge("C", "D", 3),
                new Edge("A", "D", 10)
        );

        Graph graph = new Graph(vertices, edges);
        Metrics primMetrics = new Metrics();
        Metrics kruskalMetrics = new Metrics();
        var primResult = PrimAlgorithm.run(graph, primMetrics);
        var kruskalResult = KruskalAlgorithm.run(graph, kruskalMetrics);

        Graph graph1= new Graph(vertices, edges);
        Metrics primMetrics1 = new Metrics();
        Metrics kruskalMetrics1 = new Metrics();
        var primResult1 = PrimAlgorithm.run(graph1, primMetrics1);
        var kruskalResult1 = KruskalAlgorithm.run(graph1, kruskalMetrics1);

        assertFalse("Prim operation count isnt consistent",primMetrics.getOperationsCount()!=primMetrics1.getOperationsCount());
        assertFalse("Kruskal operation count isnt consistent",kruskalMetrics.getOperationsCount()!=kruskalMetrics1.getOperationsCount());
        assertFalse("Prim execution time is negative",primMetrics.getOperationsCount()<=0);
        assertFalse("Kruskal execution time is negative",primMetrics.getOperationsCount()<=0);
    }
    @Test
    public void testResultsAreReproducible() {
        List<String> vertices = Arrays.asList("A", "B", "C", "D");
        List<Edge> edges = Arrays.asList(
                new Edge("A", "B", 1),
                new Edge("B", "C", 2),
                new Edge("C", "D", 3),
                new Edge("A", "D", 10)
        );

        Graph graph = new Graph(vertices, edges);
        Metrics primMetrics = new Metrics();
        Metrics kruskalMetrics = new Metrics();
        var primResult = PrimAlgorithm.run(graph, primMetrics);
        var kruskalResult = KruskalAlgorithm.run(graph, kruskalMetrics);

        Graph graph1= new Graph(vertices, edges);
        Metrics primMetrics1 = new Metrics();
        Metrics kruskalMetrics1 = new Metrics();
        var primResult1 = PrimAlgorithm.run(graph1, primMetrics1);
        var kruskalResult1 = KruskalAlgorithm.run(graph1, kruskalMetrics1);

        assertEquals(primResult,primResult1);
        assertEquals(kruskalResult,kruskalResult1);
    }
}