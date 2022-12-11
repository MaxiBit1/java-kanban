package test;

import data.Epic;
import data.StatusTasks;
import data.SubTask;
import data.Task;
import manager.HistoryManager;
import manager.InMemoryHistoryManager;
import manager.InMemoryTaskManager;
import manager.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Класс тестов для истории
 *
 * @author Max
 * @version 1.0
 */
class HistoryManagerTest {

    TaskManager taskManager;
    HistoryManager historyManager;
    Task task;
    Epic epic1;
    SubTask subTask1;
    SubTask subTask2;
    Task task2;

    @BeforeEach
    void setTaskManager() {
        taskManager = new InMemoryTaskManager();
        historyManager = new InMemoryHistoryManager();
        task = new Task("T1", "descriptionT1");
        task.setStatus(StatusTasks.IN_PROGRESS);
        task2 = new Task("T2", "descriptionT2");
        task2.setStatus(StatusTasks.DONE);
        subTask1 = new SubTask("S1", "description1");
        subTask1.setStatus(StatusTasks.NEW);
        subTask2 = new SubTask("S2", "descriptionS2");
        subTask2.setStatus(StatusTasks.IN_PROGRESS);
        taskManager.createTasks(task);
        taskManager.createTasks(task2);
        taskManager.createSubtacks(subTask1);
        taskManager.createSubtacks(subTask2);
    }

    /**
     * Тест добавление задач в историю
     */
    @Test
    void shouldAddTasks() {
        historyManager.add(task);
        historyManager.add(subTask1);
        historyManager.add(subTask2);
        historyManager.add(task);
        assertFalse(historyManager.getHistory().isEmpty());
        assertEquals(3, historyManager.getHistory().size());
        assertEquals(task, historyManager.getHistory().get(2));
        assertEquals(subTask1, historyManager.getHistory().get(0));
    }

    /**
     * Удаление задач из истории
     */
    @Test
    void shouldRemoveTasks() {
        historyManager.add(task);
        historyManager.add(subTask1);
        historyManager.add(task);
        historyManager.add(task2);
        historyManager.remove(task2.getId());
        assertEquals(2, historyManager.getHistory().size());
        assertEquals(subTask1, historyManager.getHistory().get(0));
        historyManager.remove(subTask1.getId());
        assertEquals(1, historyManager.getHistory().size());
        assertEquals(task, historyManager.getHistory().get(0));
    }
}