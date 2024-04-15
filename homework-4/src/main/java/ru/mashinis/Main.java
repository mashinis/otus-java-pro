package ru.mashinis;

public class Main {
    public static void main(String[] args) {
// Создание пула потоков с 5 рабочими потоками
        CustomThreadPool pool = new CustomThreadPool(2);

        pool.execute(() -> System.out.println(Thread.currentThread().getName() + " - Задача 1"));
        pool.execute(() -> System.out.println(Thread.currentThread().getName() + " - Задача 2"));
        pool.execute(() -> System.out.println(Thread.currentThread().getName() + " - Задача 3"));

        pool.shutdown();

    }
}
