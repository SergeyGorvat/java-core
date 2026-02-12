package core.сoncurrency.task1;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Обеспечение безопасного добавления и извлечения между производителем и потребителем пула потоков.
 */

public class BlockingQueue<T> {
    private final Queue<T> queue;
    private final int maxSize;

    public BlockingQueue(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize должен быть > 0");
        }

        this.maxSize = maxSize;
        this.queue = new LinkedList<>();
    }

    public void enqueue(T element) throws InterruptedException {
        if (element == null) {
            throw new IllegalArgumentException("element не может быть null");
        }

        synchronized (this) {
            while (queue.size() >= maxSize) {
                wait();
            }

            queue.offer(element);
            notify();
        }
    }

    public T dequeue() throws InterruptedException {
        synchronized (this) {
            while (queue.isEmpty()) {
                wait();
            }

            T element = queue.poll();
            notify();

            return element;
        }
    }

    public synchronized int size() {
        return queue.size();
    }
}
