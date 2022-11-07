package manager;

import data.Epic;
import data.StatusTasks;
import data.SubTask;
import data.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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

    HistoryManager historyManager = Managers.HistoryManagergetDefaultHistory();

    @Override
    public List<Task> getStorageTask() {
        return new ArrayList<>(storageTask.values());
    }

    @Override
    public List<Task> getStorageEpic() {
        return new ArrayList<>(storageEpic.values());
    }

    @Override
    public List<Task> getStorageSubtask() {
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
    public List<SubTask> getAllSubtasks(Epic epic) {
        List<SubTask> listOfSubtaskTitle = new ArrayList<>();
        for (int i = 0; i < epic.getSubtackIDs().size(); i++) {
            if (storageSubtask.get(epic.getSubtackIDs().get(i)).getEpicId() == epic.getId()) {
                listOfSubtaskTitle.add(storageSubtask.get(epic.getSubtackIDs().get(i)));
            }
        }
        return listOfSubtaskTitle;
    }

    /**
     * Метод удаления всех задач из хэш-таблицы и хэш-таблицы истории вызова
     */
    @Override
    public void deleteAllTasks() {
        System.out.println("Все задачи стерты");
        for (Integer idTask : storageTask.keySet()) {
            historyManager.remove(idTask);
        }
        storageTask.clear();
    }

    /**
     * Метод удаления всех задач-эпиков из хэш-таблицы и хэш-таблицы истории вызова
     */
    @Override
    public void deleteAllEpics() {
        System.out.println("Все задачи стерты");
        for (Integer idTask : storageEpic.keySet()) {
            historyManager.remove(idTask);
        }
        storageEpic.clear();
    }

    /**
     * Метод удаления всех подзадач из хэш-таблицы и хэш-таблицы истории вызова
     */
    @Override
    public void deleteAllSubtasks() {
        System.out.println("Все задачи стерты");
        for (Integer idTask : storageSubtask.keySet()) {
            historyManager.remove(idTask);
        }
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
    public void deleteTaskById(int indification) {
        storageTask.remove(indification);
        historyManager.remove(indification);
    }

    /**
     * Метод удаление задачи-эпик по индификатору
     * @param indification - индификатор
     */
    @Override
    public void deleteEpicById(int indification) {
        for (int i : storageEpic.get(indification).getSubtackIDs()) {
            storageSubtask.get(i).setIdEpicNull();
        }
        storageEpic.remove(indification);
        historyManager.remove(indification);
    }

    /**
     * Метод удаление подзадачи по индификатору
     * @param indification - индификатор
     */
    @Override
    public void deleteSubtaskById(int indification) {
        Epic epic = storageEpic.get(storageSubtask.get(indification).getEpicId());
        int ind = epic.getSubtackIDs().indexOf(indification);
        epic.removeNumOfSubtask(ind);
        storageSubtask.remove(indification);
        historyManager.remove(indification);
    }

    /**
     * Метод установление статуса для задачей-эпиков
     * @param epic - задача типа эпик
     */
    @Override
    public void setEpicStatus(Epic epic) {
        if (epic.getSubtackIDs().isEmpty() || checkNewStatus(epic.getSubtackIDs())) {
            epic.setStatus(StatusTasks.NEW);
        } else {
            ArrayList<Boolean> listOfBooleanStatus = new ArrayList<>();
            for (int i = 0; i < epic.getSubtackIDs().size(); i++) {
                listOfBooleanStatus.add(checkStatus(storageSubtask.get(epic.getSubtackIDs().get(i))));
            }
            if (resultBooleanStatus(listOfBooleanStatus)) {
                epic.setStatus(StatusTasks.DONE);
            } else {
                epic.setStatus(StatusTasks.IN_PROGRESS);
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
        Epic epic = storageEpic.get(storageSubtask.get(subTask.getId()).getEpicId());
        int ind = epic.getSubtackIDs().indexOf(subTask.getId());
        storageEpic.get(subTask.getEpicId()).getSubtackIDs().set(ind, subTask.getId());
        System.out.println("Обновление произошло");
    }


    @Override
    public Task getTask(int id) {
        historyManager.add(storageTask.get(id));
        return storageTask.get(id);
    }

    @Override
    public Task getEpic(int id) {
        historyManager.add(storageEpic.get(id));
        return storageEpic.get(id);
    }

    @Override
    public Task getSubtask(int id) {
        historyManager.add(storageSubtask.get(id));
        return storageSubtask.get(id);
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    /**
     * Метод проверки статуса для метода получения статуса для задач-эпиков
     * @param task - задача
     * @return возвращает результат проверки
     */
    private boolean checkStatus(Task task) {
        return Objects.equals(task.getStatus(), StatusTasks.DONE);
    }

    /**
     * Метод проверки статуса "NEW" во всех подзадачах в задачах-эпиков
     * @param listOfSubtask - список всех индификаторов подзадач в определенной задаче-эпике
     * @return возвращает результат проверки
     */
    private boolean checkNewStatus(ArrayList<Integer> listOfSubtask) {
        int countOfNewStatus = 0;
        for (int i : listOfSubtask) {
            if (storageSubtask.get(i).getStatus() == StatusTasks.NEW) {
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