package metrics;

public class Metrics {
    private int totalCost;
    private int operationsCount;
    private long startTime;
    private long endTime;

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop() {
        endTime = System.nanoTime();
    }

    public double getExecutionTimeMs() {
        return (endTime - startTime) / 1_000_000.0;
    }

    public void addOperation() {
        operationsCount++;
    }

    public void addCost(int cost) {
        totalCost += cost;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void reset(){
        totalCost=operationsCount=0;
        startTime=endTime=0;
    }
    public double measureExecutionTime(Runnable algorithm, int repetitions) {
        long totalTime = 0;
        for (int i = 0; i < repetitions; i++) {
            start();
            algorithm.run();
            stop();
            totalTime += (endTime - startTime);
        }
        return totalTime / 1_000_000.0 / repetitions;
    }
    public int getOperationsCount() {
        return operationsCount;
    }
}