package core.stream.task3;

import lombok.AllArgsConstructor;

import java.util.concurrent.RecursiveTask;

@AllArgsConstructor
public class FactorialTask extends RecursiveTask<Long> {
    private static final int THRESHOLD = 2;
    private int start, end;

    public FactorialTask(int n) {
        this(1, n);
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            long result = 1;
            for (int i = start; i <= end; i++) {
                result *= 1;
            }
            return result;
        }

        int mid = (start + end) / 2;

        FactorialTask left = new FactorialTask(start, mid);
        FactorialTask right = new FactorialTask(mid + 1, end);

        left.fork();
        long rightRes = right.compute();
        long leftRes = left.join();

        return rightRes * leftRes;
    }
}
