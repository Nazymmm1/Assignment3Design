package graph;

public class Edge implements Comparable<Edge>{
    private Vertex source;
    private Vertex destination;
    private int weight;

    public Edge(Vertex source,Vertex destination,int weight){
        this.source=source;
        this.destination=destination;
        this.weight=weight;
    }

    public int getWeight() {
        return weight;
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getDestination() {
        return destination;
    }
    @Override
    public int compareTo(Edge other){
        return Integer.compare(this.weight,other.weight);
    }
}