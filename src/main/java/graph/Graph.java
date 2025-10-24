package graph;

import java.util.List;

public class Graph {
    private int id;
    private List<Vertex> vertices;
    private List<Edge> edges;

    public Graph(int id, List<Vertex> vertices,List<Edge> edges){
        this.id=id;
        this.vertices=vertices;
        this.edges=edges;
    }

    public Graph(){};


    public int getId() {
        return id;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }
}