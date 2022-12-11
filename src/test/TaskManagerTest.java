package test;

import data.Epic;
import data.StatusTasks;
import data.SubTask;
import data.Task;
import manager.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Абстрактный класс для тестов
 *
 * @param <T> - Дженерик для классов, наследующиеся(имплементирующиеся) от интерфейса TaskManager
 * @author Max
 * @version 1.0
 */
abstract class TaskManagerTest<T extends TaskManager> {

    Task task;
    Epic epic1;
    SubTask subTask1;
    SubTask subTask2;
    Task task2;
    T taskManager;


    @BeforeEach
    void setTasks() {
        task = new Task("T1", "descriptionT1");
        task.setStatus(StatusTasks.IN_PROGRESS);
        task2 = new Task("T2", "descriptionT2");
        task2.setStatus(StatusTasks.DONE);
        epic1 = new Epic("E1", "descriptionE1");
        subTask1 = new SubTask("S1", "description1");
        subTask1.setStatus(StatusTasks.NEW);
        subTask2 = new SubTask("S2", "descriptionS2");
        subTask2.setStatus(StatusTasks.IN_PROGRESS);
    }

    /**
     * Тест на создание простой задачи
     */
    @Test
    public void shouldCreateTask() {
        taskManager.createTasks(task);
        final int idTask = task.getId();
        Task taskOff = taskManager.getTask(idTask);
        assertNotNull(taskOff, "Ошибка в записи задачи");
        assertEquals(task, taskOff, "Задачи не совпадают");

        final List<Task> exampleList = taskManager.getStorageTask();
        assertNotNull(exampleList, "Нет записи в список задач");
        assertEquals(1, exampleList.size(), "Переполнен список");
        assertEquals(task, exampleList.get(0), "Задачи не совпадают");
    }

    /**
     * Тест на создание простой задачи-эпика
     */
    @Test
    public void shouldCreateEpic() {
        taskManager.createEpic(epic1);
        final int idTask = epic1.getId();
        Epic taskOff = taskManager.getEpic(idTask);
        assertNotNull(taskOff, "Ошибка в записи задачи");
        assertEquals(epic1, taskOff, "Задачи не совпадают");

        final List<Task> exampleList = taskManager.getStorageEpic();
        assertNotNull(exampleList, "Нет записи в список задач");
        assertEquals(1, exampleList.size(), "Переполнен список");
        assertEquals(epic1, exampleList.get(0), "Задачи не совпадают");
    }

    /**
     * Тест на создание простой подзадачи
     */
    @Test
    public void shouldCreateSubtask() {
        taskManager.createSubtacks(subTask1);
        final int idTask = subTask1.getId();
        SubTask taskOff = taskManager.getSubtask(idTask);
        assertNotNull(taskOff, "Ошибка в записи задачи");
        assertEquals(subTask1, taskOff, "Задачи не совпадают");

        final List<Task> exampleList = taskManager.getStorageSubtask();
        assertNotNull(exampleList, "Нет записи в список задач");
        assertEquals(1, exampleList.size(), "Переполнен список");
        assertEquals(subTask1, exampleList.get(0), "Задачи не совпадают");
    }

    /**
     * Тест на удаление всех задач
     */
    @Test
    public void shouldDellAllTasks() {
        taskManager.createTasks(task);
        taskManager.createTasks(task2);
        taskManager.createEpic(epic1);
        taskManager.createSubtacks(subTask1);
        taskManager.createSubtacks(subTask2);
        taskManager.getTask(task.getId());
        taskManager.getTask(task2.getId());
        taskManager.getSubtask(subTask1.getId());
        taskManager.getSubtask(subTask2.getId());
        taskManager.getEpic(epic1.getId());
        taskManager.deleteAllTasks();
        taskManager.deleteAllEpics();
        assertTrue(taskManager.getStorageTask().isEmpty(), "Ошибка в удалении всех задач");
        assertTrue(taskManager.getStorageEpic().isEmpty(), "Ошибка в удалении всех задач");
    }

    /**
     * Тест удаления всех задач бедз создания
     */
    @Test
    public void shouldDellAllTaskWithoutCreate() {
        taskManager.deleteAllTasks();
        taskManager.deleteAllEpics();
        taskManager.deleteAllSubtasks();
        assertTrue(taskManager.getStorageTask().isEmpty(), "Ошибка в удалении всех задач");
        assertTrue(taskManager.getStorageEpic().isEmpty(), "Ошибка в удалении всех задач");
        assertTrue(taskManager.getStorageSubtask().isEmpty(), "Ошибка в удалении всех задач");
    }

    /**
     * Тест создание пустой задачи
     */
    @Test
    public void shouldCreateTaskWithNull() {
        NullPointerException exTask = assertThrows(
                NullPointerException.class,
                () -> taskManager.createTasks(null)
        );
        assertNull(exTask.getMessage());
    }

    /**
     * Тест создание пустой задачи-эпик
     */
    @Test
    public void shouldCreateEpicWithNull() {
        NullPointerException exTask = assertThrows(
                NullPointerException.class,
                () -> taskManager.createEpic(null)
        );
        assertNull(exTask.getMessage());
    }

