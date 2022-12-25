package data;

import manager.TaskManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy; HH:mm");
        super.setStartTime(startTimeEpic.format(formatter));
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
     * @param sortedList - отсортированный лист подзадач по времени
     */
    public void setStartTimeEpic(List<SubTask> sortedList) {
        startTimeEpic = sortedList.get(0).getStartTime();
        setStartTime();
    }

    /**
     * Получение коненчного времени задачи-эпик
     *
     * @param listSubtask - список подзадач
     * @return конечное время задачи-эпик
     */
    public LocalDateTime getEndTime(List<SubTask> listSubtask) {
        long duration = 0;
        for (Integer subtackID : subtackIDs) {
            for (Task subTask : listSubtask) {
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
