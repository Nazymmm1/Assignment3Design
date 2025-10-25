package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private List<String> vertices;
    private List<Edge> edges;
    private Map<String,List<Edge>> adjacencyList;

    public Graph(List<String> vertices,List<Edge> edges){
        this.edges=edges;
        this. vertices=vertices;
        BuildAdjacencyList();
    }

    public void BuildAdjacencyList(){
        adjacencyList= new HashMap<>();
        for(String vertex: vertices){
            adjacencyList.put(vertex,new ArrayList<>());
        }
        for(Edge edge: edges){// a to b weight 5
            adjacencyList.get(edge.getFrom()).add(edge);
            adjacencyList.get(edge.getTo()).add(new Edge(edge.getTo(), edge.getFrom(), edge.getWeight()));

        }
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<String> getVertices() {
        return vertices;
    }

    public Map<String, List<Edge>> getAdjacencyList() {
        return adjacencyList;
    }


    public int getVerticesSize(){
        return vertices.size();
    }
    public int getEdgeSize(){
        return edges.size();
    }

    @Override
    public String toString(){
        return "Graph: "+ getVerticesSize() +" vertices and "+ getEdgeSize()+" edges";
    }
}