package manager;

import data.Task;

import java.util.List;

/**
 * Интерфейс для классов, реализующих историю просмотра и удаления из истории просмотра задач
 * @author Max Vasilyev
 * @version 1.0
 */
public interface HistoryManager {
    /**
     * Метод добавления в историю список задач задачу
     * @param task - задача
     */
    void add(Task task);
    /**
     * Метод получения списка истории получения разных задач
     * @return - список истории
     */
    List<Task> getHistory();
    /**
     * Метод удаления любой задачи по айди из списка задач и из CustomLinkedList`а
     * @param id - айди задачи
     */
    void remove(int id);
}
