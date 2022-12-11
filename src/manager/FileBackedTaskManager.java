package manager;

import data.Epic;
import data.StatusTasks;
import data.SubTask;
import data.Task;
import exeption.ManagerSaveExeption;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

/**
 * Класс записи задач и истории просмотра в файл
 *
 * @author Max Vasilyev
 * @version 1.0
 */
public class FileBackedTaskManager extends InMemoryTaskManager {

    private final String fileName;
    private Task task;

    /**
     * Конструктор без параметра
     */
    public FileBackedTaskManager() {
        this.fileName = "resources/save.csv";
    }

    /**
     * Конструктор с парметором, который считывает файл
     *
     * @param fileName - название файла
     */
    public FileBackedTaskManager(String fileName) throws ManagerSaveExeption {
        this.fileName = fileName;
        try {
            String str = Files.readString(Path.of(fileName));
            if (!str.isBlank()) {
                String[] linesArr = str.split("\n");
                for (int i = 1; i < linesArr.length; i++) {
                    if (linesArr[i].isBlank()) {
                        if (!linesArr[i + 1].isEmpty()) {
                            getHistoryFromList(CSVTaskForm.getHistoryListNum(linesArr[i + 1]));
                            break;
                        }
                    } else {
                        task = CSVTaskForm.toTask(linesArr[i]);
                        whatCreateTask();
                    }
                }
            }
        } catch (IOException e) {
            throw new ManagerSaveExeption("Ошибка при чтении файла");
        }
    }

    @Override
    public void createTasks(Task task) {
        super.createTasks(task);
        save();
    }

    @Override
    public void createEpic(Epic epic) {
        super.createEpic(epic);
        save();
    }

    @Override
    public void createSubtacks(SubTask subTask) {
        super.createSubtacks(subTask);
        save();
    }

    @Override
    public void deleteAllTasks() {
        super.deleteAllTasks();
        save();
    }

    @Override
    public void deleteAllEpics() {
        super.deleteAllEpics();
        save();
    }

    @Override
    public void deleteAllSubtasks() {
        super.deleteAllSubtasks();
        save();
    }

    @Override
    public void deleteTaskById(int indification) {
        super.deleteTaskById(indification);
        save();
    }

    @Override
    public void deleteEpicById(int indification) {
        super.deleteEpicById(indification);
        save();
    }

    @Override
    public void deleteSubtaskById(int indification) {
        super.deleteSubtaskById(indification);
        save();
    }

    @Override
    public void setEpicStatus(Epic epic) {
        super.setEpicStatus(epic);
        save();
    }

    @Override
    public void setUpdateTask(Task task) {
        super.setUpdateTask(task);
        save();
    }

    @Override
    public void setUpdateEpic(Epic epic) {
        super.setUpdateEpic(epic);
        save();
    }

    @Override
    public void setUpdateSubtask(SubTask subTask) {
        super.setUpdateSubtask(subTask);
        save();
    }

    /**
     * Метод преобразования всех задач в строки
     *
     * @return - текст, в которой каждая строка задача
     */
    public String allTasksToList() {
        StringBuilder sb = new StringBuilder();
        for (int cons = 1; cons <= indificator; cons++) {
            for (Integer indT : storageTask.keySet()) {
                if (indT == cons) {
                    sb.append("\n").append(storageTask.get(indT).toString());
                    break;
                }
            }
            for (Integer indT : storageEpic.keySet()) {
                if (indT == cons) {
                    sb.append("\n").append(storageEpic.get(indT).toString());
                    break;
                }
            }
            for (Integer indT : storageSubtask.keySet()) {
                if (indT == cons) {
                    sb.append("\n").append(storageSubtask.get(indT).toString());
                    break;
                }
            }
        }
        return sb.toString();
    }

    /**
     * Метод установки истории из списка
     *
     * @param listHistory - список истории ID
     */
    public void getHistoryFromList(List<Integer> listHistory) {
        for (Integer id : listHistory) {
            for (Integer idTask : storageTask.keySet()) {
                if (Objects.equals(idTask, id)) {
                    getTask(idTask);
                }
            }
            for (Integer idEpic : storageEpic.keySet()) {
                if (Objects.equals(idEpic, id)) {
                    getEpic(idEpic);
                }
            }
            for (Integer idSubtask : storageSubtask.keySet()) {
                if (Objects.equals(idSubtask, id)) {
                    getSubtask(idSubtask);
                }
            }
        }
        save();
    }

    /**
     * Метод записи задач в файл
     *
     * @throws ManagerSaveExeption - собственное исключение
     */
    protected void save() throws ManagerSaveExeption {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("id,type,name,status,description,epic,startTime,duration");
            writer.write(allTasksToList());
            if (countGet != 0) {
                writer.write("\n" + "\n" + CSVTaskForm.historyToString(historyManager));
            }
        } catch (IOException e) {
            throw new ManagerSaveExeption("Ошибка при сохраненнии в файл");
        }
    }

    /**
     * Метод для создания определенной задачи
     */
    private void whatCreateTask() {
        switch (task.getTypeOfTask()) {
            case TASK:
                createTasks(task);
                break;
            case EPIC:
                createEpic((Epic) task);
                break;
            case SUBTASK:
                createSubtacks((SubTask) task);
                break;
        }
    }

    /**
     * Метод загрузки из файла
     *
     * @param file - файл
     * @return - объкт класса FileBackedTaskManager
     */
    public static FileBackedTaskManager loadFromFile(File file) {
        return new FileBackedTaskManager(file.toString());
    }

    public static void main(String[] args) {
        TaskManager taskManager = new FileBackedTaskManager();
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
        File file = Paths.get("resources/save.csv").toFile();
        TaskManager taskManager1 = loadFromFile(file);
        System.out.println(taskManager1.getHistory());
    }

}