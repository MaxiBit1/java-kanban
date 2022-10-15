package manager;

import data.Task;

import java.util.List;

/**
 * Интерфейс для классов, реализующих историю просмотра задач
 * @author Max Vasilyev
 * @version 1.0
 */
public interface HistoryManager {
    void add(Task task);
    List<Task> getHistory();
}
