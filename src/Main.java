import data.*;
import manager.InMemoryTaskManager;
import manager.Managers;

/**
 * Главный класс планера
 *
 * @author Max Vasilyev
 * @version 1.0
 */

public class Main {

    /**
     * Главный метод планера
     */
    public static void main(String[] args) {

//        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();
//        Task task1 = new Task();
//        task1.setTitle("Накачать шину");
//        task1.setDescription("Нужно накачать шину на велике");
//        task1.setNumOfStatus(1);
//        Task task2 = new Task();
//        task2.setTitle("Купить книгу");
//        task2.setDescription("Нужно купить книгу Хеммингуэя");
//        task2.setNumOfStatus(2);
//        inMemoryTaskManager.createTasks(task1);
//        inMemoryTaskManager.createTasks(task2);
//        System.out.println(inMemoryTaskManager.getStorageTask());
//        task2.setNumOfStatus(3);
//        inMemoryTaskManager.setUpdateTask(task2);
//        System.out.println(inMemoryTaskManager.getStorageTask());
//        inMemoryTaskManager.deletePerIndificationTask(1);
//        System.out.println(inMemoryTaskManager.getStorageTask());
//        SubTask subTask1 = new SubTask();
//        subTask1.setTitle("Собрать чемодан");
//        subTask1.setDescription("Нужно собрать чемодан");
//        subTask1.setNumOfStatus(2);
//        SubTask subTask2 = new SubTask();
//        subTask2.setTitle("Купить билеты");
//        subTask2.setDescription("Необходимо купить билеты");
//        subTask2.setNumOfStatus(3);
//        SubTask subTask3 = new SubTask();
//        subTask3.setTitle("Помыть пол");
//        subTask3.setDescription("Необходимо помыть пол");
//        subTask3.setNumOfStatus(1);
//        inMemoryTaskManager.createSubtacks(subTask1);
//        inMemoryTaskManager.createSubtacks(subTask3);
//        inMemoryTaskManager.createSubtacks(subTask2);
//        System.out.println(inMemoryTaskManager.getStorageSubtask());
//        Epic epic1 = new Epic();
//        epic1.setTitle("Путешествие");
//        epic1.setDescription("Нужно собраться в путешествие");
//        epic1.addNumOfSubtask(3);
//        epic1.addNumOfSubtask(5);
//        subTask1.setIdEpic(6);
//        subTask2.setIdEpic(6);
//        inMemoryTaskManager.setEpicStatus(epic1);
//        inMemoryTaskManager.createEpic(epic1);
//        Epic epic2 = new Epic();
//        epic2.setTitle("Уборка в доме");
//        epic2.setDescription("Необходимо убраться дома");
//        epic2.addNumOfSubtask(4);
//        subTask3.setIdEpic(7);
//        inMemoryTaskManager.setEpicStatus(epic2);
//        inMemoryTaskManager.createEpic(epic2);
//        System.out.println(inMemoryTaskManager.getStorageEpic());
//        System.out.println(inMemoryTaskManager.getAllSubtasks(epic1));
//        System.out.println(inMemoryTaskManager.getAllSubtasks(epic2));
//        subTask3.setNumOfStatus(3);
//        inMemoryTaskManager.setUpdateSubtask(subTask3);
//        inMemoryTaskManager.setEpicStatus(epic2);
//        System.out.println(inMemoryTaskManager.getStorageSubtask());
//        System.out.println(inMemoryTaskManager.getStorageEpic());
//
//        Epic epic3 = new Epic();
//        epic3.setTitle("Путешествие1");
//        epic3.setDescription("Нужно собраться в путешествие111");
//        inMemoryTaskManager.setEpicStatus(epic3);
//        inMemoryTaskManager.createEpic(epic3);
//        inMemoryTaskManager.deletePerIndificationSubtask(3);
//        System.out.println(inMemoryTaskManager.getStorageEpic());
//        System.out.println(inMemoryTaskManager.getStorageSubtask());
//        inMemoryTaskManager.deletePerIndificationEpic(7);
//        System.out.println(inMemoryTaskManager.getStorageEpic());
//        System.out.println(inMemoryTaskManager.getStorageSubtask());
//        inMemoryTaskManager.deleteAllTasks();
//        System.out.println(inMemoryTaskManager.getStorageTask());
//        System.out.println(inMemoryTaskManager.getByIndificatorEpic(6));
//        inMemoryTaskManager.deleteAllSubtasks();
//        System.out.println(inMemoryTaskManager.getStorageEpic());

        Task task1 = new Task();
        task1.setTitle("Накачать шину");
        task1.setDescription("Нужно накачать шину на велике");
        task1.setNumOfStatus(1);
        Task task2 = new Task();
        task2.setTitle("Купить книгу");
        task2.setDescription("Нужно купить книгу Хеммингуэя");
        task2.setNumOfStatus(2);
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
        Epic epic1 = new Epic();
        epic1.setTitle("Путешествие");
        epic1.setDescription("Нужно собраться в путешествие");
        epic1.addNumOfSubtask(3);
        epic1.addNumOfSubtask(5);
        subTask1.setIdEpic(6);
        subTask2.setIdEpic(6);
        Epic epic2 = new Epic();
        epic2.setTitle("Уборка в доме");
        epic2.setDescription("Необходимо убраться дома");
        epic2.addNumOfSubtask(4);
        subTask3.setIdEpic(7);

        Managers.getDefault().createTasks(task1);
        Managers.getDefault().createTasks(task2);
        System.out.println(Managers.getDefault().getStorageEpic());
        Managers.getDefault().createSubtacks(subTask1);
        Managers.getDefault().createSubtacks(subTask2);
        Managers.getDefault().createSubtacks(subTask3);
        Managers.getDefault().createEpic(epic1);
        Managers.getDefault().setEpicStatus(epic1);
        Managers.getDefault().createEpic(epic2);
        Managers.getDefault().setEpicStatus(epic2);
        Managers.getDefault().getTask(1);
        Managers.getDefault().getSubtask(3);
        Managers.getDefault().getEpic(6);
        System.out.println(Managers.getDefault().getHistory());
    }
}
