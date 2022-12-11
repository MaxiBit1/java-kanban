package test;

import manager.InMemoryTaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Класс тестов бизнес-логики
 *
 * @author Max
 * @version 1.0
 */
class InMemoryTaskManagerTest extends TaskManagerTest<InMemoryTaskManager> {
    @BeforeEach
    public void setUp() {
        taskManager = new InMemoryTaskManager();
    }

    @Test
    @Override
    public void shouldCreateTask() {
        super.shouldCreateTask();
    }

    @Test
    @Override
    public void shouldCreateEpic() {
        super.shouldCreateEpic();
    }

    @Test
    @Override
    public void shouldCreateSubtask() {
        super.shouldCreateSubtask();
    }

    @Test
    @Override
    public void shouldDellAllTasks() {
        super.shouldDellAllTasks();
    }

    @Test
    @Override
    public void shouldDellAllTaskWithoutCreate() {
        super.shouldDellAllTaskWithoutCreate();
    }

    @Test
    @Override
    public void shouldCreateTaskWithNull() {
        super.shouldCreateTaskWithNull();
    }

    @Test
    @Override
    public void shouldCreateEpicWithNull() {
        super.shouldCreateEpicWithNull();
    }

    @Test
    @Override
    public void shouldCreateSubtaskWithNull() {
        super.shouldCreateSubtaskWithNull();
    }

    @Test
    @Override
    public void shouldDeleteTaskPerIndificator() {
        super.shouldDeleteTaskPerIndificator();
    }

    @Test
    @Override
    public void shouldDeleteEpicPerIndificator() {
        super.shouldDeleteEpicPerIndificator();
    }

    @Test
    @Override
    public void shouldDeleteSubtaskPerIndificator() {
        super.shouldDeleteSubtaskPerIndificator();
    }

    @Test
    @Override
    public void shouldDeleteTaskPerIndificatorWithNull() {
        super.shouldDeleteTaskPerIndificatorWithNull();
    }

    @Test
    @Override
    public void shouldDeleteEpicPerIndificatorWithNull() {
        super.shouldDeleteEpicPerIndificatorWithNull();
    }

    @Test
    @Override
    public void shouldDeleteSubtaskTaskPerIndificatorWithNull() {
        super.shouldDeleteSubtaskTaskPerIndificatorWithNull();
    }

    @Test
    @Override
    public void shouldUpdateTask() {
        super.shouldUpdateTask();
    }

    @Test
    @Override
    public void shouldSetNewEpicStatus() {
        super.shouldSetNewEpicStatus();
    }

    @Test
    @Override
    public void shouldUpdateSubtask() {
        super.shouldUpdateSubtask();
    }
}