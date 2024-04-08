package ru.mashinis;

// Можно попробовать написать через тесты вместо вывода в консоль
public class Main {

    public static void main(String[] args) {
        MyStreamApi myStreamApi = new MyStreamApi();

        // 1
        System.out.println(myStreamApi.ListTaskStatus(StatusTask.OPEN));
        System.out.println(myStreamApi.ListTaskStatus(StatusTask.IN_OPERATION));
        System.out.println(myStreamApi.ListTaskStatus(StatusTask.CLOSED));
        System.out.println();

        // 2
        System.out.println(myStreamApi.findTaskId(0));
        System.out.println(myStreamApi.findTaskId(1));
        System.out.println(myStreamApi.findTaskId(11));
        System.out.println(myStreamApi.findTaskId(10));
        System.out.println();

        // 3
        System.out.println(myStreamApi.sortByStatus());
        System.out.println();

        // 4
        System.out.println(myStreamApi.countTaskByStatus(StatusTask.OPEN) + " - Задачи со статусом OPEN.");
        System.out.println(myStreamApi.countTaskByStatus(StatusTask.IN_OPERATION) + " - Задачи со статусом IN_OPERATION.");
        System.out.println(myStreamApi.countTaskByStatus(StatusTask.CLOSED) + " - Задачи со статусом CLOSED.");
    }

}