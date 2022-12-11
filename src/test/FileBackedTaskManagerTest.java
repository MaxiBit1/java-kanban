package test;

import manager.FileBackedTaskManager;
import manager.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс тестов для записи в файл
 *
 * @author Max
 * @version 1.0
 */

class FileBackedTaskManagerTest extends TaskManagerTest<FileBackedTaskManager> {
    @BeforeEach
    public void setUp() {
        taskManager = new FileBackedTaskManager();
    }

    /**
     * Проверка извлечения пустого списка задач
     */
    @Test
    public void shouldSaveAndReturnNullTaskFromFile() {
        File file = Paths.get("resources/save.csv").toFile();
        TaskManager taskManager1 = FileBackedTaskManager.loadFromFile(file);
        assertTrue(taskManager1.getStorageTask().isEmpty());
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

    /**
     * Проверка сохранение и извлечения из файла данных
     */
    @Test
    public void shouldSaveAndReturnTaskFromFile() {
        taskManager.createTasks(task);
        taskManager.createSubtacks(subTask1);
        taskManager.createEpic(epic1);
        taskManager.setEpicStatus(epic1);
        File file = Paths.get("resources/save.csv").toFile();
        TaskManager taskManager1 = FileBackedTaskManager.loadFromFile(file);
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> taskManager1.getHistory()
        );
        assertNull(exception.getMessage());
        assertEquals(task, taskManager1.getTask(1));
        assertEquals(epic1, taskManager1.getEpic(3));
    }

    /**
     * Тест на получения непустого списка историй
     */
    @Test
    public void shouldReturnNotNullHistoryList() {
        taskManager.createTasks(task);
        taskManager.createSubtacks(subTask1);
        taskManager.getTask(task.getId());
        taskManager.createEpic(epic1);
        taskManager.setEpicStatus(epic1);
        File file = Paths.get("resources/save.csv").toFile();
        TaskManager taskManager1 = FileBackedTaskManager.loadFromFile(file);
        assertNotNull(taskManager1.getHistory());
    }
}