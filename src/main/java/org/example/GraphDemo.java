package org.example;

import algorithms.KruskalAlgorithm;
import algorithms.PrimAlgorithm;
import graph.Edge;
import graph.Graph;
import metrics.Metrics;

import java.util.ArrayList;
import java.util.List;

public class GraphDemo {
    public static void main(String[] args) {
        List<String> vertices = List.of("A", "B", "C", "D");

        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge("A", "B", 3));
        edges.add(new Edge("A", "C", 2));
        edges.add(new Edge("B", "C", 1));
        edges.add(new Edge("B", "D", 4));
        edges.add(new Edge("C", "D", 5));
        edges.add(new Edge("A", "D", 3));
        Graph graph = new Graph(vertices, edges);

        Metrics primMetrics = new Metrics();
        PrimAlgorithm prim = new PrimAlgorithm();
        List<Edge> primResults=prim.run(graph, primMetrics);

        System.out.println("Prim's algorithm result:");
        System.out.println(primResults);
        System.out.println("Total cost: " + primMetrics.getTotalCost());
        System.out.println("Operations counts: " + primMetrics.getOperationsCount());

        Metrics kruskalMetrics = new Metrics();
        KruskalAlgorithm kruskal = new KruskalAlgorithm();
        List<Edge> kruskalResults= kruskal.run(graph, kruskalMetrics);

        System.out.println("Kruskal's algorithm result:");
        System.out.println(kruskalResults);
        System.out.println("Total cost: " + kruskalMetrics.getTotalCost());
        System.out.println("Operations counts: " + kruskalMetrics.getOperationsCount());
    }
}