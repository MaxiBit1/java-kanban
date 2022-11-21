package manager;

import Exeption.ManagerSaveExeption;
import data.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Класс записи задач и истории просмотра в файл
 * @author Max Vasilyev
 * @version 1.0
 */
public class FileBackedTaskManager extends InMemoryTaskManager implements TaskManager {

    private String fileName;

    @Override
    public void createTasks(Task task) {
        super.createTasks(task);
        try {
            save();
        } catch (ManagerSaveExeption e) {
            e.getMessageError();
        }
    }

    @Override
    public void createEpic(Epic epic) {
        super.createEpic(epic);
        try {
            save();
        } catch (ManagerSaveExeption e) {
            e.getMessageError();
        }
    }

    @Override
    public void createSubtacks(SubTask subTask) {
        super.createSubtacks(subTask);
        try {
            save();
        } catch (ManagerSaveExeption e) {
            e.getMessageError();
        }
    }

    @Override
    public void deleteAllTasks() {
        super.deleteAllTasks();
        try {
            save();
        } catch (ManagerSaveExeption e) {
            e.getMessageError();
        }
    }

    @Override
    public void deleteAllEpics() {
        super.deleteAllEpics();
        try {
            save();
        } catch (ManagerSaveExeption e) {
            e.getMessageError();
        }
    }

    @Override
    public void deleteAllSubtasks() {
        super.deleteAllSubtasks();
        try {
            save();
        } catch (ManagerSaveExeption e) {
            e.getMessageError();
        }
    }

    @Override
    public void deleteTaskById(int indification) {
        super.deleteTaskById(indification);
        try {
            save();
        } catch (ManagerSaveExeption e) {
            e.getMessageError();
        }
    }

    @Override
    public void deleteEpicById(int indification) {
        super.deleteEpicById(indification);
        try {
            save();
        } catch (ManagerSaveExeption e) {
            e.getMessageError();
        }
    }

    @Override
    public void deleteSubtaskById(int indification) {
        super.deleteSubtaskById(indification);
        try {
            save();
        } catch (ManagerSaveExeption e) {
            e.getMessageError();
        }
    }

    @Override
    public void setEpicStatus(Epic epic) {
        super.setEpicStatus(epic);
        try {
            save();
        } catch (ManagerSaveExeption e) {
            e.getMessageError();
        }
    }

    @Override
    public void setUpdateTask(Task task) {
        super.setUpdateTask(task);
        try {
            save();
        } catch (ManagerSaveExeption e) {
            e.getMessageError();
        }
    }

    @Override
    public void setUpdateEpic(Epic epic) {
        super.setUpdateEpic(epic);
        try {
            save();
        } catch (ManagerSaveExeption e) {
            e.getMessageError();
        }
    }

