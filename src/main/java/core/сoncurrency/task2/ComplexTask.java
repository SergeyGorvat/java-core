package core.сoncurrency.task2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ComplexTask implements Runnable {

    private final int taskId;
    private final CyclicBarrier barrier;
    private volatile int result;

    public ComplexTask(int taskId, CyclicBarrier barrier) {
        this.taskId = taskId;
        this.barrier = barrier;
        this.result = 0;
    }

    @Override
    public void run() {
        try {
            execute();
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void execute() {
        try {
            Thread.sleep(500 + (int) (Math.random() * 500));

            this.result = taskId * 100 + (int) (Math.random() * 100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public int getResult() {
        return result;
    }
}