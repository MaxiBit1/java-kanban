package manager;

import data.Epic;
import data.SubTask;
import data.Task;

import java.util.List;
import java.util.Set;

/**
 * Интерфейс для классов, реализующих логику менеджера
 *
 * @author Max Vasilyev
 * @version 1.0
 */
public interface TaskManager {
    /**
     * Метод получения списка задач
     *
     * @return - список задач
     */
    List<Task> getStorageTask();

    /**
     * Метод получения списка эпик-задач
     *
     * @return - список эпик-задач
     */
    List<Epic> getStorageEpic();

    /**
     * Метод получение списка подзадач
     *
     * @return - список подзадач
     */
    List<SubTask> getStorageSubtask();

    /**
     * Метод для создание задачи и сохранение его хэш-таблице
     *
     * @param task - задача
     */
    void createTasks(Task task);

    /**
     * Метод для создание задачи и сохранение его хэш-таблице
     *
     * @param epic - задача-эпик
     */
    void createEpic(Epic epic);

    /**
     * Метод для создание задачи и сохранение его хэш-таблице
     *
     * @param subTask - подзадача
     */
    void createSubtacks(SubTask subTask);

    /**
     * Метод выводящий данные о задачах-эпиках и об их подзадачах
     *
     * @param epic - задача-эпик
     * @return возвращает список этой задачи-эпик
     */
    List<SubTask> getAllSubtasks(Epic epic);

    /**
     * Метод удаления всех задач из хэш-таблицы и хэш-таблицы истории вызова
     */
    void deleteAllTasks();

    /**
     * Метод удаления всех задач-эпиков из хэш-таблицы и хэш-таблицы истории вызова
     */
    void deleteAllEpics();

    /**
     * Метод удаления всех подзадач из хэш-таблицы и хэш-таблицы истории вызова
     */
    void deleteAllSubtasks();

    /**
     * Метод удаление задачи по индификатору
     *
     * @param indification - индификатор
     */
    void deleteTaskById(int indification);

    /**
     * Метод удаление задачи-эпик по индификатору
     *
     * @param indification - индификатор
     */
    void deleteEpicById(int indification);

    /**
     * Метод удаление подзадачи по индификатору
     *
     * @param indification - индификатор
     */
    void deleteSubtaskById(int indification);

    /**
     * Метод установление статуса для задачей-эпиков
     *
     * @param epic - задача типа эпик
     */
    void setEpicStatus(Epic epic);

    /**
     * Метод для обновления данных в хэш-таблицах задач
     *
     * @param task - задача
     */
    void setUpdateTask(Task task);

    /**
     * Метод для обновления данных в хэш-таблицах задач-эпиков
     *
     * @param epic - задача-эпик
     */
    void setUpdateEpic(Epic epic);

    /**
     * Метод для обновления данных в хэш-таблицах подзадач
     *
     * @param subTask - подзадача
     */
    void setUpdateSubtask(SubTask subTask);

    /**
     * Метод получения задачи из списка
     *
     * @param id - ай задачи
     * @return - задача
     */
    Task getTask(int id);

    /**
     * Метод получения задачи-эпика из списка
     *
     * @param id - ай задачи-эпика
     * @return - задача-эпик
     */
    Epic getEpic(int id);

    /**
     * Метод получения подзадачи из списка
     *
     * @param id - ай подзадачи
     * @return - подзадача
     */
    SubTask getSubtask(int id);

    /**
     * Метод получения списка истории получения разных задач
     *
     * @return - список истории
     */
    List<Task> getHistory();

    /**
     * Метод получения упорядочного множества задач по начальному времени
     *
     * @return - упорядочного множества
     */
    Set<Task> getPrioritizedTask();

    /**
     * Метод получения упорядочного множества подзадач по начальному времени
     *
     * @return - упорядочного множества
     */
    List<SubTask> getSortPerStatrtTimeSubtask();

    /**
     * Метод для получения множества подзадач в эпик-задаче
     *
     * @param id - индетификатор эпик-задачи
     * @return - множество подзадач
     */
    List<SubTask> getEpicSubtask(int id);

    public int getIndificator();

}
