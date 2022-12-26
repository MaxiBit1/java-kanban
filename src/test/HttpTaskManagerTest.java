package test;

import data.Epic;
import data.StatusTasks;
import data.SubTask;
import data.Task;
import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class HttpTaskManagerTest {

    Task task;
    Epic epic1;
    SubTask subTask1;
    SubTask subTask2;
    Task task2;
    TaskManager taskManager;

    @BeforeEach
    void setUp() throws IOException {
        taskManager = Managers.httpTaskManagerDefault();
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




    @Test
    public void shouldSaveTaskAndReturn() throws IOException {
        taskManager.createTasks(task);
        subTask1.setEpicId(3);
        taskManager.createSubtacks(subTask1);
        taskManager.createEpic(epic1);
        TaskManager taskManager1 = Managers.httpTaskManagerDefault();
        assertEquals(task, taskManager1.getTask(task.getId()));
        assertEquals(epic1, taskManager1.getEpic(epic1.getId()));
        assertEquals(subTask1, taskManager1.getSubtask(subTask1.getId()));
    }

}