    @Override
    public void setUpdateSubtask(SubTask subTask) {
        super.setUpdateSubtask(subTask);
        try {
            save();
        } catch (ManagerSaveExeption e) {
            e.getMessageError();
        }
    }

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
    public FileBackedTaskManager(String fileName) {
        this.fileName = fileName;
        try {
            String str = Files.readString(Path.of(fileName));
            if (!str.isBlank()) {
                String[] linesArr = str.split("\n");
                for (int i = 1; i < linesArr.length - 1; i++) {
                    if (linesArr[i].isBlank()) {
                        getHistoryFromList(getHistoryListNum(linesArr[i + 1]));
                    } else {
                        toTask(linesArr[i]);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод записи задач в файл
     *
     * @throws ManagerSaveExeption - собственное исключение
     */
    private void save() throws ManagerSaveExeption {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("id,type,name,status,description,epic");
            writer.write(allTasksToList());
            if (countGet != 0) {
                writer.write("\n" + "\n" + historyToString(historyManager));
            }
        } catch (IOException e) {
            throw new ManagerSaveExeption("Ошибка при записи в файл");
        }
    }

    /**
     * Метод преобразования всех задач в строки
     *
     * @return - текст, в которой каждая строка задача
     */
    private String allTasksToList() {
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
     * Метод преобразования строки в задачу
     *
     * @param value - строка
     */
    private void toTask(String value) {
        String[] cutString = value.split(",");
        Task task;
        if (TypeOfTasks.valueOf(cutString[1]) == TypeOfTasks.TASK) {
            task = new Task(cutString[2], cutString[4]);
            setTaskAtribuit(task, cutString);
            createTasks(task);
        } else if (TypeOfTasks.valueOf(cutString[1]).equals(TypeOfTasks.EPIC)) {
            task = new Epic(cutString[2], cutString[4]);
            setTaskAtribuit(task, cutString);
            createEpic((Epic) task);
        } else {
            task = new SubTask(cutString[2], cutString[4]);
            setTaskAtribuit(task, cutString);
            setIdEpicSubtask((SubTask) task, cutString[5]);
            createSubtacks((SubTask) task);
        }
    }

    /**
     * Метод утсановки Id и статуса в задачу
     *
     * @param task      - задача
     * @param cutString - массив из строк
     */
    private void setTaskAtribuit(Task task, String[] cutString) {
        task.setId(Integer.parseInt(cutString[0]));
        task.setStatus(StatusTasks.valueOf(cutString[3]));
    }

    /**
     * Метод преобразования истории просмотра в строку
     *
     * @param historyManager - менеджер истории просмотра
     * @return - строка
     */
    private static String historyToString(HistoryManager historyManager) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : historyManager.getHistory()) {
            stringBuilder.append(task.getId());
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        return stringBuilder.toString();
    }

    /**
     * Метод преобразования строки в список ID историй просмотра
     * @param value - строка
     * @return - список
     */
    private static List<Integer> getHistoryListNum(String value) {
        String[] strNum = value.split(",");
        List<Integer> resultList = new ArrayList<>();
        for (String num : strNum) {
            resultList.add(Integer.parseInt(num));
        }
        return resultList;
    }

    /**
     * Метод установки истории из списка
     * @param listHistory - список истории ID
     */
    private void getHistoryFromList(List<Integer> listHistory) {
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
        try {
            save();
        } catch (ManagerSaveExeption e) {
            e.getMessageError();
        }
    }

    /**
     * Метод установки в подзадачу id эпик-задачи
     * @param subTask - подзадача
     * @param idEpic - id эпик задачи
     */
    private void setIdEpicSubtask(SubTask subTask, String idEpic) {
        subTask.setEpicId(Integer.parseInt(idEpic));
    }

    /**
     * Метод загрузки из файла
     * @param file - файл
     * @return - объкт класса FileBackedTaskManager
     */
    public static FileBackedTaskManager loadFromFile(File file) {
        return new FileBackedTaskManager(file.toString());
    }

    public static void main(String[] args) {
        TaskManager taskManager = new FileBackedTaskManager();
        Task task1 = new Task("Накачать шину", "Нужно накачать шину на велике");
        task1.setStatus(StatusTasks.NEW);
        SubTask subTask = new SubTask("Собрать чемодан", "Нужно собрать чемодан");
        subTask.setStatus(StatusTasks.IN_PROGRESS);
        SubTask subTask2 = new SubTask("Купить билеты", "Необходимо купить билеты");
        subTask2.setStatus(StatusTasks.DONE);
        Epic epic1 = new Epic("Путешествие", "Нужно собраться в путешествие");
        subTask.setEpicId(6);
        subTask2.setEpicId(6);
        taskManager.setEpicStatus(epic1);
        taskManager.createTasks(task1);
        taskManager.getTask(1);
        taskManager.createSubtacks(subTask);
        taskManager.createSubtacks(subTask2);
        taskManager.getSubtask(3);
        taskManager.createEpic(epic1);
        File file = Paths.get("resources/save.csv").toFile();
        TaskManager taskManager1 = loadFromFile(file);
        System.out.println(taskManager1.getHistory());
    }

}