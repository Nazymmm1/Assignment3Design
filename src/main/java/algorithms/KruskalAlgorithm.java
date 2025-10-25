package algorithms;

import graph.Edge;
import graph.Graph;
import metrics.Metrics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KruskalAlgorithm {
    public static List<Edge> run(Graph graph, Metrics metrics){
        metrics.reset();
        metrics.start();

        List<Edge> mst= new ArrayList<>();
        List<Edge> edges=new ArrayList<>(graph.getEdges());
        edges.sort(Comparator.comparingInt(Edge::getWeight));

        DSU dsu= new DSU(graph.getVertices());

        for (Edge edge: edges){
            metrics.addOperation();

            String rootX= dsu.find(edge.getFrom());
            String rootY=dsu.find(edge.getTo());

            if(!rootX.equals(rootY)){
                mst.add(edge);
                metrics.addCost(edge.getWeight());
                dsu.union(rootX,rootY);
            }
        }
        metrics.stop();
        return mst;
    }
}