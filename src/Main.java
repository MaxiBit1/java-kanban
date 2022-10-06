import data.*;
import bisnesLogic.Manager;

/**
 * Главный класс планера
 * @author Max Vasilyev
 * @version 1.0
 */

public class Main {

    /** Главный метод планера */
    public static void main(String[] args) {

        Manager manager = new Manager();
        Task task1 = new Task();
        task1.setTitle("Накачать шину");
        task1.setDescription("Нужно накачать шину на велике");
        task1.setNumOfStatus(1);
        Task task2 = new Task();
        task2.setTitle("Купить книгу");
        task2.setDescription("Нужно купить книгу Хеммингуэя");
        task2.setNumOfStatus(2);
        manager.createTasks(task1, manager.getStorageTask());
        manager.createTasks(task2, manager.getStorageTask());
        System.out.println(manager.getALLTasks());
        task2.setNumOfStatus(3);
        manager.setUpdateTask(task2, manager.getStorageTask());
        System.out.println(manager.getALLTasks());
        manager.deletePerIndification(1);
        System.out.println(manager.getALLTasks());
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
        manager.createTasks(subTask1, manager.getStorageSubtask());
        manager.createTasks(subTask3, manager.getStorageSubtask());
        manager.createTasks(subTask2, manager.getStorageSubtask());
        System.out.println(manager.getALLTasks());
        Epic epic1 = new Epic();
        epic1.setTitle("Путешествие");
        epic1.setDescription("Нужно собраться в путешествие");
        epic1.addNumOfSubtask(3);
        epic1.addNumOfSubtask(5);
        manager.setEpicStatus(epic1);
        manager.createTasks(epic1, manager.getStorageEpic());
        Epic epic2 = new Epic();
        epic2.setTitle("Уборка в доме");
        epic2.setDescription("Необходимо убраться дома");
        epic2.addNumOfSubtask(4);
        manager.setEpicStatus(epic2);
        manager.createTasks(epic2, manager.getStorageEpic());
        System.out.println(manager.getALLTasks());
        System.out.println(manager.getAllSubtasks(epic1));
        subTask3.setNumOfStatus(3);
        manager.setUpdateTask(subTask3, manager.getStorageSubtask());
        manager.setEpicStatus(epic2);
        System.out.println(manager.getALLTasks());

        Epic epic3 = new Epic();
        epic3.setTitle("Путешествие1");
        epic3.setDescription("Нужно собраться в путешествие111");
        manager.setEpicStatus(epic3);
        manager.createTasks(epic3, manager.getStorageEpic());
        System.out.println(manager.getALLTasks());
        manager.deleteAllTasks();
        System.out.println(manager.getALLTasks());

    }
}
