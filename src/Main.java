import data.Epic;
import data.StatusTasks;
import data.SubTask;
import data.Task;
import manager.Managers;

/**
 * Главный класс планера
 * @author Max Vasilyev
 * @version 1.0
 */

public class Main {

    /** Главный метод планера */
    public static void main(String[] args) {

        Task task1 = new Task("Накачать шину", "Нужно накачать шину на велике");
        task1.setStatus(StatusTasks.NEW);
        Task task2 = new Task("Купить книгу", "Нужно купить книгу Хеммингуэя");
        task2.setStatus(StatusTasks.IN_PROGRESS);
        SubTask subTask1 = new SubTask("Собрать чемодан", "Нужно собрать чемодан");
        subTask1.setStatus(StatusTasks.IN_PROGRESS);
        SubTask subTask2 = new SubTask("Купить билеты", "Необходимо купить билеты");
        subTask2.setStatus(StatusTasks.DONE);
        SubTask subTask3 = new SubTask("Помыть пол", "Необходимо помыть пол");
        subTask3.setStatus(StatusTasks.NEW);
        Epic epic1 = new Epic("Путешествие", "Нужно собраться в путешествие");
        epic1.addNumOfSubtask(3);
        epic1.addNumOfSubtask(5);
        subTask1.setEpicId(6);
        subTask2.setEpicId(6);
        Epic epic2 = new Epic("Уборка в доме", "Необходимо убраться в доме");
        epic2.addNumOfSubtask(4);
        subTask3.setEpicId(7);


        Managers.getDefault().createTasks(task1);
        Managers.getDefault().createTasks(task2);
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
        Managers.getDefault().getSubtask(4);
        System.out.println(Managers.getDefault().getHistory());
        Managers.getDefault().getTask(1);
        Managers.getDefault().getSubtask(3);
        Managers.getDefault().getEpic(6);
        Managers.getDefault().getTask(1);
        Managers.getDefault().getSubtask(3);
        Managers.getDefault().getEpic(6);
        Managers.getDefault().getTask(1);
        Managers.getDefault().getSubtask(3);
        Managers.getDefault().getEpic(6);
        System.out.println(Managers.getDefault().getHistory());
    }
}
