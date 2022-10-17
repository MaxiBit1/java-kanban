package data;

/**
 * Класс подзадач
 * @author Max Vasilyev
 * @version 1.0
 */
public class SubTask extends Task {

    private int EpicId;

    public SubTask(String title, String description) {
        super(title, description);
    }

    public int getEpicId() {
        return EpicId;
    }

    public void setEpicId(int epicId) {
        this.EpicId = epicId;
    }

    /**
     * Метод установки id задачи-эпик в ноль
     */
    public void setIdEpicNull(){
        EpicId = 0;
    }
    @Override
    public String toString() {
        return "Subtask {" +
                "title='" + super.getTitle() + '\'' +
                ", description='" + super.getDescription() + '\'' +
                ", status='" + super.getStatus() + '\'' +
                ", id= " + super.getId() + '\'' +
                ", idEpic= " + EpicId + '\'' +
                '}';
    }

}