    /**
     * Тест создание пустой подзадачи
     */
    @Test
    public void shouldCreateSubtaskWithNull() {
        NullPointerException exTask = assertThrows(
                NullPointerException.class,
                () -> taskManager.createSubtacks(null)
        );
        assertNull(exTask.getMessage());
    }

    /**
     * Тест удаления задачи по индификатору
     */
    @Test
    public void shouldDeleteTaskPerIndificator() {
        taskManager.createTasks(task);
        taskManager.createTasks(task2);
        taskManager.getTask(task.getId());
        taskManager.getTask(task2.getId());
        taskManager.deleteTaskById(task.getId());
        assertEquals(1, taskManager.getStorageTask().size(), "Ошибка в удаление задачи по индификатору");
        assertNotNull(taskManager.getStorageTask(), "Ошибка в удаление задачи по индификатору");
        assertEquals(task2, taskManager.getStorageTask().get(0), "Ошибка в удаление задачи по индификатору");
    }

    /**
     * Тест удаления задачи-эпик по индификатору
     */
    @Test
    public void shouldDeleteEpicPerIndificator() {
        taskManager.createEpic(epic1);
        taskManager.createSubtacks(subTask1);
        taskManager.createSubtacks(subTask2);
        subTask1.setEpicId(epic1.getId());
        subTask2.setEpicId(epic1.getId());
        taskManager.setEpicStatus(epic1);
        taskManager.getEpic(epic1.getId());
        taskManager.getSubtask(subTask1.getId());
        taskManager.deleteEpicById(epic1.getId());
        assertTrue(taskManager.getStorageEpic().isEmpty(), "Ошибка в удаление эпик-задачи по индификатору");
    }

    /**
     * Тест удаления подзадачи по индификатору
     */
    @Test
    public void shouldDeleteSubtaskPerIndificator() {
        taskManager.createSubtacks(subTask1);
        taskManager.createSubtacks(subTask2);
        taskManager.createEpic(epic1);
        subTask1.setEpicId(epic1.getId());
        subTask2.setEpicId(epic1.getId());
        taskManager.setEpicStatus(epic1);
        taskManager.getSubtask(subTask1.getId());
        taskManager.getSubtask(subTask2.getId());
        taskManager.deleteSubtaskById(subTask2.getId());
        assertEquals(1, taskManager.getStorageSubtask().size(), "Ошибка в удаление подзадачи по индификатору");
        assertNotNull(taskManager.getStorageSubtask(), "Ошибка в удаление подзадачи по индификатору");
        assertEquals(subTask1, taskManager.getStorageSubtask().get(0), "Ошибка в удаление подзадачи по индификатору");
    }

    /**
     * Тест удаления задачи по индификатору без создания
     */
    @Test
    public void shouldDeleteTaskPerIndificatorWithNull() {
        taskManager.deleteTaskById(task.getId());
        assertTrue(taskManager.getStorageTask().isEmpty());
    }

    /**
     * Тест удаления задачи-эпик по индификатору без создания
     */
    @Test
    public void shouldDeleteEpicPerIndificatorWithNull() {
        taskManager.deleteEpicById(epic1.getId());
        assertTrue(taskManager.getStorageEpic().isEmpty());
    }

    /**
     * Тест удаления подзадачи по индификатору без создания
     */
    @Test
    public void shouldDeleteSubtaskTaskPerIndificatorWithNull() {
        taskManager.deleteSubtaskById(subTask1.getId());
        assertTrue(taskManager.getStorageSubtask().isEmpty());
    }

    /**
     * Тест на обновление задачи
     */
    @Test
    public void shouldUpdateTask() {
        task.setStatus(StatusTasks.DONE);
        taskManager.setUpdateTask(task);
        assertEquals(StatusTasks.DONE, task.getStatus(), "Ошибка в обновлении задачи");
    }

    /**
     * Тест на получения статуса задачи-эпик
     */
    @Test
    public void shouldSetNewEpicStatus() {
        taskManager.createEpic(epic1);
        taskManager.createSubtacks(subTask1);
        taskManager.createSubtacks(subTask2);
        subTask1.setEpicId(epic1.getId());
        subTask2.setEpicId(epic1.getId());
        taskManager.setEpicStatus(epic1);
        assertEquals(StatusTasks.NEW, epic1.getStatus(), "Ошибка в установки статуса эпик задачи");
    }

    /**
     * Тест на обновление подзадачи
     */
    @Test
    public void shouldUpdateSubtask() {
        taskManager.createEpic(epic1);
        taskManager.createSubtacks(subTask1);
        subTask1.setEpicId(epic1.getId());
        taskManager.setEpicStatus(epic1);
        subTask1.setStatus(StatusTasks.IN_PROGRESS);
        taskManager.setUpdateSubtask(subTask1);
        assertEquals(StatusTasks.IN_PROGRESS, subTask1.getStatus(), "Ошибка в обновлении подзадачи");
    }


}