import java.util.ArrayList;

/**
 * Класс задач-эпиков
 * @author Max Vasilyev
 * @version 1.0
 */
public class Epic extends Task{

    private ArrayList<Integer> numOfSubtasks;
    public Epic() {
        numOfSubtasks = new ArrayList<>();
    }

    /** Метод заполенния листа подзадач
     * @param indOfSubtask - индификатор подзадачи
     */
    public void addNumOfSubtask(int indOfSubtask){
        numOfSubtasks.add(indOfSubtask);
    }

    /** Метод получения списка подзадач задачи-эпик */
    public ArrayList<Integer> getNumOfSubtasks() {
        return numOfSubtasks;
    }

    /** Переопределение метода toString() */
    @Override
    public String toString() {
        return "Epic {" +
                "title='" + super.getTitle() + '\'' +
                ", description='" + super.getDescription() + '\'' +
                ", status='" + super.getStatus() + '\'' +
                '}';
    }
}
