package data;

/**
 * Класс подзадач
 * @author Max Vasilyev
 * @version 1.0
 */
public class SubTask extends Task {

    private int epicId;

    public SubTask(String title, String description) {
        super(title, description);
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
    public void setIdEpicNull(){
        epicId = 0;
    }
    @Override
    public String toString() {
        return "Subtask {" +
                "title='" + super.getTitle() + '\'' +
                ", description='" + super.getDescription() + '\'' +
                ", status='" + super.getStatus() + '\'' +
                ", id= " + super.getId() + '\'' +
                ", idEpic= " + epicId + '\'' +
                '}';
    }

}
