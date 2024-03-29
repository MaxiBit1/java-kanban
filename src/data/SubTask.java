package data;

import java.time.format.DateTimeFormatter;

/**
 * Класс подзадач
 *
 * @author Max Vasilyev
 * @version 1.0
 */
public class SubTask extends Task {

    private int epicId;

    public SubTask(String title, String description) {
        super(title, description);
        setTypeOfTask(TypeOfTasks.SUBTASK);
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    /**
     * Метод установки id задачи-эпик в ноль
     */
    public void setIdEpicNull() {
        epicId = 0;
    }

    @Override
    public String toString() {
        return String.valueOf(super.getId()) + "," + super.getTypeOfTask() + "," + super.getTitle() +
                "," + super.getStatus() + "," + super.getDescription() + "," + epicId + ","
                + super.getStartTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy; HH:mm")) + "," + super.getDuration();
    }

}
