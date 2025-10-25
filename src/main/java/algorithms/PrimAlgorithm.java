package algorithms;

import graph.Edge;
import graph.Graph;
import metrics.Metrics;

import java.util.*;

public class PrimAlgorithm {
    public static List<Edge> run(Graph graph, Metrics metrics){
        metrics.reset();
        metrics.start();

        List<Edge> mst= new ArrayList<>();
        Set<String> visited= new HashSet<>();
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));

        String start= graph.getVertices().get(0);
        visited.add(start);

        for(Edge edge: graph.getAdjacencyList().get(start)) {
            priorityQueue.add(edge);
        }
        while (!priorityQueue.isEmpty()){
            Edge edge= priorityQueue.poll();
            metrics.addOperation();

            if(visited.contains(edge.getTo())) continue;

            mst.add(edge);
            metrics.addCost(edge.getWeight());

            visited.add(edge.getTo());

            for(Edge next: graph.getAdjacencyList().get(edge.getTo())){
                if(!visited.contains(next.getTo())){
                    priorityQueue.add(next);
                }
            }

        }

        metrics.stop();
        if (mst.size() != graph.getVertices().size() - 1) {
            return null;
        }
        return mst;

    }

}