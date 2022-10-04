import java.util.HashMap;

/**
 * Главный класс планера
 * @author Max Vasilyev
 * @version 1.0
 */
public class Main {

    /** Главный метод планера */
    public static void main(String[] args) {
        HashMap<Integer, Task> storageTask = new HashMap<>();
        HashMap<Integer, Epic> storageEpic = new HashMap<>();
        HashMap<Integer, SubTask> storageSubtask = new HashMap<>();

        Manager manager = new Manager();
        Task task1 = new Task();
        task1.setTitle("Накачать шину");
        task1.setDescription("Нужно накачать шину на велике");
        task1.setNumOfStatus(1);
        Task task2 = new Task();
        task2.setTitle("Купить книгу");
        task2.setDescription("Нужно купить книгу Хеммингуэя");
        task2.setNumOfStatus(2);
        manager.createTasks(task1, storageTask);
        manager.createTasks(task2, storageTask);
        manager.showAllTasks(storageTask);
        task2.setNumOfStatus(3);
        manager.setUpdateTask(task2, storageTask);
        manager.showAllTasks(storageTask);
        manager.deletePerIndification(storageTask, 1);
        manager.showAllTasks(storageTask);
        SubTask subTask1 = new SubTask();
        subTask1.setTitle("Собрать чемодан");
        subTask1.setDescription("Нужно собрать чемодан");
        subTask1.setNumOfStatus(2);
        SubTask subTask2 = new SubTask();
        subTask2.setTitle("Купить билеты");
        subTask2.setDescription("Необходимо купить билеты");
        subTask2.setNumOfStatus(3);
        SubTask subTask3 = new SubTask();
        subTask3.setTitle("Помыть пол");
        subTask3.setDescription("Необходимо помыть пол");
        subTask3.setNumOfStatus(1);
        manager.createTasks(subTask1, storageSubtask);
        manager.createTasks(subTask3, storageSubtask);
        manager.createTasks(subTask2, storageSubtask);
        Epic epic1 = new Epic();
        epic1.setTitle("Путешествие");
        epic1.setDescription("Нужно собраться в путешествие");
        epic1.addNumOfSubtask(1);
        epic1.addNumOfSubtask(3);
        manager.setEpicStatus(epic1, storageSubtask);
        manager.createTasks(epic1, storageEpic);
        Epic epic2 = new Epic();
        epic2.setTitle("Уборка в доме");
        epic2.setDescription("Необходимо убраться дома");
        epic2.addNumOfSubtask(2);
        manager.setEpicStatus(epic2, storageSubtask);
        manager.createTasks(epic2, storageEpic);
        manager.showAllTasks(storageSubtask);
        manager.showAllSubtasks(storageEpic,storageSubtask);
        subTask3.setNumOfStatus(3);
        manager.setUpdateTask(subTask3, storageSubtask);
        manager.setEpicStatus(epic2,storageSubtask);
        manager.showAllTasks(storageEpic);

        Epic epic3 = new Epic();
        epic3.setTitle("Путешествие1");
        epic3.setDescription("Нужно собраться в путешествие111");
        manager.setEpicStatus(epic3, storageSubtask);
        manager.createTasks(epic3, storageEpic);
        manager.showAllTasks(storageEpic);
        manager.deleteAllTasks(storageTask);
        manager.showAllTasks(storageTask);

    }
}
