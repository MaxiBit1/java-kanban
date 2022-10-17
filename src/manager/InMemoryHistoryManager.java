package manager;

import data.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс реализующий логику истории просмотра задач
 * @author Max Vasilyev
 * @version 1.0
 */
public class InMemoryHistoryManager implements HistoryManager{

    private List<Task> historyList = new ArrayList<>();
    private final int LIMIT_SIZE_ARRAY = 10;

    /**
     * Метод добавления в историю список задач задачу
     * @param task - задача
     */
    @Override
    public void add(Task task) {
        historyList.add(task);
    }

    @Override
    public List<Task> getHistory() {
        if(historyList.size() <= LIMIT_SIZE_ARRAY){
            return historyList;
        }
        return historyList.subList(historyList.size() - 10, historyList.size());
    }
}
