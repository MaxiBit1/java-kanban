package manager;

import data.Epic;
import data.SubTask;
import data.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {
    ArrayList<Task> getStorageTask();
    ArrayList<Task> getStorageEpic();
    ArrayList<Task> getStorageSubtask();
    void createTasks(Task task);
    void createEpic(Epic epic);
    void createSubtacks(SubTask subTask);
    ArrayList<SubTask> getAllSubtasks(Epic epic);
    void deleteAllTasks();
    void deleteAllEpics();
    void deleteAllSubtasks();
    void deletePerIndificationTask(int indification);
    void deletePerIndificationEpic(int indification);
    void deletePerIndificationSubtask(int indification);
    void setEpicStatus(Epic epic);
    void setUpdateTask(Task task);
    void setUpdateEpic(Epic epic);
    void setUpdateSubtask(SubTask subTask);
    Task getTask(int id);
    Task getEpic(int id);
    Task getSubtask(int id);
    List<Task> getHistory();

}
