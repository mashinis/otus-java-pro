package ru.mashinis;

public class Task {
    private static int nextId = 1;
    private final int idTask;
    private String nameTask;
    private Enum<StatusTask> statusTask;

    public Task(String nameTask, Enum<StatusTask> statusTask) {
        this.idTask = nextId++;
        this.nameTask = nameTask;
        this.statusTask = statusTask;
    }

    public Task(int idTask, String nameTask, Enum<StatusTask> statusTask) {
        this.idTask = idTask;
        this.nameTask = nameTask;
        this.statusTask = statusTask;
    }

    public int getIdTask() {
        return idTask;
    }

    public String getNameTask() {
        return nameTask;
    }

    public Enum<StatusTask> getStatusTask() {
        return statusTask;
    }

    @Override
    public String toString() {
        return "\nidTask=" + idTask +
                ", nameTask='" + nameTask +
                ", statusTask=" + statusTask;
    }
}
