package bisnesLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import data.*;

/**
 * Класс реализующий бизнес-логику
 * @author Max Vasilyev
 * @version 1.0
 */
public class Manager {

    private HashMap<Integer, Task> storageTask = new HashMap<>();
    private HashMap<Integer, Epic> storageEpic = new HashMap<>();
    private HashMap<Integer, SubTask> storageSubtask = new HashMap<>();
    private HashMap<Integer,Task> allStoragetask = new HashMap<>();
    private int indificator = 0;

    public HashMap<Integer, Task> getStorageTask() {
        return storageTask;
    }

    public HashMap<Integer, Epic> getStorageEpic() {
        return storageEpic;
    }

    public HashMap<Integer, SubTask> getStorageSubtask() {
        return storageSubtask;
    }

    /**
     * Метод для создание задачи и сохранение его хэш-таблице
     * @param task        - задача
     * @param storageTask - хэш-таблица
     */
    public void createTasks(Task task, HashMap storageTask) {
        task.setId(++indificator);
        storageTask.put(indificator, task);
        allStoragetask.put(indificator, task);
    }

    /**
     * Метод вывыдящий все данные из определенной Хэш-таблицы
     * @return возвращает все задачи
     */
    public HashMap getALLTasks() {
        System.out.println("-".repeat(25));
        return allStoragetask;
    }

    /**
     * Метод выводящий данные о задачах-эпиках и об их подзадачах
     * @param epic - задача-эпик
     * @return возвращает хэш-таблицу с эпиками и подзадачами
     */
    public HashMap getAllSubtasks(Epic epic) {
        System.out.println("-".repeat(25));
        HashMap<String, ArrayList<String>> showMapEpic = new HashMap<>();
        ArrayList<String> listOfSubtaskTitle = new ArrayList<>();
        for (int i = 0; i < epic.getNumOfSubtasks().size(); i++) {
            for (int indSub = 1; indSub <= allStoragetask.size(); indSub++) {
                if (epic.getNumOfSubtasks().get(i) == indSub) {
                    listOfSubtaskTitle.add(storageSubtask.get(indSub).getTitle());
                }
            }
        }
        showMapEpic.put(epic.getTitle(), listOfSubtaskTitle);
        return showMapEpic;
    }

    /**
     * Метод удаления всех задач из определенной хэш-таблицы
     */
    public void deleteAllTasks() {
        System.out.println("Все задачи стерты");
        allStoragetask.clear();
    }

    /**
     * Метод удаление задачи по индификатору
     * @param indification - индификатор
     */
    public void deletePerIndification(int indification) {
        for (Object ind : allStoragetask.keySet()) {
            if (indification == (Integer) ind) {
                allStoragetask.remove(ind);
                break;
            } else {
                System.out.println("Такого индификатора нет");
            }
        }
    }

    /**
     * Метод установление статуса для задачей-эпиков
     * @param epic - задача типа эпик
     */
    public void setEpicStatus(Epic epic) {
        if (epic.getNumOfSubtasks().isEmpty() || checkNewStatus(epic.getNumOfSubtasks())) {
            epic.setNumOfStatus(1);
        } else {
            ArrayList<Boolean> listOfBooleanStatus = new ArrayList<>();
            for (int i = 0; i < epic.getNumOfSubtasks().size(); i++) {
                for (int indSub = 1; indSub <= storageSubtask.size(); indSub++) {
                    if (epic.getNumOfSubtasks().get(i) == indSub) {
                        listOfBooleanStatus.add(checkStatus(storageSubtask.get(indSub)));
                    }
                }
            }
            if (resultBooleanStatus(listOfBooleanStatus)) {
                epic.setNumOfStatus(3);
            } else {
                epic.setNumOfStatus(2);
            }
        }
    }

    /**
     * Метод для обновления данных в хэш-таблицах
     *
     * @param task        - задача
     * @param storageTask - хэш-таблица задач
     */
    public void setUpdateTask(Task task, HashMap storageTask) {
        storageTask.put(task.getId(), task);
        allStoragetask.put(task.getId(), task);
        System.out.println("Обновление произошло");
    }

    public Task getByIndificator(int id) {
        return allStoragetask.get(id);
    }

    /**
     * Метод проверки статуса для метода получения статуса для задач-эпиков
     * @param task - задача
     * @return возвращает результат проверки
     */
    private boolean checkStatus(Task task) {
        return Objects.equals(task.getStatus(), "DONE");
    }

    /**
     * Метод проверки статуса "NEW" во всех подзадачах в задачах-эпиков
     * @param listOfSubtask - список всех индификаторов подзадач в определенной задаче-эпике
     * @return возвращает результат проверки
     */
    private boolean checkNewStatus(ArrayList<Integer> listOfSubtask) {
        int countOfNewStatus = 0;
        for (int i : listOfSubtask) {
            if (storageSubtask.get(i).getStatus() == "NEW") {
                countOfNewStatus++;
            }
        }
        if (countOfNewStatus == listOfSubtask.size()) {
            return true;
        }
        return false;
    }

    /**
     * Метод проверки значения статуса во всех подзадачах в задах-эпиках
     * @param arrayBoolean - список всех проверок статусов подзадач в определенной задаче-эпике
     * @return возвращает результат проверки
     */
    private boolean resultBooleanStatus(ArrayList<Boolean> arrayBoolean) {
        for (Boolean i : arrayBoolean) {
            if (!i) {
                return false;
            }
        }
        return true;
    }

}