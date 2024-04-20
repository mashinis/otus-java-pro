package ru.mashinis;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class CustomThreadPool {
    private final int poolSize;
    private final Thread[] threads;
    private final Queue<Runnable> taskQueue;
    private final AtomicBoolean isShutdown;

    public CustomThreadPool(int poolSize) {
        this.poolSize = poolSize;
        this.threads = new Thread[poolSize];
        this.taskQueue = new LinkedList<>();
        this.isShutdown = new AtomicBoolean(false);

        for (int i = 0; i < poolSize; i++) {
            threads[i] = new WorkerThread();
            threads[i].setName("Worker Thread - " + i);
            threads[i].start();
        }
    }

    public synchronized void execute(Runnable task) {
        if (isShutdown.get()) {
            throw new IllegalStateException("ThreadPool уже завершен");
        }
        taskQueue.add(task);
        notify(); // Оповещение о наличии новой задачи
    }

    public synchronized void shutdown() {
        isShutdown.set(true);
        notifyAll(); // Оповещение всех потоков о завершении
    }

    private class WorkerThread extends Thread {
        private volatile boolean isStopped = false;

        public void shutdown() {
            isStopped = true;
            interrupt(); // Прерываем поток, чтобы выйти из ожидания и завершить его работу
        }

        @Override
        public void run() {
            while (!isStopped) {
                Runnable task = null;
                synchronized (CustomThreadPool.this) {
                    while (taskQueue.isEmpty() && !isShutdown.get()) {
                        try {
                            CustomThreadPool.this.wait(); // Ожидание новой задачи
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    if (isShutdown.get()) {
                        return; // Выход из потока при завершении работы
                    }
                    task = taskQueue.poll();
                }
                try {
                    if (task != null) {
                        task.run(); // Выполнение задачи
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace(); // Обработка исключений
                }
            }
        }
    }
}
