package ru.mashinis;

public class Main {

    public static void main(String[] args) {
        MyStreamApi myStreamApi = new MyStreamApi();

        // 1
        System.out.println(myStreamApi.ListTaskStatus(StatusTask.OPEN));
        System.out.println(myStreamApi.ListTaskStatus(StatusTask.IN_OPERATION));
        System.out.println(myStreamApi.ListTaskStatus(StatusTask.CLOSED));
        System.out.println();

        // 2
        findTaskId(myStreamApi, 0);
        findTaskId(myStreamApi, 1);
        findTaskId(myStreamApi, 11);
        findTaskId(myStreamApi, 10);
        System.out.println();

        // 3
        System.out.println(myStreamApi.sortByStatus());
        System.out.println();

        // 4
        System.out.println(myStreamApi.countTaskByStatus(StatusTask.OPEN) + " - Задачи со статусом OPEN.");
        System.out.println(myStreamApi.countTaskByStatus(StatusTask.IN_OPERATION) + " - Задачи со статусом IN_OPERATION.");
        System.out.println(myStreamApi.countTaskByStatus(StatusTask.CLOSED) + " - Задачи со статусом CLOSED.");
    }

    public static void findTaskId(MyStreamApi myStreamApi, int id) {
        if (myStreamApi.isTaskId(id)) {
            System.out.println("Задача с ID = " + id + " существует.");
        } else {
            System.out.println("Задача с ID = " + id + " не существует!");
        }
    }
}