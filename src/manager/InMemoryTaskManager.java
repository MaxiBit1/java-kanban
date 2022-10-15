package manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import data.*;

/**
 * Класс реализующий логику менеджера
 * @author Max Vasilyev
 * @version 1.0
 */
public class InMemoryTaskManager implements TaskManager {

    private HashMap<Integer, Task> storageTask = new HashMap<>();
    private HashMap<Integer, Epic> storageEpic = new HashMap<>();
    private HashMap<Integer, SubTask> storageSubtask = new HashMap<>();
    private int indificator = 0;

    @Override
    public ArrayList<Task> getStorageTask() {
        return new ArrayList<>(storageTask.values());
    }

    @Override
    public ArrayList<Task> getStorageEpic() {
        return new ArrayList<>(storageEpic.values());
    }

    @Override
    public ArrayList<Task> getStorageSubtask() {
        return new ArrayList<>(storageSubtask.values());
    }

    /**
     * Метод для создание задачи и сохранение его хэш-таблице
     * @param task - задача
     */
    @Override
    public void createTasks(Task task) {
        task.setId(++indificator);
        storageTask.put(indificator, task);
    }

    /**
     * Метод для создание задачи и сохранение его хэш-таблице
     * @param epic - задача-эпик
     */
    @Override
    public void createEpic(Epic epic) {
        epic.setId(++indificator);
        storageEpic.put(indificator, epic);
    }

    /**
     * Метод для создание задачи и сохранение его хэш-таблице
     * @param subTask - подзадача
     */
    @Override
    public void createSubtacks(SubTask subTask) {
        subTask.setId(++indificator);
        storageSubtask.put(indificator, subTask);
    }

    /**
     * Метод выводящий данные о задачах-эпиках и об их подзадачах
     * @param epic - задача-эпик
     * @return возвращает список этой задачи-эпик
     */
    @Override
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
    @Override
    public void deleteAllTasks() {
        System.out.println("Все задачи стерты");
        storageTask.clear();
    }

    /**
     * Метод удаления всех задач-эпиков из хэш-таблицы
     */
    @Override
    public void deleteAllEpics() {
        System.out.println("Все задачи стерты");
        storageEpic.clear();
    }

    /**
     * Метод удаления всех подзадач из хэш-таблицы
     */
    @Override
    public void deleteAllSubtasks() {
        System.out.println("Все задачи стерты");
        storageSubtask.clear();
        for (int id : storageEpic.keySet()) {
            storageEpic.get(id).deleteAllList();
        }
    }

    /**
     * Метод удаление задачи по индификатору
     * @param indification - индификатор
     */
    @Override
    public void deletePerIndificationTask(int indification) {
        storageTask.remove(indification);
    }

    /**
     * Метод удаление задачи-эпик по индификатору
     * @param indification - индификатор
     */
    @Override
    public void deletePerIndificationEpic(int indification) {
        for (int i : storageEpic.get(indification).getNumOfSubtasks()) {
            storageSubtask.get(i).setIdEpicNull();
        }
        storageEpic.remove(indification);
    }

    /**
     * Метод удаление подзадачи по индификатору
     * @param indification - индификатор
     */
    @Override
    public void deletePerIndificationSubtask(int indification) {
        Epic epic = storageEpic.get(storageSubtask.get(indification).getIdEpic());
        int ind = epic.getNumOfSubtasks().indexOf(indification);
        epic.removeNumOfSubtask(ind);
        storageSubtask.remove(indification);

    }

    /**
     * Метод установление статуса для задачей-эпиков
     * @param epic - задача типа эпик
     */
    @Override
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
     * @param task - задача
     */
    @Override
    public void setUpdateTask(Task task) {
        storageTask.put(task.getId(), task);
        System.out.println("Обновление произошло");
    }

    /**
     * Метод для обновления данных в хэш-таблицах задач-эпиков
     * @param epic - задача-эпик
     */
    @Override
    public void setUpdateEpic(Epic epic) {
        storageEpic.put(epic.getId(), epic);
        System.out.println("Обновление произошло");
    }

    /**
     * Метод для обновления данных в хэш-таблицах подзадач
     * @param subTask - подзадача
     */
    @Override
    public void setUpdateSubtask(SubTask subTask) {
        storageSubtask.put(subTask.getId(), subTask);
        Epic epic = storageEpic.get(storageSubtask.get(subTask.getId()).getIdEpic());
        int ind = epic.getNumOfSubtasks().indexOf(subTask.getId());
        storageEpic.get(subTask.getIdEpic()).getNumOfSubtasks().set(ind, subTask.getId());
        System.out.println("Обновление произошло");
    }


    @Override
    public Task getTask(int id) {
        Managers.HistoryManagergetDefaultHistory().add(storageTask.get(id));
        return storageTask.get(id);
    }

    @Override
    public Task getEpic(int id) {
        Managers.HistoryManagergetDefaultHistory().add(storageEpic.get(id));
        return storageEpic.get(id);
    }

    @Override
    public Task getSubtask(int id) {
        Managers.HistoryManagergetDefaultHistory().add(storageSubtask.get(id));
        return storageSubtask.get(id);
    }

    /**
     * Метод проверки статуса для метода получения статуса для задач-эпиков
     * @param task - задача
     * @return возвращает результат проверки
     */
    private boolean checkStatus(Task task) {
        return Objects.equals(task.getStatus(), StatusTasks.DONE.toString());
    }

    /**
     * Метод проверки статуса "NEW" во всех подзадачах в задачах-эпиков
     * @param listOfSubtask - список всех индификаторов подзадач в определенной задаче-эпике
     * @return возвращает результат проверки
     */
    private boolean checkNewStatus(ArrayList<Integer> listOfSubtask) {
        int countOfNewStatus = 0;
        for (int i : listOfSubtask) {
            if (storageSubtask.get(i).getStatus().equals(StatusTasks.NEW.toString())) {
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