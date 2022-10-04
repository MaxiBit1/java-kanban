import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


/**
 * Класс реализующий бизнес-логику
 * @author Max Vasilyev
 * @version 1.0
 */
public class Manager {

    /** Метод для создание задачи и сохранение его хэш-таблице
     * @param task - задача
     * @param storageTask - хэш-таблица
     */
    public void createTasks(Task task, HashMap storageTask) {
        int position;
        if (storageTask.isEmpty()) {
            storageTask.put(1, task);
        } else {
            position = getKeyFromMap(storageTask) + 1;
            storageTask.put(position, task);
        }
    }

    /** Метод вывыдящий все данные из определенной Хэш-таблицы
     * @param storageTask - хэш-таблица
     */
    public void showAllTasks(HashMap storageTask) {
        System.out.println("-".repeat(25));
        if(storageTask.isEmpty()){
            System.out.println("Пустое хранилище данных");
            return;
        }
        for (Object i : storageTask.keySet()) {
            System.out.println(i + ": " + storageTask.get(i).toString());
        }
    }

    /** Метод выводящий данные о задачах-эпиках и об их подзадачах
     * @param storageEpic - хэш-таблица задач-эпиков
     * @param storageSubtask - хэш-таблица подзадач
     */
    public void showAllSubtasks(HashMap<Integer, Epic> storageEpic, HashMap<Integer, SubTask> storageSubtask) {
        System.out.println("-".repeat(25));
        for (Object j : storageEpic.keySet()) {
            System.out.println("Эпик №" + j + ": " + storageEpic.get(j).getTitle());
            System.out.println("Подзадачи Эпика: ");
            for (int i = 0; i < storageEpic.get(j).getNumOfSubtasks().size(); i++) {
                for (int indSub = 1; indSub <= storageSubtask.size(); indSub++) {
                    if (storageEpic.get(j).getNumOfSubtasks().get(i) == indSub) {
                        System.out.println(indSub + " - " + storageSubtask.get(indSub).getTitle());
                    }
                }
            }
        }
    }

    /** Метод удаления всех задач из определенной хэш-таблицы
     * @param storageTask - хэш-таблица задач
     * */
    public void deleteAllTasks(HashMap storageTask) {
        System.out.println("Все задачи стерты");
        storageTask.clear();
    }

    /** Метод удаление задачи по индификатору
     * @param storageTask - хэш-таблица задач
     */
    public void deletePerIndification(HashMap storageTask, int indification) {
        for (Object ind : storageTask.keySet()) {
            if (indification == (Integer) ind) {
                storageTask.remove(ind);
                break;
            } else {
                System.out.println("Такого индификатора нет");
            }
        }
    }

    /** Метод установление статуса для задачей-эпиков
     * @param epic - хадача типа эпик
     * @param storageSubtask - хэш-таблица подзадач
     */
    public void setEpicStatus(Epic epic, HashMap<Integer, SubTask> storageSubtask) {
        if(epic.getNumOfSubtasks().isEmpty() || checkNewStatus(epic.getNumOfSubtasks(), storageSubtask)){
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
            if(resultBooleanStatus(listOfBooleanStatus)){
                epic.setNumOfStatus(3);
            } else {
                epic.setNumOfStatus(2);
            }
        }
    }

    /** Метод для обновления данных в хэш-таблицах
     * @param task - задача
     * @param storageTask - хэш-таблица задач
     */
    public void setUpdateTask(Task task, HashMap storageTask) {
        int position = getIndForUpdate(task, storageTask);
        storageTask.put(position, task);
        System.out.println("Обновление произошло");
    }

    /** Метод получения последнего ключа с хэш-таблицы для метода создания задачи
     * @param storageTask - хэш-таблица задач
     * @return значение индификатора в хэш-таблице
     */
    private int getKeyFromMap(HashMap storageTask) {
        int result = 0;

        for (Object i : storageTask.keySet()) {
            result = (Integer) i;
        }
        return result;
    }

    /** Метод получения ключа индификатора для метода обновления
     * @param task - задача
     * @param storageTask - хэш-таблица задач
     * @return получения ключа индификатора
     */
    private int getIndForUpdate(Task task, HashMap storageTask) {
        int result = 0;

        for (Object i : storageTask.keySet()) {
            if (task == storageTask.get(i)) {
                result = (int) i;
            }
        }
        return result;
    }

    /** Метод проверки статуса для метода получения статуса для задач-эпиков
     * @param task - задача
     * @return возвращает результат проверки
     */
    private boolean checkStatus(Task task){
        return Objects.equals(task.getStatus(), "DONE");
    }

    /** Метод проверки статуса "NEW" во всех подзадачах в задачах-эпиков
     * @param listOfSubtask - список всех индификаторов подзадач в определенной задаче-эпике
     * @param storageSubtask - хэш-таблица подзадач
     * @return возвращает результат проверки
     */
    private boolean checkNewStatus(ArrayList<Integer> listOfSubtask, HashMap<Integer,SubTask> storageSubtask) {
        int countOfNewStatus = 0;
        for(int i : listOfSubtask){
            if(storageSubtask.get(i).getStatus() == "NEW") {
                countOfNewStatus++;
            }
        }
        if(countOfNewStatus == listOfSubtask.size()){
            return true;
        }
        return false;
    }

    /** Метод проверки значения статуса во всех подзадачах в задах-эпиках
     * @param arrayBoolean - список всех проверок статусов подзадач в определенной задаче-эпике
     * @return возвращает результат проверки
     */
    private boolean resultBooleanStatus(ArrayList<Boolean> arrayBoolean){
        for (Boolean i : arrayBoolean){
            if(!i){
                return false;
            }
        }
        return true;
    }

}