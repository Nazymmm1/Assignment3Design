package org.example;

import algorithms.KruskalAlgorithm;
import algorithms.PrimAlgorithm;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import graph.Edge;
import graph.Graph;
import metrics.Metrics;
import model.*;
import model.GraphResult;
import model.GraphWrapper;
import util.JsonReaderUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String fileName = "inputt.json";

        GraphWrapper inputData = JsonReaderUtil.readFromJson(fileName);

        List<GraphResult> graphResults = new ArrayList<>();

        for (GraphData graphData : inputData.getGraphs()) {
            Graph graph = new Graph(graphData.getNodes(), graphData.getEdges());

            Metrics primMetrics = new Metrics();
            PrimAlgorithm primAlgorithm = new PrimAlgorithm();
            List<Edge> primMST = primAlgorithm.run(graph, primMetrics);

            GraphResult.AlgorithmResult primResult = new GraphResult.AlgorithmResult(primMST,
                    primMetrics.getTotalCost()
                    , primMetrics.getOperationsCount(),
                    primMetrics.measureExecutionTime(() -> PrimAlgorithm.run(graph, primMetrics), 1000));

            Metrics kruskalsMetrics = new Metrics();
            KruskalAlgorithm kruskalAlgorithm = new KruskalAlgorithm();
            List<Edge> kruskalMST = kruskalAlgorithm.run(graph, kruskalsMetrics);

            GraphResult.AlgorithmResult kruskalResult = new GraphResult.AlgorithmResult(kruskalMST, kruskalsMetrics.getTotalCost(),
                    kruskalsMetrics.getOperationsCount(),
                    kruskalsMetrics.measureExecutionTime(() -> KruskalAlgorithm.run(graph, kruskalsMetrics), 1000));

            graphResults.add(new GraphResult(graphData.getId(), graphData.getNodes().size(),
                    graphData.getEdges().size(), primResult, kruskalResult));

        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter("output.json")) {
            java.util.Map<String, Object> output = new java.util.HashMap<>();
            output.put("results", graphResults);

            writer.write(gson.toJson(output));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}