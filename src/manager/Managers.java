package manager;

/**
 * Унитарный класс
 * @author Max Vasilyev
 * @version 1.0
 */
public class Managers  {
    private static TaskManager taskManager = new InMemoryTaskManager();
    private static InMemoryHistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();

    public static TaskManager getDefault() {
        return taskManager;
    }

    public static InMemoryHistoryManager HistoryManagergetDefaultHistory() {
        return inMemoryHistoryManager;
    }
}
