package ru.mashinis;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MyStreamApi {
    private List<Task> testData;

    public MyStreamApi() {
        this.testData = Arrays.asList
                (
                        new Task("Bug fixing", StatusTask.OPEN),
                        new Task("Feature development", StatusTask.IN_OPERATION),
                        new Task("Code review", StatusTask.CLOSED),
                        new Task("Database optimization", StatusTask.OPEN),
                        new Task("UI redesign", StatusTask.IN_OPERATION),
                        new Task("Performance testing", StatusTask.CLOSED),
                        new Task("Documentation", StatusTask.OPEN),
                        new Task("API integration", StatusTask.IN_OPERATION),
                        new Task("Security audit", StatusTask.CLOSED),
                        new Task("Deployment", StatusTask.OPEN)
                );
    }

    /** TODO 1. Получение списка задач по выбранному статусу; */
    public List<Task> ListTaskStatus(Enum<StatusTask> status) {
        return testData.stream()
                .filter(statusTask -> statusTask.getStatusTask().equals(status))
                .collect(Collectors.toList());
    }

    /** TODO 2. Проверка наличия задачи с указанным ID; */
    public boolean isTaskId(int id) {
        return testData.stream()
                .anyMatch(idTask -> idTask.getIdTask() == id);
    }

    /** TODO 3. Получение списка задач в отсортированном по статусу виде */
    public List<Task> sortByStatus() {
        return testData.stream()
                .sorted(Comparator.comparing(task -> task.getStatusTask().ordinal()))
                .collect(Collectors.toList());
    }

    /** TODO 4. Подсчет количества задач по определенному статусу. */
    public Long countTaskByStatus(Enum<StatusTask> status) {
        return testData.stream()
                .filter(statusTask -> statusTask.getStatusTask().equals(status))
                .count();
    }
}
