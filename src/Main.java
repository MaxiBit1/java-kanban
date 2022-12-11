import data.Epic;
import data.StatusTasks;
import data.SubTask;
import data.Task;
import manager.Managers;
import manager.TaskManager;

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

        TaskManager taskManager = Managers.getDefault();

        Task task1 = new Task("T1", "Dec1");
        task1.setStatus(StatusTasks.IN_PROGRESS);
        task1.setStartTime("11.12.2022; 09:00");
        task1.setDuration(15);
        Task task2 = new Task("T2", "Dec2");
        task2.setStatus(StatusTasks.NEW);
        taskManager.createTasks(task1);
        taskManager.createTasks(task2);
        SubTask subTask1 = new SubTask("S1", "DecS1");
        subTask1.setStatus(StatusTasks.NEW);
        subTask1.setStartTime("11.12.2022; 09:40");
        subTask1.setDuration(10);
        taskManager.createSubtacks(subTask1);
        SubTask subTask2 = new SubTask("S2", "DecS1");
        subTask2.setStatus(StatusTasks.NEW);
        subTask2.setStartTime("11.12.2022; 09:50");
        subTask2.setDuration(40);
        taskManager.createSubtacks(subTask2);
        Epic epic1 = new Epic("E1", "DescE1");
        taskManager.createEpic(epic1);
        subTask1.setEpicId(5);
        subTask2.setEpicId(5);
        taskManager.setEpicStatus(epic1);
        epic1.setStartTimeEpic(taskManager);
        System.out.println(epic1.getEndTime(taskManager));
        System.out.println(taskManager.getPrioritizedTask());
    }
}
