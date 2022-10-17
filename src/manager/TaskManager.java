package manager;

import data.Epic;
import data.SubTask;
import data.Task;

import java.util.List;

/**
 * Интерфейс для классов, реализующих логику менеджера
 * @author Max Vasilyev
 * @version 1.0
 */
public interface TaskManager {
    List<Task> getStorageTask();
    List<Task> getStorageEpic();
    List<Task> getStorageSubtask();
    void createTasks(Task task);
    void createEpic(Epic epic);
    void createSubtacks(SubTask subTask);
    List<SubTask> getAllSubtasks(Epic epic);
    void deleteAllTasks();
    void deleteAllEpics();
    void deleteAllSubtasks();
    void deleteTaskById(int indification);
    void deleteEpicById(int indification);
    void deleteSubtaskById(int indification);
    void setEpicStatus(Epic epic);
    void setUpdateTask(Task task);
    void setUpdateEpic(Epic epic);
    void setUpdateSubtask(SubTask subTask);
    Task getTask(int id);
    Task getEpic(int id);
    Task getSubtask(int id);
    List<Task> getHistory();
}
