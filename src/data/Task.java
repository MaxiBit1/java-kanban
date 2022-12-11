package data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Класс простой задачи
 *
 * @author Max Vasilyev
 * @version 1.0
 */

public class Task {
    private String title;
    private String description;
    private StatusTasks status;
    private int id;
    private TypeOfTasks typeOfTask;
    private long duration;
    private LocalDateTime startTime;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy; HH:mm");


    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        typeOfTask = TypeOfTasks.TASK;
        startTime = LocalDateTime.MAX;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setStatus(StatusTasks status) {
        this.status = status;
    }

    public StatusTasks getStatus() {
        return status;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setStartTime(String startTime) {
        this.startTime = LocalDateTime.parse(startTime, formatter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(title, task.title)
                && Objects.equals(description, task.description)
                && status == task.status;
    }

    public TypeOfTasks getTypeOfTask() {
        return typeOfTask;
    }

    public void setTypeOfTask(TypeOfTasks typeOfTask) {
        this.typeOfTask = typeOfTask;
    }

    public LocalDateTime getEndTime() {
        return startTime.plusMinutes(duration);
    }

    public long getDuration() {
        return duration;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, status, id);
    }

    @Override
    public String toString() {
        return String.valueOf(id) + "," + typeOfTask + "," + title + "," + status + "," + description + "," + " " + ","
                + startTime.format(formatter) + "," + duration;
    }

}

