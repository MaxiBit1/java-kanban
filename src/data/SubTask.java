package data;

/**
 * Класс подзадач
 * @author Max Vasilyev
 * @version 1.0
 */
public class SubTask extends Task {

    private int idEpic;

    public int getIdEpic() {
        return idEpic;
    }

    public void setIdEpic(int idEpic) {
        this.idEpic = idEpic;
    }

    /**
     * Метод установки id задачи-эпик в ноль
     */
    public void setIdEpicNull(){
        idEpic = 0;
    }
    @Override
    public String toString() {
        return "Subtask {" +
                "title='" + super.getTitle() + '\'' +
                ", description='" + super.getDescription() + '\'' +
                ", status='" + super.getStatus() + '\'' +
                ", id= " + super.getId() + '\'' +
                ", idEpic= " + idEpic + '\'' +
                '}';
    }

}
