package model;

import graph.Edge;

import java.util.List;
//for Jsom
public class GraphData {
    private int id;
    private List<String> nodes;
    private List<Edge> edges;

    public int getId() { return id; }
    public List<String> getNodes() { return nodes; }
    public List<Edge> getEdges() { return edges; }

    @Override
    public String toString() {
        return "Graph "+id+": " + nodes.size() +" nodes, " + edges.size() +" edges";
    }
}