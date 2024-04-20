package ru.mashinis;

public class Main {
    public static void main(String[] args) {
        CustomThreadPool pool = new CustomThreadPool(5);

        for (int i = 1; i <= 100; i++) {
            int taskNumber = i;
            pool.execute(() -> System.out.println(Thread.currentThread().getName() + " - Выполняется задача " + taskNumber));
        }

        pool.shutdown();
    }
}