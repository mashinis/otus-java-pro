package ru.mashinis;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class CustomThreadPool implements Runnable {
    private final AtomicBoolean isShutdown;
    private final Queue<Runnable> taskQueue;

    public CustomThreadPool(final int opacity) {
        Thread[] threads = new Thread[opacity];
        this.isShutdown = new AtomicBoolean(false);
        this.taskQueue = new LinkedList<>();

        for (int i = 0; i < opacity; i++) {
            threads[i] = new Thread(this);
            threads[i].setName("Worker Thread - " + i);
            threads[i].start();
        }
    }

    public synchronized void execute(Runnable task) {
        if (isShutdown.get()) {
            throw new IllegalStateException("ThreadPool уже завершен");
        }
        taskQueue.add(task);
        notify();
    }

    public synchronized void shutdown() {
        isShutdown.set(true);
        notifyAll();
    }

    @Override
    public void run() {
        while (true) {
            Runnable task = null;
            synchronized (this) {
                while (taskQueue.isEmpty() && !isShutdown.get()) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (isShutdown.get() && taskQueue.isEmpty()) {
                    break;
                }
                task = taskQueue.poll();
            }
            try {
                if (task != null) {
                    task.run();
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }
}