package manager;

/**
 * Унитарный класс
 * @author Max Vasilyev
 * @version 1.0
 */
public class Managers  {

    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    public static InMemoryHistoryManager HistoryManagergetDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
