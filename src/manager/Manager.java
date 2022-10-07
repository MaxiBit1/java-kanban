package manager;

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
    private int indificator = 0;

    public ArrayList<Task> getStorageTask() {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        for (Task task : storageTask.values()) {
            listOfTasks.add(task);
        }
        return listOfTasks;
    }

    public ArrayList<Task> getStorageEpic() {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        for (Task task : storageEpic.values()) {
            listOfTasks.add(task);
        }
        return listOfTasks;
    }

    public ArrayList<Task> getStorageSubtask() {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        for (Task task : storageSubtask.values()) {
            listOfTasks.add(task);
        }
        return listOfTasks;
    }

    /**
     * Метод для создание задачи и сохранение его хэш-таблице
     *
     * @param task - задача
     */
    public void createTasks(Task task) {
        task.setId(++indificator);
        storageTask.put(indificator, task);
    }

    /**
     * Метод для создание задачи и сохранение его хэш-таблице
     *
     * @param epic - задача-эпик
     */
    public void createEpic(Epic epic) {
        epic.setId(++indificator);
        storageEpic.put(indificator, epic);
    }

    /**
     * Метод для создание задачи и сохранение его хэш-таблице
     *
     * @param subTask - подзадача
     */
    public void createSubtacks(SubTask subTask) {
        subTask.setId(++indificator);
        storageSubtask.put(indificator, subTask);
    }

    /**
     * Метод выводящий данные о задачах-эпиках и об их подзадачах
     *
     * @param epic - задача-эпик
     * @return возвращает список этой задачи-эпик
     */
    public ArrayList<SubTask> getAllSubtasks(Epic epic) {
        ArrayList<SubTask> listOfSubtaskTitle = new ArrayList<>();
        for (int i = 0; i < epic.getNumOfSubtasks().size(); i++) {
            if (storageSubtask.get(epic.getNumOfSubtasks().get(i)).getIdEpic() == epic.getId()) {
                listOfSubtaskTitle.add(storageSubtask.get(epic.getNumOfSubtasks().get(i)));
            }
        }
        return listOfSubtaskTitle;
    }

    /**
     * Метод удаления всех задач из хэш-таблицы
     */
    public void deleteAllTasks() {
        System.out.println("Все задачи стерты");
        storageTask.clear();
    }

    /**
     * Метод удаления всех задач-эпиков из хэш-таблицы
     */
    public void deleteAllEpics() {
        System.out.println("Все задачи стерты");
        storageEpic.clear();
    }

    /**
     * Метод удаления всех подзадач из хэш-таблицы
     */
    public void deleteAllSubtasks() {
        System.out.println("Все задачи стерты");
        storageSubtask.clear();
    }

    /**
     * Метод удаление задачи по индификатору
     *
     * @param indification - индификатор
     */
    public void deletePerIndificationTask(int indification) {
        for (Object ind : storageTask.keySet()) {
            if (indification == (Integer) ind) {
                storageTask.remove(ind);
                break;
            } else {
                System.out.println("Такого индификатора нет");
            }
        }
    }

    /**
     * Метод удаление задачи-эпик по индификатору
     * @param indification - индификатор
     */
    public void deletePerIndificationEpic(int indification) {
        for (Object ind : storageEpic.keySet()) {
            if (indification == (Integer) ind) {
                storageEpic.remove(ind);
                break;
            } else {
                System.out.println("Такого индификатора нет");
            }
        }
    }

    /**
     * Метод удаление подзадачи по индификатору
     *
     * @param indification - индификатор
     */
    public void deletePerIndificationSubtask(int indification) {
        for (Object ind : storageSubtask.keySet()) {
            if (indification == (Integer) ind) {
                storageSubtask.remove(ind);
                break;
            } else {
                System.out.println("Такого индификатора нет");
            }
        }
    }

    /**
     * Метод установление статуса для задачей-эпиков
     *
     * @param epic - задача типа эпик
     */
    public void setEpicStatus(Epic epic) {
        if (epic.getNumOfSubtasks().isEmpty() || checkNewStatus(epic.getNumOfSubtasks())) {
            epic.setNumOfStatus(1);
        } else {
            ArrayList<Boolean> listOfBooleanStatus = new ArrayList<>();
            for (int i = 0; i < epic.getNumOfSubtasks().size(); i++) {
                listOfBooleanStatus.add(checkStatus(storageSubtask.get(epic.getNumOfSubtasks().get(i))));
            }
            if (resultBooleanStatus(listOfBooleanStatus)) {
                epic.setNumOfStatus(3);
            } else {
                epic.setNumOfStatus(2);
            }
        }
    }

    /**
     * Метод для обновления данных в хэш-таблицах задач
     *
     * @param task - задача
     */
    public void setUpdateTask(Task task) {
        storageTask.put(task.getId(), task);
        System.out.println("Обновление произошло");
    }

    /**
     * Метод для обновления данных в хэш-таблицах задач-эпиков
     *
     * @param epic - задача-эпик
     */
    public void setUpdateEpic(Epic epic) {
        storageEpic.put(epic.getId(), epic);
        System.out.println("Обновление произошло");
    }

    /**
     * Метод для обновления данных в хэш-таблицах подзадач
     *
     * @param - задача-эпик
     */
    public void setUpdateSubtask(SubTask subTask) {
        storageSubtask.put(subTask.getId(), subTask);
        System.out.println("Обновление произошло");
    }


    public Task getByIndificatorTask(int id) {
        return storageTask.get(id);
    }

    public Task getByIndificatorEpic(int id) {
        return storageEpic.get(id);
    }

    public Task getByIndificatorSubtask(int id) {
        return storageSubtask.get(id);
    }

    /**
     * Метод проверки статуса для метода получения статуса для задач-эпиков
     *
     * @param task - задача
     * @return возвращает результат проверки
     */
    private boolean checkStatus(Task task) {
        return Objects.equals(task.getStatus(), "DONE");
    }

    /**
     * Метод проверки статуса "NEW" во всех подзадачах в задачах-эпиков
     *
     * @param listOfSubtask - список всех индификаторов подзадач в определенной задаче-эпике
     * @return возвращает результат проверки
     */
    private boolean checkNewStatus(ArrayList<Integer> listOfSubtask) {
        int countOfNewStatus = 0;
        for (int i : listOfSubtask) {
            if (storageSubtask.get(i).getStatus().equals("NEW")) {
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
     *
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