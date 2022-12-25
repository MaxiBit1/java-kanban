package manager;

import java.io.IOException;

/**
 * Унитарный класс
 * @author Max Vasilyev
 * @version 1.0
 */
public class Managers  {

    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    public static InMemoryHistoryManager historyManagergetDefaultHistory() {
        return new InMemoryHistoryManager();
    }

    public static TaskManager fileBackedTaskManagersDefault() {
        return new FileBackedTaskManager();
    }

    public static TaskManager httpTaskManagerDefault() throws IOException {
        return new HttpTaskManager();
    }
}
