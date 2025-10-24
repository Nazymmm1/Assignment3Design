package org.example;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");

        List<Vertex> vertices = new ArrayList<>();
        vertices.add(a);
        vertices.add(b);
        vertices.add(c);

        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(a, b, 4));
        edges.add(new Edge(a, c, 3));
        edges.add(new Edge(b, c, 2));

        Graph graph = new Graph(1, vertices, edges);

        System.out.println("Graph created with " + graph.getVertices().size() + " vertices and " + graph.getEdges().size() + " edges.");
    }
}