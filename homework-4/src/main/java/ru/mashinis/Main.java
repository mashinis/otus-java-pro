package ru.mashinis;

public class Main {
    public static void main(String[] args) {
        CustomThreadPool pool = new CustomThreadPool(3);

        pool.execute(() -> System.out.println(Thread.currentThread().getName() + " - Задача 1"));
        pool.execute(() -> System.out.println(Thread.currentThread().getName() + " - Задача 2"));
        pool.execute(() -> System.out.println(Thread.currentThread().getName() + " - Задача 3"));

        pool.shutdown();
    }
}
