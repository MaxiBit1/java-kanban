package data;

import manager.TaskManager;

import java.time.LocalDateTime;
import java.util.ArrayList;


/**
 * Класс задач-эпиков
 *
 * @author Max Vasilyev
 * @version 1.0
 */
public class Epic extends Task {

    private ArrayList<Integer> subtackIDs;
    private LocalDateTime startTimeEpic;

    public Epic(String title, String description) {
        super(title, description);
        subtackIDs = new ArrayList<>();
        setTypeOfTask(TypeOfTasks.EPIC);
    }

    public void setStartTime() {
        super.setStartTime(startTimeEpic.format(super.formatter));
    }

    /**
     * Метод удаление из листа определенный номер подзадачи
     *
     * @param indificator - индификатор
     */
    public void removeNumOfSubtask(int indificator) {
        subtackIDs.remove(indificator);
    }

    /**
     * Установка начального времени задачи-эпика
     *
     * @param taskManager - менеджер
     */
    public void setStartTimeEpic(TaskManager taskManager) {
        startTimeEpic = taskManager.getSortPerStatrtTimeSubtask().get(0).getStartTime();
        setStartTime();
    }

    /**
     * Получение коненчного времени задачи-эпик
     *
     * @param taskManager - менеджер
     * @return конечное время задачи-эпик
     */
    public LocalDateTime getEndTime(TaskManager taskManager) {
        long duration = 0;
        for (Integer subtackID : subtackIDs) {
            for (Task subTask : taskManager.getStorageSubtask()) {
                if (subtackID == subTask.getId()) {
                    duration += subTask.getDuration();
                }
            }
        }
        return super.getStartTime().plusMinutes(duration);
    }


    /**
     * Метод удаления всего листа
     */
    public void deleteAllList() {
        subtackIDs.clear();
    }

    public ArrayList<Integer> getSubtackIDs() {
        return subtackIDs;
    }

}
