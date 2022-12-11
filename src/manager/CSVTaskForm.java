package manager;

import data.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVTaskForm {
    private static File file = Paths.get("resources/save.csv").toFile();
    private static FileBackedTaskManager fileBackedTaskManager = FileBackedTaskManager.loadFromFile(file);


    /**
     * Метод преобразования строки в задачу
     *
     * @param value - строка
     */
    public static Task toTask(String value) {
        String[] cutString = value.split(",");
        Task task;
        if (TypeOfTasks.valueOf(cutString[1]) == TypeOfTasks.TASK) {
            task = new Task(cutString[2], cutString[4]);
            setTaskAtribuit(task, cutString);
        } else if (TypeOfTasks.valueOf(cutString[1]).equals(TypeOfTasks.EPIC)) {
            task = new Epic(cutString[2], cutString[4]);
            setTaskAtribuit(task, cutString);
        } else {
            task = new SubTask(cutString[2], cutString[4]);
            setTaskAtribuit(task, cutString);
            setIdEpicSubtask((SubTask) task, cutString[5]);
        }
        return task;
    }

    /**
     * Метод утсановки Id и статуса в задачу
     *
     * @param task      - задача
     * @param cutString - массив из строк
     */
    private static void setTaskAtribuit(Task task, String[] cutString) {
        task.setId(Integer.parseInt(cutString[0]));
        task.setStatus(StatusTasks.valueOf(cutString[3]));
        task.setStartTime(cutString[6]);
        task.setDuration(Long.parseLong(cutString[7]));
    }

    /**
     * Метод преобразования истории просмотра в строку
     *
     * @param historyManager - менеджер истории просмотра
     * @return - строка
     */
    public static String historyToString(HistoryManager historyManager) {
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
     *
     * @param value - строка
     * @return - список
     */
    public static List<Integer> getHistoryListNum(String value) {
        String[] strNum = value.split(",");
        List<Integer> resultList = new ArrayList<>();
        for (String num : strNum) {
            resultList.add(Integer.parseInt(num));
        }
        return resultList;
    }

    /**
     * Метод установки в подзадачу id эпик-задачи
     *
     * @param subTask - подзадача
     * @param idEpic  - id эпик задачи
     */
    private static void setIdEpicSubtask(SubTask subTask, String idEpic) {
        subTask.setEpicId(Integer.parseInt(idEpic));
    }

}
