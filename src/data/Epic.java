package data;

import java.util.ArrayList;


/**
 * Класс задач-эпиков
 * @author Max Vasilyev
 * @version 1.0
 */
public class Epic extends Task{

    private ArrayList<Integer> SubtackIDs;
    public Epic(String title, String description) {
        super(title, description);
        SubtackIDs = new ArrayList<>();
    }

    /** Метод заполенния листа подзадач
     * @param indOfSubtask - индификатор подзадачи
     */
    public void addNumOfSubtask(int indOfSubtask){
        SubtackIDs.add(indOfSubtask);
    }

    /** Метод удаление из листа определенный номер подзадачи
      * @param indificator - индификатор
     */
    public void removeNumOfSubtask(int indificator){
        SubtackIDs.remove(indificator);
    }

    /**
     * Метод удаления всего листа
     */
    public void deleteAllList(){
        SubtackIDs.clear();
    }

    public ArrayList<Integer> getSubtackIDs() {
        return SubtackIDs;
    }

    @Override
    public String toString() {
        return "Epic {" +
                "title='" + super.getTitle() + '\'' +
                ", description='" + super.getDescription() + '\'' +
                ", status='" + super.getStatus() + '\'' +
                ", id= " + super.getId() + '\'' +
                ", idSubtask= " + SubtackIDs.toString() + '\'' +
                '}';
    }
}
