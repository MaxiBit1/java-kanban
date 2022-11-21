import data.Epic;
import data.StatusTasks;
import data.SubTask;
import data.Task;
import manager.Managers;
import manager.TaskManager;

/**
 * Главный класс планера
 * @author Max Vasilyev
 * @version 1.0
 */

public class Main {

    /** Главный метод планера */
    public static void main(String[] args) {

        TaskManager taskManager = Managers.getDefault();

        Task task1 = new Task("Накачать шину", "Нужно накачать шину на велике");
        task1.setStatus(StatusTasks.NEW);
        Task task2 = new Task("Купить книгу", "Нужно купить книгу Хеммингуэя");
        task2.setStatus(StatusTasks.IN_PROGRESS);
        SubTask subTask = new SubTask("Собрать чемодан", "Нужно собрать чемодан");
        subTask.setStatus(StatusTasks.IN_PROGRESS);
        SubTask subTask2 = new SubTask("Купить билеты", "Необходимо купить билеты");
        subTask2.setStatus(StatusTasks.DONE);
        SubTask subTask3 = new SubTask("Помыть пол", "Необходимо помыть пол");
        subTask3.setStatus(StatusTasks.NEW);
        Epic epic1 = new Epic("Путешествие", "Нужно собраться в путешествие");
        subTask.setEpicId(6);
        subTask2.setEpicId(6);
        subTask3.setEpicId(6);
        Epic epic2 = new Epic("Уборка в доме", "Необходимо убраться в доме");


        taskManager.createTasks(task1);
        taskManager.createTasks(task2);
        taskManager.createSubtacks(subTask);
        taskManager.createSubtacks(subTask2);
        taskManager.createSubtacks(subTask3);
        taskManager.createEpic(epic1);
        taskManager.setEpicStatus(epic1);
        taskManager.createEpic(epic2);
        taskManager.setEpicStatus(epic2);
        System.out.println(epic1);
        System.out.println(epic2);
//        taskManager.getTask(1);
//        taskManager.getTask(2);
//        taskManager.getSubtask(5);
//        taskManager.getSubtask(3);
//        taskManager.getSubtask(4);
//        System.out.println(taskManager.getHistory());
//        taskManager.getSubtask(5);
//        System.out.println(taskManager.getHistory());
//        taskManager.getEpic(6);
//        taskManager.getEpic(7);
//        System.out.println(taskManager.getHistory());
//        taskManager.getEpic(6);
//        taskManager.deleteTaskById(2);
//        taskManager.deleteSubtaskById(3);
//        System.out.println(taskManager.getHistory());
    }
}
