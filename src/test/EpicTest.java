package test;

import data.Epic;
import data.StatusTasks;
import data.SubTask;
import manager.InMemoryTaskManager;
import manager.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Класс для проверки состояний эпик-задач
 *
 * @author Max
 * @version 1.0
 */

class EpicTest {

    TaskManager taskManager;
    Epic epic;
    SubTask subTask1;
    SubTask subTask2;

    @BeforeEach
    void setTaskManager() {
        taskManager = new InMemoryTaskManager();
    }

    private void setSubtask1(StatusTasks statusTasks) {
        subTask1 = new SubTask("Sub1", "descSub 1");
        subTask1.setStatus(statusTasks);
        subTask1.setEpicId(3);
        taskManager.createSubtacks(subTask1);
    }

    private void setSubtask2(StatusTasks statusTasks) {
        subTask2 = new SubTask("Sub2", "descSub 2");
        subTask2.setStatus(statusTasks);
        subTask2.setEpicId(3);
        taskManager.createSubtacks(subTask2);
    }

    private void setEpic() {
        epic = new Epic("1", "description 1");
        taskManager.createEpic(epic);
        taskManager.setEpicStatus(epic);
    }

    /**
     * Тест на установку эпик-задаче статуса NEW c пустым списком подзадач
     */
    @Test
    public void shouldStatusEpicNewWithNullSubtask() {
        setEpic();
        assertEquals(StatusTasks.NEW, epic.getStatus());
    }

    /**
     * Тест на установку эпик-задаче статуса NEW
     */
    @Test
    public void shouldStatusEpicNewWithSubtaskStatusNew() {
        setSubtask1(StatusTasks.NEW);
        setSubtask2(StatusTasks.NEW);
        setEpic();
        assertEquals(StatusTasks.NEW, epic.getStatus());
    }

    /**
     * Тест на установку эпик-задаче статуса DONE
     */
    @Test
    public void shouldStatusEpicDoneWithSubtaskStatusDone() {
        setSubtask1(StatusTasks.DONE);
        setSubtask2(StatusTasks.DONE);
        setEpic();
        assertEquals(StatusTasks.DONE, epic.getStatus());
    }

    /**
     * Тест на установку эпик-задаче статуса NEW
     */
    @Test
    public void shouldStatusEpicNewWithSubtaskStatusNewAndDone() {
        setSubtask1(StatusTasks.DONE);
        setSubtask2(StatusTasks.NEW);
        setEpic();
        assertEquals(StatusTasks.NEW, epic.getStatus());
    }

    /**
     * Тест на установку эпик-задаче статуса IN_PROGRESS
     */
    @Test
    public void shouldStatusEpicInProgressWithSubtaskStatusIn_Progress() {
        setSubtask1(StatusTasks.IN_PROGRESS);
        setSubtask2(StatusTasks.IN_PROGRESS);
        setEpic();
        assertEquals(StatusTasks.IN_PROGRESS, epic.getStatus());
    }

    /**
     * Тест на проверку установления времени в эпик-задаче
     */
    @Test
    public void shouldSetEpicTime() {
        setSubtask1(StatusTasks.IN_PROGRESS);
        setSubtask2(StatusTasks.IN_PROGRESS);
        setEpic();
        subTask1.setStartTime("11.12.2022, 09:40");
        subTask1.setDuration(10);
        subTask2.setStartTime("11.12.2022, 09:50");
        subTask2.setDuration(40);
        epic.setStartTimeEpic(taskManager.getSortPerStatrtTimeSubtask());
        assertEquals("2022-12-11T10:30", epic.getEndTime(taskManager.getStorageSubtask()).toString());
    }

}