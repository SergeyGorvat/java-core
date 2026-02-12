package core.сoncurrency.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public record ComplexTaskExecutor(int numberOfTasks) {

    public void executeTasks(int numThreads) {
        CyclicBarrier barrier = new CyclicBarrier(numThreads);
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<ComplexTask> tasks = new ArrayList<>();

        try {
            for (int i = 1; i <= numThreads; i++) {
                ComplexTask task = new ComplexTask(i, barrier);
                tasks.add(task);
                executor.submit(task);
            }

            executor.shutdown();
            executor.awaitTermination(10, TimeUnit.SECONDS);

            int totalResult = 0;
            for (ComplexTask task : tasks) {
                totalResult += task.getResult();
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            executor.shutdownNow();
        }
    }
}