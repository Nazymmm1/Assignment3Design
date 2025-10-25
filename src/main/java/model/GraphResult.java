package model;

import graph.Edge;

import java.util.List;

public class GraphResult {
    private int id;
    private InputStats inputStats;
    private AlgorithmResult prim;
    private AlgorithmResult kruskal;

    private static class InputStats {
        private int vertices;
        private int edges;

        public InputStats(int vertices, int edges) {
            this.edges = edges;
            this.vertices = vertices;
        }
    }
    public static class AlgorithmResult{
        private List<Edge> mstEdges;
        private int totalCost ;
        private int opertionCount;
        private double executionTime;

        public AlgorithmResult(List<Edge> mstEdges, int totalCost,
                               int opertionCount, double executionTime) {
            this.mstEdges = mstEdges;
            this.totalCost = totalCost;
            this.opertionCount = opertionCount;
            this.executionTime = executionTime;
        }
    }
    public GraphResult(int id, int vertices, int edgesCount,
                       AlgorithmResult prim, AlgorithmResult kruskal) {
        this.id = id;
        this.inputStats = new InputStats(vertices, edgesCount);
        this.prim = prim;
        this.kruskal = kruskal;
    }
}