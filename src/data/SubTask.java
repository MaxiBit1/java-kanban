package data;

/**
 * Класс подзадач
 * @author Max Vasilyev
 * @version 1.0
 */
public class SubTask extends Task {

    /** Переопределение метода toString() */
    @Override
    public String toString() {
        return "Subtask {" +
                "title='" + super.getTitle() + '\'' +
                ", description='" + super.getDescription() + '\'' +
                ", status='" + super.getStatus() + '\'' +
                '}';
    }

}
