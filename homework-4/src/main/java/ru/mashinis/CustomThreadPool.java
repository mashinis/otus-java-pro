package ru.mashinis;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class CustomThreadPool {
    private Queue<Runnable> runnableQueue;
    private List<WorkerThread> threads;
    private AtomicBoolean isThreadPoolShutDownInitiated;

    public CustomThreadPool(final int noOfThreads) {
        this.runnableQueue = new LinkedList<>();
        this.threads = new ArrayList<>(noOfThreads);
        this.isThreadPoolShutDownInitiated = new AtomicBoolean(false);

        for (int i = 1; i <= noOfThreads; i++) {
            WorkerThread thread = new WorkerThread(runnableQueue, this);
            thread.setName("Worker Thread - " + i);
            thread.start();
            threads.add(thread);
        }
    }

    public void execute(Runnable r) {
        if (isThreadPoolShutDownInitiated.get()) {
            throw new IllegalStateException("ThreadPool уже завершен");
        }
        runnableQueue.add(r);
    }

    public void shutdown() {
        isThreadPoolShutDownInitiated.set(true);
    }

    private class WorkerThread extends Thread {
        private Queue<Runnable> taskQueue;

        private CustomThreadPool threadPool;

        public WorkerThread(Queue<Runnable> taskQueue, CustomThreadPool threadPool) {
            this.taskQueue = taskQueue;
            this.threadPool = threadPool;
        }

        @Override
        public void run() {
            while (true) {
                Runnable task = null;
                synchronized (threadPool) {
                    if (threadPool.isThreadPoolShutDownInitiated.get() && taskQueue.isEmpty()) {
                        return;
                    }
                    if (!taskQueue.isEmpty()) {
                        task = taskQueue.poll();
                    }
                }
                if (task != null) {
                    task.run();
                }
            }
        }
    }
}