package manager;

import data.*;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Класс реализующий логику менеджера
 *
 * @author Max Vasilyev
 * @version 1.0
 */
public class InMemoryTaskManager implements TaskManager {
    Comparator<Task> comparable = Comparator.comparing(Task::getStartTime);

    protected HashMap<Integer, Task> storageTask = new HashMap<>();
    protected HashMap<Integer, Epic> storageEpic = new HashMap<>();
    protected HashMap<Integer, SubTask> storageSubtask = new HashMap<>();
    private final Set<Task> priotitySet = new TreeSet<>(comparable);
    protected int indificator = 0;
    protected int countGet = 0;

    HistoryManager historyManager = Managers.historyManagergetDefaultHistory();

    @Override
    public List<Task> getStorageTask() {
        return new ArrayList<>(storageTask.values());
    }

    @Override
    public List<Epic> getStorageEpic() {
        return new ArrayList<>(storageEpic.values());
    }

    @Override
    public List<SubTask> getStorageSubtask() {
        return new ArrayList<>(storageSubtask.values());
    }

    @Override
    public void createTasks(Task task) {
        task.setId(++indificator);
        storageTask.put(indificator, task);
        priotitySet.add(task);
    }

    @Override
    public void createEpic(Epic epic) {
        epic.setId(++indificator);
        epic.setStartTimeEpic(getStorageSubtask());
        setEpicStatus(epic);
        storageEpic.put(indificator, epic);
    }

    @Override
    public void createSubtacks(SubTask subTask) {
        subTask.setId(++indificator);
        storageSubtask.put(indificator, subTask);
        priotitySet.add(subTask);
    }

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

    @Override
    public void deleteAllTasks() {
        System.out.println("Все задачи стерты");
        if (!historyManager.getHistory().isEmpty()) {
            for (Integer idTask : storageTask.keySet()) {
                historyManager.remove(idTask);
            }
        }
        storageTask.clear();
        countGet = 0;
    }

    @Override
    public void deleteAllEpics() {
        System.out.println("Все задачи стерты");
        if (!historyManager.getHistory().isEmpty()) {
            for (Integer idTask : storageEpic.keySet()) {
                historyManager.remove(idTask);
            }
        }
        storageEpic.clear();
        countGet = 0;
    }

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
        countGet = 0;
    }

    @Override
    public void deleteTaskById(int indification) {
        storageTask.remove(indification);
        historyManager.remove(indification);
    }

    @Override
    public void deleteEpicById(int indification) {
        for (int i : storageEpic.get(indification).getSubtackIDs()) {
            storageSubtask.get(i).setIdEpicNull();
        }
        storageEpic.remove(indification);
        historyManager.remove(indification);
    }

    @Override
    public void deleteSubtaskById(int indification) {
        Epic epic = storageEpic.get(storageSubtask.get(indification).getEpicId());
        int ind = epic.getSubtackIDs().indexOf(indification);
        epic.removeNumOfSubtask(ind);
        storageSubtask.remove(indification);
        historyManager.remove(indification);
    }

    @Override
    public void setEpicStatus(Epic epic) {
        for (SubTask subTask : storageSubtask.values()) {
            if (epic.getId() == subTask.getEpicId()) {
                epic.getSubtackIDs().add(subTask.getId());
            }
        }
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

    @Override
    public void setUpdateTask(Task task) {
        storageTask.put(task.getId(), task);
        System.out.println("Обновление произошло");
    }

    @Override
    public void setUpdateEpic(Epic epic) {
        storageEpic.put(epic.getId(), epic);
        System.out.println("Обновление произошло");
    }

    @Override
    public void setUpdateSubtask(SubTask subTask) {
        storageSubtask.put(subTask.getId(), subTask);
        Epic epic = storageEpic.get(storageSubtask.get(subTask.getId()).getEpicId());
        if(epic != null) {
            int ind = epic.getSubtackIDs().indexOf(subTask.getId());
            storageEpic.get(subTask.getEpicId()).getSubtackIDs().set(ind, subTask.getId());
        }
        System.out.println("Обновление произошло");
    }


    @Override
    public Task getTask(int id) {
        countGet++;
        historyManager.add(storageTask.get(id));
        return storageTask.get(id);
    }

    @Override
    public Epic getEpic(int id) {
        countGet++;
        historyManager.add(storageEpic.get(id));
        return storageEpic.get(id);
    }

    @Override
    public SubTask getSubtask(int id) {
        countGet++;
        historyManager.add(storageSubtask.get(id));
        return storageSubtask.get(id);
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    @Override
    public Set<Task> getPrioritizedTask() {
        Instant endTimePerez = null;
        for (Task task : priotitySet) {
            if (endTimePerez == null) {
                endTimePerez = task.getEndTime().toInstant(ZoneOffset.UTC);
            } else {
                if (endTimePerez.isAfter(task.getEndTime().toInstant(ZoneOffset.UTC))) {
                    priotitySet.remove(task);
                } else {
                    endTimePerez = task.getEndTime().toInstant(ZoneOffset.UTC);
                    ;
                }
            }
        }
        return priotitySet;
    }

    /**
     * Метод проверки статуса для метода получения статуса для задач-эпиков
     *
     * @param task - задача
     * @return возвращает результат проверки
     */
    private boolean checkStatus(Task task) {
        return Objects.equals(task.getStatus(), StatusTasks.DONE);
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
            if (storageSubtask.get(i).getStatus() == StatusTasks.NEW) {
                countOfNewStatus++;
            }
        }
        if (countOfNewStatus > 0) {
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

    @Override
    public List<SubTask> getSortPerStatrtTimeSubtask() {
        List<SubTask> resultList = new ArrayList<>(storageSubtask.values());
        resultList.sort(comparable);
        return resultList;
    }

    @Override
    public List<SubTask> getEpicSubtask(int id) {
        List<SubTask> resultSubtask = new ArrayList<>();
        for (Integer subtackID : storageEpic.get(id).getSubtackIDs()) {
            for (Integer idStorageSubtask : storageSubtask.keySet()) {
                if (subtackID == idStorageSubtask) {
                    resultSubtask.add(storageSubtask.get(idStorageSubtask));
                }
            }
        }
        return resultSubtask;
    }

    @Override
    public int getIndificator() {
        return indificator;
    }
}